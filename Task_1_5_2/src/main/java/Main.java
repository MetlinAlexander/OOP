import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.Date;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import org.apache.commons.cli.*;

/**
 * класс для работы с командной строкой
 * а также для сохранения/загрузки в/из json
 */
public class Main {

    /**
     * метод который вызываем для работы с записной книжкой.
     *
     * @param args
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static void main(String[] args)
            throws IOException, ParseException, java.text.ParseException {
        String path = "src/main/resources/notebook.json";
        //пробуем открыть json, если его нет создаем новую записную книжку
        File file = new File(path);
        NoteBook notebook;
        if (file.exists()) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd.MM.yyyy H:mm").create();
            Type typeToken = new TypeToken<NoteBook>() { }.getType();
            notebook = gson
                    .fromJson(new FileReader(path), typeToken);
            if (notebook == null) {
                notebook = new NoteBook();
            }
        } else {
            notebook = new NoteBook();
        }
        // работа с аргументами командной строки
        Options options = new Options();

        Option option = new Option("add", true, "add note");
        option.setArgs(2);
        options.addOption(option);

        option = new Option("rm", true, "remove note");
        option.setArgs(1);
        options.addOption(option);

        Option show = new Option("show", false, "show records");
        options.addOption(show);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("add")) {
            notebook.addNote(cmd.getOptionValues("add")[0],
                             cmd.getOptionValues("add")[1]);
        } else if (cmd.hasOption("rm")) {
            notebook.deleteNote(cmd.getOptionValue("rm"));
        } else if (cmd.hasOption("show")) {
            String[] arguments = cmd.getArgs();
            if (arguments.length == 0) {
                notebook.showNotes();
            } else {
                ArrayList<String> words = new ArrayList<>();
                for (int i = 2; i < arguments.length; i++) {
                    words.add(arguments[i]);
                }
                SimpleDateFormat formatter =
                        new SimpleDateFormat("dd.MM.yyyy H:mm");
                Date date1 = formatter.parse(arguments[0]);
                Date date2 = formatter.parse(arguments[1]);
                notebook.showSortNotes(date1, date2, words);
            }
        }
        // сериализуем данные обратно в json
        Gson gsonFinal = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy H:mm").create();
        try (FileWriter writer = new FileWriter(path)) {
            gsonFinal.toJson(notebook, writer);
        } catch (IOException e) {
            throw(e);
        }
    }
}
