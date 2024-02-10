import java.lang.reflect.Array;
import java.util.*;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question8 {

    /*
        Reads in an equation from the user
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Double> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        String equation;

        System.out.println("Example Equation: 4 * ( 3 + 5 )");
        System.out.println("Please enter equation with space between each character: ");
        equation = in.nextLine().trim();
        System.out.println("Equation: " + equation);

        //regex taken from Q3, modified to include */+()-
        String[] splitEquation = equation.split("[^A-Za-z0-9_*/+()-]");
        System.out.println("splitEquation = ");
        for (String s : splitEquation) {
            System.out.println(s);
        }


        for (int i = 0; i < splitEquation.length; i++) {

            //found regex for integer: "\\d+" / .matches("\\d+")
            //link: https://stackoverflow.com/questions/16331423/whats-the-java-regular-expression-for-an-only-integer-numbers-string
            if (splitEquation[i].matches("\\d+")) {
                System.out.println("Int found in equation: " + splitEquation[i]);

                //convert to int (double, for division compatibility), push to stack
                numberStack.push(Double.parseDouble(splitEquation[i]));
                System.out.println("STATE NumberStack: " + numberStack);
            }

            //found site that lets me generate regex
            //link: https://regex-generator.olafneumann.org/?sampleText=%2B-*%2F&flags=i
            else {
                System.out.println("Operator found: " + splitEquation[i]);
                operatorStack.push(splitEquation[i].charAt(0));

                //while inside ()
                if (splitEquation[i].charAt(0) == ')') {
                    System.out.println("Closing Parenthesis found... ");
                    while (!operatorStack.isEmpty() && operatorStack.peek() != ')') {
                        solveStacks(numberStack, operatorStack);
                    }
                }

                printStacks(numberStack, operatorStack);
            }
        }

        System.out.println("Now solving...");

        //numberStack.sort(Comparator.reverseOrder());
        solveStacks(numberStack, operatorStack);

        System.out.println("Final STATE NumberStack: " + numberStack);

        System.out.println("Result: " + numberStack.peek());
    }

    public static void solveStacks(Stack<Double> numberStack, Stack<Character> operatorStack) {
        //while not (?: because
        while (!operatorStack.isEmpty() && numberStack.size() > 1 && operatorStack.peek() != '(') {

            //take two most recent numbers
            double y = numberStack.pop();
            double x = numberStack.pop();
            double result = 0;
            char operator = operatorStack.pop();

            //ORDERING: BIM*D/A+S-

            //made into switch case
            switch (operator) {
                case '*':
                    System.out.printf("Multiplying %f and %f\n", x, y);
                    result = x * y;
                    break;

                case '/':
                    System.out.printf("Dividing %f and %f\n", x, y);
                    result = x / y;
                    break;

                case '+':
                    System.out.printf("Adding %f and %f\n", x, y);
                    result = x + y;
                    break;

                case '-':
                    System.out.printf("Subtracting %f and %f\n", x, y);
                    result = x - y;
                    break;

                default:
                    System.out.println("Operator not 1 of 4 normal ones.");
                    break;
            }

            //pop the operator when not ( or )
            operatorStack.pop();
            System.out.println("Removing top operator of operatorStack");

            numberStack.push(result);

            printStacks(numberStack, operatorStack);
        }

        //this is popping the ( when we're done the brackets
        if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
            operatorStack.pop();
        }
    }

    public static void printStacks(Stack<Double> nums, Stack<Character> ops) {
        System.out.println("NumberStack: " + nums);
        System.out.println("OperatorStack: " + ops);
    }
}
