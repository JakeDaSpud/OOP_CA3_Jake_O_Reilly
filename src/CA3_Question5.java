import java.util.*;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */

public class CA3_Question5
{
    //Declare queues: Deques because I don't want to change priority of the planes, I want FIFO on the plane order
    static Queue<String> toTakeoff = new ArrayDeque<>();
    static Queue<String> toLand = new ArrayDeque<>();

    public static void main(String[] args)
    {
        //Menu system taken from Q6
        Scanner in = new Scanner(System.in);

        String command = "";
        String flightSymbol = "";

        do {
            System.out.print("enter command: ");

            command = in.next();

            //takeoff queues into toTakeoff
            if (command.equalsIgnoreCase("takeoff")) {
                flightSymbol = in.next();
                toTakeoff.offer(flightSymbol);
            }

            //land queues into toLand
            else if (command.equalsIgnoreCase("land")) {
                flightSymbol = in.next();
                toLand.offer(flightSymbol);
            }

            //next checks the queues
            else if (command.equalsIgnoreCase("next")) {
                //Landing planes polled first
                while (!toLand.isEmpty()) {
                    System.out.println(toLand.poll() + " has landed.");
                }

                //Takeoff planes polled second
                while (!toTakeoff.isEmpty()) {
                    System.out.println(toTakeoff.poll() + " has taken off.");
                }
            }

        } while (!command.equalsIgnoreCase("quit"));
    }
}
