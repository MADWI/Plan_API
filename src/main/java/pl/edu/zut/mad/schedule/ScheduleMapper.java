package pl.edu.zut.mad.schedule;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.model.outer.Day;
import pl.edu.zut.mad.schedule.model.outer.Lesson;
import pl.edu.zut.mad.schedule.model.outer.Teacher;
import pl.edu.zut.mad.schedule.model.outer.TimeRange;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Component
class ScheduleMapper {

    List<Day> daysFrom(final List<Schedule> schedule) {
        return schedule.stream()
                .collect(groupingBy(s -> LocalDate.parse(s.getDate())))
                .entrySet()
                .stream()
                .map(this::dayFrom)
                .sorted(comparing(Day::getDate))
                .collect(toList());
    }

    private Day dayFrom(Map.Entry<LocalDate, List<Schedule>> entry) {
        return new Day(entry.getKey(), entry.getValue().stream()
                .map(this::lessonFrom)
                .sorted(comparing(lesson -> lesson.getTimeRange().getFrom()))
                .collect(toList()));
    }

    private Lesson lessonFrom(final Schedule schedule) {
        final Teacher teacher = new Teacher(schedule.getAcademicTitle(),
                schedule.getName(), schedule.getSurname());
        final TimeRange timeRange = new TimeRange(LocalTime.parse(schedule.getTimeFrom()),
                LocalTime.parse(schedule.getTimeTo()));
        final Teacher substituteTeacher = substituteTeacherFrom(schedule);

        return new Lesson(schedule.getRoom(), schedule.getCourseType(), schedule.getSubject(),
                schedule.getSemester(), schedule.getFaculty(), schedule.getFacultyAbbreviation(),
                schedule.getFieldOfStudy(), schedule.getReservationStatus(),
                schedule.getReservationStatusAbbreviation(), schedule.getStatus(), teacher, timeRange,
                substituteTeacher);
    }

    @Nullable
    private Teacher substituteTeacherFrom(final Schedule schedule) {
        if (StringUtils.isEmpty(schedule.getSubstituteName())
                || StringUtils.isEmpty(schedule.getSubstituteSurname())) {
            return null;
        } else {
            return new Teacher(schedule.getSubstituteTitle(),
                    schedule.getSubstituteName(), schedule.getSubstituteSurname());
        }
    }
}
