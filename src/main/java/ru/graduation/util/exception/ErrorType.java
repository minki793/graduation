package ru.graduation.util.exception;
import static ru.graduation.util.ValidationUtil.TIME_LIMIT;
import org.springframework.http.HttpStatus;

public enum ErrorType {
    APP_ERROR("Ошибка приложения", HttpStatus.INTERNAL_SERVER_ERROR),
    //  http://stackoverflow.com/a/22358422/548473
    DATA_NOT_FOUND("Данные не найдены", HttpStatus.UNPROCESSABLE_ENTITY),
    DATA_ERROR("Ошибка в данных", HttpStatus.CONFLICT),
    TIME_ERROR("Изменение голоса после " + TIME_LIMIT, HttpStatus.UNPROCESSABLE_ENTITY),
    VALIDATION_ERROR("Ошибка проверки данных", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
