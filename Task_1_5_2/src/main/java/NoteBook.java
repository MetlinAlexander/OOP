import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * класс реализующий обьект типа записная книжка
 */
public class NoteBook {

    private ArrayList<Note> allNotes;

    /**
     * конструктор для записной книжки.
     */
    NoteBook() {
        this.allNotes = new ArrayList<>();
    }

    /**
     * метод для добавления новой заметки.
     *
     * @param title заголовок заметки
     * @param content содержание заметки
     */
    public void addNote(String title, String content) {
        allNotes.add(new Note(title, content));
    }

    /**
     * метод для получения всех заметок.
     *
     * @return все заметки
     */
    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    /**
     * метод для удаления заметок по заголовку.
     *
     * @param title заголовок
     */
    public void deleteNote(String title) {
        this.allNotes.removeIf(x -> x.getTitle().equals(title));
    }

    /**
     * метод для вывода в консоль всех заметок.
     */
    public void showNotes() {
        for (int i = 0; i < allNotes.size(); i++) {
            System.out.println(allNotes.get(i));
        }
    }

    /**
     * метод для вывода некторых заметок отсортированных по времени.
     *
     * @param date1 время от
     * @param date2 время до
     * @param words список ключевых слов
     */
    public void showSortNotes(Date date1, Date date2, ArrayList<String> words) {
        ArrayList<Note> notSorted = new ArrayList<>();
        for (int i = 0; i < allNotes.size(); i++) {
            if (words.contains(allNotes.get(i).getTitle())) {
                if (allNotes.get(i).getDate().after(date1)
                        && allNotes.get(i).getDate().before(date2)) {
                    notSorted.add(allNotes.get(i));
                }
            }
        }
        notSorted.sort(Comparator.comparing(Note::getDate));
        for (int i = 0; i < notSorted.size(); i++) {
            System.out.println(notSorted.get(i));
        }
    }
}
