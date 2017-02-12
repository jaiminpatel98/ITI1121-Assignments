/**
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class BirthdayParadox {


	/** 
     * Random generator 
     */
	private static java.util.Random generator = new java.util.Random();


	/** 
     * Runs the series of experiments, and stores the result into
     * a Statistics object
     * 
     * @param range the size of the set from which random number are drawn
     * @param numberOfRuns the number of experiments to run
	 *
	 * @return a reference to a Statistics instance that holds the result
	 * of the experiment
     */
 	public static Statistics runExperiments(int range, int numberOfRuns){
          int exp[] = new int[range];
          int count = 0;
          boolean flag = false;
          for (int i=0; i<numberOfRuns; i++) {
               for (int k=0; k<range; k++) {
                    exp[k] = generator.nextInt((365)+1);

               }
               for (int j=0; j<range; j++) {
                    for (int l=j+1; l<range; l++) {
                         if (exp[j] == exp[l]) {
                              flag = true;
                              count++;
                              break;
                         }
                    }
                    if (flag) {
                         flag = false;
                         break;
                    }
               }
               int min = 365;
               int max = 1;
               for (int x=0; x<range; x++) {
                    if (exp[x] > max) {
                         max = exp[x];
                    }
                    if (exp[x] < min) {
                         min = exp[x];
                    }
               }
          }
          //turn data in a Statistic
	}

 	/** 
     * Runs a single experiment.
     * The parameter range defines the size of the set from which
     * the experiment is drawn
     * 
     * @param range the size of the set from which random number are drawn
     *
	 * @return the number of random draw in the set that the method 
	 * used before drawing the same element for the second time
     */
	
 	private static int oneRun(int range){

// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

	}
	

	/** 
     * Main method. The default size of the set is 365, and
     * the experiment is run 50 times. Both numbers can be reset
     * from the command line.
     * This method runs the experiments and prints the
     * resulting Statistics
     * 
     * @param args if not empty, contains the runtime values for
     * the size of the set and the number of runs
     */
	public static void main(String[] args) {

// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

	}

}