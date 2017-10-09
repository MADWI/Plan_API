package pl.edu.zut.mad.schedule.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@SuppressWarnings("unused")
@EqualsAndHashCode
class ScheduleId implements Serializable {

    private Integer groupId;
    private String academicTitle;
    private String name;
    private String surname;
    private String room;
    private String courseType;
    private String subject;
    private String semester;
    private String faculty;
    private String facultyAbbreviation;
    private String fieldOfStudy;
    private String form;
    private String cycle;
    private String date;
    private String day;
    private String timeFrom;
    private String timeTo;
}
