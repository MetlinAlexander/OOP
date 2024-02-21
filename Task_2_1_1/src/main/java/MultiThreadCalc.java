import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * класс в котором реализована проверка массива с помощью нескольких потоков.
 */
public class MultiThreadCalc {

    /**
     * проверка массива на вхождение НЕпростого числа с помощью.
     * нескольких потоков.
     *
     * @param arr array to check.
     * @param threadsValue количество потоков
     * @return true if consists Not prime
     * @throws InterruptedException
     */
    public static boolean consistNotPrime(final long[] arr,
                                          final int threadsValue)
            throws InterruptedException {
        AtomicInteger curIndex = new AtomicInteger(0);
        AtomicBoolean result = new AtomicBoolean(false);
        Thread[] threads = new Thread[threadsValue];

        for (int i = 0; i < threadsValue; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    int cur = curIndex.getAndIncrement();
                    if (cur >= arr.length) {
                        return;
                    }
                    if (!Primes.isPrime(arr[cur])) {
                        result.set(true);
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < threadsValue; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result.get();
    }

}
