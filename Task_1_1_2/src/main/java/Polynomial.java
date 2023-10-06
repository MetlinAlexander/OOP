import java.util.Arrays;
import java.util.Map;

/**
 * класс реализующий объектное представление многочлена степени n.
 * и методы для работы с ним
 */
public class Polynomial {

    private int[] coef;
    private int size;

    public static void main(String[] args) {
        System.out.print("Hello\n");
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
        Polynomial p2 = new Polynomial(new int[] {3, 2, 8});
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.plus(p2.differentiate(1)).toString());
        System.out.println(p1.times(p2).evaluate(2));
    }

    /**
     * конструктор для создания многочлена с заданными коэффициентами.
     * @param coef - массив коэффицентов
     */
    public Polynomial(int[] coef) {
//        System.out.print(Arrays.toString(coef) + "\n");
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
//    сложение, вычитание, умножение на другой многочлен;

    /**
     * метод для сложения двух полиномов.
     * @param b - второй полином для сложения
     * @return сумму полиномов
     */
    public Polynomial plus(Polynomial b) {
        int new_len = Math.max(this.size, b.size);
        int[] newArr = new int[new_len];
        for (int i = 0; i < new_len; i++) {
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
     * @param b - вычитаемое
     * @return разность полиномов
     */
    public Polynomial minus(Polynomial b) {
        int new_len = Math.max(this.size, b.size);
        int[] newArr = new int[new_len];
        for (int i = 0; i < new_len; i++) {
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

    public Polynomial times(Polynomial b) {
        // ищем максимальный индекс с ненулевым значением в полиноме а
        int max_deg_a = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.coef[i] != 0) {
                max_deg_a = i;
            }
        }
        // ищем максимальный индекс с ненулевым значением в полиноме b
        int max_deg_b = 0;
        for (int i = 0; i < b.size; i++) {
            if (b.coef[i] != 0) {
                max_deg_b = i;
            }
        }
        // создаем новый полином с произведением
        int new_len = max_deg_a + max_deg_b + 1;
        int[] newArr = new int[new_len];
        for (int i = 0; i<new_len; i++) {
            newArr[i] = 0;
        }
        // выполняем умножение
        for (int i = 0; i < max_deg_a+1; i++) {
            for (int j = 0; j < max_deg_b+1; j++) {
                newArr[i+j] += this.coef[i] * b.coef[j];
            }
//            System.out.print(Arrays.toString(newArr));
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
     * @param order - степень производной
     * @return продифференцируемый полином
     */
    public Polynomial differentiate(int order) {
        int new_len = this.size - order;
        int[] newArr = new int[new_len];
        int temp_coef;
        int temp_deg;
        for (int i = 0; i < new_len; i++) {
            temp_coef = this.coef[this.size - 1 - i];
            temp_deg = this.size - 1 - i;
            for (int j = 0; j < order; j++) {
                temp_coef *= temp_deg;
                temp_deg -= 1;
            }
            newArr[temp_deg] = temp_coef;
        }
        return new Polynomial(newArr);
    }


//    сравнение на равенство с другим многочленом;

    /**
     * функция получение строкового представления.
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
