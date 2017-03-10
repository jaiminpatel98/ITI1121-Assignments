import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JButton;

/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController extends JFrame implements ActionListener {
    private int size;
    private GameModel model;
    private GameView view;
    private JButton quit = new JButton("Quit");
    private JButton reset = new JButton("Reset"); 
    private DotButton greyButton;
    private DotButton orangeButton;
    private DotButton blueButton;
    private DotButton greenButton;
    private DotButton purpleButton;
    private DotButton redButton;
    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */
    public GameController(int size) {
        this.size = size;
        this.model = new GameModel(size);
        this.view = new GameView(model, this);
        quit.addActionListener(this);
        quit.setActionCommand("Quit");
        reset.addActionListener(this);
        reset.setActionCommand("Reset");

        greyButton = view.getButton(0);
        greyButton.addActionListener(this);
        greyButton.setActionCommand(greyButton.getColorString(0));

        orangeButton = view.getButton(1);
        orangeButton.addActionListener(this);
        orangeButton.setActionCommand(orangeButton.getColorString(1));

        blueButton = view.getButton(2);
        blueButton.addActionListener(this);
        blueButton.setActionCommand(blueButton.getColorString(2));

        greenButton = view.getButton(3);
        greenButton.addActionListener(this);
        greenButton.setActionCommand(greenButton.getColorString(3));

        purpleButton = view.getButton(4);
        purpleButton.addActionListener(this);
        purpleButton.setActionCommand(purpleButton.getColorString(4));

        redButton = view.getButton(5);
        redButton.addActionListener(this);
        redButton.setActionCommand(redButton.getColorString(5));

        flood(model.getCurrentSelectedColor());
        //test
        // Scanner scanner = new Scanner(System.in);
        // System.out.println(model);
        view.update();
        while (!model.isFinished()) {
             //System.out.println("Selected color: ");
             selectColor(model.getCurrentSelectedColor());
             view.update();
         }
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
            System.exit(0);
        }
        if (e.getActionCommand().equals("grey")) {
            selectColor(0);
            view.update();
        }
        if (e.getActionCommand().equals("orange")) {
            selectColor(1);
            view.update();
        }
        if (e.getActionCommand().equals("blue")) {
            selectColor(2);
            view.update();
        }
        if (e.getActionCommand().equals("green")) {
            selectColor(3);
            view.update();
        }
        if (e.getActionCommand().equals("purple")) {
            selectColor(4);
            view.update();
        }
        if (e.getActionCommand().equals("red")) {
            selectColor(5);
            view.update();
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
                System.out.println("You finished");
            }
        }
    }
    public JButton getResetButton()
    {
        return this.reset;
    }
    public JButton getQuitButton()
    {
        return this.quit;
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