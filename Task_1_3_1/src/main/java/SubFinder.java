import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class SubFinder {
    /**
     * метод реализующий поиск всех подстрок в данной строке
     * а также определяющий индекс начала каждого вхождения заданной подстроки.
     *
     * @param fileName - имя файла из которого будет браться строка.
     * @param subString - подстрока которую нужно найти.
     * @return - ArrayList содержащий индекс начал всех вхождений данной подстроки.
     * @throws Exception - возвращает исключение если файл не был найден.
     */
    public static ArrayList<Integer> find(String fileName, String subString) throws Exception {
        ArrayList<Integer> subIndex = new ArrayList<>();
        int buffCapacity = Math.max(2, subString.length() * 2);
        char[] myBuffer = new char[buffCapacity];
        char[] toCopy = new char[buffCapacity - subString.length() + 1];
        try (FileReader reader = new FileReader(fileName)) {
            int wasScanned = reader.read(myBuffer);
            // выходим если закончилась строка
            if (wasScanned == -1) {
                return subIndex;
            }
            boolean isFind;
            int curIndex = 0;
            while (true) {
                // ищем подстроку
                for (int i = 0; i < wasScanned - subString.length() + 1; i++) {
                    // начинаем искать место где подстрока начинается
                    if (myBuffer[i] == subString.charAt(0)) {
                        isFind = true;
                        for (int j = i; j < i + subString.length(); j++) {
                            if (myBuffer[j] != subString.charAt(j - i)) {
                                isFind = false;
                                break;
                            }
                        }
                        if (isFind) {
                            subIndex.add(curIndex);
                        }
                    }
                    curIndex += 1;
                }
                // делаем пересос, чтоб проверить вторую половину буфера
                System.arraycopy(myBuffer, wasScanned - subString.length() + 1, myBuffer, 0, subString.length() - 1);
                // Считываем еще кусок
                wasScanned = reader.read(toCopy);
                // Если считывать нечего выходим
                if (wasScanned == -1) {
                    break;
                }
                // Копируем считанное в конец буфера
                System.arraycopy(toCopy, 0, myBuffer, subString.length() - 1, wasScanned);
                // нужно добавить то что мы не обработали с прошлого раза
                wasScanned += subString.length() - 1;
            }
        } catch (IOException ex) {
            throw new FileNotFoundException("Can't find file");
        }
        return subIndex;
    }
}
