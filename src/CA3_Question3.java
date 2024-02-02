import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */

public class CA3_Question3
{
    static Map<String, Set<Integer>> wordIndexLine_Map = new TreeMap<>();

    public static void mapWord_LineNumber(String fileName, Map<String, Set<Integer>> map) throws FileNotFoundException {
        //File reading from Q4
        Scanner fileIn = new Scanner(new File(fileName));

        //reading file
        //assigning entries to map

        int lineIndex = 0;

        //entire file not empty / parsed
        while (fileIn.hasNext()) {

            //take in current line of file,
            //delimiter essentially every character, takes in every next string
            Scanner currentLine = new Scanner(fileIn.nextLine());
            currentLine = currentLine.useDelimiter("[^A-Za-z0-9_]+");

            //while current line of file is not empty
            while (currentLine.hasNext()) {

                System.out.println("DEBUG: CURRENT FULL FILE LINE: " + currentLine);

                //declare temp variables
                String word = "";
                int wordIndex = -1;

                word = currentLine.next();
                wordIndex = lineIndex;

                System.out.println("DEBUG:: CURRENT WORD OF LINE: " + word);

                //check if map entry where key is dupe,
                //if it has a set already, if it's a dupe,
                //add the line number to that entry's set,
                //otherwise instantiate new set mapped to word

                //can't find index in empty map
                //if (!map.isEmpty() && lineIndex > -1) {

                    //map already has the word as a key
                    if (map.containsKey(word)) {
                        //set value of that key to be what it was, + the new indexLine added to the Set<Integer>
                        map.get(word).add(lineIndex);
                        System.out.println("DEBUG::: ALREADY IN MAP, ADD NEW INDEX: ");
                        System.out.println(map);
                    }

                    else {
                        //make new entry with word and empty set, then add index to set
                        map.put(word, new TreeSet<Integer>());
                        map.get(word).add(lineIndex);
                        System.out.println("DEBUG:::: ADDED TO MAP: ");
                        System.out.println(map);
                    }
                //}
            }

            //current line index i'm on
            lineIndex++;
        }
    }

    //in the end have a map of
    //map<String wordInTheLine, Set<Integer> indexesOfWhereTheWordShowsUp>
    //print out the map essentially, like this:
    //and: [appears in line] 1, 5, 7

    //Function to print out all of map in order
    public static void enhancedLoopPrint(Map<String, Set<Integer>> mapIn) {
    }

    public static void main(String[] args) throws FileNotFoundException {
        mapWord_LineNumber("src/CA3_Question1.java", wordIndexLine_Map);

        //JAKE YOU WERE HERE THIS CAUSING ERRORS I AM TRYING TO DO Q6
//        for (Map<String, Set<Integer>> item : wordIndexLine_Map.entrySet()) {
//            System.out.println(item);
//        }
    }
}
