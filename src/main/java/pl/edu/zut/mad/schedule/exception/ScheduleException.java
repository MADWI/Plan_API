package pl.edu.zut.mad.schedule.exception;

import org.springframework.http.HttpStatus;

public abstract class ScheduleException extends RuntimeException {

    private final HttpStatus status;

    ScheduleException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
