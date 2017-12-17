package pl.edu.zut.mad.schedule.exception;

import org.springframework.http.HttpStatus;

class NotFoundException extends ScheduleException {

    NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
