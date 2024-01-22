import java.util.Stack;
import java.util.spi.AbstractResourceBundleProvider;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question1
{
    static Stack<Integer> driveway = new Stack<Integer>();
    static Stack<Integer> street = new Stack<Integer>();
    static boolean endFlag = false;

    public static void runSimulation() {
        System.out.println("Starting simulation.");

        printStacks();

        while (!endFlag) {

            //Add 3 cars
            input(4);
            input(28);
            input(36);
            input(40);
            input(100);

            input(-49);
            input(-36);
            input(-4);

            //End
            input(0);
        }
    }

    public static void input(int carInput) {
        if (carInput == 0) {
            System.out.println("\ninput(0): Ending simulation.");
            endFlag = true;
        }

        else if (carInput >= 0) {
            //sout what's happening
            System.out.printf("\nEntering a car with plate [%d] into the driveway.", carInput);

            //put into driveway
            driveway.push(carInput);
        }

        else if (carInput <= 0) {

            carInput = -carInput;

            //take out of driveway
            System.out.printf("\nAttempting to remove car with plate [%d] from the driveway.", carInput);

            System.out.printf("\nChecking if car [%d] is in the driveway.", carInput);
            if (driveway.search(carInput) >= 0) {
                System.out.printf("\nCar [%d] is in the driveway.", carInput);

                //Pushing all driveway cars into street
                while (driveway.peek() != carInput) {
                    System.out.printf("\nTop item of Driveway: Car [%d]", driveway.peek());

                    System.out.printf("\nMoving car [%d] to the street.", driveway.peek());
                    street.push(driveway.pop());

                    printStacks();
                }

                //Removing top (wanted) car
                System.out.printf("\nRemoving Car [%d] from the driveway, bye bye!", carInput);
                driveway.pop();

                //Pushing all street cars back into driveway
                while (!street.isEmpty()) {
                    //While street not empty, move push top item into driveway

                    System.out.printf("\nStreet not empty, moving car [%d] to the driveway.", street.peek());
                    driveway.push(street.pop());

                    printStacks();
                }
            }

            else {
                System.out.printf("\nCar [%d] is not in the driveway. Cannot remove.", carInput);
            }
        }

        printStacks();
    }

    public static void printStacks() {
        System.out.println("\nStacks:");
        System.out.println("Street: " + street.toString());
        System.out.println("Driveway: " + driveway.toString());
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
