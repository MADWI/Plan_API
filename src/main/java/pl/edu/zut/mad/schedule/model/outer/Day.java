package pl.edu.zut.mad.schedule.model.outer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@AllArgsConstructor
public class Day {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate date;
    private final List<Lesson> lessons;
}
