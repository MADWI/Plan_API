package pl.edu.zut.mad.schedule.model.inner;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

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

    public String getDay() {
        return day.trim();
    }
}
