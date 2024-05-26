package dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;


/**
 * model represents course.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Course extends GroovyConfigurable {
    private ArrayList<Task> tasks;
    private ArrayList<Student> students;
}
