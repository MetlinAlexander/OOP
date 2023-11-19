import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MainTest {
    private static GradeBook goodStudent = new GradeBook();

    @BeforeAll
    static void setUp() {
        Semester sem1 = new Semester(1);
        Subject peLesson = new Subject("PE", Grade.FOUR);
        Subject imperative = new Subject("imperative", Grade.FIFE);
        Subject haskell = new Subject("haskell", Grade.FIFE);
        sem1.addSubject(peLesson);
        sem1.addSubject(imperative);
        sem1.addSubject(haskell);

        Semester sem2 = new Semester(2);
        Subject peLesson2 = new Subject("PE", Grade.FIFE);
        Subject math = new Subject("math", Grade.FOUR);
        sem2.addSubject(peLesson2);
        sem2.addSubject(math);

        goodStudent.addSemester(sem1);
        goodStudent.addSemester(sem2);

        goodStudent.setQualificationWork(Grade.FIFE);
    }

    @Test
    void testAverage() {
        Assertions.assertEquals(4.583333333333334, goodStudent.getAverageMark());
    }

    @Test
    void testStipend() {
        assert(goodStudent.isStipendPossible());
    }

    @Test
    void testRedDiploma() {
        assert(goodStudent.isRedDiplomPossible());
    }
}