package cn.ohyeah.ww.protocol.impl;

import cn.halcyon.utils.ThreadSafeClientConnManagerUtil;
import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.protocol.Constant;
import cn.ohyeah.ww.protocol.HeadWrapper;
import cn.ohyeah.ww.protocol.IProcessor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class DefaultProcessor {
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
        for (int i = 0; i < cmds.length; ++i) {
            String procName = processorBasePackage+"."+ StringUtils.capitalize(cmds[i])
                    +"Processor";
            try {
                IProcessor proc = (IProcessor)Class.forName(procName).newInstance();
                processors.put(cmds[i], proc);
                String[] userdatas = Constant.getCmdUserdatas(i);
                for (int j = 0; j < userdatas.length; ++j) {
                    Method m = proc.getClass().getMethod(userdatas[i], ByteBuffer.class);
                    methods.put(cmds[i]+"."+userdatas[j], m);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (NoSuchMethodException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public ByteBuffer process(ByteBuffer req) {
        ByteBuffer rsp = null;
        HeadWrapper head = new HeadWrapper(req.getInt(req.getReaderIndex()));

        if (head.getAckparam() == 1) {
            rsp = req;
        }
        else if (head.getTag() == Constant.PROTOCOL_TAG_GAME_SERVER) {
            String cmd = Constant.getProtocolCmd(head.getCommand());
            String userdata = Constant.getCmdUserdata(head.getCommand(), head.getUserdata());
            IProcessor proc = processors.get(cmd);
            Method method = methods.get(cmd+"."+userdata);
            try {
                rsp = (ByteBuffer)method.invoke(proc, req);
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InvocationTargetException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return rsp;
    }
}
