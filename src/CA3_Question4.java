import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
/**
 *  Name: jake o'reilly
 *  Class Group: gd2a
 */
public class CA3_Question4 {

    //check if a tag is opening or closing
    public static boolean isOpening(String tag) {
        if (tag.contains("/")) {
            return false;
        }

        return true;
    }

    /*
        filename: name of the file to test.
     */
    public static boolean validate(String filename) throws FileNotFoundException
    {
        Stack<String> openTags = new Stack<>();
        Scanner fileIn = new Scanner(new File(filename));
        String tag;

        while (fileIn.hasNext()) {
            tag = fileIn.next();
            System.out.println("current Tag: "+tag);

            //adding opening tags to openTags, because they are open and haven't been closed yet (there is a future tag with / in it)
            if (isOpening(tag)) {
                openTags.push(tag);
            }

            //not opening, check if the peek() of openTags is the same as this tag, both ignoring case and without the "/" as second last character
            else {
                System.out.println(tag);
                //get tag into non closing form (sin el /)
                tag = "<" + tag.substring(2, tag.length());
                System.out.println(tag);

                //tag is closing the previous tag, pop() it
                if (tag.equalsIgnoreCase(openTags.peek())) {
                    openTags.pop();
                }

                //tags don't match, incorrect order of closing tags
                else {
                    return false;
                }
            }
        }

        return true;
    }

    /*
        This function tests the files in the files array to see if
        they are valid.
        tags_valid.txt should return true;
        tags_invalid.txt should output as invalid;
    */

    public static void main(String[] args) throws FileNotFoundException {
        //reading files
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};

        //getting
        for (String fName: files) {

            //filename print
            System.out.print(fName +": ");

            //validate returns true
            if (validate(fName)) {
                System.out.println("This file is valid");
            }

            //validate returns false
            else {
                System.out.println("This file is invalid");
            }
        }

    }
}
