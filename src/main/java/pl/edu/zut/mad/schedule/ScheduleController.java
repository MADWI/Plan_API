package pl.edu.zut.mad.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.zut.mad.schedule.exception.BadRequestException;
import pl.edu.zut.mad.schedule.exception.EmptyDatabaseException;
import pl.edu.zut.mad.schedule.exception.NotFoundException;
import pl.edu.zut.mad.schedule.model.inner.GroupAlbum;
import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.model.outer.Day;
import pl.edu.zut.mad.schedule.search.ScheduleSpecificationFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/schedule")
public class ScheduleController {

    private final GroupAlbumRepository groupAlbumRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleSpecificationFactory scheduleSpecificationFactory;
    private final MessageSource messageSource;

    @Autowired
    public ScheduleController(GroupAlbumRepository groupAlbumRepository,
                              ScheduleRepository scheduleRepository,
                              ScheduleMapper scheduleMapper,
                              ScheduleSpecificationFactory scheduleSpecificationFactory,
                              MessageSource messageSource) {
        this.groupAlbumRepository = groupAlbumRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.scheduleSpecificationFactory = scheduleSpecificationFactory;
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/{albumNumber}")
    @ResponseBody
    public List<Day> getByNumber(@PathVariable Integer albumNumber,
                                 @RequestParam(required = false) Map<String, String> params) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }
        List<GroupAlbum> groupAlbumList = groupAlbumRepository.findByAlbumNumber(albumNumber.toString());
        List<Integer> groupIds = groupAlbumList.stream()
                .map(GroupAlbum::getGroupId)
                .collect(Collectors.toList());
        if (groupIds.isEmpty()) {
            throw new NotFoundException(albumNumber.toString());
        }

        final Specification<Schedule> specification = scheduleSpecificationFactory.specification(params, groupIds);
        final List<Schedule> studentSchedule = scheduleRepository.findAll(specification);
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
        if (params.isEmpty()) {
            throw handleMissingParameter();
        }

        Optional<Specification<Schedule>> optionalSpecification = scheduleSpecificationFactory.specification(params);
        Specification<Schedule> specification = optionalSpecification
                .orElseThrow(this::handleMissingParameter);
        final List<Schedule> searchedSchedule = scheduleRepository.findAll(specification);
        if (searchedSchedule.isEmpty()) {
            throw new NotFoundException(params.toString());
        }

        return scheduleMapper.daysFrom(searchedSchedule);
    }

    @GetMapping(path = "/teacher/{surname}")
    @ResponseBody
    public List<String> findTeacher(@PathVariable String surname,
                                    @RequestParam(required = false) Integer size) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }

        List<Schedule> schedules = scheduleRepository.findDistinctBySurnameContaining(surname);
        if (schedules.isEmpty()) {
            throw new NotFoundException(surname);
        }

        return extractResultFromSchedule(schedules, size, Schedule::getSurname);
    }

    @GetMapping(path = "/subject/{subject}")
    @ResponseBody
    public List<String> findSubject(@PathVariable String subject,
                                    @RequestParam(required = false) Integer size) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }

        List<Schedule> schedules = scheduleRepository.findDistinctBySubjectContaining(subject);
        if (schedules.isEmpty()) {
            throw new NotFoundException(subject);
        }

        return extractResultFromSchedule(schedules, size, Schedule::getSubject);
    }

    private List<String> extractResultFromSchedule(List<Schedule> schedules, Integer size,
                                                   Function<Schedule, String> mapper) {
        if (size == null) {
            return schedules.stream()
                    .map(mapper)
                    .distinct()
                    .collect(Collectors.toList());
        } else {
            return schedules.stream()
                    .map(mapper)
                    .distinct()
                    .limit(size)
                    .collect(Collectors.toList());
        }
    }

    private BadRequestException handleMissingParameter() throws BadRequestException {
        final String message = messageSource.getMessage(
                "errMissingParam",
                null,
                LocaleContextHolder.getLocale());

        throw new BadRequestException(message);
    }
}
