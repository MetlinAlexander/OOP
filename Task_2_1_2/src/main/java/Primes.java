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
    public static boolean isPrime(final long number) {
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        for (long i = 10000000469L; i < 10000111469L; i++){
//            if(isPrime(i)){
//                System.out.print(i + "L, ");
//            }
//        }
//    }
}