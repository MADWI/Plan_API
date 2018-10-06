package pl.edu.zut.mad.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.edu.zut.mad.schedule.exception.ScheduleExceptionFactory;
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
    private final ScheduleExceptionFactory exceptionFactory;

    @Autowired
    public ScheduleService(GroupAlbumRepository groupAlbumRepository,
                           ScheduleRepository scheduleRepository,
                           ScheduleSpecificationFactory scheduleSpecificationFactory,
                           ScheduleExceptionFactory exceptionFactory) {
        this.groupAlbumRepository = groupAlbumRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleSpecificationFactory = scheduleSpecificationFactory;
        this.exceptionFactory = exceptionFactory;
    }

    List<Schedule> findByAlbumNumber(final int albumNumber, final Map<String, String> params) {
        if (scheduleRepository.count() == 0) {
            throw exceptionFactory.emptyDatabase();
        }
        List<GroupAlbum> groupAlbumList = groupAlbumRepository.findByAlbumNumber(String.valueOf(albumNumber));
        List<Integer> groupIds = groupAlbumList.stream()
                .map(GroupAlbum::getGroupId)
                .collect(Collectors.toList());
        if (groupIds.isEmpty()) {
            throw exceptionFactory.notFound(String.valueOf(albumNumber));
        }

        final Specification<Schedule> specification = scheduleSpecificationFactory.specification(params, groupIds);
        return scheduleRepository.findAll(specification);
    }

    List<Schedule> findBy(Map<String, String> params) {
        if (params.isEmpty()) {
            throw exceptionFactory.missingParam();
        }
        Optional<Specification<Schedule>> optionalSpecification = scheduleSpecificationFactory.specification(params);
        Specification<Schedule> specification = optionalSpecification
                .orElseThrow(exceptionFactory::missingParam);
        return scheduleRepository.findAll(specification);
    }

    List<String> getDictionaryFor(Map<String, String> params) {
        Integer limit = params.containsKey(LIMIT) ? Integer.parseInt(params.get(LIMIT)) : null;
        String filter = params.get(FILTER);
        if (!params.containsKey(filter)) {
            throw exceptionFactory.missingRequiredParam(filter);
        }

        Optional<Specification<Schedule>> optionalSpecification = scheduleSpecificationFactory.specification(params);
        Specification<Schedule> specification = optionalSpecification
                .orElseThrow(exceptionFactory::missingParam);
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
            throw exceptionFactory.unsupportedParam(filter);
        }
    }
}
