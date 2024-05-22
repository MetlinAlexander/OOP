import dsl.Course;
import dsl.Student;
import dsl.Task;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import org.apache.groovy.groovysh.Main;
import org.codehaus.groovy.control.CompilerConfiguration;
import utils.Downloader;
import utils.TaskTotal;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

public class App {
    private static String configPath = "src/main/resources/config.groovy";
    private static String repoPath = "src/main/resources/repos/";
    private static HashMap<Student, HashMap<Task, TaskTotal>> taskInfo = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // delete old data
        deleteOldRepo(repoPath);
        // configure work with dsl
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);
        DelegatingScript script = (DelegatingScript)sh.parse(new File(configPath));
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
            boolean downloaded = mainDownloader.download(student.getRepo(), student.getNickname());
            // put tasks for current student
            for (var task : config.getTasks()) {
                taskInfo.get(student).put(task, new TaskTotal());
                taskInfo.get(student).get(task).setDownloaded(downloaded);
            }

        }
        System.out.println(config);
    }

    private static void deleteOldRepo(String path){
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

