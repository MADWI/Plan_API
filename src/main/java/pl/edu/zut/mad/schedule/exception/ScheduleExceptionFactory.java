package pl.edu.zut.mad.schedule.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ScheduleExceptionFactory {

    private final MessageSource messageSource;

    @Autowired
    public ScheduleExceptionFactory(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static BadRequestException badRequest(String message) throws BadRequestException {
        throw new BadRequestException(message);
    }

    public BadRequestException unsupportedParam(String param) throws BadRequestException {
        final String message = getMessage("errUnsupportedParam", param);
        throw new BadRequestException(message);
    }

    public BadRequestException missingParam() throws BadRequestException {
        final String message = getMessage("errMissingParam");
        throw new BadRequestException(message);
    }

    public BadRequestException missingRequiredParam(String param) throws BadRequestException {
        final String message = getMessage("errMissingRequiredParam", param);
        throw new BadRequestException(message);
    }

    public BadRequestException invalidDateRange() throws BadRequestException {
        final String message = getMessage("errInvalidDateRange");
        throw new BadRequestException(message);
    }

    public EmptyDatabaseException emptyDatabase() throws EmptyDatabaseException {
        final String message = getMessage("errEmptyDb");
        throw new EmptyDatabaseException(message);
    }

    public NotFoundException notFound(String value) throws NotFoundException {
        final String message = getMessage("errNotFound", value);
        throw new NotFoundException(message);
    }

    @SuppressWarnings("ConstantConditions")
    private String getMessage(String code) {
        return messageSource.getMessage(
                code,
                null,
                LocaleContextHolder.getLocale());
    }

    private String getMessage(String code, String args) {
        return messageSource.getMessage(
                code,
                new String[]{args},
                LocaleContextHolder.getLocale());
    }
}
