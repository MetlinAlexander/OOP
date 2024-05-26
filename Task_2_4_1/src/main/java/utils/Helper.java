package utils;

import lombok.SneakyThrows;
import org.gradle.tooling.BuildException;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import com.puppycrawl.tools.checkstyle.Main;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Helper {

    private TaskTotal taskTotal;
    private String taskPath;

    private String userPath;

    public Helper(TaskTotal task, String taskPath, String userPath) {
        this.taskTotal = task;
        this.taskPath = taskPath;
        this.userPath = userPath;
    }

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
        if(new File(this.taskPath + "/build/docs/javadoc/").exists()) {
            this.taskTotal.setJavadoc(true);
        }
    }

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
        while(reader.ready()) {
            if (reader.readLine().contains("[WARN]")) {
                warningsCount++;
            }
        }
        taskTotal.setCheckstyleWarnings(warningsCount);
        reader.close();
    }

    public void runTests() {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(this.taskPath));
        ProjectConnection connection = connector.connect();
        try {
            BuildLauncher build = connection.newBuild();
            build.forTasks("test");
            build.run();
        } catch (BuildException ignored) {

        } finally {
            connection.close();
        }
        validateTests();
    }

    @SneakyThrows
    private void validateTests() {
        File testResultsPath = new File(this.taskPath + "/build/test-results/test");
        if (testResultsPath.exists()) {
            File[] files = testResultsPath.listFiles((dir, name) -> name.endsWith(".xml"));
            int allTests = 0;
            int failed = 0;
            int skiped = 0;
            for (File file : files) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = null;
                builder = factory.newDocumentBuilder();
                Document document = null;
                document = builder.parse(file);
                Node elem = document.getElementsByTagName("testsuite").item(0);
                NamedNodeMap attributes = elem.getAttributes();
                allTests += Integer.parseInt(attributes.getNamedItem("tests").getNodeValue());
                failed += Integer.parseInt(attributes.getNamedItem("failures").getNodeValue());
                skiped += Integer.parseInt(attributes.getNamedItem("skipped").getNodeValue());
            }
            int passed = allTests - failed - skiped;
            taskTotal.setTestsFailed(failed);
            taskTotal.setTestsSkipped(skiped);
            taskTotal.setTestsPassed(passed);
        }
    }

}
