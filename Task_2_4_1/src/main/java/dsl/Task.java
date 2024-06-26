package dsl;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * model represents task.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Task extends GroovyConfigurable {
    private String id;
    private String name;
    private LocalDate softDeadline;
    private LocalDate hardDeadline;
}
