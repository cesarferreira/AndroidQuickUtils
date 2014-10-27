package quickutils.core.exceptions;

/**
 * Created by cesarferreira on 10/1/14.
 */
public class InvalidKeyForTimeStampException extends RuntimeException {
    public InvalidKeyForTimeStampException() {
        super("The KEY you provided for this timestamp is invalid");
    }

    public InvalidKeyForTimeStampException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidKeyForTimeStampException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InvalidKeyForTimeStampException(Throwable throwable) {
        super(throwable);
    }
}
