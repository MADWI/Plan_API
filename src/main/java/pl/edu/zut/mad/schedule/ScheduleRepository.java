package pl.edu.zut.mad.schedule;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pl.edu.zut.mad.schedule.model.dictionary.FacultyAbbreviation;
import pl.edu.zut.mad.schedule.model.dictionary.Room;
import pl.edu.zut.mad.schedule.model.dictionary.Subject;
import pl.edu.zut.mad.schedule.model.dictionary.Surname;
import pl.edu.zut.mad.schedule.model.inner.Schedule;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer>, JpaSpecificationExecutor<Schedule> {

    List<Surname> findDistinctBySurnameContaining(String surname, Pageable pageable);

    List<Subject> findDistinctBySubjectContaining(String subject, Pageable pageable);

    List<Room> findDistinctByRoomContaining(String room, Pageable pageable);

    List<FacultyAbbreviation> findDistinctByFacultyAbbreviationContaining(String facultyAbbreviation, Pageable pageable);
}
