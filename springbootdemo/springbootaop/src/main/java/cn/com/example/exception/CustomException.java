package cn.com.example.exception;

/**
 * Created by fangzy on 2018/2/9 15:32
 */
public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
