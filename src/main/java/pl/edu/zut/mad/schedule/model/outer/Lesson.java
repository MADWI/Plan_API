package pl.edu.zut.mad.schedule.model.outer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Lesson {
    private final String room;
    private final String courseType;
    private final String subject;
    private final String semester;
    private final String faculty;
    private final String facultyAbbreviation;
    private final String fieldOfStudy;
    private final Teacher teacher;
    private final TimeRange timeRange;
}
