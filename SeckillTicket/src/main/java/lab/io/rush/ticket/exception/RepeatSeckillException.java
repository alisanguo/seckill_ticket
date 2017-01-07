package lab.io.rush.ticket.exception;

/**
 * Created by ali on 16-12-28.
 */
public class RepeatSeckillException extends SeckillException{
    public RepeatSeckillException(String message) {
        super(message);
    }

    public RepeatSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
