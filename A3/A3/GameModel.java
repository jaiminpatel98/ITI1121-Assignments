import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the followiung information:
 * - the state of all the ``dots'' on the board (color, captured or not)
 * - the size of the board
 * - the number of steps since the last reset
 * - the current color of selection
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


    /**
     * predefined values to capture the color of a DotInfo
     */
    public static final int COLOR_0           = 0;
    public static final int COLOR_1           = 1;
    public static final int COLOR_2           = 2;
    public static final int COLOR_3           = 3;
    public static final int COLOR_4           = 4;
    public static final int COLOR_5           = 5;
    public static final int NUMBER_OF_COLORS  = 6;
    Random generator = new Random();
    private int steps;
    private int size; //size of the boardboard
    private DotInfo[][] board;
    privaate int selColor;

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param size
     *            the size of the board
     */
    public GameModel(int size) {
        this.size = size; //initializes size of board
        board = new DotInfor[size][size];
        reset();
    }


    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){ 
        GameModel model = new GameModel(size); //restarts the GameModel
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                int color = generator.nextInt(6);
                board[i][j]= new DotInfo(i, j, color);
            }
        }
        steps = 0;
    }


    /**
     * Getter method for the size of the game
     * 
     * @return the value of the attribute sizeOfGame
     */   
    public int getSize(){ 
        return(size); 
    }

    /**
     * returns the current color  of a given dot in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public int getColor(int i, int j){
        return(board[i][j].getColor());
        //return(DotInfo(i,j).getColor());
    }

    /**
     * returns true is the dot is captured, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCaptured(int i, int j){
        return(board[i][j].isCaptured());
    }

    /**
     * Sets the status of the dot at coordinate (i,j) to captured
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void capture(int i, int j){
        board[i][j].setCaptured(true);
   }


    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        return(steps);
    }

    /**
     * Setter method for currentSelectedColor
     * 
     * @param val
     *            the new value for currentSelectedColor
    */   
    public void setCurrentSelectedColor(int val) {
        selColor = val;
    }

    /**
     * Getter method for currentSelectedColor
     * 
     * @return currentSelectedColor
     */   
    public int getCurrentSelectedColor() {
        return(selColor);
    }


    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        return(boardpi][j]);
    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new color.
     */
     public void step(){
        steps++;
    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the dats are captured.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        boolean fin = true;
        int i = 0;
        int j = 0;
        while(fin) {
            if (i==(size-1)) {
                j++;
            }
            fin = board[i][j].isCaptured();
            i++;
        } /*while loop continues if it keeps finding captured dots,
        but if it comes across not captured dot the loop stops and returns false.*/
        return (fin);
    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        return("Model(Size: " + size +", Steps: " + steps + ", Finished: " + isFinished() + ")");
    }

}