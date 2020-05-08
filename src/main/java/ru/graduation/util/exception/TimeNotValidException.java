package ru.graduation.util.exception;
import static ru.graduation.util.ValidationUtil.TIME_LIMIT;

public class TimeNotValidException extends ApplicationException {

    //  http://stackoverflow.com/a/22358422/548473
    public TimeNotValidException(String arg) {
        super(ErrorType.TIME_ERROR, arg);
    }
}