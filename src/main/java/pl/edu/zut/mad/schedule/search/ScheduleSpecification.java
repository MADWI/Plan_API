package pl.edu.zut.mad.schedule.search;

import org.springframework.data.jpa.domain.Specification;
import pl.edu.zut.mad.schedule.model.inner.Schedule;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static pl.edu.zut.mad.schedule.model.inner.Schedule.Fields.*;

public class ScheduleSpecification implements Specification<Schedule> {

    private SearchCriteria searchCriteria;

    ScheduleSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        final String searchKey = searchCriteria.getKey();
        final String searchValue = searchCriteria.getValue();
        if (searchKey.equals(DATE_FROM.getKey())) {
            return builder.greaterThanOrEqualTo(root.get(DATE.getKey()), searchValue);
        } else if (searchKey.equals(DATE_TO.getKey())) {
            return builder.lessThanOrEqualTo(root.get(DATE.getKey()), searchValue);
        } else if (searchKey.equals(FACULTY_ABBREVIATION.getKey())) {
            return builder.equal(root.get(FACULTY_ABBREVIATION.getKey()), searchValue);
        } else {
            return builder.like(root.get(searchKey), "%" + searchValue + "%");
        }
    }
}
