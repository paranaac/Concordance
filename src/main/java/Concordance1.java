
/**
 * Concordance Problem
 * @author Alejandro
 * @date 3/25/2015
 */

import java.util.*;
import java.io.*;

public class Concordance1 {

    public static void main(String[] args) throws IOException {
        final String INPUT_WORD = "bday.txt"; // NAME OF INPUT FILE
        System.out.println("Computing...\n");
        long startTime = System.nanoTime();
        inputWords(INPUT_WORD);
        System.out.println("\nTime elapsed: "
                + (System.nanoTime() - startTime) / 1000000 + " ms.");
    }
    /**
     * Go through the file and extract identifiers Prints out the key (identifier) and value (occurrence/line)
     * @throws java.io.IOException
     * @param fileName is the file to read in
     */
    @SuppressWarnings("resource")
    public static void inputWords(String fileName) throws IOException {
        AvlMap<String, MapElement> avlMap = new AvlMap<>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line, word, symbols = " .,<>`!?@#$%^&*()_+=[]{}-;:\t\'\"|";
        MapElement current;

        for (int i = 1; ((line = in.readLine()) != null); i++) { // Line feed \n 
            StringTokenizer t = new StringTokenizer(line, symbols); //Split by delimiters 
            while (t.hasMoreElements()) {
                word = t.nextToken().toUpperCase(); //Ignore casing
                current = avlMap.get(word); //Get current map node, empty key return null

                if (!avlMap.containsKey(word)) {
                    current = new MapElement();
                    avlMap.put(word, current);
                }
                current.update(i);
            }
        }
        // Print out ordered result
        avlMap.printMap(); 
    }
}
