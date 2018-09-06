package CustomException;

/**
 * @author wmlbuaa
 * @date 2018-08-31 16:31
 * 9-6 finished first time.
 */
public class StreamException extends Exception implements CustomExceptionInterface {
    private String errorCode;
    private boolean propertiesKey = true;

    public StreamException(String message) {
        super(message);
    }

    public StreamException(String errorCode, String message) {
        this(errorCode, message, true);
    }


    public StreamException(String errorCode, String message, Throwable cause) {
        this(errorCode, message, cause, true);
    }

    public StreamException(String errorCode, String message, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public StreamException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public StreamException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }
}
