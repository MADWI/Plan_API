package pl.edu.zut.mad.schedule.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class ErrorMessage {

    private final Long timestamp;
    private final Integer code;
    private final String error;
    private final String message;

    public ErrorMessage(HttpStatus status, String message) {
        this.timestamp = Instant.now().toEpochMilli();
        this.code = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
    }
}
