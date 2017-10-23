package pl.edu.zut.mad.schedule.model.outer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Lesson {
    private final String room;
    private final String courseType;
    private final String subject;
    private final String semester;
    private final String faculty;
    private final String facultyAbbreviation;
    private final String fieldOfStudy;
    private final String reservationStatus;
    private final Teacher teacher;
    private final TimeRange timeRange;
}
