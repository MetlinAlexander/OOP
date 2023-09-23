/**
 * Класс выполняющий Heapsort
 */
public class Main {
    /**
     * Функция выполняющая просеивание через кучу
     * @param numbers - массвив Int, который нужно отсортировать
     * @param root - индекс корневого узла в куче
     * @param bottom - индекс последнего узла в куче
     */
    private static void siftDown(int[] numbers, int root, int bottom)
    {
        int maxChild;
        int done = 0;

        while ((root * 2 <= bottom) && (done==0)) {
            if (root * 2 == bottom)
                maxChild = root * 2;

            else if (numbers[root * 2] > numbers[root * 2 + 1])
                maxChild = root * 2;
            else
                maxChild = root * 2 + 1;

            if (numbers[root] < numbers[maxChild])
            {
                int temp = numbers[root];
                numbers[root] = numbers[maxChild];
                numbers[maxChild] = temp;
                root = maxChild;
            }
            else done = 1;
        }
    }

    /**
     * Функция выполняющая Heapsort данного массива array
     * @param array - массив, который нужно отсортировать
     * @return возвращает отсортированный массив
     */
    public static int[] heapsort(int[] array){
        int size = array.length;
        if(size==0) return array;
        for(int i = (size / 2); i >= 0; i--){
            siftDown(array, i, size - 1);
        }
        for (int i = size - 1; i >= 1; i--)
        {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            siftDown(array, 0, i - 1);
        }
        return array;
    }
}


