package pl.edu.zut.mad.schedule.model.outer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimeRange {

    private final Long from;
    private final Long to;
}
