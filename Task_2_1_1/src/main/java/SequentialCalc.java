/**
 * класс в котором реализована последовательная проверка массива.
 */
public class SequentialCalc {

    /**
     * последователное нахождение НЕпростого числа в данном массиве.
     *
     * @param arr array to check
     * @return true if consist not Prime
     */
    public static boolean consistNotPrime(final long[] arr) {
        for (long j : arr) {
            if (!Primes.isPrime(j)) {
                return true;
            }
        }
        return false;
    }
}
