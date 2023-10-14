package it.live.newlook.exceptionHandler;

import it.live.newlook.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse runtimeException(RuntimeException exception) {
        return
                ApiResponse.builder().message(exception.getMessage()).status(409).build();
    }
}
