import java.util.ArrayList;
import java.util.Date;

/**
 * класс реализующий обьект типа записная книжка
 */
public class NoteBook {

    private ArrayList<Note> allNotes;

    NoteBook() {
        this.allNotes = new ArrayList<>();
    }

    public void addNote(String title, String content) {
        allNotes.add(new Note(title, content));
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    public void deleteNote(String title) {
        this.allNotes.removeIf(x -> x.getTitle().equals(title));
    }

    public void showNotes() {
        for (int i = 0; i < allNotes.size(); i++) {
            System.out.println(allNotes.get(i));
        }
    }

    public void showSortNotes(Date date1, Date date2, ArrayList<String> words) {
        ArrayList<Note> notSorted = new ArrayList<>();
        for (int i = 0; i < allNotes.size(); i++) {
            if (words.contains(allNotes.get(i).getTitle())) {
                if (allNotes.get(i).getDate().after(date1)
                        && allNotes.get(i).getDate().before(date2)) {
                    System.out.println(allNotes.get(i));
                }
            }
        }
    }
}
