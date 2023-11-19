import java.util.ArrayList;
import java.util.HashMap;

/**
 * класс реализиющий зачетную книжку.
 */
public class GradeBook {

    private Grade qualificationWork;
    private ArrayList<Semester> allSemesters = new ArrayList<>();
    private int lastSemester = 0;

    /**
     * метод для добавления нового семестра.
     *
     * @param sem семестр для добавления
     */
    public void addSemester(Semester sem) {
        allSemesters.add(sem);
        lastSemester += 1;
    }

    /**
     * добавить оценку для дипломной работы.
     *
     * @param grade - оценка за работу
     */
    public void setQualificationWork(Grade grade) {
        this.qualificationWork = grade;
    }

    /**
     * получение текущего среднего балла за все время обучения.
     *
     * @return средний балл
     */
    public double getAverageMark() {
        double ans = allSemesters.stream().
                mapToDouble(x -> x.getAverage()).
                average().getAsDouble();
        return ans;
    }

    /**
     * проверка на возможность получения красного диплома.
     *
     * @return true/false
     */
    public boolean isRedDiplomPossible() {
        HashMap<String, Grade> lastGrades = new HashMap<>();
        for (int i = 0; i < lastSemester; i++) {
            allSemesters.get(i).getSubjArr().
                    stream().
                    forEach(x -> lastGrades.
                            put(x.getSubjectName(), x.getGrades()));
        }
        int fifes = 0;
        int fours = 0;
        for (HashMap.Entry<String, Grade> e : lastGrades.entrySet()) {
            if (e.getValue() == Grade.FIFE) {
                fifes += 1;
            } else if (e.getValue() == Grade.FOUR) {
                fours += 1;
            }
        }

        boolean noThree = allSemesters.stream().
                allMatch(Semester::isStipendPossible);

        return (noThree
                &
                (qualificationWork == Grade.FIFE)
                &
                ((double) fifes / (fours + fifes)) >= 0.75
                );
    }

    /**
     * возможности получения повышенной стипендии в этом семестре.
     *
     * @return true/false
     */
    public boolean isStipendPossible() {
        return allSemesters.get(lastSemester - 1).isStipendPossible();
    }
}
