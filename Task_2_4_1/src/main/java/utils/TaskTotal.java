package utils;

import lombok.Data;

/**
 * class for saving task results.
 *
 */
@Data
public class TaskTotal {

    private boolean downloaded;
    private boolean compiled = false;
    private boolean javadoc = false;
    private boolean softDeadline = false;
    private boolean hardDeadline = false;
    private Double mark = -0.5;
    private int checkstyleWarnings = -1;
    private int testsPassed = -1;
    private int testsSkipped = -1;
    private int testsFailed = -1;

    public TaskTotal() {

    }
}
