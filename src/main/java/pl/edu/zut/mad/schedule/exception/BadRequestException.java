package pl.edu.zut.mad.schedule.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ScheduleException {

    BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
