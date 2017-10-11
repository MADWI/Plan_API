package pl.edu.zut.mad.schedule;

import org.springframework.data.repository.CrudRepository;
import pl.edu.zut.mad.schedule.model.inner.GroupAlbum;

import java.util.List;

public interface GroupAlbumRepository extends CrudRepository<GroupAlbum, Integer> {

    List<GroupAlbum> findByAlbumNumber(String albumNumber);
}
