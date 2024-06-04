package metlin;

import metlin.utils.Primes;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimesTest {
    public static boolean result;

    @Test
    public void testIsPrime1() {
        assertTrue(Primes.isPrime(7919));
    }

    @Test
    public void testIsPrime2() {
        assertFalse(Primes.isPrime(7900));
    }

    @Test
    public void testIsPrime3() {
        assertTrue(Primes.isPrime(952681));
    }

    @Test
    public void testIsPrime4() {
        assertFalse(Primes.isPrime(950000230));
    }

    @Test
    public void test2() throws InterruptedException {
        result = false;
        ArrayList<Integer> serverData = new ArrayList<>();
        Collections.addAll(serverData, 120, 1234, 432552, 1234554, 99999, 952681);
        Thread serverThread = new Thread(new ServThread(serverData));
        serverThread.start();
        new Thread(new ClientThread()).start();
        new Thread(new ClientThread()).start();
        serverThread.join();
        assertTrue(result);
    }
}