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
    public void testInvalidCoord()
    {
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
     * Tests wehn revtangle os touching the border
     */
    public void testInBounds()
    {
        cp.processor("insert iso 1020 1020 4 4");
        String expectedOutput = "Rectangle accepted: iso 1020, 1020, 4, 4";
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

}
