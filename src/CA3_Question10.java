import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question10
{

    public static void main(String[] args) throws FileNotFoundException {

        //Take in city names and map to objects
        //Ignoring TreeSet, using HashSet instead because equal weight towns are treated as duplicates
        HashMap<String, HashSet<DistanceTo>> cityDistances = new HashMap<>();

        Scanner cityData = new Scanner(new File("Q10_CityCityWeight_Data.txt"));
        while (cityData.hasNext()) {
            String originCityName = cityData.next();
            String destinationCityName = cityData.next();
            int distance = cityData.nextInt();

            //check if weighted edge is already accounted for
            if (!cityDistances.containsKey(originCityName)) {
                cityDistances.put(originCityName, new HashSet<DistanceTo>());
            }
            //adding the distance and dest. city once added
            cityDistances.get(originCityName).add(new DistanceTo(destinationCityName, distance));
        }

        //big fancy printout of city : dest. city
        for (String originCity : cityDistances.keySet()) {

            System.out.println("\nComing from City: " + originCity);
            HashSet<DistanceTo> distances = cityDistances.get(originCity);

            for (DistanceTo distanceTo : distances) {
                System.out.println("  -> [Dest. City: " + distanceTo.getTarget() + "] [Weight: " + distanceTo.getDistance() + "]");
            }

        }

        findMinWeightToAllCities("Pierre", cityDistances);
    }

    public static void findMinWeightToAllCities(String startingCity, HashMap<String, HashSet<DistanceTo>> cityDistances) {
        System.out.println("\nMinimum distances from " + startingCity + " to every other city...");

//        for (String fromCity : cityDistances.keySet()) {
//
//            if (fromCity != startingCity) {
//
//                HashSet<DistanceTo> distance = cityDistances.get(fromCity);
//
//                for (DistanceTo distanceTo : distance) {
//                    System.out.println("Current fromCity: [" + fromCity + ", Weight " +  distanceTo.getDistance() + "]");
//                }
//
//            }
//        }

        Queue<DistanceTo> queue = new PriorityQueue<>();
        queue.offer(new DistanceTo(startingCity, 0));

        HashMap<String, Integer> shortestKnownDistance = new HashMap<>();

        //while pq is not empty
        while (!queue.isEmpty()) {

            //get its smallest element (am using Collections.min which uses natural ordering)
            DistanceTo min = Collections.min(queue);

            //if its target is not a key in shortestKnownDistance
            if (!shortestKnownDistance.containsKey(min)) {

                //let d be the distance to that target
                int d = min.getDistance();

                //put (target, d) into shortestKnownDistance
                shortestKnownDistance.put(startingCity, d);
            }
        }
    }
}
