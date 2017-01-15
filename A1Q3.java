/**
 * The class <b>A1Q3</b> implements a static function 
 * that counts the number of positive integers
 * in an array
 *
 * @author Marcel Turcotte (turcotte@eecs.uottawa.ca)
 *
 */


public class A1Q3 {

	/** 
     * Returns the longest run of consecutive identical elements in elems.
     * We assume that the list is not null.
     * 
     *   @param elems the list of integers
     *   @return size of the longest run
     */
 
    private static int getLongestRun(int[] elems) {

// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        int prev = 0; 
        int size = 1;
        int max_size = 1;
        for (int i = 1; i < elems.length; i++) {
            if (elems[i] == elems[prev]) {
                size++;
                if (size > max_size) {
                    max_size = size; 
                }
            }
            else {
                size = 1;
            }
            prev = i;
        }
        return (max_size);
    }

 	/**
     * The main method of this program. Gets an array of
     * strings as input parameter. The array is assumed to
     * be non-null, and all the strings in the array are
     * parsable as integer.
     *
     * The function prints out the longest run of consecutive 
     * identical integers parsed in args
     * @param args space-separated list of strings parsable as integers
	 */    

 	public static void main(String[] args) {

// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
        int[] intargs = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            intargs[i] = Integer.parseInt(args[i]);
        }
        if (intargs.length > 0) {
            int x = A1Q3.getLongestRun(intargs);
            System.out.println(x);
        }
        else {
            System.out.println(0);
        }
    }
}
