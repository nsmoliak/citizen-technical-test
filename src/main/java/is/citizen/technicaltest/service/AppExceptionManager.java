package is.citizen.technicaltest.service;

import is.citizen.technicaltest.dto.ErrorResponseDto;
import is.citizen.technicaltest.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AppExceptionManager {
    private final MessageSource messageSource;

    public ErrorResponseDto getError(ErrorType code) {
        return ErrorResponseDto.builder()
                .type(code.getType())
                .message(getMessage("error." + code.getType()))
                .messageTitle(getMessage("error." + code.getType() + "_title"))
                .build();
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
}