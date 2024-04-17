import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimesTest {

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
}