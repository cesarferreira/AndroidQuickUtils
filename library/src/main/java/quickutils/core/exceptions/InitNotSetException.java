package quickutils.core.exceptions;

/**
 * Created by cesarferreira on 10/1/14.
 */
public class InitNotSetException extends RuntimeException {
    public InitNotSetException() {
        super("You must Init the library first!!\n e.g: QuickUtils.init(context)");
    }

    public InitNotSetException(String detailMessage) {
        super(detailMessage);
    }

    public InitNotSetException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InitNotSetException(Throwable throwable) {
        super(throwable);
    }
}
