package cn.com.example.exception;

/**
 * 自定义异常
 * Created by fangzy on 2018/2/9 11:48
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
