import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Hi calculator ");
        try {
            System.out.println("ans: " + Calculator.evaluator("pow log 11 sqrt * 13 cos - 6 1"));
        }
        catch (Exception ex){
            System.out.println("something wrong");
        }

    }

    private static double[] pop2ToDouble(Stack<Double> numStack){
        double[] numbers = new double[2];
        numbers[0] = numStack.pop();
        numbers[1] = numStack.pop();
        return numbers;
    }

    private static double pop1ToDouble(Stack<Double> numStack){
        return numStack.pop();
    }

    public static double evaluator(String expression)
            throws WrongPolishNotationException,
            WrongFunctionArgumentException
    {
        double ans = 0;
        String[] atoms = expression.split(" ");
        Stack<Double> numStack = new Stack<>();

        System.out.println(expression);
        double[] nums;
        for(int i = atoms.length - 1; i>=0; i--) {
            System.out.println(atoms[i]);

            // сначала пытаемся понять является ли текущий атом какой-то операцией
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
                // если не подошла не одна операция, значит это число, либо выкидываем exception
                default:
                    try {
                        numStack.push(Double.parseDouble(atoms[i]));
                    } catch (Exception ex){
                        throw new WrongFunctionArgumentException();
                    }
            }
        }
        ans = numStack.pop();
        if(!numStack.isEmpty()) {
            throw new WrongPolishNotationException();
        }
        return ans;
    }
}
