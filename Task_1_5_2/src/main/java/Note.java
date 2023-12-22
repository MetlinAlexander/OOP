import java.util.Date;

/**
 * класс реализующий обьект типа заметка.
 */
public class Note {
    private final String title;
    private final String content;
    private final Date date;

    /**
     * конструктор для заметки.
     *
     * @param title заголовок заметки
     * @param content содержание заметки
     */
    Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = new Date();
    }

    /**
     * получить время текущей заметки.
     *
     * @return время
     */
    public Date getDate() {
        return date;
    }

    /**
     * вернуть заголовок заметки.
     *
     * @return заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * метод для преобразования к строке.
     *
     * @return строку
     */
    @Override
    public String toString() {
        return "Title: " + this.title
                + "\ncontent: " + this.content
                + "\ndate: " + this.date + "\n---------------";
    }
}
