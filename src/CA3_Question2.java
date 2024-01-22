import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question2 {
    //Pair class which just is a duo of integers for row and column coordinates
    //Made as an inner-class because this is a very small class
    static class Pair {
        private int row = 0, column = 0;

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public String toString() {
            return "Pair[" + row + ", " + column + ']';
        }
    }

    //array 10x10 of 0s initialised from function
    static int[][] arr = floodFillStart();

    //validate if int is 1 - 10
    public static boolean isOneToTen(int checking) {
        if (checking <= 0 || checking >= 11) {
            return false;
        }

        return true;
    }

    /*
        Starter function to create the 2D array and populate it with 0

     */
    public static int[][] floodFillStart() {
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                arr[x][y] = 0;
            }
        }
        return arr;
    }

    /*
        Helper function to display the image
     */
    public static void display(int[][] arr) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }

    private static void fill(int r, int c, int[][] arr) {
        System.out.println("fill() r: " + r + " c: " + c);

        //
        Stack<int[][]> toBeFilledPairs = new Stack<int[][]>();
        int fillStage = 1;

        //set this number position in array to the fillStage number
        arr[r][c] = fillStage;

        display(arr);
    }

    public static void start() {
        Scanner kb = new Scanner(System.in);

        int startRow = 0, startColumn = 0;

        while (!isOneToTen(startRow)) {
            //take in row and column starting pair
            System.out.printf("Enter your starting ROW number (1-10): ");
            startRow = kb.nextInt();

            if (!isOneToTen(startRow)) {
                System.out.println("Invalid number, try again.");
            }
        }

        while (!isOneToTen(startColumn)) {
            System.out.printf("Enter your starting COLUMN number (1-10): ");
            startColumn = kb.nextInt();

            if (!isOneToTen(startColumn)) {
                System.out.println("Invalid number, try again.");
            }
        }

        Pair startCoordinates = new Pair(startRow, startColumn);
        System.out.println("Starting coordinates: " + startCoordinates.toString());

        for (int currentRow = startCoordinates.getRow() - 1; currentRow < 10; currentRow++) {
            for (int currentColumn = startCoordinates.getColumn() - 1; currentColumn < 10; currentColumn++) {
                fill(currentColumn, currentRow, arr);
            }
        }

        display(arr);
    }
    public static void main(String[] args) {
        start();
    }

}