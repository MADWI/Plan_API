package pl.edu.zut.mad.schedule.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "plan")
@Getter
public class Schedule {

    @Id
    @Column(name = "ID_Grupy")
    private Integer groupId;

    @Column(name = "tytul")
    private String academicTitle;

    @Column(name = "imie")
    private String name;

    @Column(name = "nazwisko")
    private String surname;

    @Column(name = "sala")
    private String room;

    @Column(name = "forma")
    private String courseType;

    @Column(name = "przedmiot")
    private String subject;

    @Column(name = "semestr")
    private String semester;

    @Column(name = "wydzial")
    private String faculty;

    @Column(name = "wydz_sk")
    private String facultyAbbreviation;

    @Column(name = "kierunek")
    private String fieldOfStudy;

    @Column(name = "rodzaj")
    private String form;

    @Column(name = "typ")
    private String cycle;

    @Column(name = "data_zajec")
    private String date;

    @Column(name = "dow_zajec")
    private String day;

    @Column(name = "od")
    private String timeFrom;

    @Column(name = "do")
    private String timeTo;
}
