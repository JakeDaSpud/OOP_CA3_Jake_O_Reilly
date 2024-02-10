/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {NORTH, SOUTH,EAST,WEST};

public class CA3_Question9
{
    //basically a pair of x and y coords
    static class Player {
        private int x;
        private int y;

        public Player(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static void display(int[][] image)
    {
        for (int x = 0; x < image.length; x++)
        {
            for (int y = 0; y < image[0].length; y++)
            {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }
    }

    public void solve(int x, int y, DIRECTION dir) {
        Player player = new Player(1, 1);
        //while ()
    }

    public void setupMaze(int[][] maze) {

    }

    public static void main(String[] args) {
        display(new int[7][7]);
    }
}
