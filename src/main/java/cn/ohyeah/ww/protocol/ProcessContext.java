package cn.ohyeah.ww.protocol;

import cn.ohyeah.stb.util.ByteBuffer;
import org.jboss.netty.channel.Channel;

import java.util.Map;

public class ProcessContext {
    private HeadWrapper head;
    private int resultCode;
    private String errorMessage;
    private Object result;
    private ByteBuffer request;
    private ByteBuffer response;
    private Channel channel;
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public ByteBuffer getRequest() {
        return request;
    }

    public void setRequest(ByteBuffer request) {
        this.request = request;
    }

    public ByteBuffer createResponse(int size) {
        if (response == null) {
            response = new ByteBuffer(size);
        }
        return response;
    }

    public ByteBuffer getResponse() {
        return response;
    }

    public void setResponse(ByteBuffer response) {
        this.response = response;
    }

    public HeadWrapper getHead() {
        return head;
    }

    public void setHead(HeadWrapper head) {
        this.head = head;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
