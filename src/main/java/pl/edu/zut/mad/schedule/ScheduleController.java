package pl.edu.zut.mad.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.zut.mad.schedule.exception.EmptyDatabaseException;
import pl.edu.zut.mad.schedule.exception.NotFoundException;
import pl.edu.zut.mad.schedule.model.inner.GroupAlbum;
import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.model.outer.Day;
import pl.edu.zut.mad.schedule.search.ScheduleSpecificationFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/schedule")
public class ScheduleController {

    private final GroupAlbumRepository groupAlbumRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleSpecificationFactory scheduleSpecificationFactory;

    @Autowired
    public ScheduleController(GroupAlbumRepository groupAlbumRepository,
                              ScheduleRepository scheduleRepository,
                              ScheduleMapper scheduleMapper,
                              ScheduleSpecificationFactory scheduleSpecificationFactory) {
        this.groupAlbumRepository = groupAlbumRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.scheduleSpecificationFactory = scheduleSpecificationFactory;
    }

    @GetMapping(path = "/{albumNumber}")
    @ResponseBody
    public List<Day> getByNumber(@PathVariable Integer albumNumber) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }
        List<GroupAlbum> groupAlbumList = groupAlbumRepository.findByAlbumNumber(albumNumber.toString());
        List<Integer> groupIds = groupAlbumList.stream()
                .map(GroupAlbum::getGroupId)
                .collect(Collectors.toList());

        final List<Schedule> studentSchedule = scheduleRepository.findByGroupIdIn(groupIds);
        if (studentSchedule.isEmpty()) {
            throw new NotFoundException(albumNumber.toString());
        }
        return scheduleMapper.daysFrom(studentSchedule);
    }

    @GetMapping
    @ResponseBody
    public List<Day> find(@RequestParam Map<String, String> params) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }

        Specification<Schedule> specification = scheduleSpecificationFactory.specification(params);
        final List<Schedule> searchedSchedule = scheduleRepository.findAll(specification);
        if (searchedSchedule.isEmpty()) {
            throw new NotFoundException(params.toString());
        }

        return scheduleMapper.daysFrom(searchedSchedule);
    }
}
