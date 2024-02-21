public class SequentialCalc {

    public static boolean consistNotPrime(int[] arr) {
        for (int j : arr) {
            if (!Primes.isPrime(j)) {
                return true;
            }
        }
        return false;
    }

}
