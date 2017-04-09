import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using a
 *  binary search tree.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class TreeFrequencyTable implements FrequencyTable {

    // Stores the elements of this binary search tree (frequency
    // table)
    
    private static class Elem {
    
        private String key;
        private long count;
    
        private Elem left;
        private Elem right;
    
        private Elem(String key) {
            this.key = key;
            this.count = 0;
            left = null;
            right = null;
        }
    }

    private Elem root = null; // A reference to the root element
    private int size = 0; // The size of the tree

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */
    
    public int size() {
        return size;
    }
  
    /** Creates an entry in the frequency table and initializes its
     *  count to zero.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void init(String key) {
        Elem p = new Elem(key);
        if (root == null) {
            root = p;
        }
        Elem current = root;
        Elem parent = null;
        while(current != null) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
                size++;
                if (current == null) {
                    parent.left = p;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = p;
                }
            }
            
        }
    }
  
    /** The method updates the frequency associed with the key by one.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void update(String key) throws NoSuchElementException{
        
        Elem p = root;
        if (p != null) {
            while (p != null) {
                if (p == null) {
                    throw new NoSuchElementException("No Key Matches");
                }
                if (key == p.key) {
                    p.count++;
                } else if (key.compareTo(p.key) < 0) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
        } else {
            System.out.println("Empty List");
        }

    }
  
    /**
     * Looks up for key in this TreeFrequencyTable, returns associated value.
     *
     * @param key value to look for
     * @return value the value associated with this key
     * @throws NoSuchElementException if the key is not found
     */
  
    public long get(String key) throws NoSuchElementException{
        long counter = 0;
        Elem p = root;
        if (p != null) {
            while (p != null) {
                if (p == null) {
                    throw new NoSuchElementException("No Key Matches");
                }
                if (key.compareTo(p.key) == 0) {
                    counter = (p.count);
                } else if (key.compareTo(p.key) < 0) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
        } else {
            System.out.println("Empty List");
        }
        return (counter);

    }
  
    /** Returns the list of keys in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {

        LinkedList<String> list = new LinkedList<String>();
        list = keys(root, list);
        return list;

    }
    private LinkedList<String> keys(Elem p, LinkedList<String> linked) {

        if (p != null) {
            keys(p.left, linked);
            linked.addLast(p.key);
            keys(p.right, linked);
        }
        return (linked);


    }

    /** Returns the values in the order specified by the method compareTo of the key
     *  objects.
     *
     *  @return the values
     */

    public long[] values() {

	   long[] list = new long[size];
       treeToList(root, list, 0);
       return (list);

    }

    private int treeToList(Elem e, long[] list, int p) {
    if (e.left != null) {
        p = treeToList(e.left, list, p);
    }
    list[p++] = e.count;
    if (e.right != null) {
        p = treeToList(e.right, list, p);
    }
    return (p);
}

    /** Returns a String representation of the tree.
     *
     * @return a String representation of the tree.
     */

    public String toString() {
        return toString( root );
    }

    // Helper method.
  
    private String toString(Elem current) {
    
        if (current == null) {
            return "{}";
        }
    
        return "{" + toString(current.left) + "[" + current.key + "," + current.count + "]" + toString(current.right) + "}";
    }

    
  
}