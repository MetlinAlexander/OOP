package dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class Task extends GroovyConfigurable {

    String id;
    String name;
    LocalDate softDeadline;
    LocalDate hardDeadline;
}
