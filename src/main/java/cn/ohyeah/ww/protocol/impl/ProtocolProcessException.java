package cn.ohyeah.ww.protocol.impl;

public class ProtocolProcessException extends RuntimeException {
    public ProtocolProcessException() {}

    public ProtocolProcessException(String message) {
        super(message);
    }

    public ProtocolProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolProcessException(Throwable cause) {
        super(cause);
    }

    public ProtocolProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
