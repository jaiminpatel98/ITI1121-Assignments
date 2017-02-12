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
          Statistics stat = new Statistics(numberOfRuns);
          boolean flag = false;
          for (int i=0; i<numberOfRuns; i++) {
               int count = 0;
               int n[] = new int[range];
               for (int k=0; k<range; k++) {
                    count++;
                    n[k] = generator.nextInt((range)+1);
                    for (int j=0; j<k; j++) {
                         if (n[k] == n[j]) {
                              stat.updateStatistics(count);
                              break;
                         }
                    }
                    break;
               }
          }
          return (stat);
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
          int exp[] = new int[range];
          int count = 0;
          boolean flag = false;
          for (int i=0; i<range; i++) {
               count++;
               exp[i] = generator.nextInt((range)+1);
               for (int k=0; k<i; k++) {
                    if (exp[i] == exp[k]) {
                         break;
                    }
               }
          }
          return (count);   
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
          int[] intargs = new int[args.length];
          for (int i = 0; i < args.length; i++) {
               intargs[i] = Integer.parseInt(args[i]);
          }
          int range;
          int numberOfRuns;
          if (args.length == 0) {
               range = 365;
               numberOfRuns = 50;
          }
          else {
               range = intargs[0];
               numberOfRuns = intargs[1];
          }
          Statistics x = BirthdayParadox.runExperiments(range, numberOfRuns);
          System.out.println(x);
	}

}