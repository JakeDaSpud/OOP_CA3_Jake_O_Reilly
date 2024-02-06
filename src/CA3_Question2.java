import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question2
{
    //Simple pair storage class, only 2 int values
    static class Pair {
        private int row;
        private int column;

        Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }

        //getters
        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        //setters
        public void setRow(int row) {
            this.row = row;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        //tostring
        @Override
        public String toString() {
            return "Pair[" + "row=" + row + ", column=" + column + "]";
        }
    }

    //Starter function to create the 2D array and populate it with 0
    public static int[][] floodFillStart() {
        int[][] arr = new int[10][10];

        for (int x = 0; x < 10; x++) {

            for (int y = 0; y < 10; y++) {
                arr[x][y] = 0;
            }
        }

        return arr;
    }

    //Helper function to display the image
    public static void display(int[][] arr) {

        for (int x = 0; x < 10; x++) {

            for (int y = 0; y < 10; y++) {
                System.out.printf("%4d", arr[x][y]);
            }

            System.out.println();
        }
    }
    private static void fill(int currentRow, int currentCol, int[][] arr) {
        Stack<Pair> toFill = new Stack<>();

        //Starting currentPair value is the input
        Pair currentPair = new Pair(currentRow, currentCol);
        //Next pair checked to be added to stack
        Pair checkingPair = new Pair(-1, -1);
        int fillStage = 1;

        for (int i = 1; i <= (10*10); i++) {
            System.out.printf("On Fill Turn No. %d...\n", i);

            //set this number position in array to the fillStage number
            arr[currentPair.getRow()][currentPair.getColumn()] = fillStage;

            //Check North: row - 1
            if (currentPair.getRow() > 0) {
                checkingPair = new Pair(currentPair.getRow() - 1, currentPair.getColumn());

                if (arr[checkingPair.getRow()][checkingPair.getColumn()] == 0) {
                    toFill.push(checkingPair);
                    System.out.println("Added Pair at " + checkingPair);
                }
            }

            //Check East: col + 1
            if (currentPair.getColumn() < 9) {
                checkingPair = new Pair(currentPair.getRow(), currentPair.getColumn() + 1);

                if (arr[checkingPair.getRow()][checkingPair.getColumn()] == 0) {
                    toFill.push(checkingPair);
                    System.out.println("Added Pair at " + checkingPair);
                }
            }

            //Check South: row + 1
            if (currentPair.getRow() < 9) {
                checkingPair = new Pair(currentPair.getRow() + 1, currentPair.getColumn());

                if (arr[checkingPair.getRow()][checkingPair.getColumn()] == 0) {
                    toFill.push(checkingPair);
                    System.out.println("Added Pair at " + checkingPair);
                }
            }

            //Check West: col - 1
            if (currentPair.getColumn() > 0) {
                checkingPair = new Pair(currentPair.getRow(), currentPair.getColumn() - 1);

                if (arr[checkingPair.getRow()][checkingPair.getColumn()] == 0) {
                    toFill.push(checkingPair);
                    System.out.println("Added Pair at " + checkingPair);
                }
            }

            System.out.println("Stack: " + toFill);
            display(arr);

            fillStage++;
            currentPair = toFill.pop();
        }

    }

    public static void start() {
        Scanner kb = new Scanner(System.in);
        int firstRow = -1, firstCol = -1;

        System.out.println("Initial Array: ");
        int[][] arr = floodFillStart();
        display(arr);

        //wow i've finally used do while
        do {
            System.out.println("\nEnter your initial coordinates (e.g. [0, 0] up to [9, 9]): ");
            firstRow = kb.nextInt();
            firstCol = kb.nextInt();
        } while (!((firstRow > -1 && firstRow < 10) && (firstCol > -1 && firstCol < 10)));

        Pair initialPair = new Pair(firstRow, firstCol);

        System.out.println("Initial Location: " + initialPair);

        //Filling the array using initial pair
        fill(initialPair.getRow(), initialPair.getColumn(), arr);
    }

    public static void main(String[] args) {
        start();
    }

}