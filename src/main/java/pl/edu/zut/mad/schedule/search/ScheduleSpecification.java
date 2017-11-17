package pl.edu.zut.mad.schedule.search;

import org.springframework.data.jpa.domain.Specification;
import pl.edu.zut.mad.schedule.model.inner.Schedule;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static pl.edu.zut.mad.schedule.model.inner.Schedule.Fields.DATE;

public class ScheduleSpecification implements Specification<Schedule> {

    private SearchCriteria searchCriteria;

    ScheduleSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        if (searchCriteria.getKey().equals(Schedule.Fields.DATE_FROM.getKey())) {
            return cb.greaterThanOrEqualTo(root.get(DATE.getKey()), searchCriteria.getValue());
        } else if (searchCriteria.getKey().equals(Schedule.Fields.DATE_TO.getKey())) {
            return cb.lessThanOrEqualTo(root.get(DATE.getKey()), searchCriteria.getValue());
        } else {
            return cb.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
        }
    }
}
