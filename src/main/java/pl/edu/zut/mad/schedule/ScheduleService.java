package pl.edu.zut.mad.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.edu.zut.mad.schedule.exception.BadRequestException;
import pl.edu.zut.mad.schedule.exception.EmptyDatabaseException;
import pl.edu.zut.mad.schedule.exception.NotFoundException;
import pl.edu.zut.mad.schedule.model.inner.GroupAlbum;
import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.search.ScheduleSpecificationFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ScheduleService {

    private static final String LIMIT = "limit";
    private static final String FILTER = "filter";

    private final GroupAlbumRepository groupAlbumRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleSpecificationFactory scheduleSpecificationFactory;
    private final MessageSource messageSource;

    @Autowired
    public ScheduleService(GroupAlbumRepository groupAlbumRepository,
                           ScheduleRepository scheduleRepository,
                           ScheduleSpecificationFactory scheduleSpecificationFactory,
                           MessageSource messageSource) {
        this.groupAlbumRepository = groupAlbumRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleSpecificationFactory = scheduleSpecificationFactory;
        this.messageSource = messageSource;
    }

    public List<Schedule> findByAlbumNumber(final int albumNumber, final Map<String, String> params) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }
        List<GroupAlbum> groupAlbumList = groupAlbumRepository.findByAlbumNumber(String.valueOf(albumNumber));
        List<Integer> groupIds = groupAlbumList.stream()
                .map(GroupAlbum::getGroupId)
                .collect(Collectors.toList());
        if (groupIds.isEmpty()) {
            throw new NotFoundException(String.valueOf(albumNumber));
        }

        final Specification<Schedule> specification = scheduleSpecificationFactory.specification(params, groupIds);
        return scheduleRepository.findAll(specification);
    }

    public List<Schedule> findBy(Map<String, String> params) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }
        if (params.isEmpty()) {
            throw handleMissingParameter();
        }
        Optional<Specification<Schedule>> optionalSpecification = scheduleSpecificationFactory.specification(params);
        Specification<Schedule> specification = optionalSpecification
                .orElseThrow(this::handleMissingParameter);
        return scheduleRepository.findAll(specification);
    }

    public List<String> getDictionaryFor(Map<String, String> params) {
        if (scheduleRepository.count() == 0) {
            throw new EmptyDatabaseException();
        }

        Integer limit = params.containsKey(LIMIT) ? Integer.parseInt(params.get(LIMIT)) : null;
        String filter = params.get(FILTER);
        if (!params.containsKey(filter)) {
            throw handleMissingParameter(filter);
        }

        Optional<Specification<Schedule>> optionalSpecification = scheduleSpecificationFactory.specification(params);
        Specification<Schedule> specification = optionalSpecification
                .orElseThrow(this::handleMissingParameter);
        List<Schedule> schedules = scheduleRepository.findAll(specification);
        return extractResultFromSchedule(schedules, limit, getMapper(filter));
    }

    private List<String> extractResultFromSchedule(List<Schedule> schedules, Integer limit,
                                                   Function<Schedule, String> mapper) {
        Stream<String> dictionary = schedules.stream()
                .map(mapper)
                .distinct()
                .sorted();

        if (limit == null) {
            return dictionary.collect(Collectors.toList());
        } else {
            return dictionary.limit(limit)
                    .collect(Collectors.toList());
        }
    }

    private Function<Schedule, String> getMapper(String filter) {
        Schedule.Field field = Schedule.Field.fieldOf(filter);
        if (field.getMapper().isPresent()) {
            return field.getMapper().get();
        } else {
            final String message = messageSource.getMessage(
                    "errUnsupportedParam",
                    new String[]{filter},
                    LocaleContextHolder.getLocale());
            throw new BadRequestException(message);
        }
    }

    private BadRequestException handleMissingParameter() throws BadRequestException {
        final String message = messageSource.getMessage(
                "errMissingParam",
                null,
                LocaleContextHolder.getLocale());

        throw new BadRequestException(message);
    }

    private BadRequestException handleMissingParameter(String parameter) throws BadRequestException {
        final String message = messageSource.getMessage(
                "errMissingRequiredParam",
                new String[]{parameter},
                LocaleContextHolder.getLocale());

        throw new BadRequestException(message);
    }
}
