package pl.edu.zut.mad.schedule.model.outer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Teacher {
    private final String academicTitle;
    private final String name;
    private final String surname;
}
