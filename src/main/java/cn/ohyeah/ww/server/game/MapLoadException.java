package cn.ohyeah.ww.server.game;

public class MapLoadException extends RuntimeException {
    public MapLoadException() {
    }

    public MapLoadException(String message) {
        super(message);
    }

    public MapLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapLoadException(Throwable cause) {
        super(cause);
    }

    public MapLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
