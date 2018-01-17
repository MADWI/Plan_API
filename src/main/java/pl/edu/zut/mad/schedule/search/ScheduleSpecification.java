package pl.edu.zut.mad.schedule.search;

import org.springframework.data.jpa.domain.Specification;
import pl.edu.zut.mad.schedule.exception.ScheduleExceptionFactory;
import pl.edu.zut.mad.schedule.model.inner.Schedule;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static pl.edu.zut.mad.schedule.model.inner.Schedule.Field.*;

public class ScheduleSpecification implements Specification<Schedule> {

    static final String DATE_PATTERN = "dd-MM-yyyy";

    private final SearchCriteria searchCriteria;

    ScheduleSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        final String searchKey = searchCriteria.getKey();
        final String searchValue = searchCriteria.getValue();
        final Schedule.Field field = Schedule.Field.fieldOf(searchKey);

        switch (field) {
            case FACULTY_ABBREVIATION:
                return builder.equal(root.get(FACULTY_ABBREVIATION.getKey()), searchValue);
            case RESERVATION_STATUS_ABBREVIATION:
                return builder.equal(root.get(RESERVATION_STATUS_ABBREVIATION.getKey()), searchValue);
            case DATE_FROM:
                return builder.greaterThanOrEqualTo(root.get(DATE.getKey()), formatDate(searchValue));
            case DATE_TO:
                return builder.lessThanOrEqualTo(root.get(DATE.getKey()), formatDate(searchValue));
            case DATE:
                return builder.equal(root.get(DATE.getKey()), formatDate(searchValue));
            case GROUP_ID:
                return root.get(GROUP_ID.getKey())
                        .in(searchValue);
            default:
                return builder.like(root.get(searchKey), searchValue);
        }
    }

    private String formatDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
            return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException exception) {
            throw ScheduleExceptionFactory.badRequest(exception.getMessage());
        }
    }
}
