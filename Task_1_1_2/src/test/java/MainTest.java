//import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void simpleTest1(){
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[] {3, 2, 8});
        Assertions.assertEquals("7x^3 + 6x^2 + 19x + 6", p1.plus(p2.differentiate(1)).toString());
    }

    @Test
    void printtest1() {
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Assertions.assertEquals("7x^3 + 6x^2 + 3x + 4", p1.toString());
    }

    @Test
    void printtestZeroandOne() {
        Polynomial p1 = new Polynomial(new int[] {0, 3, 6, 7, 0, 1});
        Assertions.assertEquals("x^5 + 7x^3 + 6x^2 + 3x", p1.toString());
    }

    @Test
    void plus1test() {
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[] {2, 16});
        Assertions.assertEquals("7x^3 + 6x^2 + 19x + 6", p1.plus(p2).toString());
    }

    @Test
    void minus1test() {
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[] {2, 16});
        Assertions.assertEquals("7x^3 + 6x^2 - 13x + 2", p1.minus(p2).toString());
    }

    @Test
    void evaluatetest1() {
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Assertions.assertEquals(90, p1.evaluate(2));
    }
}