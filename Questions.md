**Object Oriented Programming\
Continuous Assessment 3\
Assignment Questions\
December 2023**

*BSc (Hons) in Computing in Games Development\
BSc (Hons) in Computing in Software Development\
Stage 2*

Weighting: 15%\
Deadline: Monday 12th February 2024

Answer all Questions. All questions carry equal makes (10 marks each)\
Use the starter code provide on Moodle.

Students may work in pairs to solve problems and design solutions, but students must complete the implementation individually.

Each student must create a repo on GitHub called “oop-ca3-firstname-lastname” and push commits to their work, with meaningful commit messages, on an ongoing basis. You must share this Repo with your lecturer for assessment purposes.

A zipped version of the source files must be uploaded to Moodle by the deadline in addition to the GitHub Repo.\
You must reference any code that you have used by inserting comments with relevant URLS in your code.

Students will demonstrate and will be interviewed on their work.

---

### Question 1 – Car Parking (Stack)
A homeowner rents out parking spaces in a driveway during special events. The driveway is
a “last-in, first-out” LIFO stack. Of course, when a car owner retrieves a vehicle that wasn’t
the last one in, the cars blocking it must temporarily move to the street so that the
requested vehicle can leave. Write a program that models this behaviour, using one stack for
the driveway and one stack for the street. Use integer values as license plate numbers (e.g.
1,2,3,4…). Positive numbers add a car (1,2,3…), negative numbers remove a car(-2,-1,…), zero
stops the simulation. Print out the stack after each operation is complete.

So, entering “1” means – add car number 1 to the driveway, entering “-2” means - retrieve
car number 2 from the driveway.

---

### Question 2 - Flood Fill (Stack)
In a paint program, a “flood fill” fills all empty pixels of a drawing with a given colour,
stopping when it reaches occupied pixels. In this exercise, you will implement a simple
variation of this algorithm, flood-filling a 10 × 10 array of integers that are initially 0.
Prompt for the starting row and column (the starting cell for the flood fill).
- Push the (row, column) pair onto a stack. You will need to provide a simple Pair class
(storing row and column).
- Repeat the following operations until the stack is empty.
  - Pop off the (row, column) pair from the top of the stack.
  - If it has not yet been filled, fill the corresponding cell location with a number
  1, 2, 3, and so on (this number is incremented at each step to show the order
  in which the square is filled).
  - Push the coordinates of any unfilled neighbours in the north, east, south, or
  west direction on the stack.
- When you are done (i..e stack is empty), print the entire 2D array.
---

### Question 3 – Java Identifier Count (Map)
Write a program that reads a Java source file and produces an index of all identifiers in the
file. (Identifiers are variable names, class names and keywords etc.) For each identifier, print
all lines - with the line number - in which it occurs. For simplicity, we will consider each string
consisting only of letters, numbers, and underscores an identifier. Declare a Scanner **in** for
reading from the source file and call **in.useDelimiter("[^A-Za-z0-9_]+")**. Then each call to
next returns an identifier.

---

### Question 4 – Nested HTML Tags (Stack)
Write a program that checks whether a sequence of HTML tags is properly nested. For each
opening tag, such as ````<p>````, there must be a closing tag ````</p>````.\
A tag such as ``<p>`` may have
other tags inside, for example :\
````<p> <ul> <li> </li> </u> **<a>** </p>````\
The inner tags must be closed before the outer ones. Your program should process a file
containing tags. For simplicity, assume that the tags are separated by spaces, and that there is
no text inside the tags.

---

### Question 5 – Airport Flights (Queue)
An airport has only one runway. When it is busy, planes wishing to take off or land have to
wait. Implement a simulation, using two queues, one each for the planes waiting to take off
and land. Landing planes get priority. The user enters commands takeoff flightSymbol,
land flightSymbol, next, and quit. The first two commands place the flight in the
appropriate queue. The next command finishes the current take-off or landing and enables
the next one, printing the action (takeoff or land) and the flight symbol.
For example:
````
    takeoff( “Flight-100”);     // is queued
    takeoff(“Flight-220”);      // is queued
    land(“Flight-320”);         // is queued
    next(); // will complete the takeoff/landing of the
            // current flight and initiate landing of
            // the next prioritized flight i.e. Flight-320
````

---

### Question 6 – Stock Shares Tax Calculation (Queue)
Suppose you buy 100 shares of a stock at $12 per share, then another 100 at $10 per share,
and then sell 150 shares at $15. You have to pay taxes on the gain, but exactly what is the
gain? In the United States, the FIFO rule holds: You first sell all shares of the first batch for a
profit of $300, then 50 of the shares from the second batch, for a profit of $250, yielding a
total profit of $550. Write a program that can make these calculations for arbitrary
purchases and sales of shares in a single company. The user enters commands
**buy quantity price**, and **sell quantity** (which causes the gain to be displayed), and quit.\
Hint:
Keep a queue of objects of a class **Block** that contains the quantity and price of a block of
shares.

---

### Question 7 – Multi-Company Stock Shares Tax Calculation (Queue)
Extend Question 6 to a program that can handle shares of multiple companies. The user
enters commands ***buy symbol quantity price*** and sell symbol quantity.\
Hint: Keep a Map<String, Queue<Block>> that manages a separate queue for each stock symbol.


---

### Question 8 Arithmetic Expression Calculator (Stack)
Implement a calculator to evaluate arithmetic expressions for the operators + - * / and
parenthesis ( ). See the accompanying PDF document which is an extract from a book
explaining the algorithm.

---

### Question 9 – Backtracking through a Maze (Stack)
Implement a backtracking algorithm, using a Stack, to find a path through a maze from start to exit.
Refer to description of algorithm (from textbook) in the accompanying PDF.

---

### Question 10 – Shortest Distance to City (Map, TreeSet, PriorityQueue)
Consider the problem of finding the least expensive routes to all cities in a network from a given
starting point.

IMAGE HERE

For example, in this network, the least expensive route from Pendleton to Peoria has cost 8
(going through Pierre and Pueblo). The following helper class expresses the distance to
another city:

````
public class DistanceTo implements Comparable {
    private String target;
    private int distance;

    public DistanceTo(String city, int dist) {
        target = city;
        distance = dist; }

    public String getTarget() { return target; }
    public int getDistance() { return distance; }

    public int compareTo(DistanceTo other) {
        return distance - other.distance;
    }
}
````

All direct connections between cities are stored in a **Map<String,TreeSet<DistanceTo>>**.

The algorithm now proceeds as follows:\
**Let *from* be the starting point.**\
**Add DistanceTo(from, 0) to a priority queue.**\
**Construct a map shortestKnownDistance from city names to distances.**\
**While the priority queue is not empty:**
- Get its smallest element. 
- If its target is not a key in shortestKnownDistance 
  - Let d be the distance to that target. 
  - Put (target, d) into shortestKnownDistance. 
  - For all cities c that have a direct connection from target 
    - Add DistanceTo(c, d + distance from target to c) to the priority queue.**

When the algorithm has finished, *shortestKnownDistance* contains the shortest distance
from the starting point to all reachable targets. Your task is to write a program that
implements this algorithm. Your program should read in lines, from a file, of the form:

***city1 city2 distance***

The starting point is the first city in the first line. Print the shortest distances to all other
cities.
