package pl.edu.zut.mad.schedule;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.zut.mad.schedule.exception.ScheduleExceptionFactory;
import pl.edu.zut.mad.schedule.model.ErrorMessage;
import pl.edu.zut.mad.schedule.model.inner.Schedule;
import pl.edu.zut.mad.schedule.model.outer.Day;

import java.util.List;
import java.util.Map;

@Api(value = "Schedule")
@Controller
@RequestMapping(path = "/schedule")
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

    @ApiOperation(value = "Get schedule by student's album number", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request parameters", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorMessage.class),
            @ApiResponse(code = 503, message = "Service unavailable", response = ErrorMessage.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dateFrom", value = "Start date in format dd-MM-yyyy", paramType = "query",
                    format = "dd-MM-yyyy", example = "17-12-2017"),
            @ApiImplicitParam(name = "dateTo", value = "End date in format dd-MM-yyyy", paramType = "query",
                    format = "dd-MM-yyyy", example = "17-12-2017")
    })
    @GetMapping(path = "/{albumNumber}")
    @ResponseBody
    public List<Day> getByNumber(@PathVariable Integer albumNumber,
                                 @RequestParam(required = false) Map<String, String> params) {
        final List<Schedule> studentSchedule = scheduleService.findByAlbumNumber(albumNumber, params);
        checkIfEmpty(studentSchedule, albumNumber.toString());
        return scheduleMapper.daysFrom(studentSchedule);
    }

    @ApiOperation(value = "Find schedule by provided parameters", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request parameters", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorMessage.class),
            @ApiResponse(code = 503, message = "Service unavailable", response = ErrorMessage.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Teacher's first name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "surname", value = "Teacher's last name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "room", value = "Room name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "courseType", value = "Course type, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "subject", value = "Subject, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "semester", value = "Semester number, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "faculty", value = "Faculty, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "facultyAbbreviation", value = "Faculty abbreviation",
                    paramType = "query"),
            @ApiImplicitParam(name = "fieldOfStudy", value = "Field of study, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "form", value = "Form, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "reservationStatus", value = "Reservation status, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "reservationStatusAbbreviation", value = "Reservation status abbreviation",
                    paramType = "query"),
            @ApiImplicitParam(name = "status", value = "Status, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "substituteSurname", value = "Substitute teacher's last name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "substituteName", value = "Substitute teacher's first name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "group", value = "Group, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "dateFrom", value = "Start date in format dd-MM-yyyy", paramType = "query",
                    format = "dd-MM-yyyy", example = "17-12-2017"),
            @ApiImplicitParam(name = "dateTo", value = "End date in format dd-MM-yyyy", paramType = "query",
                    format = "dd-MM-yyyy", example = "17-12-2017")
    })
    @GetMapping
    @ResponseBody
    public List<Day> find(@RequestParam Map<String, String> params) {
        final List<Schedule> searchedSchedule = scheduleService.findBy(params);
        checkIfEmpty(searchedSchedule, params.toString());
        return scheduleMapper.daysFrom(searchedSchedule);
    }

    @ApiOperation(value = "Get all values for queried field", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request parameters", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorMessage.class),
            @ApiResponse(code = 503, message = "Service unavailable", response = ErrorMessage.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filter", value = "Specifies which field will be returned in response",
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "Limits number of returned records",
                    paramType = "query"),
            @ApiImplicitParam(name = "name", value = "Teacher's first name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "surname", value = "Teacher's last name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "room", value = "Room name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "courseType", value = "Course type, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "subject", value = "Subject, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "semester", value = "Semester number, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "faculty", value = "Faculty, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "facultyAbbreviation", value = "Faculty abbreviation",
                    paramType = "query"),
            @ApiImplicitParam(name = "fieldOfStudy", value = "Field of study, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "form", value = "Form, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "reservationStatus", value = "Reservation status, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "reservationStatusAbbreviation", value = "Reservation status abbreviation",
                    paramType = "query"),
            @ApiImplicitParam(name = "status", value = "Status, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "substituteSurname", value = "Substitute teacher's last name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "substituteName", value = "Substitute teacher's first name, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "group", value = "Group, " +
                    "supports wildcard characters: '%' - matches zero or more of any character; " +
                    "'_' - matches any single character",
                    paramType = "query"),
            @ApiImplicitParam(name = "dateFrom", value = "Start date in format dd-MM-yyyy", paramType = "query",
                    format = "dd-MM-yyyy", example = "17-12-2017"),
            @ApiImplicitParam(name = "dateTo", value = "End date in format dd-MM-yyyy", paramType = "query",
                    format = "dd-MM-yyyy", example = "17-12-2017")
    })
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
