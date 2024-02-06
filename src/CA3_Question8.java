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
        Stack<Integer> numberStack = new Stack<>();
        String equation;

        System.out.println("Please enter equation: ");
        equation = in.nextLine().trim();

        //found regex for integer: "\\d+"
        //link: https://stackoverflow.com/questions/16331423/whats-the-java-regular-expression-for-an-only-integer-numbers-string
        if (equation.matches("\\d+")) {
            //convert to int, push to stack
            numberStack.push(Integer.parseInt(equation));
        }

        //found site that lets me generate regex
        //link: https://regex-generator.olafneumann.org/?sampleText=%2B-*%2F&flags=i
        else if (equation.matches("\\+-\\*/")) {
            //take two most recent numbers
            int x = numberStack.pop();
            int y = numberStack.pop();
            int result = 0;

            if (equation.equals("+")) {
                System.out.println("Adding...");
                result = x + y;
            }

            else if (equation.equals("-")) {
                System.out.println("Subtracting...");
                result = x - y;
            }

            else if (equation.equals("*")) {
                System.out.println("Multiplying...");
                result = x * y;
            }

            else if (equation.equals("/")) {
                System.out.println("Dividing...");
                result = x / y;
            }

            numberStack.push(result);
        }

        System.out.println("Result: " + numberStack.peek());
    }
}
