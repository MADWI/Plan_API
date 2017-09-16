package pl.edu.zut.mad.schedule.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "album_grupa")
@Getter
public class GroupAlbum {

    @Id
    @Column(name = "ID_Grupy")
    private Integer groupId;

    @Column(name = "G_NUMER_ALBUMU")
    private String albumNumber; // TODO: use Integer?
}
