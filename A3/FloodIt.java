/**
 * The class <b>FloodIt</b> launches the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
/*Authors for this iteration: Sam Worrod (8653389) and Jaimin Patel (8721083)
 *Course: ITI 1121 A
 *Assignment 3
 */
public class FloodIt {


   /*
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If a game size (<12) is passed as parameter, it is 
     * used as the board size. Otherwise, a default value is passed
     * 
     * @param args
     *            command line parameters
     */
    public static void main(String[] args) {
        StudentInfo.display();
        if(args.length==0)
        {
            GameController controller = new GameController(12);
        }
        else{
            int intargs = Integer.parseInt(args[0]);
            if(intargs<12){
             GameController controller = new GameController(12);
            }
            else
            {
                GameController controller = new GameController(intargs);
            }
        }
       
    }
}