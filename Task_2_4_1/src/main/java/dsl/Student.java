package dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * model represents student.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Student extends GroovyConfigurable {

    private String nickname;
    private String passportName;
    private String repo;
    private String group;
}
