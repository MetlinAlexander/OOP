package dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Student extends GroovyConfigurable {

    private String nickname;
    private String familyName;
    private String repo;
    private String group;
}
