import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

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

        System.out.println("Example Equation: ( 3 + 5 )");
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
                System.out.println("STATE OperatorStack: " + operatorStack);


                System.out.println("NumberStack: " + numberStack);
            }
        }

        System.out.println("Now solving...");

        //numberStack.sort(Comparator.reverseOrder());
        while (numberStack.size() != 1) {


            //take two most recent numbers
            double y = numberStack.pop();
            double x = numberStack.pop();
            double result = 0;

            //ORDERING: BIMDAS
            //brackets
            if (operatorStack.peek() == '(') {
                System.out.println("Opening Parentheses??...");
                operatorStack.pop();
            }

            else if (operatorStack.peek() == ')') {
                System.out.println("Closing Parentheses??...");
                operatorStack.pop();
            }

            //multiplication
            else if (operatorStack.peek() == '*') {
                System.out.println("Multiplying...");
                result = x * y;
                operatorStack.pop();
            }

            //division
            else if (operatorStack.peek() == '/') {
                System.out.println("Dividing...");
                result = x / y;
                operatorStack.pop();
            }

            //addition
            else if (operatorStack.peek() == '+') {
                System.out.println("Adding...");
                result = x + y;
                operatorStack.pop();
            }

            //subtraction
            else if (operatorStack.peek() == '-') {
                System.out.println("Subtracting...");
                result = x - y;
                operatorStack.pop();
            }

            numberStack.push(result);
        }

        System.out.println("Final STATE NumberStack: " + numberStack);

        System.out.println("Result: " + numberStack.peek());
    }
}
