package utils;

import dsl.Student;
import dsl.Task;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class for creating html page.
 */
public class HtmlConstructor {
    /**
     * render hmtl page with given params.
     *
     * @param tasks all tasks
     * @param tasksResults all info about students and task results
     */
    @SneakyThrows
    public static void render(
            List<Task> tasks,
            HashMap<Student, HashMap<Task, TaskTotal>> tasksResults) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setClassForTemplateLoading(HtmlConstructor.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("tasks", tasks);
        root.put("tasksResults", tasksResults);

        Template temp = cfg.getTemplate("template.ftl");
        Writer out = new OutputStreamWriter(new FileOutputStream(
                "src/main/resources/index.html"));
        temp.process(root, out);
    }
}