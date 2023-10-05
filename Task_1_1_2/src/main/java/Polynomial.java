import java.util.Arrays;
import java.util.Map;

public class Polynomial {

    private int[] coef;
    private int size;


    public static void main(String[] args) {
        System.out.print("Hello\n");
        Polynomial p1 = new Polynomial(new int[] {4, 3, 6, 7});
//        System.out.print(p1.toString());
//        System.out.println(p1.plus(p2.differentiate(1)).toString());
        Polynomial p2 = new Polynomial(new int[] {2, 16});
        System.out.println(p1.plus(p2).toString());
    }

//    создание многочлена с заданными коэффициентами;
    public Polynomial(int[] coef) {
        System.out.print(Arrays.toString(coef) + "\n");
        if (coef.length == 0) {
            System.out.println("Polynomial will be equals to 0.");
            this.size = 1;
            this.coef = new int[] {0};
        } else {
            this.size = coef.length;
            this.coef = new int[this.size];
            for (int i = 0; i<this.size; i++) {
                this.coef[i] = coef[i];
            }
        }
    }
//    сложение, вычитание, умножение на другой многочлен;
    public Polynomial plus(Polynomial b) {
        int new_len = Math.max(this.size, b.size);
        int [] newArr = new int[new_len];
        for (int i = 0; i<new_len; i++) {
            newArr[i] = 0;
            if(this.size > i) {
                newArr[i] += this.coef[i];
            }
            if(b.size > i) {
                newArr[i] += b.coef[i];
            }
            System.out.print(newArr[i] + "\n");
        }
        return new Polynomial(newArr);
    }




//    вычисление значения в точке;


//    взятие i’ой производной;
//    public Polynomial differentiate(int order) {
//
//    }


//    сравнение на равенство с другим многочленом;


//    получение строкового представления. 4x^3 + 3x^2 + 6x + 7
    @Override
    public String toString() {
        String polistring = "";
//        int size = this.coef.length;
        for(int i =size-1; i>=0; i-- ) {
            if(this.coef[i] == 0) {
                continue;
            }
            if (polistring != ""){
                polistring = polistring + " + ";
            }
            if (this.coef[i] != 1 & i != 0) {
                polistring = polistring + this.coef[i];
            }
            if (i == size - 1) {
                polistring = polistring + "x^" + (i);
            } else if (i == 1) {
                polistring = polistring  + "x";
            } else if (i == 0) {
                polistring = polistring + this.coef[i];
            } else {
                polistring = polistring + "x^" + (i);
            }

        }
        return polistring;
    }
}
