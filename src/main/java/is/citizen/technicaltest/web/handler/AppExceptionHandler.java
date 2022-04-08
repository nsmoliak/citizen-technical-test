package is.citizen.technicaltest.web.handler;

import is.citizen.technicaltest.dto.ErrorResponseDto;
import is.citizen.technicaltest.exception.BadRequestException;
import is.citizen.technicaltest.exception.ErrorType;
import is.citizen.technicaltest.exception.ServiceException;
import is.citizen.technicaltest.service.AppExceptionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.annotations.ApiIgnore;

import static is.citizen.technicaltest.exception.ErrorType.BAD_REQUEST;
import static is.citizen.technicaltest.exception.ErrorType.SERVICE_ERROR;

@Slf4j
@ApiIgnore
@RestControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {
    private final AppExceptionManager exceptionManager;

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleBadDataException(BadRequestException ex) {
        ErrorResponseDto response = buildError(BAD_REQUEST, ex);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, BAD_REQUEST.getStatus());
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleServiceException(ServiceException ex) {
        ErrorResponseDto response = buildError(SERVICE_ERROR, ex);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, SERVICE_ERROR.getStatus());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleAllOtherException(Exception ex) {
        ErrorResponseDto response = buildError(SERVICE_ERROR, ex);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, SERVICE_ERROR.getStatus());
    }

    private ErrorResponseDto buildError(ErrorType code, Throwable ex) {
        log.error(ex.getMessage(), ex);
        return exceptionManager.getError(code);
    }
}