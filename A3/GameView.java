import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;



/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out the actual game and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame{
    private DotButton[][] buttonBoard;
    private GameModel model;
    private GameController gameController;
    private int size;
    private DotButton grey;
    private DotButton orange;
    private DotButton blue;
    private DotButton green;
    private DotButton purple;
    private DotButton red;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel model, GameController gameController) {
        this.model = model;
        this.gameController = gameController;
        size = model.getSize();

        setTitle("Flood It");
        setSize(40*size,(40*size+100));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(size,size));
        if (size>=10 && size<=25) {
            boardPanel.setSize(28*size, 28*size);
        }
        else if (size>25) {
            boardPanel.setSize(11*size, 11*size);
        }
        add(boardPanel, BorderLayout.NORTH);

        buttonBoard = new DotButton[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (size>=10 && size<=25) {
                    buttonBoard[i][j] = new DotButton(i, j, model.getColor(i,j), 0);
                    buttonBoard[i][j].setColor(model.getColor(i,j));
                    boardPanel.add(buttonBoard[i][j]);
                    buttonBoard[i][j].setVisible(true);
                }
                if (size>25) {
                    buttonBoard[i][j] = new DotButton(i, j, model.getColor(i,j), 1);
                    buttonBoard[i][j].setColor(model.getColor(i,j));
                    boardPanel.add(buttonBoard[i][j]);
                    buttonBoard[i][j].setVisible(true);
                }
            }
            boardPanel.setVisible(true);
        } //gives each dot a button in an identical matrix to board, but instead of each element being DotInfo, they are DotButton

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,6));
        controlPanel.setSize(40*6, 40);
        add(controlPanel, BorderLayout.CENTER);
        grey = new DotButton(0,3);
        controlPanel.add(grey);
        grey.setVisible(true);
        orange = new DotButton(1,3);
        controlPanel.add(orange);
        orange.setVisible(true);
         blue = new DotButton(2,3);
        controlPanel.add(blue);
        blue.setVisible(true);
        green = new DotButton(3,3);
        controlPanel.add(green);
        green.setVisible(true);
        purple = new DotButton(4,3);
        controlPanel.add(purple);
        purple.setVisible(true);
        red = new DotButton(5,3);
        controlPanel.add(red);
        red.setVisible(true);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1,3));
        infoPanel.setSize(40*6, 40);
        add(infoPanel, BorderLayout.SOUTH);
        JLabel numOfSteps = new JLabel("Number of steps: " + model.getNumberOfSteps());
        infoPanel.add(numOfSteps);
        JButton reset = gameController.getResetButton();        
        JButton quit = gameController.getQuitButton();
        infoPanel.add(reset);
        infoPanel.add(quit);

        controlPanel.setVisible(true);
        infoPanel.setVisible(true);
        setVisible(true);

    }

    /**
     * update the status of the board's DotButton instances based on the current game model
     */

    public void update(){
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (model.isCaptured(i,j)) {
                    buttonBoard[i][j].setColor(model.getCurrentSelectedColor());
                    //buttonBoard[i][j].add(getCurrentLabel());
                    //buttonBoard[i][j].setVisible(true);
                    //boardPanel.add(buttonBoard[i][j]);
                    //boardPanel.setVisible(true);                
                }
            }
        }
    }
    public DotButton getButton(int color) {
        if (color == 0) {
            return (grey);
        }
        else if (color == 1) {
            return (orange);
        }
        else if (color == 2) {
            return (blue);
        }
        else if (color == 3) {
            return (green);
        }
        else if (color == 4) {
            return (purple);
        }
        else{
            return (red);
        }
        
    }


}