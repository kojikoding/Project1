import java.util.List;
import student.TestCase;

/**
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly.
 * Also test passing improper command to ensure
 * all class functionalities work as intended.
 * 
 * @author Labiba Sajjad
 * @version <version_no>
 */
public class CommandProcessorTest extends TestCase {

    private static final boolean String = false;
    private Database db;
    private CommandProcessor cp;

    /**
     * The setUp() method will be called automatically before
     * each test and reset whatever the test modified. For this
     * test class, only a new database object is needed, so
     * creat a database here for use in each test case.
     */
    public void setUp() { 
        db = new Database();
        cp = new CommandProcessor(db);

    } 


    // ----------------------------------------------------------
    /**
     * Tests a valid rectangle
     */
    public void testValid() {
        cp.processor("insert a 1 0 2 4");
        String expectedOutput = "Rectangle accepted: a 1, 0, 2, 4";
        assertEquals(expectedOutput, cp.getOutput());

    }


    // ----------------------------------------------------------
    /**
     * Tests for when two recs with the same name is inserted
     */
    public void testValidSameName() {
        cp.processor("insert a 1 0 2 4");
        cp.processor("insert a 1 0 2 4");
        cp.processor("insert a 3 6 7 8");
        String expectedOutput = "Rectangle accepted: a 1, 0, 2, 4\n"
            + "Rectangle accepted: a 1, 0, 2, 4\n"
            + "Rectangle accepted: a 3, 6, 7, 8";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests for invalid coord
     */
    public void testInvalidCoord() {
        cp.processor("insert b -1 3 5 3");
        String expectedOutput = "Rectangle rejected: b -1, 3, 5, 3";
        assertEquals(expectedOutput, cp.getOutput());
    }


    /**
     * Tests for when coords are out of bounds
     */
    public void testOutofBounds() {

        cp.processor("insert b 900 900 200 200");
        String expectedOutput = "Rectangle rejected: b 900, 900, 200, 200";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests for when height is out of bounds
     */
    public void testOutofBoundsY() {
        cp.processor("insert b 900 900 2 200");
        String expectedOutput = "Rectangle rejected: b 900, 900, 2, 200";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests wehn rectangle is touching the border
     */
    public void testInBounds() {
        cp.processor("insert iso 1020 1020 4 4");
        String expectedOutput = "Rectangle accepted: iso 1020, 1020, 4, 4";
        assertEquals(expectedOutput, cp.getOutput());

    }


    /**
     * Tests when rectangle with inValid name
     */
    public void testInsertInvalidName() {
        cp.processor("insert 9a 1020 1020 4 4");
        String expectedOutput = "Rectangle rejected: 9a 1020, 1020, 4, 4";
        assertEquals(expectedOutput, cp.getOutput());
    }


    // ----------------------------------------------------------
    /**
     * Tests to check if the tree dump is being output
     */
    public void testDump() {

        // Process rectangles
        cp.processor("insert name2 1 0 2 4");
        cp.processor("insert name3 1 2 1023 4");

        // Dump to the database
        cp.processor("dump");

        // Get a string of dump
        String out = db.dump();
        String expectedOutput = "BST dump:\n"
            + "Node has depth 0, Value (name2, 1, 0, 2, 4)\n"
            + "Node has depth 1, Value (name3, 1, 2, 1023, 4)\n"
            + "BST size is: 2\n";

        // Compare expected output with execuated output
        assertEquals(expectedOutput, out);
    }


    /**
     * Tests search method to see if correctly prints out the rectangles
     * with the specified name
     */
    public void testSearch() {
        cp.processor("insert a 10 10 15 15");
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");

        cp.processor("search a");
        String out = db.search("a");

        String expectedOut = "Rectangles found matching \"a\":\n"
            + "(a, 10, 10, 15, 15)\n" + "(a, 50, 21, 52, 1)";

        assertEquals(expectedOut, out);
    }


    /**
     * Tests with rectangle that doesn't exist
     */

    public void testNoRecSearch() {

        cp.processor("search c");
        String out = db.search("c");
        String expectedOutput = "Rectangle not found: c";

        assertEquals(expectedOutput, out);

    }


    /**
     * Tests the remove(name) method
     */
    public void testRemoveName() {
        //
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert a 590 21 52 1");
        cp.processor("insert b 10 9 2 4");

        cp.processor("remove a");

        // dump to check if a was removed
        cp.processor("dump");
        String out = db.dump();

        String expectedOutput = "BST dump:\n"
            + "Node has depth 0, Value (a, 590, 21, 52, 1)\n"
            + "Node has depth 1, Value (b, 10, 9, 2, 4)\n" + "BST size is: 2\n";
        // Compare expected output with execuated output
        assertEquals(expectedOutput, out);

        // Search for a to see if it returns the right a
        cp.processor("search a");
        out = db.search("a");
        expectedOutput = "Rectangles found matching \"a\":\n"
            + "(a, 590, 21, 52, 1)";
        assertEquals(expectedOutput, out);

    }


    /**
     * Tests remove(name) with non existing rec
     */
    public void testRemoveNameInvalid() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");

        // Trying to remove that doesn't exist
        cp.processor("remove c");
        String out = db.remove("c");

        String expected = "Rectangle not found: (c)";
        assertEquals(expected, out);

    }


    // ----------------------------------------------------------
    /**
     * Tests the remove(x y w h) method
     * everything valid
     */
    public void testRemoveCoord() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");

        cp.processor("remove 50 21 52 1"); 

        cp.processor("dump");
        String out = db.dump();

        String expectedOutput = "BST dump:\n"
            + "Node has depth 0, Value (b, 10, 9, 2, 4)\n" + "BST size is: 1\n";
        assertEquals(expectedOutput, out);

    }


    /**
     * Tests the remove(x y w h) method with two rec w same coords
     */
    public void testRemoveSameCoord() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");
        cp.processor("insert a 50 21 52 1");

        cp.processor("remove 50 21 52 1");

        cp.processor("dump");
        String out = db.dump();

        String expectedOutput = "BST dump:\n"
            + "Node has depth 0, Value (a, 50, 21, 52, 1)\n"
            + "Node has depth 1, Value (b, 10, 9, 2, 4)\n" + "BST size is: 2\n";
        assertEquals(expectedOutput, out);

    }


    /**
     * Tests the remove(x y w h) method with a nonexisting rec
     */
    public void testRemoveNonRec() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");
        cp.processor("insert a 50 21 52 1");

        cp.processor("remove 55 20 55 10");
        cp.processor("dump");
        String out = db.remove(55, 20, 55, 10);

        String expected = "Rectangle not found: (55, 20, 55, 10)";

        assertEquals(expected, out);
    }


    /**
     * Tests the remove(x y w h) method with invalid coord
     */
    public void testRemoveInvalidCoord() {
        cp.processor("insert a 50 21 52 1");
        cp.processor("insert b 10 9 2 4");
        cp.processor("remove -55 -20 55 10");

        String out = db.remove(-55, -20, 55, 10);

        String expected = "Rectangle rejected: (-55, -20, 55, 10)";

        assertEquals(expected, out);

    } 

// /**
// * Tests the get
// */
// public void testGetTree()
// {
// BST<KVPair<String, Rectangle>> tree = db.getTree();
// assertNotNull(tree);
// }

}
