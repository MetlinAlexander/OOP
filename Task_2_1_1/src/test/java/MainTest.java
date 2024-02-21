import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
последовательное вычисление: 1300

 */

class MainTest {

    final int[] array1 = new int[]{6, 8, 7, 13, 5, 9, 4};
    final int[] array2 = new int[]{
            5, 53, 157, 173, 211, 257,
            263, 373, 563, 593, 607,
            653, 733, 947, 977, 1103,
            1123, 1187, 1223, 1367,
            1511, 1747, 1753, 1907,
            2287, 2417, 2677, 2903,
            2963, 3307, 3313, 3637,
            3733, 4013, 4409, 4457,
            4597, 4657, 4691, 4993,
            5107, 5113, 5303, 5387,
            5393, 29084449};

    @Test
    public void testSequentialArray1() {
        assertTrue(SequentialCalc.consistNotPrime(array1));
    }

    @Test
    public void testSequentialWithTime() {
        long begin, end;
        boolean res = true;
        begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++){
            res = SequentialCalc.consistNotPrime(array2);
        }
        end = System.currentTimeMillis();
        System.out.println("Sequential: " + (end - begin));
        assertTrue(res);
    }


}