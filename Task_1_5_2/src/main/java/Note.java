import java.util.Date;

/**
 * класс реализующий обьект типа заметка
 */
public class Note {
    private final String title;
    private final String content;
    private final Date date;

    Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Title: " + this.title
                + "\ncontent: " + this.content
                + "\ndate: " + this.date + "\n---------------";
    }
}
