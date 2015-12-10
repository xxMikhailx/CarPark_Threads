package by.bsu.carpark.exception;

/**
 * Created by Михаил on 06.12.2015.
 */
public class ParkException extends Exception {

    public ParkException() {
    }

    public ParkException(String message) {
        super(message);
    }

    public ParkException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkException(Throwable cause) {
        super(cause);
    }

    public ParkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
