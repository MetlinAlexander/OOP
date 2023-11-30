import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalcTests {

    @Test
    void sampleTest(){
        String expression = "sin + - 1 2 1";
        double expectAns = 0;
        try {
            double ans = Calculator.evaluator(expression);
            assertTrue(Math.abs(ans - expectAns) <= 0.0001);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void harderTest(){
        String expression = "pow 3 log 11 sqrt * 13 sin + 1 cos 0";
        double expectAns = 1.760843;
        try {
            double ans = Calculator.evaluator(expression);
            assertTrue(Math.abs(ans - expectAns) <= 0.0001);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void exceptionTest1(){
        String expression = "1 sin + - 1 2 1";
        assertThrows(WrongPolishNotationException.class, () -> {
            Calculator.evaluator(expression);
        });
    }

    @Test
    void exceptionTest2(){
        String expression = "sin a";
        assertThrows(WrongFunctionArgumentException.class, () -> {
            Calculator.evaluator(expression);
        });
    }
}
