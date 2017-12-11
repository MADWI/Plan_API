package pl.edu.zut.mad.schedule.model.outer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    private final String reservationStatusAbbreviation;
    private final String status;
    private final Teacher teacher;
    private final TimeRange timeRange;
    private final Teacher substituteTeacher;
}
