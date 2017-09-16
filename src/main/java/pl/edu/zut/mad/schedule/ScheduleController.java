package pl.edu.zut.mad.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.zut.mad.schedule.model.GroupAlbum;
import pl.edu.zut.mad.schedule.model.Schedule;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api")
public class ScheduleController {

    @Autowired
    private GroupAlbumRepository groupAlbumRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping(path = "/schedule/{albumNumber}")
    @ResponseBody
    public List<Schedule> getByNumber(@PathVariable Integer albumNumber) {
        List<GroupAlbum> groupAlbumList = groupAlbumRepository.findByAlbumNumber(albumNumber.toString());
        List<Integer> groupIds = groupAlbumList.stream()
                .map(GroupAlbum::getGroupId)
                .collect(Collectors.toList());
        return scheduleRepository.findByGroupIdIn(groupIds);
    }
}
