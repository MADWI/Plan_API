package pl.edu.zut.mad.schedule.exception;

import org.springframework.http.HttpStatus;

class EmptyDatabaseException extends ScheduleException {

    EmptyDatabaseException(String message) {
        super(message, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
