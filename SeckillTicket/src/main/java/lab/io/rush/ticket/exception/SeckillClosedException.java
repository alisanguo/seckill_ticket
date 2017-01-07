package lab.io.rush.ticket.exception;

/**
 * Created by ali on 16-12-28.
 */
public class SeckillClosedException extends SeckillException {
    public SeckillClosedException(String message) {
        super(message);
    }

    public SeckillClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
