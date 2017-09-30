package pl.edu.zut.mad.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.zut.mad.schedule.exception.NotFoundException;
import pl.edu.zut.mad.schedule.model.GroupAlbum;
import pl.edu.zut.mad.schedule.model.Schedule;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api")
public class ScheduleController {

    private final GroupAlbumRepository groupAlbumRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleController(GroupAlbumRepository groupAlbumRepository,
                              ScheduleRepository scheduleRepository) {
        this.groupAlbumRepository = groupAlbumRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping(path = "/schedule/{albumNumber}")
    @ResponseBody
    public List<Schedule> getByNumber(@PathVariable Integer albumNumber) {
        List<GroupAlbum> groupAlbumList = groupAlbumRepository.findByAlbumNumber(albumNumber.toString());
        List<Integer> groupIds = groupAlbumList.stream()
                .map(GroupAlbum::getGroupId)
                .collect(Collectors.toList());

        final List<Schedule> studentSchedule = scheduleRepository.findByGroupIdIn(groupIds);
        if (studentSchedule.isEmpty()) {
            throw new NotFoundException(albumNumber);
        }

        return studentSchedule;
    }
}
