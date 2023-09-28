//import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void heapsort1() {
        int[] ans = {1, 3, 2, 7, 6};
        Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {1, 2, 3, 6, 7}, ans);
    }

    @Test
    void heapsort2() {
        int[] ans = {24, 31, 15, 20, 52, 6};
        Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {6, 15, 20, 24, 31, 52}, ans);
    }

    @Test
    void heapsort_empty() {
        int[] ans = {};
        Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {}, ans);
    }

    @Test
    void heapsort_one_element() {
        int[] ans = {1};
        Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {1}, ans);
    }

    @Test
    void heapsort_negative() {
        int[] ans = {10, -1, 7, 0, -1000, 347, -500};
        Main.heapsort(ans);
        Assertions.assertArrayEquals(new int[] {-1000, -500, -1, 0, 7, 10, 347}, ans);
    }

    @Test
    void heapsort_big_data() {
        int[] testArray;
        testArray = new int[10000];
        for (int i = 0; i < 10000; i++) {
            testArray[i] = ThreadLocalRandom.current().nextInt();
        }
        Main.heapsort(testArray);
        for (int i = 0; i < 9999; i++) {
            if (testArray[i]>testArray[i+1]) {
                Assertions.fail();
            }
        }
    }
}
