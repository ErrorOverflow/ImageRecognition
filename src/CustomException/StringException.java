package CustomException;

/**
 * @author wmlbuaa
 * @date 2018-08-31 16:31
 */
public class StringException extends Exception implements CustomExceptionInterface {
    private String errorCode;
    private boolean propertiesKey = true;

    public StringException(String message) {
        super(message);
    }

    public StringException(String errorCode, String message) {
        this(errorCode, message, true);
    }


    public StringException(String errorCode, String message, Throwable cause) {
        this(errorCode, message, cause, true);
    }

    public StringException(String errorCode, String message, boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public StringException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    public StringException(String message, Throwable cause) {
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
