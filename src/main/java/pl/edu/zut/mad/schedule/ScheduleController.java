package pl.edu.zut.mad.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.zut.mad.schedule.exception.ScheduleExceptionFactory;
import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.model.outer.Day;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/schedule")
public class ScheduleController {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleService scheduleService;
    private final ScheduleExceptionFactory exceptionFactory;

    @Autowired
    public ScheduleController(ScheduleMapper scheduleMapper,
                              ScheduleService scheduleService,
                              ScheduleExceptionFactory exceptionFactory) {
        this.scheduleMapper = scheduleMapper;
        this.scheduleService = scheduleService;
        this.exceptionFactory = exceptionFactory;
    }

    @GetMapping(path = "/{albumNumber}")
    @ResponseBody
    public List<Day> getByNumber(@PathVariable Integer albumNumber,
                                 @RequestParam(required = false) Map<String, String> params) {
        final List<Schedule> studentSchedule = scheduleService.findByAlbumNumber(albumNumber, params);
        checkIfEmpty(studentSchedule, albumNumber.toString());
        return scheduleMapper.daysFrom(studentSchedule);
    }

    @GetMapping
    @ResponseBody
    public List<Day> find(@RequestParam Map<String, String> params) {
        final List<Schedule> searchedSchedule = scheduleService.findBy(params);
        checkIfEmpty(searchedSchedule, params.toString());
        return scheduleMapper.daysFrom(searchedSchedule);
    }

    @GetMapping(path = "/dictionary")
    @ResponseBody
    public List<String> getDictionary(@RequestParam Map<String, String> params) {
        List<String> dictionary = scheduleService.getDictionaryFor(params);
        checkIfEmpty(dictionary, params.toString());
        return dictionary;
    }

    private <T> void checkIfEmpty(List<T> results, String params) {
        if (results.isEmpty()) {
            throw exceptionFactory.notFound(params);
        }
    }
}
