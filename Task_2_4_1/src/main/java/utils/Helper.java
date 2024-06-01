package utils;

import com.puppycrawl.tools.checkstyle.Main;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import lombok.SneakyThrows;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


/**
 * helper class to check tasks.
 */
public class Helper {

    private TaskTotal taskTotal;
    private String taskPath;

    private String userPath;

    /**
     * constructor for current helper.
     *
     * @param task task info
     * @param taskPath task results
     * @param userPath path of user repo
     */
    public Helper(TaskTotal task, String taskPath, String userPath) {
        this.taskTotal = task;
        this.taskPath = taskPath;
        this.userPath = userPath;
    }

    /**
     * method to check compile.
     */
    public void runCompile() {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(this.taskPath));
        ProjectConnection connection = connector.connect();
        try {
            BuildLauncher build = connection.newBuild();
            build.forTasks("compileJava");
            build.run();
        } catch (BuildException e) {
            return;
        } finally {
            connection.close();
        }
        this.taskTotal.setCompiled(true);
    }

    /**
     * method to check javadocs.
     */
    public void generateDocs() {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(this.taskPath));
        ProjectConnection connection = connector.connect();
        try {
            BuildLauncher build = connection.newBuild();
            build.forTasks("javadoc");
            build.run();
        } catch (BuildException e) {
            return;
        } finally {
            connection.close();
        }
        if (new File(this.taskPath + "/build/docs/javadoc/").exists()) {
            this.taskTotal.setJavadoc(true);
        }
    }

    /**
     * method to check checkstyle.
     */
    @SneakyThrows
    public void checkStyle() {
        String clPath = userPath + "/clResult.txt";
        catchSystemExit(() -> {
            String configPath = userPath +  "/.github/google_checks.xml";
            var mainSourcePath = taskPath + "/src/main/java";
            Main.main("-c", configPath, "-o", clPath, mainSourcePath);
        });
        BufferedReader reader = new BufferedReader(new FileReader(clPath));
        int warningsCount = 0;
        while (reader.ready()) {
            if (reader.readLine().contains("[WARN]")) {
                warningsCount++;
            }
        }
        taskTotal.setCheckstyleWarnings(warningsCount);
        reader.close();
    }

    /**
     * method to run tests.
     */
    public void runTests() {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(this.taskPath));
        ProjectConnection connection = connector.connect();
        try {
            BuildLauncher build = connection.newBuild();
            build.forTasks("test");
            build.run();
        } catch (BuildException ignored) {
            return;
        } finally {
            connection.close();
        }
        validateTests();
    }

    /**
     * method to validate test results.
     */
    @SneakyThrows
    private void validateTests() {
        File testResultsPath =
                new File(this.taskPath + "/build/test-results/test");
        if (testResultsPath.exists()) {
            File[] files = testResultsPath
                    .listFiles((dir, name) -> name.endsWith(".xml"));
            int allTests = 0;
            int failed = 0;
            int skiped = 0;
            for (File file : files) {
                DocumentBuilderFactory factory =
                        DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;
                builder = factory.newDocumentBuilder();
                Document document = null;
                document = builder.parse(file);
                Node elem = document.getElementsByTagName("testsuite").item(0);
                NamedNodeMap attributes = elem.getAttributes();
                allTests += Integer.parseInt(attributes
                        .getNamedItem("tests")
                        .getNodeValue());
                failed += Integer.parseInt(attributes
                        .getNamedItem("failures")
                        .getNodeValue());
                skiped += Integer.parseInt(attributes
                        .getNamedItem("skipped")
                        .getNodeValue());
            }
            int passed = allTests - failed - skiped;
            taskTotal.setTestsFailed(failed);
            taskTotal.setTestsSkipped(skiped);
            taskTotal.setTestsPassed(passed);
        }
    }

}
