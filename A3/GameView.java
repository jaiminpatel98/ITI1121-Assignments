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

public class GameView extends JFrame {
    private DotButton[][] buttonBoard;
    private GameModel model;
    private GameController gameController;
    private int size;

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
        setSize(300,700);
        
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
                }
                if (size>25) {
                    buttonBoard[i][j] = new DotButton(i, j, model.getColor(i,j), 1);
                    buttonBoard[i][j].setColor(model.getColor(i,j));
                    boardPanel.add(buttonBoard[i][j]);
                }
            }
        } //gives each dot a button in an identical matrix to board, but instead of each element being DotInfo, they are DotButton

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,6));
        controlPanel.setSize(40*6, 40);
        add(controlPanel, BorderLayout.CENTER);
        DotButton grey = new DotButton(0,3);
        controlPanel.add(grey);
        DotButton orange = new DotButton(1,3);
        controlPanel.add(orange);
        DotButton blue = new DotButton(2,3);
        controlPanel.add(blue);
        DotButton green = new DotButton(3,3);
        controlPanel.add(green);
        DotButton purple = new DotButton(4,3);
        controlPanel.add(purple);
        DotButton red = new DotButton(5,3);
        controlPanel.add(red);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1,3));
        infoPanel.setSize(40*6, 40);
        add(infoPanel, BorderLayout.SOUTH);
        JLabel numOfSteps = new JLabel("Number of steps: " + model.getNumberOfSteps());
        infoPanel.add(numOfSteps);
        JButton reset = new JButton("Reset");
        JButton quit = new JButton("Quit");
        infoPanel.add(reset);
        infoPanel.add(quit);

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
                }

            }
        }
    }

}