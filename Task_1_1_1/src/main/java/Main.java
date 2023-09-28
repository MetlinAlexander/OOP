class Main {

    /**
     * Функция выполняющая просеивание через кучу.
     * @param numbers массвив Int, который нужно отсортировать.
     * @param root индекс корневого узла в куче.
     * @param bottom индекс последнего узла в куче.
     */

    private static void siftDown(int[] numbers, int root, int bottom) {
        int maxChild;

        while ((root * 2 <= bottom)) {
            if (root * 2 == bottom) {
                maxChild = root * 2;
            } else if (numbers[root * 2] > numbers[root * 2 + 1]) {
                maxChild = root * 2;
            } else {
                maxChild = root * 2 + 1;
            }
            if (numbers[root] < numbers[maxChild]) {
                int temp = numbers[root];
                numbers[root] = numbers[maxChild];
                numbers[maxChild] = temp;
                root = maxChild;
            } else {
                break;
            }
        }
    }

    /**
     * Функция выполняющая Heapsort данного массива array.
     * @param array - массив, который нужно отсортировать.
     */
    public static void heapsort(int[] array) {
        int size = array.length;
        if (size == 0) {
            return;
        }
        for (int i = (size / 2); i >= 0; i--) {
            siftDown(array, i, size - 1);
        }
        for (int i = size - 1; i >= 1; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            siftDown(array, 0, i - 1);
        }
    }
}


