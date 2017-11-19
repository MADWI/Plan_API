package pl.edu.zut.mad.schedule.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.model.inner.Schedule.Fields;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Component
public class ScheduleSpecificationFactory {

    public Specification<Schedule> specification(Map<String, String> params) {
        List<ScheduleSpecification> scheduleSpecifications = params.entrySet()
                .stream()
                .filter(this::isValidParam)
                .map(e -> new SearchCriteria(e.getKey(), e.getValue()))
                .map(ScheduleSpecification::new)
                .collect(toList());

        Specification<Schedule> specification = scheduleSpecifications.get(0);
        for (int i = 1; i < scheduleSpecifications.size(); i++) {
            specification = Specifications.where(specification)
                    .and(scheduleSpecifications.get(i));
        }

        return specification;
    }

    private boolean isValidParam(Map.Entry<String, String> e) {
        return Arrays.stream(Fields.values())
                .anyMatch(f -> f.getKey().equals(e.getKey()));
    }
}
