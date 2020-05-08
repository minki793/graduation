package ru.graduation.util.exception;

public class ApplicationException extends RuntimeException {
    private final ErrorType type;

    public ApplicationException(String msg) {
        this(ErrorType.APP_ERROR, msg);
    }

    public ApplicationException(ErrorType type, String msg) {
        super(msg);
        this.type = type;
    }

    public ErrorType getType() {
        return type;
    }


}