import dsl.Course;
import dsl.Student;
import dsl.Task;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.groovy.groovysh.Main;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import utils.Downloader;
import utils.Helper;
import utils.HtmlConstructor;
import utils.TaskTotal;


/**
 * main class to start lab checking.
 */
public class App {
    private static String configPath = "src/main/resources/config.groovy";
    private static String repoPath = "src/main/resources/repos/";
    private static HashMap<Student, HashMap<Task, TaskTotal>> taskInfo =
            new HashMap<>();

    /**
     * main method to check lab.
     *
     * @param args extra args
     * @throws Exception some exception
     */
    public static void main(final String[] args) throws Exception {
        // delete old data
        deleteOldRepo(repoPath);
        // configure work with dsl
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(),
                new Binding(), cc);
        DelegatingScript script = (DelegatingScript) sh
                .parse(new File(configPath));
        Course config = new Course();
        script.setDelegate(config);
        script.run();
        config.postProcess();


        // work with students
        Downloader mainDownloader = new Downloader(repoPath);
        for (var student : config.getStudents()) {
            // work with current student
            taskInfo.put(student, new HashMap<>());

            //download repo for cur student
            boolean downloaded = mainDownloader.download(student.getRepo(),
                    student.getNickname());
            if (!downloaded) {
                continue;
            }
            // put tasks for current student
            for (var task : config.getTasks()) {
                taskInfo.get(student).put(task, new TaskTotal());
                taskInfo.get(student).get(task).setDownloaded(downloaded);
            }

            // check every task
            for (var task : config.getTasks()) {
                String taskPath = repoPath
                        + "/" + student.getNickname()
                        + "/" + task.getId();
                Helper helper = new Helper(taskInfo.get(student).get(task),
                        taskPath,
                        repoPath + "/" + student.getNickname());
                helper.runCompile();
                helper.generateDocs();
                helper.checkStyle();
                helper.runTests();
                checkDeadlines(task,
                        taskInfo.get(student).get(task),
                        student);
                setMark(task, taskInfo.get(student).get(task));
            }
        }
        try {
            HtmlConstructor.render(config.getTasks(), taskInfo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void deleteOldRepo(final String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * calculate and set mark for current task.
     *
     * @param task task from config
     * @param taskTotal task result
     */
    private static void setMark(Task task, TaskTotal taskTotal) {
        double totalMark = -0.5;
        if (taskTotal.isCompiled()
                && taskTotal.isJavadoc()
                && taskTotal.getTestsPassed() >= 0
                && taskTotal.getTestsFailed() == 0) {
            totalMark += 0.5;
        }
        if (taskTotal.isSoftDeadline()) {
            totalMark += 0.5;
        }
        if (taskTotal.isHardDeadline()) {
            totalMark += 0.5;
        }
        taskTotal.setMark(totalMark);
    }

    /**
     * method to check deadlines.
     *
     * @param task task from config
     * @param taskTotal task result
     * @param student student from config
     */
    @SneakyThrows
    private static void checkDeadlines(Task task,
                                       TaskTotal taskTotal,
                                       Student student) {
        File repository = new File(repoPath + student.getNickname());
        var commits = Git.open(repository).log().addPath(task.getId()).call();

        LocalDate firstCommitDate = null;
        LocalDate lastCommitDate = null;

        for (RevCommit commit : commits) {
            LocalDate commitDate = LocalDate
                                    .ofInstant(Instant
                                    .ofEpochSecond(commit.getCommitTime()),
                                    ZoneId.systemDefault());
            if (firstCommitDate == null || commitDate
                    .isBefore(firstCommitDate)) {
                firstCommitDate = commitDate;
            }
            if (lastCommitDate == null || commitDate
                    .isAfter(lastCommitDate)) {
                lastCommitDate = commitDate;
            }
        }

        boolean softDeadline = firstCommitDate.isBefore(task.getSoftDeadline());
        boolean hardDeadline = lastCommitDate.isBefore(task.getHardDeadline());

        taskTotal.setSoftDeadline(softDeadline);
        taskTotal.setHardDeadline(hardDeadline);
        Git.shutdown();
    }

}

