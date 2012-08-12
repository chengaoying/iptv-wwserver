package cn.ohyeah.ww.protocol;

public class ProcessorContext {
    private HeadWrapper head;
    private int resultCode;
    private String errorMessage;
    private Object result;

    public HeadWrapper getHeadWrapper() {
        return head;
    }

    public void setHeadWrapper(HeadWrapper head) {
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
}
