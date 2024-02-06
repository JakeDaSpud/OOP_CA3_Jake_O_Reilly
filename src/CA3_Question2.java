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
    private static void fill(int initialRow, int initialCol, int[][] arr) {
        Stack<Pair> toFill = new Stack<>();

        //Check North


        //Check East

        //Check South

        //Check West

        display(arr);
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