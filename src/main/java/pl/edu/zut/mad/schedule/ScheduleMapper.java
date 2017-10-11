package pl.edu.zut.mad.schedule;

import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.model.outer.Day;
import pl.edu.zut.mad.schedule.model.outer.Lesson;
import pl.edu.zut.mad.schedule.model.outer.Teacher;
import pl.edu.zut.mad.schedule.model.outer.TimeRange;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

class ScheduleMapper {

    static List<Day> daysFrom(final List<Schedule> schedule) {
        return schedule.stream()
                .collect(groupingBy(s -> LocalDate.parse(s.getDate())))
                .entrySet()
                .stream()
                .map(e -> new Day(e.getKey(), e.getValue().stream()
                        .map(ScheduleMapper::lessonFrom)
                        .sorted(comparing(lesson -> lesson.getTimeRange().getFrom()))
                        .collect(toList())))
                .sorted(comparing(Day::getDate))
                .collect(toList());
    }

    private static Lesson lessonFrom(final Schedule schedule) {
        final Teacher teacher = new Teacher(schedule.getAcademicTitle(),
                schedule.getName(), schedule.getSurname());
        final TimeRange timeRange = new TimeRange(LocalTime.parse(schedule.getTimeFrom()),
                LocalTime.parse(schedule.getTimeTo()));

        return new Lesson(schedule.getRoom(), schedule.getCourseType(), schedule.getSubject(),
                schedule.getSemester(), schedule.getFaculty(), schedule.getFacultyAbbreviation(),
                schedule.getFieldOfStudy(), teacher, timeRange);
    }
}
