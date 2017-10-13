package pl.edu.zut.mad.schedule.model.inner;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@SuppressWarnings("unused")
@Entity(name = "album_grupa")
@IdClass(GroupAlbumId.class)
@Getter
public class GroupAlbum {

    @Id
    @Column(name = "ID_Grupy")
    private Integer groupId;

    @Id
    @Column(name = "G_NUMER_ALBUMU")
    private String albumNumber;
}
