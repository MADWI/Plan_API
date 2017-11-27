package pl.edu.zut.mad.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.zut.mad.schedule.exception.BadRequestException;
import pl.edu.zut.mad.schedule.exception.EmptyDatabaseException;
import pl.edu.zut.mad.schedule.exception.NotFoundException;
import pl.edu.zut.mad.schedule.model.ErrorMessage;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public ErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({
            NotFoundException.class,
            EmptyDatabaseException.class,
            BadRequestException.class,
            RuntimeException.class
    })
    public ResponseEntity<ErrorMessage> handleException(Exception exception, HttpServletRequest request) {
        if (exception instanceof NotFoundException) {
            return handleNotFoundException((NotFoundException) exception, request);
        } else if (exception instanceof EmptyDatabaseException) {
            return handleEmptyDatabaseException((EmptyDatabaseException) exception, request);
        } else if (exception instanceof BadRequestException) {
            return handleBadRequestException((BadRequestException) exception, request);
        } else {
            return handleRuntimeException((RuntimeException) exception, request);
        }
    }

    private ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception,
                                                                 HttpServletRequest request) {
        final String message = messageSource.getMessage(
                "errNotFound",
                new String[]{exception.getValue()},
                LocaleContextHolder.getLocale());
        final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, message, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleEmptyDatabaseException(EmptyDatabaseException exception,
                                                                      HttpServletRequest request) {
        final String message = messageSource.getMessage(
                "errEmptyDb",
                null,
                LocaleContextHolder.getLocale());
        final ErrorMessage errorMessage =
                new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, message, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException exception,
                                                                   HttpServletRequest request) {
        final ErrorMessage errorMessage =
                new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    private ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException exception,
                                                                HttpServletRequest request) {
        LOG.error(exception.getMessage(), exception);
        final ErrorMessage errorMessage =
                new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }
}
