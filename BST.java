import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Stub for binary search tree class
 * We use generics here because we want this BST to be able to hold more than
 * just Rectangles (or KVPairs)
 * 
 * @author {Patrick Hardy}
 * @version June 6, 2024
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
        if (e == null) {
            throw new IllegalArgumentException("Cannot insert null value into BST!");
        }
        root = insertHelper(e, root);
        size++;
    }
    
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
    
 // ----------------------------------------------------------
    /**
     * Remove the specified value from the tree.
     *
     * @param x
     *            the item to remove
     */
    public void remove(T x) {
        boolean[] isRemoved = new boolean[1];
        root = removeHelper(root, x, isRemoved);
        if (isRemoved[0]) {
            size--;
        }
    }
    /**
     * Private method to remove a value from a subtree.
     *
     * @param rt the root of the subtree.
     * @param key the item to remove.
     * @return the new root of the subtree.
     */
    private BSTNode<T> removeHelper(BSTNode<T> rt, T key, boolean[] isRemoved) {
        if (rt == null) {
            return null;
        }
        if (rt.getValue().compareTo(key) > 0) {
            rt.setLeft(removeHelper(rt.getLeft(), key, isRemoved));
        } else if (rt.getValue().compareTo(key) < 0) {
            rt.setRight(removeHelper(rt.getRight(), key, isRemoved));
        } else {
            isRemoved[0] = true;
            if (rt.getLeft() == null) {
                return rt.getRight();
            } else if (rt.getRight() == null) {
                return rt.getLeft();
            } else {
                BSTNode<T> temp = getMax(rt.getLeft());
                rt.setValue(temp.getValue());
                rt.setLeft(deleteMax(rt.getLeft()));
            }
        }
        return rt;
    }
    
    /**
     * Search for a value in the tree.
     *
     * @param key the item to search for
     * @return the item if found, null otherwise
     */
    public T search(T key) {
        return findHelper(root, key);
    }
    
    /**
     * Private method to find a value in a subtree.
     *
     * @param rt the root of the subtree.
     * @param key the item to find.
     * @return the item if found, null otherwise
     */
    private T findHelper(BSTNode<T> rt, T key) {
        if (rt == null) {
            return null;
        }
        if (rt.getValue().compareTo(key) > 0) {
            return findHelper(rt.getLeft(), key);
        } else if (rt.getValue().compareTo(key) == 0) {
            return rt.getValue();
        } else {
            return findHelper(rt.getRight(), key);
        }
    }

    /**
     * Private method to delete the maximum value in a subtree.
     *
     * @param rt the root of the subtree.
     * @return the new root of the subtree.
     */
    private BSTNode<T> deleteMax(BSTNode<T> rt) {
        if (rt.getRight() == null) {
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
        
    /**
     * This method dumps the structure and values of the BST to console
     * dump() initiates recursive traversal
     */
    public String dump() {
        //dumpRec(root, 0);
        //System.out.println("BST size is: " + size());
        StringBuilder sb = new StringBuilder();
        dumpRec(root, 0, sb);
        sb.append("BST size is: ").append(size()).append("\n");
        String result = sb.toString();
        System.out.println(result); // Optional: keep printing for debugging
        return result;
    }
    /**
     * Performs in-order traversal
     * printing node depth and value
     * 
     * @param root
     * @param depth
     * 
     */
    private void dumpRec(BSTNode<T> root, int depth, StringBuilder sb) {
        if (root == null)
            return;
        dumpRec(root.getLeft(), depth + 1, sb);
        //System.out.println("Node has depth " + depth + ", Value " + root.getValue());
        sb.append("Node has depth ").append(depth).append(", Value ").append(root.getValue()).append("\n");
        dumpRec(root.getRight(), depth + 1, sb);
    }


    /**
     * This is an auto-generated method stub for an iterator object because we
     * have implemented Iterable to traverse the BST (perhaps during the
     * intersections command)
     */
    @Override
    public Iterator<BSTNode<T>> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<BSTNode<T>> {
        private Stack<BSTNode<T>> stack = new Stack<>();
        private BSTNode<T> current;

        public MyIterator() {
            current = root;
            pushLeft(current);
        }

        private void pushLeft(BSTNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public BSTNode<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode<T> node = stack.pop();
            if (node.getRight() != null) {
                pushLeft(node.getRight());
            }
            return node;
        }
    }
}



