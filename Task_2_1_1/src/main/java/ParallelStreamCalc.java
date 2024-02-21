import java.util.Arrays;

/**
 * класс в котором реализована проверка массива с помощью parallesStream.
 */
public class ParallelStreamCalc {

    /**
     * проверка массива на вхождение НЕпростого числа с помозью ParallelStream.
     *
     * @param arr array to check
     * @return true if consists not Prime
     */
    public static boolean consistNotPrime(final long[] arr) {
        return Arrays.stream(arr).parallel()
                .anyMatch(num -> !Primes.isPrime(num));
    }
}
