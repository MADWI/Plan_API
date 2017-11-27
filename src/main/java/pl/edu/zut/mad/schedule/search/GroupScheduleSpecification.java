package pl.edu.zut.mad.schedule.search;

import org.springframework.data.jpa.domain.Specification;
import pl.edu.zut.mad.schedule.model.inner.Schedule;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static pl.edu.zut.mad.schedule.model.inner.Schedule.Fields.GROUP_ID;

public class GroupScheduleSpecification implements Specification<Schedule> {

    private final List<Integer> groupIds;

    GroupScheduleSpecification(List<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return root.get(GROUP_ID.getKey())
                .in(groupIds);
    }
}
