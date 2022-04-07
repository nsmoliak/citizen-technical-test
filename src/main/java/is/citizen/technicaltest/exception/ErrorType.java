package is.citizen.technicaltest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
    BAD_REQUEST("bad_request", HttpStatus.BAD_REQUEST);

    private final String type;
    private final String title;
    private final String message;
    private final HttpStatus status;

    ErrorType(String type, HttpStatus status) {
        this.type = type;
        this.title = "error_message." + type + ".title";
        this.message = "error_message." + type + ".message";
        this.status = status;
    }

    @Override
    public String toString() {
        return type;
    }
}