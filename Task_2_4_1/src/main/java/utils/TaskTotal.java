package utils;

import lombok.Data;

@Data
public class TaskTotal {

    boolean downloaded;
    boolean compiled = false;
    boolean javadoc = false;
    boolean softDeadline = false;
    boolean hardDeadline = false;
    String mark = "-0.5";
    int checkstyleWarnings = -1;
    int testsPassed = -1;
    int testsSkipped = -1;
    int testsFailed = -1;

    public TaskTotal () {

    }
}
