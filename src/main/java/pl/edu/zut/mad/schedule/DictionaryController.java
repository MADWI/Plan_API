package pl.edu.zut.mad.schedule;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.zut.mad.schedule.model.ErrorMessage;
import pl.edu.zut.mad.schedule.model.dictionary.FacultyAbbreviation;
import pl.edu.zut.mad.schedule.model.dictionary.Room;
import pl.edu.zut.mad.schedule.model.dictionary.Subject;
import pl.edu.zut.mad.schedule.model.dictionary.Surname;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Dictionary")
@Controller
@RequestMapping(path = "/dictionary")
public class DictionaryController {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public DictionaryController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @ApiOperation(value = "Get list of teachers' surnames", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request parameters", response = ErrorMessage.class),
            @ApiResponse(code = 503, message = "Service unavailable", response = ErrorMessage.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "surname", value = "Full surname or substring",
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Amount of returned surnames", defaultValue = "20",
                    paramType = "query"),
            @ApiImplicitParam(name = "page", value = "Page number", defaultValue = "0", paramType = "query")
    })
    @GetMapping(path = "/surname")
    @ResponseBody
    public List<String> getSurnames(@RequestParam String surname, @ApiIgnore Pageable pageable) {
        return scheduleRepository.findDistinctBySurnameContaining(surname, pageable)
                .stream()
                .map(Surname::getSurname)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get list of subjects", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request parameters", response = ErrorMessage.class),
            @ApiResponse(code = 503, message = "Service unavailable", response = ErrorMessage.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "Full subject name or substring",
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Amount of returned subjects", defaultValue = "20",
                    paramType = "query"),
            @ApiImplicitParam(name = "page", value = "Page number", defaultValue = "0", paramType = "query")
    })
    @GetMapping(path = "/subject")
    @ResponseBody
    public List<String> getSubjects(@RequestParam String subject, @ApiIgnore Pageable pageable) {
        return scheduleRepository.findDistinctBySubjectContaining(subject, pageable)
                .stream()
                .map(Subject::getSubject)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get list of rooms", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request parameters", response = ErrorMessage.class),
            @ApiResponse(code = 503, message = "Service unavailable", response = ErrorMessage.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "room", value = "Full room name or substring",
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Amount of returned rooms", defaultValue = "20",
                    paramType = "query"),
            @ApiImplicitParam(name = "page", value = "Page number", defaultValue = "0", paramType = "query")
    })
    @GetMapping(path = "/room")
    @ResponseBody
    public List<String> getRooms(@RequestParam String room, @ApiIgnore Pageable pageable) {
        return scheduleRepository.findDistinctByRoomContaining(room, pageable)
                .stream()
                .map(Room::getRoom)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get list of faculties", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request parameters", response = ErrorMessage.class),
            @ApiResponse(code = 503, message = "Service unavailable", response = ErrorMessage.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "faculty", value = "Full faculty name or substring",
                    required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Amount of returned faculties", defaultValue = "20",
                    paramType = "query"),
            @ApiImplicitParam(name = "page", value = "Page number", defaultValue = "0", paramType = "query")
    })
    @GetMapping(path = "/faculty")
    @ResponseBody
    public List<String> getFacultyAbbreviations(String faculty, @ApiIgnore Pageable pageable) {
        return scheduleRepository.findDistinctByFacultyAbbreviationContaining(faculty, pageable)
                .stream()
                .map(FacultyAbbreviation::getFacultyAbbreviation)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
