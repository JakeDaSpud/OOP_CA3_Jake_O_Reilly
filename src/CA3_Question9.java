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
enum DIRECTION {NORTH, SOUTH, EAST, WEST};

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

        @Override
        public String toString() {
            return "XYD[" +
                    "x:" + x +
                    ", y:" + y +
                    ", dir:" + direction +
                    "]";
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
        Stack<XYDPair> nextMovesStack = new Stack<>();
        Set<XYDPair> nonRepeatableMovesSet = new HashSet<>();

        System.out.println("Maze Size: " + maze.length);
        System.out.println("Player Starting Position: " + player.getX() + ", " + player.getY());

        if (!isValidPlayer(player, maze)) {
            System.out.println("Invalid Player Position Error.");
            System.out.println("Get walled...");
            System.exit(-11);
        }

        //while player x != 0 || maze.length && player y != 0 || maze.length
        while (!mazeSolved) {
            maze[player.getY()][player.getX()] = 999;

            display(maze);

            //reset where player was, not there anymore
            maze[player.getY()][player.getX()] = 0;

            //leave a marker that player has been on that tile
            //maze[player.getY()][player.getX()] = 3;

            System.out.printf("--= Step %d =--\n", globalCounter);

            checkNESWAndAdd(player, maze, nextMovesStack, nonRepeatableMovesSet);

            globalCounter++;

            //this just means the player isn't in an edge position, which would be the exit to the maze
            if ((player.getX() == 0 || player.getX() == maze.length - 1) || (player.getY() == 0 || player.getY() == maze.length - 1)) {
                System.out.printf("Maze solved in %d steps.\n", globalCounter);
                mazeSolved = !mazeSolved;
            }

            //traversal phase
            if (!nextMovesStack.isEmpty()) {
                XYDPair nextMove = nextMovesStack.pop();
                nonRepeatableMovesSet.add(nextMove);
                System.out.println("Next move is " + nextMove);


                //if next is north, keep going while north is 0
                if (nextMove.getDirection() == DIRECTION.NORTH) {
                    while (maze[player.getY() - 1][player.getX()] == 0) {
                        //move north one
                        player.setY(player.getY() - 1);
                        System.out.println("moved one north, now at " + player.getX() + " " + player.getY());
                    }
                }

                //if next is east, keep going while east is 0
                else if (nextMove.getDirection() == DIRECTION.EAST) {
                    while (maze[player.getY()][player.getX() + 1] == 0) {
                        //move east one
                        player.setX(player.getX() + 1);
                        System.out.println("moved one east, now at " + player.getX() + " " + player.getY());
                    }
                }

                //if next is south, keep going while south is 0
                else if (nextMove.getDirection() == DIRECTION.SOUTH) {
                    while (maze[player.getY() + 1][player.getX()] == 0) {
                        //move south one
                        player.setY(player.getY() + 1);
                        System.out.println("moved one south, now at " + player.getX() + " " + player.getY());
                    }
                }

                //if next is west, keep going while west is 0
                else if (nextMove.getDirection() == DIRECTION.WEST) {
                    if (maze[player.getY()][player.getX() - 1] == 0) {
                        //move west one
                        player.setX(player.getX() - 1);
                        System.out.println("moved one west, now at " + player.getX() + " " + player.getY());
                    }
                }

                System.out.println("adjAvailTiles west of me: " + adjAvailableTiles(player, maze, nextMove));
                System.out.println("Old moves: " + nonRepeatableMovesSet);

            } else {
                System.out.println("No more directions to go...");
            }
        }
    }

    //logic to check all adjacent tiles to player and then add commands to stack
    public static void checkNESWAndAdd(Player player, int[][] maze, Stack<XYDPair> nextMoves, Set<XYDPair> oldMoves) {
        //stack adding phase
        //check west -x,    if 0, add to stack
        if (maze[player.getY()][player.getX() - 1] == 0) {
            System.out.println("West is traversable, trying to add to Stack.");

            if (!oldMoves.contains(new XYDPair(player.getX(), player.getY(), DIRECTION.WEST))) {
                nextMoves.push(new XYDPair(player.getX(), player.getY(), DIRECTION.WEST));
            } else {System.out.println("Not added. Move has been done already.");}
        }

        //check south +y,   if 0, add to stack
        if (maze[player.getY() + 1][player.getX()] == 0) {
            System.out.println("South is traversable, trying to add to Stack.");

            if (!oldMoves.contains(new XYDPair(player.getX(), player.getY(), DIRECTION.SOUTH))) {
                nextMoves.push(new XYDPair(player.getX(), player.getY(), DIRECTION.SOUTH));
            } else {System.out.println("Not added. Move has been done already.");}
        }

        //check east +x,    if 0, add to stack
        if (maze[player.getY()][player.getX() + 1] == 0) {
            System.out.println("East is traversable, trying to add to Stack.");

            if (!oldMoves.contains(new XYDPair(player.getX(), player.getY(), DIRECTION.EAST))) {
                nextMoves.push(new XYDPair(player.getX(), player.getY(), DIRECTION.EAST));
            } else {System.out.println("Not added. Move has been done already.");}
        }

        //check north -y,   if 0, add to stack
        if (maze[player.getY() - 1][player.getX()] == 0) {
            System.out.println("North is traversable, trying to add to Stack.");

            if (!oldMoves.contains(new XYDPair(player.getX(), player.getY(), DIRECTION.NORTH))) {
                nextMoves.push(new XYDPair(player.getX(), player.getY(), DIRECTION.NORTH));
            } else {System.out.println("Not added. Move has been done already.");}
        }
    }

    //takes in nextMove to know which direction's tile availability to ignore (the one we're coming from)
    public static int adjAvailableTiles(Player player, int[][] maze, XYDPair nextMove) {
        int adjOpenTiles = 0;

        if (nextMove.getDirection() == DIRECTION.WEST && maze[player.getY()][player.getX() - 1] == 0) {
            System.out.println("West is open");
            adjOpenTiles++;
        }

        if (nextMove.getDirection() == DIRECTION.SOUTH && maze[player.getY() + 1][player.getX()] == 0) {
            System.out.println("South is open");
            adjOpenTiles++;
        }

        if (nextMove.getDirection() == DIRECTION.EAST && maze[player.getY()][player.getX() + 1] == 0) {
            System.out.println("East is open");
            adjOpenTiles++;
        }

        if (nextMove.getDirection() == DIRECTION.NORTH && maze[player.getY() - 1][player.getX()] == 0) {
            System.out.println("North is open");
            adjOpenTiles++;
        }

        return adjOpenTiles;
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
