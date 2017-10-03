package pl.edu.zut.mad.schedule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
    private final long id;
}
