import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using linked
 *  elements. The linked structure is circular and uses a dummy node.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class LinearFrequencyTable implements FrequencyTable {

    // Linked elements

    private static class Elem {

    	private String key;
    	private long count;
    	private Elem previous;
    	private Elem next;

    	private Elem(String key, Elem previous, Elem next) {
    	    this.key = key;
    	    this.count = 0;
    	    this.previous = previous;
    	    this.next = next;
    	}

    }

    private Elem head;
    private int size;

    /** Constructs and empty <strong>FrequencyTable</strong>.
     */

    public LinearFrequencyTable() {
	head = new Elem(null, null, null); // dummy node
	head.previous = head; // making the dummy node circular
	head.next = head; // making the dummy node circular
	size = 0;
    }

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */

    public int size() {
	   return size;
    }
  
    /** Returns the frequency value associated with this key.
     *
     *  @param key key whose frequency value is to be returned
     *  @return the frequency associated with this key
     *  @throws NoSuchElementException if the key is not found
     */

    public long get(String key) throws NoSuchElementException{

        Elem p = head.next;
        if (p != null) {
            while (key.compareTo(p.key) != 0) {
                if (p.next == null) {
                    throw new NoSuchElementException("No Key Matches");
                }
                p = p.next;
            }
        }
        else {
            System.out.println("Empty List");
        } 
        return (p.count);   

    }

    /** Creates an entry in the frequency table and initializes its
     *  count to zero. The keys are kept in order (according to their
     *  method <strong>compareTo</strong>).
     *
     *  @param key key with which the specified value is to be associated
     *  @throws IllegalArgumentException if the key was alreaddy present
     */

    public void init(String key) {
        Elem q = head.next;
        Elem after;
        if (q != null) {
            while (q.next != head && key.compareTo(q.next.key) >= 0) {
                q = q.next;
            }
            after = q.next;
            q.next = new Elem(key, q, after);
            after.previous = q.next;

        }   
        else {
            head.next = new Elem(key, head, null);
        }
        size++;

    }

    /** The method updates the frequency associed with the key by one.
     *
     *  @param key key with which the specified value is to be associated
     *  @throws NoSuchElementException if the key is not found
     */

    public void update(String key) throws NoSuchElementException{
	
	    Elem p = head.next;
        if (p != null) {
            while ((key.compareTo(p.key)) != 0) {
                if (p.next == null) {
                    throw new NoSuchElementException("No Key Matches");
                }
                p = p.next;
            }
        }
        else {
            System.out.println("Empty List");
        }
        p.count++;

    }

    /** Returns the list of keys in order, according to the method
     *  <strong>compareTo</strong> of the key objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {

        LinkedList<String> list = new LinkedList<String>();
        Elem p = head.next;
        if (p != null) {
            while (p.next != null) {
                list.addLast(p.key);
            }
        }
        else {
            System.out.println("Empty List");
        }
        return (list);

    }

    /** Returns an array containing the frequencies of the keys in the
     *  order specified by the method <strong>compareTo</strong> of
     *  the key objects.
     *
     *  @return an array of frequency counts
     */

    public long[] values() {

        Elem p = head.next;
        long[] list = new long[size];
        if (p != null) {
            for (int i = 0; i < size; i++) {
                list[i] = p.count;
                p = p.next;
            }
        }
        else {
            System.out.println("Empty List");
        }
        return (list);

    }

    /** Returns a <code>String</code> representations of the elements
     * of the frequency table.
     *  
     *  @return the string representation
     */

    public String toString() {

	StringBuffer str = new StringBuffer("{");
	Elem p = head.next;

	while (p != head) {
	    str.append("{key="+p.key+", count="+p.count+"}");
	    if (p.next != head) {
		str.append(",");
	    }
	    p = p.next;
	}
	str.append("}");
	return str.toString();
    }

}