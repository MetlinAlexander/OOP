//import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class MainTest {
    @Test
    void heapsort1() {
        int[] ans = Main.heapsort(new int[] {1, 3, 2, 7, 6});
        Assertions.assertArrayEquals(new int[] {1, 2, 3, 6, 7}, ans);
    }
    @Test
    void heapsort2() {
        int[] ans = Main.heapsort(new int[] {24, 31, 15, 20, 52, 6});
        Assertions.assertArrayEquals(new int[] {6, 15, 20, 24, 31, 52}, ans);
    }
    @Test
    void heapsort_empty() {
        int[] ans = Main.heapsort(new int[] {});
        Assertions.assertArrayEquals(new int[] {}, ans);
    }
    @Test
    void heapsort_one_element() {
        int[] ans = Main.heapsort(new int[] {1});
        Assertions.assertArrayEquals(new int[] {1}, ans);
    }
    @Test
    void heapsort_negative() {
        int[] ans = Main.heapsort(new int[] {10, -1, 7, 0, -1000, 347, -500});
        Assertions.assertArrayEquals(new int[] {-1000, -500, -1, 0, 7, 10, 347}, ans);
    }
    @Test
    void heapsort_big_data() {
        int[] testArray;
        testArray = new int[10000];
        for (int i=0; i<10000; i++) {
            testArray[i] = ThreadLocalRandom.current().nextInt();
        }
        int[] ans = Main.heapsort(testArray);
        Assertions.assertArrayEquals(testArray, ans);
    }
}
