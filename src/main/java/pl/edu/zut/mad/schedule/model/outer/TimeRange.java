package pl.edu.zut.mad.schedule.model.outer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@AllArgsConstructor
public class TimeRange {

    @JsonFormat(pattern = "HH:mm")
    private final LocalTime from;

    @JsonFormat(pattern = "HH:mm")
    private final LocalTime to;
}
