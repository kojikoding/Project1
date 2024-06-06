/*
 * Useful Testing Links
 * https://web-cat.org/eclstats/junit-quickstart/
 * https://lti.cs.vt.edu/LTI_ruby/Books/CS3/html/mutationtesting.html
 */
//
import java.util.Iterator;
import student.TestCase;


// -------------------------------------------------------------------------
/**
 * This class tests the methods of BST class
 *
 * @author CS Staff
 * @version 2024-05-22
 */
public class BSTTest extends student.TestCase {

    private BST<String> bst;
   

    /**
     * setUp the condition.
     */
    public void setUp() {
        // Nothing to setup here. May not be true for your tests
        bst = new BST<>();
    }


    /**
     * This defines an assertFuzzyContains method that you could use to test your code
     * 
     * @param m the message for the assertion
     * @param line the line of text to check
     * @param substrs the substrings to check for
     */
    public void assertFuzzyContains(String m, String line, String... substrs) {
        assertTrue(m, fuzzyContains(line, substrs));
    }

    /**
     * Utility method to check if the given line contains all substrings
     * 
     * @param line the line of text to check
     * @param substrs the substrings to check for
     * @return true if all substrings are found in the line, false otherwise
     */
    public boolean fuzzyContains(String line, String... substrs) {
        for (String substr : substrs) {
            if (!line.contains(substr)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test tree not null
     */
    public void testBasic() {
        BST<String> theTree = new BST<>();
        assertNotNull(theTree);
    }

    /**
     * Test tree size in empty tree
     */
    public void testBST() {
        assertEquals("empty BST size should be zero", 0, bst.size());
    }

    /**
     * Test inserting a single element into the BST
     */
    public void testInsertSingle() {
        bst.insert("hello");
        assertEquals("BST size should be one after one insert", 1, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value hello");
    }

<<<<<<< HEAD
    /**
     * Test inserting multiple elements into the BST
     */
    public void testInsertMultiple() {
        bst.insert("hello");
        bst.insert("world");
        bst.insert("apple");
        assertEquals("BST size should be three after three inserts", 3, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value hello");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value apple");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value world");
    }

    /**
     * Test inserting duplicate elements into the BST
     */
    public void testInsertDuplicate() {
        bst.insert("hello");
        bst.insert("hello");
        assertEquals("BST size should be two after inserting duplicate", 2, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain first 'hello'", dumpOutput, "Node has depth 0", "Value hello");
        assertFuzzyContains("BST dump should contain duplicate 'hello'", dumpOutput, "Node has depth 1", "Value hello");
    }

    /**
     * Test searching in the BST
     */
    public void testSearch() {
        bst.insert("hello");
        bst.insert("world");
        bst.insert("apple");

        assertEquals("Search should find 'hello'", "hello", bst.search("hello"));
        assertEquals("Search should find 'world'", "world", bst.search("world"));
        assertEquals("Search should find 'apple'", "apple", bst.search("apple"));
        assertNull("Search should not find 'banana'", bst.search("banana"));
    }

    /**
     * Test removing from the BST
     */
    public void testRemove() {
        bst.insert("hello");
        bst.insert("world");
        bst.insert("apple");
        bst.insert("banana");
        bst.insert("cherry");

        // Remove leaf node
        bst.remove("banana");
        assertEquals("BST size should be four after removing leaf", 4, bst.size());
        assertNull("Search should not find removed leaf 'banana'", bst.search("banana"));

        // Remove node with one child
        bst.remove("cherry");
        assertEquals("BST size should be three after removing node with one child", 3, bst.size());
        assertNull("Search should not find removed node 'cherry'", bst.search("cherry"));

        // Remove node with two children
        bst.remove("hello");
        assertEquals("BST size should be two after removing node with two children", 2, bst.size());
        assertNull("Search should not find removed node 'hello'", bst.search("hello"));

        // Remove root node
        bst.remove("world");
        assertEquals("BST size should be one after removing root node", 1, bst.size());
        assertNull("Search should not find removed root 'world'", bst.search("world"));

        // Remove the last node
        bst.remove("apple");
        assertEquals("BST size should be zero after removing the last node", 0, bst.size());
        assertNull("Search should not find removed node 'apple'", bst.search("apple"));
    }

    /**
     * Test dumping the BST structure
     */
    public void testDump() {
        bst.insert("hello");
        bst.insert("world");
        bst.insert("apple");

        String dumpOutput = bst.dump();
        assertFuzzyContains("Dump output should contain root node", dumpOutput, "Node has depth 0", "Value hello");
        assertFuzzyContains("Dump output should contain left child", dumpOutput, "Node has depth 1", "Value apple");
        assertFuzzyContains("Dump output should contain right child", dumpOutput, "Node has depth 1", "Value world");
    }

    // Additional tests for improved coverage

    /**
     * Test inserting null
     */
    public void testInsertNull() {
        try {
            bst.insert(null);
            fail("Should throw IllegalArgumentException when inserting null");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Test removing from an empty BST
     */
    public void testRemoveFromEmpty() {
        bst.remove("nothing");
        assertEquals("BST size should still be zero", 0, bst.size());
    }

    /**
     * Test searching in an empty BST
     */
    public void testSearchEmpty() {
        assertNull("Search should return null in an empty BST", bst.search("nothing"));
    }

    /**
     * Test removing non-existent element
     */
    public void testRemoveNonExistent() {
        bst.insert("hello");
        bst.remove("nonexistent");
        assertEquals("BST size should still be one", 1, bst.size());
    }

    /**
     * Test searching for a non-existent element
     */
    public void testSearchNonExistent() {
        bst.insert("hello");
        assertNull("Search should return null for non-existent element", bst.search("nonexistent"));
    }

    /**
     * Test iterator
     */
    /**
     * Test iterator
     */
    public void testIterator() {
        bst.insert("hello");
        bst.insert("world");
        bst.insert("apple");

        Iterator<BSTNode<String>> iterator = bst.iterator();
        assertTrue("Iterator should have next", iterator.hasNext());
        assertEquals("Iterator next should return 'apple'", "apple", iterator.next().getValue());
        assertTrue("Iterator should have next", iterator.hasNext());
        assertEquals("Iterator next should return 'hello'", "hello", iterator.next().getValue());
        assertTrue("Iterator should have next", iterator.hasNext());
        assertEquals("Iterator next should return 'world'", "world", iterator.next().getValue());
        assertFalse("Iterator should not have next", iterator.hasNext());
    }
=======
>>>>>>> branch 'main' of git@github.com:kojikoding/Project1.git


    /**
     * Test inserting elements in descending order
     */
<<<<<<< HEAD
    public void testInsertDescending() {
        bst.insert("z");
        bst.insert("y");
        bst.insert("x");
        assertEquals("BST size should be three after inserting three elements", 3, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value z");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value y");
        assertFuzzyContains("BST dump should contain left grandchild", dumpOutput, "Node has depth 2", "Value x");
=======
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
>>>>>>> branch 'main' of git@github.com:kojikoding/Project1.git
    }

    /**
     * Test inserting elements in ascending order
     */
    public void testInsertAscending() {
        bst.insert("a");
        bst.insert("b");
        bst.insert("c");
        assertEquals("BST size should be three after inserting three elements", 3, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value a");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value b");
        assertFuzzyContains("BST dump should contain right grandchild", dumpOutput, "Node has depth 2", "Value c");
    }

    /**
     * Test inserting and removing multiple elements
     */
    public void testInsertAndRemoveMultiple() {
        bst.insert("a");
        bst.insert("b");
        bst.insert("c");
        bst.insert("d");
        bst.insert("e");

        assertEquals("BST size should be five after inserting five elements", 5, bst.size());

        bst.remove("c");
        bst.remove("e");
        assertEquals("BST size should be three after removing two elements", 3, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value a");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value b");
        assertFuzzyContains("BST dump should contain right grandchild", dumpOutput, "Node has depth 2", "Value d");
    }

    /**
     * Test removing the root node
     */
    public void testRemoveRoot() {
        bst.insert("root");
        bst.insert("left");
        bst.insert("right");

        assertEquals("BST size should be three before removing root", 3, bst.size());
        bst.remove("root");
        assertEquals("BST size should be two after removing root", 2, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain new root node", dumpOutput, "Node has depth 0", "Value left");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value right");
    }

    /**
     * Test inserting minimum and maximum values
     */
    public void testInsertMinMax() {
        bst.insert("a");
        bst.insert("z");
        assertEquals("BST size should be two after inserting min and max", 2, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value a");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value z");
    }

    /**
     * Test removing minimum and maximum values
     */
    public void testRemoveMinMax() {
        bst.insert("a");
        bst.insert("z");
        bst.remove("a");
        assertEquals("BST size should be one after removing min", 1, bst.size());
        bst.remove("z");
        assertEquals("BST size should be zero after removing max", 0, bst.size());
    }

    /**
     * Test removing nodes with complex tree structure
     */
    public void testRemoveComplex() {
        bst.insert("m");
        bst.insert("a");
        bst.insert("z");
        bst.insert("b");
        bst.insert("y");

        assertEquals("BST size should be five after inserting five elements", 5, bst.size());

        bst.remove("m");
        assertEquals("BST size should be four after removing root", 4, bst.size());
        assertNull("Search should not find removed root 'm'", bst.search("m"));

        bst.remove("a");
        assertEquals("BST size should be three after removing left child", 3, bst.size());
        assertNull("Search should not find removed left child 'a'", bst.search("a"));

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain new root node", dumpOutput, "Node has depth 0", "Value b");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value y");
        assertFuzzyContains("BST dump should contain right grandchild", dumpOutput, "Node has depth 2", "Value z");
    }

    /**
     * Test edge cases
     */
    public void testEdgeCases() {
        bst.insert("");
        bst.insert(" ");
        assertEquals("BST size should be two after inserting edge cases", 2, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value ");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value ");
    }

    /**
     * Test inserting into a balanced tree
     */
    public void testInsertBalanced() {
        bst.insert("m");
        bst.insert("c");
        bst.insert("t");
        bst.insert("a");
        bst.insert("b");

        assertEquals("BST size should be five after inserting into balanced tree", 5, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value m");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value c");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value t");
        assertFuzzyContains("BST dump should contain left grandchild", dumpOutput, "Node has depth 2", "Value a");
        assertFuzzyContains("BST dump should contain right grandchild", dumpOutput, "Node has depth 2", "Value b");
    }

    /**
     * Test removing from a balanced tree
     */
    public void testRemoveBalanced() {
        bst.insert("m");
        bst.insert("c");
        bst.insert("t");
        bst.insert("a");
        bst.insert("b");

        bst.remove("c");
        assertEquals("BST size should be four after removing from balanced tree", 4, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value m");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value b");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value t");
        assertFuzzyContains("BST dump should contain left grandchild", dumpOutput, "Node has depth 2", "Value a");
    }

    /**
     * Test iterator on an empty tree
     */
    public void testIteratorEmpty() {
        Iterator<BSTNode<String>> iterator = bst.iterator();
        assertFalse("Iterator should not have next on an empty tree", iterator.hasNext());
    }

    /**
     * Test inserting large number of elements
     */
    public void testInsertMany() {
        for (int i = 0; i < 100; i++) {
            bst.insert("elem" + i);
        }
        assertEquals("BST size should be 100 after inserting 100 elements", 100, bst.size());

        String dumpOutput = bst.dump();
        for (int i = 0; i < 100; i++) {
            assertFuzzyContains("BST dump should contain 'elem" + i + "'", dumpOutput, "Value elem" + i);
        }
    }

    /**
     * Test removing large number of elements
     */
    public void testRemoveMany() {
        for (int i = 0; i < 100; i++) {
            bst.insert("elem" + i);
        }
        for (int i = 0; i < 100; i++) {
            bst.remove("elem" + i);
        }
        assertEquals("BST size should be 0 after removing all elements", 0, bst.size());

        String dumpOutput = bst.dump();
        for (int i = 0; i < 100; i++) {
            assertFalse("BST dump should not contain 'elem" + i + "'", dumpOutput.contains("Value elem" + i));
        }
    }

    /**
     * Test iterator edge cases
     */
    public void testIteratorEdgeCases() {
        bst.insert("middle");
        bst.insert("left");
        bst.insert("right");

        Iterator<BSTNode<String>> iterator = bst.iterator();
        assertTrue("Iterator should have next", iterator.hasNext());
        assertEquals("Iterator next should return 'left'", "left", iterator.next().getValue());
        assertTrue("Iterator should have next", iterator.hasNext());
        assertEquals("Iterator next should return 'middle'", "middle", iterator.next().getValue());
        assertTrue("Iterator should have next", iterator.hasNext());
        assertEquals("Iterator next should return 'right'", "right", iterator.next().getValue());
        assertFalse("Iterator should not have next", iterator.hasNext());
    }
    /**
     * Test inserting elements with similar prefixes
     */
    public void testInsertSimilarPrefixes() {
        bst.insert("apple");
        bst.insert("apricot");
        bst.insert("banana");
        bst.insert("applesauce");

        assertEquals("BST size should be four after inserting elements with similar prefixes", 4, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain root node", dumpOutput, "Node has depth 0", "Value apple");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value applesauce");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value apricot");
        assertFuzzyContains("BST dump should contain right grandchild", dumpOutput, "Node has depth 2", "Value banana");
    }

    /**
     * Test removing elements with similar prefixes
     */
    public void testRemoveSimilarPrefixes() {
        bst.insert("apple");
        bst.insert("apricot");
        bst.insert("banana");
        bst.insert("applesauce");

        bst.remove("apple");
        assertEquals("BST size should be three after removing 'apple'", 3, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain new root node", dumpOutput, "Node has depth 0", "Value apricot");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value applesauce");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value banana");
    }

    /**
     * Test edge cases for removing the only element in the tree
     */
    public void testRemoveOnlyElement() {
        bst.insert("only");
        assertEquals("BST size should be one after inserting one element", 1, bst.size());

        bst.remove("only");
        assertEquals("BST size should be zero after removing the only element", 0, bst.size());
        assertNull("Search should not find the removed element", bst.search("only"));
    }

    /**
     * Test edge cases for removing the root when it has two children
     */
    public void testRemoveRootWithTwoChildren() {
        bst.insert("root");
        bst.insert("left");
        bst.insert("right");

        bst.remove("root");
        assertEquals("BST size should be two after removing the root", 2, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain new root node", dumpOutput, "Node has depth 0", "Value left");
        assertFuzzyContains("BST dump should contain right child", dumpOutput, "Node has depth 1", "Value right");
    }

    /**
     * Test edge cases for complex tree reorganization
     */
    public void testComplexReorganization() {
        bst.insert("m");
        bst.insert("c");
        bst.insert("t");
        bst.insert("a");
        bst.insert("e");
        bst.insert("r");
        bst.insert("z");
        bst.insert("b");
        bst.insert("d");
        bst.insert("f");
        bst.insert("s");
        bst.insert("u");

        assertEquals("BST size should be twelve after complex insertion", 12, bst.size());

        bst.remove("t");
        assertEquals("BST size should be eleven after removing node with two children", 11, bst.size());

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain new subtree root", dumpOutput, "Node has depth 0", "Value m");
        assertFuzzyContains("BST dump should contain left child", dumpOutput, "Node has depth 1", "Value c");
        assertFuzzyContains("BST dump should contain right subtree root", dumpOutput, "Node has depth 1", "Value r");
        assertFuzzyContains("BST dump should contain remaining children", dumpOutput, "Node has depth 2", "Value s");
        assertFuzzyContains("BST dump should contain right grandchild", dumpOutput, "Node has depth 2", "Value u");
    }

    /**
     * Test dumping the BST structure with a single element
     */
    public void testDumpSingleElement() {
        bst.insert("single");

        String dumpOutput = bst.dump();
        assertFuzzyContains("BST dump should contain the single element", dumpOutput, "Node has depth 0", "Value single");
    }

    /**
     * Test iterator with only one element
     */
    public void testIteratorSingleElement() {
        bst.insert("single");

        Iterator<BSTNode<String>> iterator = bst.iterator();
        assertTrue("Iterator should have next", iterator.hasNext());
        assertEquals("Iterator next should return 'single'", "single", iterator.next().getValue());
        assertFalse("Iterator should not have next", iterator.hasNext());
    }

    /**
     * Test iterator on a large balanced tree
     */
    public void testIteratorLargeBalanced() {
        for (int i = 0; i < 100; i++) {
            bst.insert("elem" + i);
        }
        assertEquals("BST size should be 100 after inserting 100 elements", 100, bst.size());

        Iterator<BSTNode<String>> iterator = bst.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals("Iterator should have traversed 100 elements", 100, count);
    }

    /**
     * Test removing non-existent element in large tree
     */
    public void testRemoveNonExistentInLargeTree() {
        for (int i = 0; i < 100; i++) {
            bst.insert("elem" + i);
        }
        bst.remove("nonexistent");
        assertEquals("BST size should still be 100", 100, bst.size());
    }
}
