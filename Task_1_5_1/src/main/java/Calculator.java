import java.util.Stack;

/**
 * класс реализующий вычисление выражений записанных в префиксной форме.
 */
public class Calculator {

    /**
     * метод который вытаскивает из стека два элемента.
     *
     * @param numStack стек
     * @return массив из двух чисел
     */
    private static double[] pop2ToDouble(Stack<Double> numStack) {
        double[] numbers = new double[2];
        numbers[0] = numStack.pop();
        numbers[1] = numStack.pop();
        return numbers;
    }

    /**
     * метод возвращаюший из стека 1 значение.
     *
     * @param numStack стек
     * @return число
     */
    private static double pop1ToDouble(Stack<Double> numStack) {
        return numStack.pop();
    }

    /**
     * метод который по заданной строке вычисляет значение выражения,
     * записанного в префиксной форме.
     *
     * @param expression - выражение в префиксной форме
     * @return - вычисленной выражение
     * @throws WrongPolishNotationException - исключение если
     * что-то ни так в префксной записи
     * @throws WrongFunctionArgumentException - исключение если
     * что-то ни так в аргументах функции
     */
    public static double evaluator(String expression)
            throws WrongPolishNotationException,
            WrongFunctionArgumentException {
        double ans;
        String[] atoms = expression.split(" ");
        Stack<Double> numStack = new Stack<>();
        double[] nums;
        for (int i = atoms.length - 1; i >= 0; i--) {
            // сначала пытаемся понять является ли текущий
            // атом какой-то операцией
            //(+, -,*, /) (log, pow, sqrt, sin, cos).
            switch (atoms[i]) {
                case "+":
                    nums = pop2ToDouble(numStack);
                    numStack.push(nums[0] + nums[1]);
                    break;
                case "-":
                    nums = pop2ToDouble(numStack);
                    numStack.push(nums[0] - nums[1]);
                    break;
                case "*":
                    nums = pop2ToDouble(numStack);
                    numStack.push(nums[0] * nums[1]);
                    break;
                case "/":
                    nums = pop2ToDouble(numStack);
                    numStack.push(nums[0] / nums[1]);
                    break;
                case "log":
                    nums = pop2ToDouble(numStack);
                    numStack.push(Math.log(nums[1]) / Math.log(nums[0]));
                    break;
                case "pow":
                    nums = pop2ToDouble(numStack);
                    numStack.push(Math.pow(nums[0], nums[1]));
                    break;
                case "sqrt":
                    numStack.push(Math.sqrt(pop1ToDouble(numStack)));
                    break;
                case "sin":
                    numStack.push(Math.sin(pop1ToDouble(numStack)));
                    break;
                case "cos":
                    numStack.push(Math.cos(pop1ToDouble(numStack)));
                    break;
                // если не подошла не одна операция,
                // значит это число, либо выкидываем exception
                default:
                    try {
                        numStack.push(Double.parseDouble(atoms[i]));
                    } catch (Exception ex) {
                        throw new WrongFunctionArgumentException();
                    }
            }
        }
        ans = numStack.pop();
        if (!numStack.isEmpty()) {
            throw new WrongPolishNotationException();
        }
        return ans;
    }
}
