import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
ParallelStream: 421
MultiThread2: 473
MultiThread3: 387
MultiThread4: 422
MultiThread5: 353
MultiThread6: 301
Sequential: 643
 */

class MainTest {

    final long[] array1 = new long[]{6, 8, 7, 13, 5, 9, 4};
    final long[] array2 = new long[]{
            20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
            6998009, 6998029, 6998039, 20165149, 6998051, 6998053,
            1000000007, 1000000007, 1000000009, 1000000009,
            10000000469L, 10000000501L, 10000000537L, 10000000583L,
            10000000589L, 10000000597L, 10000000601L, 10000000631L,
            10000000643L, 10000000649L, 10000000667L, 10000000679L,
            10000000469L, 10000000501L, 10000000537L, 10000000583L,
            10000000589L, 10000000597L, 10000000601L, 10000000631L,
            10000000643L, 10000000649L, 10000000667L, 10000000679L
    };

    @Test
    public void testSequentialArray1() {
        assertTrue(SequentialCalc.consistNotPrime(array1));
    }

    @Test
    public void testSequentialWithTime() {
        long begin;
        long end;
        boolean res = true;
        begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            res = SequentialCalc.consistNotPrime(array2);
        }
        end = System.currentTimeMillis();
        System.out.println("Sequential: " + (end - begin));
        assertFalse(res);
    }

    @Test
    public void testParallelStreamArray1() {
        assertTrue(ParallelStreamCalc.consistNotPrime(array1));
    }

    @Test
    public void testParallelStreamWithTime() {
        long begin;
        long end;
        boolean res = true;
        begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            res = ParallelStreamCalc.consistNotPrime(array2);
        }
        end = System.currentTimeMillis();
        System.out.println("ParallelStream: " + (end - begin));
        assertFalse(res);
    }

    @Test
    public void testMultiThreadArray1() throws InterruptedException {
        assertTrue(MultiThreadCalc.consistNotPrime(array1, 3));

    }

    @Test
    public void testMultiThreadWithTime() throws InterruptedException {
        long begin;
        long end;
        boolean res = true;
        for (int cnThread = 2; cnThread <= 6; cnThread++) {
            begin = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                res = MultiThreadCalc.consistNotPrime(array2, cnThread);
            }
            end = System.currentTimeMillis();
            System.out.println("MultiThread" + cnThread + ": " + (end - begin));
        }
        assertFalse(res);
    }
}