package pl.edu.zut.mad.schedule.model.outer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class Day {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate date;
    private final List<Lesson> lessons;
}
