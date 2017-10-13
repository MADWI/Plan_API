package pl.edu.zut.mad.schedule.model.inner;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@SuppressWarnings("unused")
@EqualsAndHashCode
class GroupAlbumId implements Serializable {

    private Integer groupId;
    private String albumNumber;
}
