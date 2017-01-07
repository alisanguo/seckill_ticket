package lab.io.rush.ticket.domain;

/**
 * Created by ali on 16-12-27.
 */
public class SeckillResult<T> {

    private boolean success;

    private T data;

    public SeckillResult(){}

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
