import java.util.List;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This class tests the methods of BST class
 *
 * @author CS Staff
 * @version 2024-05-22
 */
public class BSTTest2 extends student.TestCase {

    private BST<String> bst;

    /**
     * setUp the condition.
     */
    public void setUp() {
        // Nothing to setup here. May not be true for your tests
        bst = new BST<String>();
        bst.insert("gas");
        bst.insert("bar");
        bst.insert("cat");

    }


    /**
     * This defines an assertFuzzyContains method that you could use to test
     * your code
     * 
     * @param m
     * @param line
     * @param substrs
     */
    public void assertFuzzyContains(String m, String line, String... substrs) {
        assertTrue(m, fuzzyContains(line, substrs));
    }


    /**
     * Example 1: Test tree not null
     */
    public void testBasic() {
        BST<String> theTree = new BST<String>();
        assertNotNull(theTree);
    }

//
// /**
// * Example 2: Test tree size in empty tree
// */
// public void testBST() {
// assertEquals("empty BST size should be zero", 0, bst.size());
// }


    /**
     * Example 4:
     */
    public void testFuzzy() {
        /*
         * this will not run until you implement some dump method for your BST
         * but you can assert that some text exists in an output of a dump
         * 
         */

        bst = new BST<String>();
        bst.insert("hello");

        String[] dumps = bst.dump().split("\n");
        assertFuzzyContains("BST dump should have one real node", dumps[1],
            "Node has depth", "Value (hello)");
    }


    /**
     * Tests to see if the inserts in setUp works
     */
    public void testInsert() {
        assertEquals(3, bst.size());
        assertEquals("(bar, cat, gas)", bst.toString());
    }

    /**
     * Tests to see if remove works correctly for leaf node 
     */
    public void testRemoveLeafNode() {

        assertEquals("cat", bst.remove("cat"));
        assertEquals(2, bst.size());
        assertTrue(bst.find("car").isEmpty());
        assertEquals("(bar, gas)", bst.toString());
    }
    
    /**
     * Tests to see if remove works correctly with a node with one child
     */
    public void testOneChildNodeLeft()
    {
        
        assertEquals("bar", bst.remove("bar"));
        assertEquals(2, bst.size());
        assertEquals("(cat, gas)", bst.toString());
        
    }  
    
    /**
     * Tests to see if removes works correctly with a one child
     */
    public void testOneChildNodeRight()
    {
        bst.insert("joke");
        bst.insert("horse");
        assertEquals("joke", bst.remove("joke"));
        assertEquals("(bar, cat, gas, horse)", bst.toString());
        
    }
    
    /**
     * Tests to see if remove works correctly with a node with two children
     */
    public void testTwoChildNodeLeft()
    {
        bst.insert("abc");
        assertEquals(4, bst.size());
        assertEquals("(abc, bar, cat, gas)", bst.toString());
        
        assertEquals("bar", bst.remove("bar"));
        assertEquals("(abc, cat, gas)", bst.toString());
        
    } 
    
    /**
     * Tests to see if remove works correctly with a node with two children
     */
    public void testTwoChildNodeRight()
    {
        bst.insert("joke");
        bst.insert("horse");
        bst.insert("lion");
        assertEquals("joke", bst.remove("joke"));
        assertEquals("(bar, cat, gas, horse, lion)", bst.toString());
        
    }
    
    /**
     * Tests to see if remove works correctly when root is deleted
     */
    public void testRemoveRoot()
    {
        assertEquals("gas", bst.remove("gas"));
        assertEquals(2, bst.size()); 
        assertTrue(bst.find("gas").isEmpty());
        assertEquals("(bar, cat)", bst.toString());
    }
    
    /**
     * Tests remove with null root 
     */
    public void testRemoveNullR()
    {
        assertEquals(3, bst.size()); 
        assertNull(bst.remove("nonexistent"));
        assertEquals("(bar, cat, gas)", bst.toString());
        
        bst = new BST<String>();
        assertEquals(0, bst.size()); 
        assertNull(bst.remove("bruh")); 
        assertEquals("()", bst.toString());
        
    }
    
    /**
     * 
     */
    
    public void testRemoveNodeWithTwoChildrenComplex() {
        bst = new BST<String>();
        bst.insert("mango");
        bst.insert("banana");
        bst.insert("cherry");
        bst.insert("apple");
        bst.insert("blueberry");
        bst.insert("grape");
        bst.insert("peach");

        // mango has two children: banana (left) and peach (right)
        assertEquals("mango", bst.remove("mango"));
        assertEquals(6, bst.size());
        assertTrue(bst.find("mango").isEmpty());
        
        assertEquals("(apple, banana, blueberry, cherry, grape, peach)", bst.toString());
    }
     
    /**
     * Tests to see if find works correctly
     */
    public void testFind() {
        List<String> found = bst.find("gas");
        assertEquals(1, found.size());
        assertEquals("gas", found.get(0));

        found = bst.find("nonexistent");
        assertTrue(found.isEmpty());
      
         
    }
    
   
    

    
    
   
    
    

}
