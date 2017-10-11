package pl.edu.zut.mad.schedule.model.outer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class TimeRange {

    @JsonFormat(pattern = "HH:mm")
    private final LocalTime from;

    @JsonFormat(pattern = "HH:mm")
    private final LocalTime to;
}
