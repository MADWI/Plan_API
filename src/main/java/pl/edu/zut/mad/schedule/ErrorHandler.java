package pl.edu.zut.mad.schedule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.zut.mad.schedule.exception.ScheduleException;
import pl.edu.zut.mad.schedule.model.ErrorMessage;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<ErrorMessage> handleScheduleException(ScheduleException exception,
                                                                HttpServletRequest request) {
        final ErrorMessage errorMessage = new ErrorMessage(exception.getStatus(),
                exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus())
                .body(errorMessage);
    }
}
