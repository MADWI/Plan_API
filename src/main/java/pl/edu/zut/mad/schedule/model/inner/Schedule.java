package pl.edu.zut.mad.schedule.model.inner;

import lombok.Getter;

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
@Getter
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
        RESERVATION_STATUS("reservationStatus", Schedule::getReservationStatus);

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
