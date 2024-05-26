package dsl;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * model represents course.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Course extends GroovyConfigurable {
    private ArrayList<Task> tasks;
    private ArrayList<Student> students;
}
