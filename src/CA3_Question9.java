import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {NORTH,SOUTH,EAST,WEST};

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
    static class XYDPair {
        private int x;
        private int y;
        private DIRECTION direction = DIRECTION.NORTH;

        public XYDPair(int x, int y, DIRECTION direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public DIRECTION getDirection() {
            return direction;
        }

        public void setDirection(DIRECTION direction) {
            this.direction = direction;
        }
    }

    public static void display(int[][] image) {
        for (int x = 0; x < image.length; x++)
        {
            for (int y = 0; y < image[0].length; y++)
            {
                System.out.printf("%4d", image[x][y]);
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void solve(Player player, int[][] maze, DIRECTION dir) {
        boolean mazeSolved = false;
        int globalCounter = 1;
        Stack<XYDPair> nextInstructionsToFollow = new Stack<>();

        System.out.println("Maze Size: " + maze.length);
        System.out.println("Player Starting Position: " + player.getX() + ", " + player.getY());

        if (!isValidPlayer(player, maze)) {
            System.out.println("Invalid Player Position Error.");
        }

        //while player x != 0 || maze.length && player y != 0 || maze.length
        while (!mazeSolved) {
            maze[player.getY()][player.getX()] = 999;

            display(maze);
            System.out.printf("--= Step %d =--\n", globalCounter);

            //stack adding phase
            //check north -y,   if 0, add to stack
            if (maze[player.getY() - 1][player.getX()] == 0) {
                System.out.println("North is traversable, adding to Stack.");
                nextInstructionsToFollow.push(new XYDPair(player.getX(), player.getY() - 1, DIRECTION.NORTH));
            }

            //check east +x,    if 0, add to stack
            if (maze[player.getY()][player.getX() + 1] == 0) {
                System.out.println("East is traversable, adding to Stack.");
                nextInstructionsToFollow.push(new XYDPair(player.getX() + 1, player.getY(), DIRECTION.EAST));
            }

            //check south +y,   if 0, add to stack
            if (maze[player.getY() + 1][player.getX()] == 0) {
                System.out.println("South is traversable, adding to Stack.");
                nextInstructionsToFollow.push(new XYDPair(player.getX(), player.getY() + 1, DIRECTION.SOUTH));
            }

            //check west -x,    if 0, add to stack
            if (maze[player.getY()][player.getX() - 1] == 0) {
                System.out.println("West is traversable, adding to Stack.");
                nextInstructionsToFollow.push(new XYDPair(player.getX() - 1, player.getY(), DIRECTION.WEST));
            }

            globalCounter++;

            //this just means the player isn't in an edge position, which would be the exit to the maze
            if ((player.getX() == 0 || player.getX() == maze.length - 1) || (player.getY() == 0 || player.getY() == maze.length - 1)) {
                System.out.printf("Maze solved in %d steps.\n", globalCounter);
                mazeSolved = !mazeSolved;
            }
        }
    }

    public static boolean isValidPlayer(Player player, int[][] maze) {
        if (player.getX() < 0 || player.getY() < 0 || player.getX() >= maze.length || player.getY() >= maze.length) {
            System.out.println("Invalid: Player is starting outside of maze...");
            return false;
        }

        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze.length; y++) {
                if (maze[player.getY()][player.getX()] != 0) {
                    System.out.println("Invalid: Player is starting on a maze wall...");
                    return false;
                }
            }
        }

        System.out.println("GOOD: Player is in valid starting position...");
        return true;
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
        Player player = new Player(4, 3);
        DIRECTION dir = DIRECTION.NORTH;

        setupMaze(theMaze);

        solve(player, theMaze, dir);
    }
}
