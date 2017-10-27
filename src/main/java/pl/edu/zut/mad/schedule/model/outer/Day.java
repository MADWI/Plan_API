package pl.edu.zut.mad.schedule.model.outer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Day {

    private final Long date;
    private final List<Lesson> lessons;
}
