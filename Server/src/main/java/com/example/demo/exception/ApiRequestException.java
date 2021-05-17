package main.java.com.example.demo.exception;

public class ApiRequestException extends RuntimeException{
    private static String errorCode;
    public ApiRequestException() { }
    public ApiRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
