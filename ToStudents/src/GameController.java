import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    /**
     * Reference to the view of the board
     */
    private GameView gameView;
    /**
     * Reference to the model of the game
     */
    private GameModel gameModel;
 

    private Stack<GameModel> gameStackUndo = new GenericLinkedStack<GameModel>();
    private Stack<GameModel> gameStackRedo = new GenericLinkedStack<GameModel>();
    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */

    public GameController(int size) {
        gameModel = new GameModel(size);
        gameModel = gameModel.restart();
        gameView = new GameView(gameModel, this);
        flood();
        gameView.update();
    }

    /**
     * resets the game
     */
    public void reset(){
        gameModel.reset();
        flood();
        gameView.update();
    }

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) 
    {
        if(gameModel.getStartingDot() == null)
        {
            if (e.getSource() instanceof DotButton) 
            {
                selectColor(((DotButton)(e.getSource())).getColor());
                gameModel.setStartingDot((((DotButton)(e.getSource())).getColumn()),(((DotButton)(e.getSource())).getRow()));
            }
            else if (e.getSource() instanceof JButton) 
            {
                JButton clicked = (JButton)(e.getSource());
                if (clicked.getText().equals("Quit")) 
                {
                    System.exit(0);
                } 
                else if (clicked.getText().equals("Reset"))
                {
                    reset();
                }
                else if(clicked.getText().equals("Redo"))
                {
                    System.out.println("Redo");
                }
                else if(clicked.getText().equals("Settings"))
                {
                    gameView.settingsMenu();
                }
            
            }
            else if(e.getSource() instanceof JRadioButton)
            {
                JRadioButton clicked = (JRadioButton)(e.getSource());
                if(clicked.getText().equals("Plane"))
                {
                    gameModel.setSettings(0, gameModel.getSettings()[1]);
                }
                else if(clicked.getText().equals("Torus"))
                {
                    gameModel.setSettings(1, gameModel.getSettings()[1]);
                }
                else if(clicked.getText().equals("Orthagonal"))
                {
                    gameModel.setSettings(gameModel.getSettings()[0],0);
                }
                else if(clicked.getText().equals("Diagonal"))
                {
                    gameModel.setSettings(gameModel.getSettings()[0],1);
                }
            }
        }       
        else
        {
            if (e.getSource() instanceof DotButton) 
            {
                selectColor(((DotButton)(e.getSource())).getColor());
            }
            else if (e.getSource() instanceof JButton)
            {
                JButton clicked = (JButton)(e.getSource());
                if (clicked.getText().equals("Quit")) 
                {
                    System.exit(0);
                } 
                else if (clicked.getText().equals("Reset"))
                {
                    reset();
                }
                else if(clicked.getText().equals("Undo"))
                {
                    System.out.println("Undo");
                }
                else if(clicked.getText().equals("Redo"))
                {
                    System.out.println("Redo");
                }
                else if(clicked.getText().equals("Settings"))
                {
                    gameView.settingsMenu();
                }
            }
            else if(e.getSource() instanceof JRadioButton)
            {
                JRadioButton clicked = (JRadioButton)(e.getSource());
                if(clicked.getText().equals("Plane"))
                {
                    gameModel.setSettings(0, gameModel.getSettings()[1]);
                }
                else if(clicked.getText().equals("Torus"))
                {
                    gameModel.setSettings(1, gameModel.getSettings()[1]);
                }
                else if(clicked.getText().equals("Orthagonal"))
                {
                    gameModel.setSettings(gameModel.getSettings()[0],0);
                }
                else if(clicked.getText().equals("Diagonal"))
                {
                    gameModel.setSettings(gameModel.getSettings()[0],1);
                }
            }

        }
    }


    /**
     * <b>selectColor</b> is the method called when the user selects a new color.
     * If that color is not the currently selected one, then it applies the logic
     * of the game to capture possible locations. It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param color
     *            the newly selected color
     */
    public void selectColor(int color){
        if(color != gameModel.getCurrentSelectedColor()) {
            gameModel.setCurrentSelectedColor(color);
            flood();
            gameModel.step();
            gameView.update();

            if(gameModel.isFinished()) {
                      Object[] options = {"Play Again",
                                "Quit"};
                        int n = JOptionPane.showOptionDialog(gameView,
                                "Congratulations, you won in " + gameModel.getNumberOfSteps() 
                                    +" steps!\n Would you like to play again?",
                                "Won",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if(n == 0){
                            reset();
                        } else{
                            System.exit(0);
                        }   
                }            
            }        
    }

   /**
     * <b>flood</b> is the method that computes which new dots should be ``captured'' 
     * when a new color has been selected. The Model is updated accordingly
     */
     private void flood() {

        Stack<DotInfo> stack = new GenericLinkedStack<DotInfo>();
        for(int i =0; i < gameModel.getSize(); i++) {
           for(int j =0; j < gameModel.getSize(); j++) {
                if(gameModel.isCaptured(i,j)) {
                    stack.push(gameModel.get(i,j));
                }
           }
        }

        DotInfo dotInfo;
        if(!stack.isEmpty)
        {
            while(!stack.isEmpty())
            {
                dotInfo = stack.pop();
                //REGULAR GAME
                if((dotInfo.getX() > 0) && shouldBeCaptured (dotInfo.getX()-1, dotInfo.getY())) 
                {
                    gameModel.capture(dotInfo.getX()-1, dotInfo.getY());
                    stack.push(gameModel.get(dotInfo.getX()-1, dotInfo.getY()));
                }  
                if((dotInfo.getX() < gameModel.getSize()-1) && shouldBeCaptured (dotInfo.getX()+1, dotInfo.getY())) 
                {
                    gameModel.capture(dotInfo.getX()+1, dotInfo.getY());
                    stack.push(gameModel.get(dotInfo.getX()+1, dotInfo.getY()));
                }
                if((dotInfo.getY() > 0) && shouldBeCaptured (dotInfo.getX(), dotInfo.getY()-1)) 
                {
                    gameModel.capture(dotInfo.getX(), dotInfo.getY()-1);
                    stack.push(gameModel.get(dotInfo.getX(), dotInfo.getY()-1));
                }  
                if((dotInfo.getY() < gameModel.getSize()-1) && shouldBeCaptured (dotInfo.getX(), dotInfo.getY()+1)) 
                {
                    gameModel.capture(dotInfo.getX(), dotInfo.getY()+1);
                    stack.push(gameModel.get(dotInfo.getX(), dotInfo.getY()+1));
                }

                //PLAYING IN TORUS FORMAT (ABLE TO WRAP AROUND THE BOARD)
                if(gameModel.getSettings()[0]==1)
                {
                
                }
            }

        }
        
    }


    /**
     * <b>shouldBeCaptured</b> is a helper method that decides if the dot
     * located at position (i,j), which is next to a captured dot, should
     * itself be captured
     * @param i
     *            row of the dot
     * @param j
     *            column of the dot
     */
    
   private boolean shouldBeCaptured(int i, int j) {
        if(!gameModel.isCaptured(i, j) &&
           (gameModel.getColor(i,j) == gameModel.getCurrentSelectedColor())) {
            return true;
        } else {
            return false;
        }
    }


}
