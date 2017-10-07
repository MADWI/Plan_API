package pl.edu.zut.mad.schedule.model;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ScheduleId implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleId that = (ScheduleId) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (academicTitle != null ? !academicTitle.equals(that.academicTitle) : that.academicTitle != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (room != null ? !room.equals(that.room) : that.room != null) return false;
        if (courseType != null ? !courseType.equals(that.courseType) : that.courseType != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;
        if (faculty != null ? !faculty.equals(that.faculty) : that.faculty != null) return false;
        if (facultyAbbreviation != null ? !facultyAbbreviation.equals(that.facultyAbbreviation) : that.facultyAbbreviation != null)
            return false;
        if (fieldOfStudy != null ? !fieldOfStudy.equals(that.fieldOfStudy) : that.fieldOfStudy != null) return false;
        if (form != null ? !form.equals(that.form) : that.form != null) return false;
        if (cycle != null ? !cycle.equals(that.cycle) : that.cycle != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (timeFrom != null ? !timeFrom.equals(that.timeFrom) : that.timeFrom != null) return false;
        return timeTo != null ? timeTo.equals(that.timeTo) : that.timeTo == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (academicTitle != null ? academicTitle.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (courseType != null ? courseType.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        result = 31 * result + (facultyAbbreviation != null ? facultyAbbreviation.hashCode() : 0);
        result = 31 * result + (fieldOfStudy != null ? fieldOfStudy.hashCode() : 0);
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (cycle != null ? cycle.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (timeFrom != null ? timeFrom.hashCode() : 0);
        result = 31 * result + (timeTo != null ? timeTo.hashCode() : 0);
        return result;
    }
}
