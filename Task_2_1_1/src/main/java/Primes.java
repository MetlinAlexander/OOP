/**
 * Utility class that contains a function for defining a prime number.
 */
public final class Primes {

    /**
     * private constructor for utility class.
     */
    private Primes() { }

    /**
     * a function for determining the simplicity of a number.
     * A simple algorithm is used to check up to the square of the number
     * with the running time O(sqrt(N)).
     *
     * @param number - number to check
     * @return true if number is prime
     */
    public static boolean isPrime(final int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
