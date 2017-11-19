package pl.edu.zut.mad.schedule;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pl.edu.zut.mad.schedule.model.inner.Schedule;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer>, JpaSpecificationExecutor<Schedule> {

    List<Schedule> findByGroupIdIn(List<Integer> groupsId);
}
