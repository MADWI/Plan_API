package pl.edu.zut.mad.schedule.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@SuppressWarnings("unused")
@EqualsAndHashCode
class GroupAlbumId implements Serializable {

    private Integer groupId;
    private String albumNumber;
}
