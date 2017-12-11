package pl.edu.zut.mad.schedule.model.outer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@AllArgsConstructor
public class Teacher {
    private final String academicTitle;
    private final String name;
    private final String surname;
}
