package cn.ohyeah.ww.protocol.impl;

import cn.halcyon.utils.ThreadSafeClientConnManagerUtil;
import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.protocol.Constant;
import cn.ohyeah.ww.protocol.HeadWrapper;
import cn.ohyeah.ww.protocol.IProcessor;
import cn.ohyeah.ww.protocol.ProcessFrame;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class DefaultProcessor {
    private static Log log = LogFactory.getLog(DefaultProcessor.class);
    private String platformServer;
    private DefaultHttpClient httpClient;
    private String processorBasePackage;
    private Map<String, Method> methods;
    private Map<String, IProcessor> processors;

    public void setPlatformServer(String platformServer) {
        this.platformServer = platformServer;
    }

    public void setHttpClient(DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setProcessors(Map<String, IProcessor> processors) {
        this.processors = processors;
    }

    protected void initProcessors() {
        String[] cmds = Constant.getProtocolCmds();
        String procName = "";
        String methodName = "";
        try {
            for (int i = 0; i < cmds.length; ++i) {
                procName = processorBasePackage+"."+ StringUtils.capitalize(cmds[i])+"Processor";
                IProcessor proc = (IProcessor)Class.forName(procName).newInstance();
                processors.put(cmds[i], proc);
                String[] userdatas = Constant.getCmdUserdatas(i);
                for (int j = 0; j < userdatas.length; ++j) {
                    methodName = userdatas[i];
                    Method m = proc.getClass().getMethod(methodName, ByteBuffer.class);
                    methods.put(cmds[i]+"."+userdatas[j], m);
                }
            }
        } catch (ClassNotFoundException e) {
            String msg = String.format("can't find protocol processor class, (class=%s)", procName);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        } catch (InstantiationException|IllegalAccessException e) {
            String msg = String.format("can't construct protocol processor instance, (class=%s)", procName);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        } catch (NoSuchMethodException e) {
            String msg = String.format("can't find processor method, (class=%s, method=%s)", procName, methodName);
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    public ByteBuffer process(ProcessFrame frame) {
        ByteBuffer req = frame.getRequest();
        ByteBuffer rsp = null;
        HeadWrapper head = new HeadWrapper(req.getInt(req.getReaderIndex()));

        if (head.getAckparam() == 1) {
            rsp = req;
        }
        else if (head.getTag() == Constant.PROTOCOL_TAG_GAME_SERVER) {
            String cmd = Constant.getProtocolCmd(head.getCommand());
            String userdata = Constant.getCmdUserdata(head.getCommand(), head.getUserdata());
            IProcessor proc = processors.get(cmd);
            if (proc == null) {
                String msg = String.format("没有对应的协议处理器, (cmd=%s)", cmd);
                throw new ProtocolProcessException(msg);
            }
            Method method = methods.get(cmd+"."+userdata);
            if (method == null) {
                String msg = String.format("没有对应的协议处理器方法, (cmd=%s, userdata=%s)", cmd, userdata);
                throw new ProtocolProcessException(msg);
            }
            try {
                rsp = (ByteBuffer)method.invoke(proc, req);
            } catch (IllegalAccessException e) {
                String msg = String.format("协议处理器方法无法访问, (cmd=%s, userdata=%s)", cmd, userdata);
                throw new ProtocolProcessException(msg);
            } catch (InvocationTargetException e) {
                throw new ProtocolProcessException(e.getCause());
            }
        }
        else {
            HttpPost httpPost = new HttpPost(platformServer);
            httpPost.setHeader("Content-Type", "application/octet-stream");
            httpPost.setEntity(new ByteArrayEntity(req.buffer()));
            try {
                byte[] data = ThreadSafeClientConnManagerUtil.executeForBodyByteArray(httpClient, httpPost);
                rsp = new ByteBuffer(data);
            } catch (Exception e) {
                String msg = String.format("与中央服务器通讯失败");
                throw new ProtocolProcessException(msg, e);
            }
        }
        return rsp;
    }
}
