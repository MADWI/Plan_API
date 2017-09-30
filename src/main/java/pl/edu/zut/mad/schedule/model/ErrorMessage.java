package pl.edu.zut.mad.schedule.model;

import org.springframework.http.HttpStatus;

import java.time.Instant;

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

    public long getTimestamp() {
        return timestamp;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
