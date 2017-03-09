import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {
  
    private GameModel model;
    private GameView view;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */
    public GameController(int size) {
        model = new GameModel(size);
        view = new GameView(model, this);
        flood(model.getCurrentSelectedColor());
        //test
        // Scanner scanner = new Scanner(System.in);
        // System.out.println(model);

        // while (!model.isFinished()) {
        //     System.out.println("Selected color: ");
        //     selectColor(scanner.nextInt());
        //     System.out.println(model);
        // }
    }

    /**
     * resets the game
     */
    public void reset(){
        model.reset();
    }

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Reset")) {
            model.reset();
        }
        if (e.getActionCommand().equals("Quit")) {
            
        }
    }
    

    /**
     * <b>selectColor</b> is the method called when the user selects a new color.
     * If that color is not the currently selected one, then it applies the logic
     * of the game to capture possible locations. It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives two options: start a new game, or exit
     * @param color
     *            the newly selected color
     */
    public void selectColor(int color){
        if (color != model.getCurrentSelectedColor()) {
            model.setCurrentSelectedColor(color);
            model.step();
            flood(color);
            if (model.isFinished()) {
                System.out.println("u finished");
            }
        }
    }


    private void flood(int newColor) {
        boolean flag = false;
        DotInfo d;
        int size = model.getSize();
        int capacity = model.getSize();
        ArrayStack stack = new ArrayStack(capacity);
        for (int i=0; i<capacity; i++) {
            for (int j=0; j<capacity; j++) {
                if (model.isCaptured(i,j)) {
                    stack.push(model.get(i,j));
                }
            }
        }
        while (!stack.isEmpty()) {
            DotInfo dot = (DotInfo) stack.pop();
            int x = dot.getX();
            int y = dot.getY();


            if (y!=size-1) {
                flag = true;
                d = model.get(x,y+1);
                if (!(d.isCaptured()) && d.getColor()==newColor) {
                    model.capture(d.getX(), d.getY());
                    stack.push(d);
                }
                if (x!=size-1) {
                    d = model.get(x+1,y);
                    if (!(d.isCaptured()) && d.getColor()==newColor) {
                        model.capture(d.getX(), d.getY());
                        stack.push(d);
                    }
                } 
                if (x!=0) {
                    d = model.get(x-1,y);
                    if (!(d.isCaptured()) && d.getColor()==newColor) {
                        model.capture(d.getX(), d.getY());
                        stack.push(d);
                    }
                }
                
            }
            if (y!=0) {
                d = model.get(x,y-1);
                if (!(d.isCaptured()) && d.getColor()==newColor) {
                    model.capture(d.getX(), d.getY());
                    stack.push(d);
                }
                if (x!=size-1 && flag==false) {
                    d = model.get(x+1,y);
                    if (!(d.isCaptured()) && d.getColor()==newColor) {
                        model.capture(d.getX(), d.getY());
                        stack.push(d);
                    }
                } 
                if (x!=0 && flag==false) {

                    d = model.get(x-1,y);
                    if (!(d.isCaptured()) && d.getColor()==newColor) {
                        model.capture(d.getX(), d.getY());
                        stack.push(d);
                    }
                }
                
            }
            flag = false;
        }   
    }
}