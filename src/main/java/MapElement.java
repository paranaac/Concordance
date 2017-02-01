
/**
 * MapElement Class
 * @author Alejandro
 * @date 3/25/2015
 */
public class MapElement {

    private int occurrence = 0;
    AvlSet<Integer> avlSet = new AvlSet<>();

    /**
     * Increment occurrence and add line number to an identifier
     * @param line is the line number where identifier is found
     */
    public void update(int line) {
        addLineNumber(line);
        updateOccurrence();
    }

    private void addLineNumber(int line) {
        avlSet.add(line); // No duplicates
    }

    private void updateOccurrence() {
        occurrence++;
    }

    public int getOccurrence() {
        return occurrence;
    }

    @Override
    public String toString() {
        return " occurs " + getOccurrence() + " times on lines " + avlSet.printTree();
    }

}
