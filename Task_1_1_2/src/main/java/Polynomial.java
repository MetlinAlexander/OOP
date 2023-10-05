import java.util.Arrays;

public class Polynomial {

    private int[] coef;

    public static void main(String[] args) {
        System.out.print("Hello\n");
        Polynomial p1 = new Polynomial(new int[] {0, 3, 1, 0, 1, 4, 7, 0});
        System.out.print(p1);
    }

//    создание многочлена с заданными коэффициентами;
    public Polynomial(int[] coef) {
        // TODO: 05-Oct-23 Нет проверки входных данных при создании полинома,
        //  например, что массив коэффициентов непуст
        this.coef = coef;
    }
//    сложение, вычитание, умножение на другой многочлен;
//    вычисление значения в точке;
//    взятие i’ой производной;
//    сравнение на равенство с другим многочленом;


//    получение строкового представления. 4x^3 + 3x^2 + 6x + 7
    @Override
    public String toString() {
        String polistring = "";
        int size = this.coef.length;
        for(int i =size-1; i>=0; i-- ) {
            if(this.coef[size - i - 1] == 0) {
                continue;
            }
            if (polistring != ""){
                polistring = polistring + " + ";
            }
            if (this.coef[size - i - 1] != 1 & i != 0) {
                polistring = polistring + this.coef[size - i - 1];
            }
            if (i == size - 1) {
                polistring = "x^" + (i);
            } else if (i == 1) {
                polistring = polistring  + "x";
            } else if (i == 0) {
                polistring = polistring + this.coef[size - i - 1];
            } else {
                polistring = polistring + "x^" + (i);
            }

        }
        return polistring;
    }
}
