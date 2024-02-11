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
        //while ()
    }

    public static void setupMaze(int[][] maze) {
        int wall = -11;

        maze[0][0] = wall; maze[0][1] = wall; maze[0][2] = wall; maze[0][3] = wall; maze[0][4] = wall; maze[0][5] = wall; maze[0][6] = wall; maze[0][7] = wall;
        maze[1][0] = wall; maze[1][7] = wall;
        maze[2][0] = wall; maze[2][1] = wall; maze[2][2] = wall; maze[2][3] = wall; maze[2][5] = wall; maze[2][6] = wall; maze[2][7] = wall;
        maze[3][7] = wall;
        maze[4][0] = wall; maze[4][1] = wall; maze[4][2] = wall; maze[4][3] = wall; maze[4][5] = wall; maze[4][6] = wall; maze[4][7] = wall;
        maze[5][0] = wall; maze[5][5] = wall; maze[5][6] = wall; maze[5][7] = wall;
        maze[6][0] = wall; maze[6][1] = wall; maze[6][2] = wall; maze[6][3] = wall; maze[6][5] = wall; maze[6][6] = wall; maze[6][7] = wall;
        maze[7][0] = wall; maze[7][1] = wall; maze[7][2] = wall; maze[7][3] = wall; maze[7][4] = wall; maze[7][5] = wall; maze[7][6] = wall; maze[7][7] = wall;
    }

    public static void main(String[] args) {
        int[][] theMaze = new int[8][8];
        Player player = new Player(3, 4);

        setupMaze(theMaze);

        display(theMaze);
    }
}
