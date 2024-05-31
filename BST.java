import java.util.Iterator;

/**
 * Stub for binary search tree class
 * We use generics here because we want this BST to be able to hold more than
 * just Rectangles (or KVPairs)
 * 
 * @author {Your Name Here}
 * @param <T>
 *            the generic type; extends Comparable
 */
public class BST<T extends Comparable<T>> implements Iterable<BSTNode<T>> {
    /** The root. */
    private BSTNode<T> root;

    /** The size. */
    private int size;

    /**
     * Instantiates a new Binary Search Tree.
     */
    public BST() {
        root = null;
        size = 0;
    }


    /**
     * Size of the tree
     *
     * @return the tree size as an int
     */
    public int size() {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Inserts a given node with the given value
     * 
     * @param e
     *            the value to be inserted
     */
    public void insert(T e) {
        root = insertHelper(e, root);
        size++;
    }
    
 // ----------------------------------------------------------
    /**
     * Remove the specified value from the tree.
     *
     * @param x
     *            the item to remove
     */
    public void remove(T x)
    {
        root = removeHelper(root, x);
    }


    // ----------------------------------------------------------
    /**
     * Private method to insert a value into a subtree.
     *
     * @param x
     *            the item to insert.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BSTNode<T> insertHelper(T x, BSTNode<T> node) {
        if (node == null) {
            return new BSTNode<T>(x);
        }
        if (node.getValue().compareTo(x) >= 0) {
            node.setLeft(insertHelper(x, node.getLeft()));
        }
        else {
            node.setRight(insertHelper(x, node.getRight()));
        }
        return node;
    }


    private T findHelper(BSTNode<T> rt, T key) {
        if (rt == null) {
            return null;
        }
        if (rt.getValue().compareTo(key) > 0) {
            return findHelper(rt.getLeft(), key);
        }
        else if (rt.getValue().compareTo(key) == 0) {
            return rt.getValue();
        }
        else {
            return findHelper(rt.getRight(), key);
        }
    }


    private BSTNode<T> deleteMax(BSTNode<T> rt)
    {
        if (rt.getRight() == null)
        {
            return rt.getLeft();
        }
        rt.setRight(deleteMax(rt.getRight()));
        return rt;
        
    }


    /**
     * Private method to find the largest value in
     *
     * @param node
     *            root node
     * @return node containing the largest item.
     */
    private BSTNode<T> getMax(BSTNode<T> node) {
        if (node == null) {
            return null;
        }
        else if (node.getRight() == null) {
            return node;
        }
        else {
            return getMax(node.getRight());
        }
    }


    private BSTNode<T> removeHelper(BSTNode<T> rt, T key)
    {
        if (rt == null)
        {
            return null;
        }
        if(rt.getValue().compareTo(key) > 0)
        {
            rt.setLeft(removeHelper(rt.getLeft(), key));
        }
        else if(rt.getValue().compareTo(key) < 0)
        {
            rt.setRight(removeHelper(rt.getRight(), key));
        }
        else
        {
            if(rt.getLeft() == null)
            {
                return rt.getRight();
            }
            else if(rt.getRight() == null)
            {
                return rt.getLeft();
            }
            else
            {
                BSTNode<T> temp = getMax(rt.getLeft());
                rt.setValue(temp.getValue());
                rt.setLeft(deleteMax(rt.getLeft()));
            }
            
        }
        return rt;
    }
    
    /**
     * This method dumps the structure and values of the BST to console
     * dump() initiates recursive traversal
     */
    public void dump() {
        dumpRec(root, 0);
        System.out.println("BST size is: " + size());
    }
    /**
     * Performs in-order traversal
     * printing node depth and value
     * 
     * @param root
     * @param depth
     * 
     */
    private void dumpRec(BSTNode<T> root, int depth) {
        if (root == null)
            return;
        dumpRec(root.getLeft(), depth + 1);
        System.out.println("Node has depth " + depth + ", Value " + root.getValue());
        dumpRec(root.getRight(), depth + 1);
    }


    /**
     * This is an auto-generated method stub for an iterator object because we
     * have implemented Iterable to traverse the BST (perhaps during the
     * intersections command)
     */
    @Override
    public Iterator<BSTNode<T>> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
