/**
 * The class  <b>Statistics</b> accumulates the results of
 * the experiments. It know ahead of time how many experiments
 * will be run, and provides at the end the min, the max, the
 * average and the standard deviation for the data.
 *
 * <b> this class should not use classes such as Array, 
 * Lists etc. </b> to store the data, only prinitive types 
 * and java arrays.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */
public class Statistics {
     int count = 0;
     int results[];
     double avg;
     double stdv;
     int min;
     int max;
     int runs;
	/** 
     * Constructor
     * 
     * @param numberOfRuns the number of experiments that will be run
     */
 	public  Statistics(int numberOfRuns){
          runs = numberOfRuns;
          results = new int[runs];
	}
	
	/** 
     * Updates statistics after one experiment.
     * This method cannot be called more times than the 
     * paramter that was passed in the constructor. If
     * it is, an error message should be printed and
     * no change should occur.
     *   @param value the result of the new experiment
     */
	public void updateStatistics(int value){
          count ++;
          if (count>runs) {
               System.out.println("Error: You called updateStatistic more times than the number of experiments!");
          }
          results[count-1] = value;
          average();
          standardDeviation();
          min = 10000000;
          max = -1;
          for (int i=0; i<runs; i++) {
               if (results[i] > max) {
                    max = results[i];
               }
               if (results[i] < min) {
                    min = results[i];
               }
          }
	}
	

	/** 
     *   @return the current average of the values passed
     * to the method updateStatistic
     */
	public double average(){
          int sum = 0;
          for (int i=0; i<runs; i++) {
               sum += results[i];
          }
          avg = (sum/runs)*100;
          avg = Math.round(avg);
          avg = avg/100;
          return (avg);
	}


	/** 
     *   @return the current standard deviation of the values passed
     * to the method updateStatistic
     */
	public double standardDeviation(){
          int summ = 0;
          double mean = average();
          double standev[] = new double[runs];
          for (int i=0; i<runs; i++) {
               standev[i] = Math.pow((results[i] - mean), 2);
          }
          for (int i=0; i<runs; i++) {
               summ += standev[i];
          }
          stdv = Math.sqrt(summ/runs);
          stdv = Math.round(stdv*100);
          stdv = stdv/100;
          return (stdv);
	}

	/** 
     *  @return Returns the complete statistics information:
     * current minimum, current maximim, current average and
     * current standard deviation. For the last two, only two 
     * digits decimals are shown
     */
	public String toString(){
          String stat = "We have run " + runs + " experiments\nThe min was: " + min + "\nThe max was: " + max + "\nThe mean was: " + avg + "\nThe standard deviation was: " + stdv + "";
          return (stat);
	}

}