package pl.edu.zut.mad.schedule.model.inner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("unused")
@Entity(name = "plan")
@IdClass(ScheduleId.class)
public class Schedule {

    @Id
    @Column(name = "ID_Grupy")
    private Integer groupId;

    @Id
    @Column(name = "tytul")
    private String academicTitle;

    @Id
    @Column(name = "imie")
    private String name;

    @Id
    @Column(name = "nazwisko")
    private String surname;

    @Id
    @Column(name = "sala")
    private String room;

    @Id
    @Column(name = "forma")
    private String courseType;

    @Id
    @Column(name = "przedmiot")
    private String subject;

    @Id
    @Column(name = "semestr")
    private String semester;

    @Id
    @Column(name = "wydzial")
    private String faculty;

    @Id
    @Column(name = "wydz_sk")
    private String facultyAbbreviation;

    @Id
    @Column(name = "kierunek")
    private String fieldOfStudy;

    @Id
    @Column(name = "rodzaj")
    private String form;

    @Id
    @Column(name = "typ")
    private String cycle;

    @Id
    @Column(name = "data_zajec")
    private String date;

    @Id
    @Column(name = "dow_zajec")
    private String day;

    @Id
    @Column(name = "od")
    private String timeFrom;

    @Id
    @Column(name = "do")
    private String timeTo;

    @Id
    @Column(name = "Status_rez_n")
    private String reservationStatus;

    @Id
    @Column(name = "Status_rez_skr")
    private String reservationStatusAbbreviation;

    @Id
    @Column(name = "status")
    private String status;

    @Id
    @Column(name = "Zca_Tytul")
    private String substituteTitle;

    @Id
    @Column(name = "Zca_Nazwisko")
    private String substituteSurname;

    @Id
    @Column(name = "Zca_Imie")
    private String substituteName;

    public String getDay() {
        return day.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getAcademicTitle() {
        return academicTitle.trim();
    }

    public String getName() {
        return name.trim();
    }

    public String getSurname() {
        return surname.trim();
    }

    public String getRoom() {
        return room.trim();
    }

    public String getCourseType() {
        return courseType.trim();
    }

    public String getSubject() {
        return subject.trim();
    }

    public String getSemester() {
        return semester.trim();
    }

    public String getFaculty() {
        return faculty.trim();
    }

    public String getFacultyAbbreviation() {
        return facultyAbbreviation.trim();
    }

    public String getFieldOfStudy() {
        return fieldOfStudy.trim();
    }

    public String getForm() {
        return form.trim();
    }

    public String getCycle() {
        return cycle.trim();
    }

    public String getDate() {
        return date.trim();
    }

    public String getTimeFrom() {
        return timeFrom.trim();
    }

    public String getTimeTo() {
        return timeTo.trim();
    }

    public String getReservationStatus() {
        return reservationStatus.trim();
    }

    public String getReservationStatusAbbreviation() {
        return reservationStatusAbbreviation.trim();
    }

    public String getStatus() {
        return status.trim();
    }

    public String getSubstituteTitle() {
        return substituteTitle.trim();
    }

    public String getSubstituteSurname() {
        return substituteSurname.trim();
    }

    public String getSubstituteName() {
        return substituteName.trim();
    }

    public enum Field {
        NAME("name", Schedule::getName),
        SURNAME("surname", Schedule::getSurname),
        ROOM("room", Schedule::getRoom),
        COURSE_TYPE("courseType", Schedule::getCourseType),
        SUBJECT("subject", Schedule::getSubject),
        SEMESTER("semester", Schedule::getSemester),
        FACULTY("faculty", Schedule::getFaculty),
        FACULTY_ABBREVIATION("facultyAbbreviation", Schedule::getFacultyAbbreviation),
        FIELD_OF_STUDY("fieldOfStudy", Schedule::getFieldOfStudy),
        FORM("form", Schedule::getForm),
        DATE_FROM("dateFrom", null),
        DATE_TO("dateTo", null),
        DATE("date", Schedule::getDate),
        GROUP_ID("groupId", schedule -> schedule.getGroupId().toString()),
        RESERVATION_STATUS("reservationStatus", Schedule::getReservationStatus),
        RESERVATION_STATUS_ABBREVIATION("reservationStatusAbbreviation", Schedule::getReservationStatusAbbreviation),
        STATUS("status", Schedule::getStatus),
        SUBSTITUTE_SURNAME("substituteSurname", Schedule::getSubstituteSurname),
        SUBSTITUTE_NAME("substituteName", Schedule::getSubstituteName);

        private static final Map<String, Field> valuesLookup = new HashMap<>();

        static {
            for (Field field : Field.values()) {
                valuesLookup.put(field.getKey(), field);
            }
        }

        private final String key;
        private final Function<Schedule, String> mapper;

        Field(String key, Function<Schedule, String> mapper) {
            this.key = key;
            this.mapper = mapper;
        }

        public static Field fieldOf(String value) {
            return valuesLookup.get(value);
        }

        public String getKey() {
            return key;
        }

        public Optional<Function<Schedule, String>> getMapper() {
            return Optional.ofNullable(mapper);
        }
    }
}
