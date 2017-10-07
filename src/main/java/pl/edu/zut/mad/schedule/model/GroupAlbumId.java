package pl.edu.zut.mad.schedule.model;

import java.io.Serializable;

@SuppressWarnings("unused")
class GroupAlbumId implements Serializable {

    private Integer groupId;
    private String albumNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupAlbumId that = (GroupAlbumId) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        return albumNumber != null ? albumNumber.equals(that.albumNumber) : that.albumNumber == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (albumNumber != null ? albumNumber.hashCode() : 0);
        return result;
    }
}
