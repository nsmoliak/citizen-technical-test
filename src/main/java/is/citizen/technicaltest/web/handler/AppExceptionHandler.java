package is.citizen.technicaltest.web.handler;

import is.citizen.technicaltest.dto.ErrorResponseDto;
import is.citizen.technicaltest.exception.BadRequestException;
import is.citizen.technicaltest.exception.ErrorType;
import is.citizen.technicaltest.service.AppExceptionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static is.citizen.technicaltest.exception.ErrorType.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {
    private final AppExceptionManager exceptionManager;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadDataException(BadRequestException ex) {
        ErrorResponseDto response = buildError(BAD_REQUEST, ex);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, BAD_REQUEST.getStatus());
    }

    private ErrorResponseDto buildError(ErrorType code, Throwable ex) {
        log.error(ex.getMessage(), ex);
        return exceptionManager.getError(code);
    }
}