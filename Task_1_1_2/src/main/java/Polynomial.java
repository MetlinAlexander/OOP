//import java.util.Arrays;
//import java.util.Map;

import java.util.Objects;

/**
 * класс реализующий объектное представление многочлена степени n.
 * и методы для работы с ним
 */
public class Polynomial {

    private int[] coef;
    private int size;

    public static void main(String[] args) {
        System.out.print("Hello\n");
    }

    /**
     * конструктор для создания многочлена с заданными коэффициентами.
     *
     * @param coef - массив коэффицентов
     */
    public Polynomial(int[] coef) {
        if (coef.length == 0) {
            System.out.println("Polynomial will be equals to 0.");
            this.size = 1;
            this.coef = new int[] {0};
        } else {
            this.size = coef.length;
            this.coef = new int[this.size];
            for (int i = 0; i < this.size; i++) {
                this.coef[i] = coef[i];
            }
        }
    }

    /**
     * метод для сложения двух полиномов.
     *
     * @param b - второй полином для сложения
     * @return сумму полиномов
     */
    public Polynomial plus(Polynomial b) {
        int newlen = Math.max(this.size, b.size);
        int[] newArr = new int[newlen];
        for (int i = 0; i < newlen; i++) {
            newArr[i] = 0;
            if (this.size > i) {
                newArr[i] += this.coef[i];
            }
            if (b.size > i) {
                newArr[i] += b.coef[i];
            }
        }
        return new Polynomial(newArr);
    }

    /**
     * метод для вычитания двух полиномов.
     *
     * @param b - вычитаемое
     * @return разность полиномов
     */
    public Polynomial minus(Polynomial b) {
        int newlen = Math.max(this.size, b.size);
        int[] newArr = new int[newlen];
        for (int i = 0; i < newlen; i++) {
            newArr[i] = 0;
            if (this.size > i) {
                newArr[i] += this.coef[i];
            }
            if (b.size > i) {
                newArr[i] -= b.coef[i];
            }
        }
        return new Polynomial(newArr);
    }

    /**
     * функция выполняющая умножение двух полиномов.
     *
     * @param b - второй множитель
     * @return произведение полиномов
     */
    public Polynomial times(Polynomial b) {
        // ищем максимальный индекс с ненулевым значением в полиноме а
        int maxDegA = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.coef[i] != 0) {
                maxDegA = i;
            }
        }
        // ищем максимальный индекс с ненулевым значением в полиноме b
        int maxDegB = 0;
        for (int i = 0; i < b.size; i++) {
            if (b.coef[i] != 0) {
                maxDegB = i;
            }
        }
        // создаем новый полином с произведением
        int newlen = maxDegA + maxDegB + 1;
        int[] newArr = new int[newlen];
        for (int i = 0; i < newlen; i++) {
            newArr[i] = 0;
        }
        // выполняем умножение
        for (int i = 0; i < maxDegA + 1; i++) {
            for (int j = 0; j < maxDegB + 1; j++) {
                newArr[i + j] += this.coef[i] * b.coef[j];
            }
        }
        return new Polynomial(newArr);
    }

    public static int pow(int value, int powValue) {
        int result = 1;
        for (int i = 1; i <= powValue; i++) {
            result = result * value;
        }
        return result;
    }

    /**
     * функция реализующая вычисление значения в точке.
     *
     * @param x - значения переменной
     * @return вычисленное значение
     */
    public int evaluate(int x) {
         int answer = this.coef[0];
         for (int i = 1; i < this.size; i++) {
             answer += this.coef[i] * pow(x, i);
         }
         return answer;
    }

    /**
     * функция реализующая взятие i’ой производной.
     *
     * @param order - степень производной
     * @return продифференцируемый полином
     */
    public Polynomial differentiate(int order) {
        int newlen = this.size - order;
        int[] newArr = new int[newlen];
        int tempcoef;
        int tempdeg;
        for (int i = 0; i < newlen; i++) {
            tempcoef = this.coef[this.size - 1 - i];
            tempdeg = this.size - 1 - i;
            for (int j = 0; j < order; j++) {
                tempcoef *= tempdeg;
                tempdeg -= 1;
            }
            newArr[tempdeg] = tempcoef;
        }
        return new Polynomial(newArr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coef) * Objects.hashCode(size);
    }

    /**
     * реализицая сравнения двух полиномов.
     *
     * @param obj второй обьект для сравнения
     * @return true/false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Polynomial)) {
            return false;
        }
        Polynomial b = (Polynomial) obj;
        // ищем максимальный индекс с ненулевым значением в полиноме а
        int maxDegA = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.coef[i] != 0) {
                maxDegA = i;
            }
        }
        // ищем максимальный индекс с ненулевым значением в полиноме b
        int maxDegB = 0;
        for (int i = 0; i < b.size; i++) {
            if (b.coef[i] != 0) {
                maxDegB = i;
            }
        }
        // сравниеваем
        if (maxDegB != maxDegA) {
            return false;
        }

        for (int i = 0; i < maxDegA; i++) {
            if (this.coef[i] != b.coef[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * функция получение строкового представления.
     *
     * @return строку вида 4x^3 + 3x^2 + 6x + 7
     */
    @Override
    public String toString() {
        String polistring = "";
        for (int i = size - 1; i >= 0; i--) {
            if (this.coef[i] == 0) {
                continue;
            }
            if (polistring != "") {
                if (this.coef[i] > 0) {
                    polistring = polistring + " + ";
                } else {
                    polistring = polistring + " - ";
                }
            }
            if (this.coef[i] != 1 & i != 0) {
                if (this.coef[i] > 0 | (size - 1 == i)) {
                    polistring = polistring + this.coef[i];
                } else {
                    polistring = polistring + (this.coef[i] * (-1));
                }
            }
            if (i == size - 1) {
                polistring = polistring + "x^" + (i);
            } else if (i == 1) {
                polistring = polistring  + "x";
            } else if (i == 0) {
                if (this.coef[i] > 0) {
                    polistring = polistring + this.coef[i];
                } else {
                    polistring = polistring + (this.coef[i] * (-1));
                }
            } else {
                polistring = polistring + "x^" + (i);
            }
        }
        return polistring;
    }
}
