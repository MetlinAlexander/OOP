import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.fail;

public class SubTests {

    @Test
    void sampleTest() {
        ArrayList<Integer> realAnswer = new ArrayList<>(Arrays.asList(1, 8));
        try {
            ArrayList<Integer> answer = SubFinder.find("src/test/java/textfile.txt", "bra");
            Assertions.assertEquals(realAnswer, answer);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    void bigTest() {
        ArrayList<Integer> realAnswer = new ArrayList<>(Arrays.asList(1381, 24270, 32449, 33205, 34820, 38215, 38907, 43183, 51187, 53293, 60978, 75115, 98199, 108590, 110625, 117638));
        try {
            ArrayList<Integer> answer = SubFinder.find("src/test/java/24.txt", "ftw");
            Assertions.assertEquals(realAnswer, answer);
        } catch (Exception ex) {
            fail();
        }
    }
}
