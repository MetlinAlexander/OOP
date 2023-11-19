import java.util.ArrayList;

public class Semester {

    private int term;

    private ArrayList<Subject> subjArr;

    Semester(int term) {
        this.term = term;
        this.subjArr = new ArrayList<>();
    }

    /**
     * добавить предмет.
     *
     * @param subject предмет для добавления
     */
    public void addSubject(Subject subject) {
        this.subjArr.add(subject);
    }

    /**
     * получить список всех предметов.
     *
     * @return список предметов
     */
    public ArrayList<Subject> getSubjArr() {
        return subjArr;
    }

    /**
     * проверка на возможность получения стипендии в этом семестре.
     *
     * @return true/false
     */
    public boolean isStipendPossible() {
        return this.subjArr.stream().noneMatch(x -> x.getGrades().value < 4);
    }

    /**
     * получение средней оценки за данный семестр.
     *
     * @return средний балл
     */
    public double getAverage() {
        double ans = this.subjArr.stream()
                .mapToInt(x -> x.getGrades().value)
                .average().getAsDouble();
        return ans;
    }
}
