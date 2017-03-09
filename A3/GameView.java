import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.imageio.ImageIO;
import java.awt.image.BufferedImage;


/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out the actual game and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {
	private GameModel model;
	private GameController gameController;
	private int size;

	BufferedImage greyN   = ImageIO.read(new File("data/N/ball-0.png"));
	BufferedImage orangeN = ImageIO.read(new File("data/N/ball-1.png"));
	BufferedImage blueN   = ImageIO.read(new File("data/N/ball-2.png"));
	BufferedImage greenN  = ImageIO.read(new File("data/N/ball-3.png"));
	BufferedImage purpleN = ImageIO.read(new File("data/N/ball-4.png"));
	BufferedImage redN    = ImageIO.read(new File("data/N/ball-5.png"));

	JLabel greyNLabel = new JLabel(new ImageIcon(greyN));
	JLabel orangeNLabel = new Jlabel

	BufferedImage greyM   = ImageIO.read(new File("data/M/ball-0.png"));
	BufferedImage orangeM = ImageIO.read(new File("data/M/ball-1.png"));
	BufferedImage blueM   = ImageIO.read(new File("data/M/ball-2.png"));
	BufferedImage greenM  = ImageIO.read(new File("data/M/ball-3.png"));
	BufferedImage purpleM = ImageIO.read(new File("data/M/ball-4.png"));
	BufferedImage redM    = ImageIO.read(new File("data/M/ball-5.png"));

	BufferedImage greyS   = ImageIO.read(new File("data/S/ball-0.png"));
	BufferedImage orangeS = ImageIO.read(new File("data/S/ball-1.png"));
	BufferedImage blueS   = ImageIO.read(new File("data/S/ball-2.png"));
	BufferedImage greenS  = ImageIO.read(new File("data/S/ball-3.png"));
	BufferedImage purpleS = ImageIO.read(new File("data/S/ball-4.png"));
	BufferedImage redS    = ImageIO.read(new File("data/S/ball-5.png"));

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
    	this.size = size;
    	this.gameController = gameController;
    	JLabel input = new JLabel(model.toString());
		add(input);
    	setBackground(Color.WHITE);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    	JPanel gameBoard = new JPanel();
    	add(gameBoard, BorderLayout.NORTH);
    	gameBoard.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
    	if (size>25) {
    		gameBoard.setSize(size*11, size*11);
    	}
    	if (size<=25 && size>=10) {
    		gameBoard.setSize(size*28, size*28);
    	}
    	if (size<10) {
    		gameBoard.setSize(size*40, size*40);
    	}
    	gameBoard.setLayout(new GridLayout(size,size));
    	//this nested for loop goes through the entire board,
    	//and adds an icon to the space on the grid,
    	//depending on their colour and size of the board.
    	DotButton[][] boardDots = new DotButton[size][size]; // this array assigns every dot on the board to a unique DotButton class
    	for (int i=0; i<size; i++) {
    		for (int j=0; j<size; j++) {
    			if (model.getColor(i, j) == 0) {
    				if (size>=10 && size<=25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 2);
    					gameBoard.add(greyN);
   					}
   					if (size<10) {
   						boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 3);
   						gameBoard.add(greyM);
   					}
   					if (size>25) {
   						boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 1);
   						gameBoard.add(greyS);
   					}
    			}
    			if (model.getColor(i, j) == 1) {
    				if(size>=10 && size<=25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 2);
    					gameBoard.add(orangeN);
    				}
    				if (size<10) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 3);
    					gameBoard.add(orangeM);
    				}
    				if (size>25) {
    					DotButton[i][j] = new DotButton(i, j, model.getColor(i, j), 1);
    					gameBoard.add(orangeS);
    				}
    			}
    			if (model.getColor(i, j) == 2) {
    				boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 2);
    				if (size>=10 && size<=25) {
    					gameBoard.add(blueN);
    				}
    				if (size<10) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 3);
    					gameBoard.add(blueM);
    				}
    				if (size>25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 1);
    					gameBoard.add(blueS);
    				}
    			}
    			if (model.getColor(i, j) == 3) {
    				if (size>=10 && size<=25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 2);
    					gameBoard.add(greenN);
    				}
    				if (size<10) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 3);
    					gameBoard.add(greenM);
    				}
    				if (size>25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 3);
    					gameBoard.add(greenS);
    				}   			
    			}
    			if (model.getColor(i, j) == 4) {
    				if (size>=10 && size<=25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 2);
    					gameBoard.add(purpleN);
    				}
    				if (size<10) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 3);
    					gameBoard.add(purpleM);
    				}
    				if (size>25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 1);
    					gaemBoard.add(purpleS);
    				}				
    			}
    			if (model.getColor(i, j) == 5) {
    				if (size>=10 && size<=25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 2);
    					gameBoard.add(redN);
    				}
    				if (size<10) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 3);
    					gameBoard.add(redM);
    				}
    				if (size>25) {
    					boardDots[i][j] = new DotButton(i, j, model.getColor(i, j), 1);
    					gameBoard.add(redS);
    				}
    			}
    		}
    	}

    	JPanel colorsel = new JPanel();
    	add(colorsel, BorderLayout.CENTER);
    	colorsel.setLayout(new GridLayout(6,1));
    	colorsel.setSize(168, 28);
    	//this adds the selecter color buttons
    	for (int i=1; i<=6; i++) {
    		if (i==1) {
    			selButton = new JButton(greyN);
    			selButton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==2) {
    			selButton = new JButton(orangeN);
    			selButton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==3) {
    			selButton = new JButton(blueN);
    			selButton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==4) {
    			selButton = new JButton(greenN);
    			selButton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==5) {
    			selButton = new JButton(purpleN);
    			selButton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==6) {
    			selButton = new JButton(redN);
    			selButton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    	}

    	JPanel control = new JPanel();
    	control.setSize(size, 200);
    	add(control, BorderLayout.SOUTH);


    	JButton reset = new JButton("Reset");
    	reset.setFocusPainted(false);
    	control.add(reset, BorderLayout.CENTER);

    	JButton quit = new JButton("Quit");
    	quit.setFocusPainted(false);
    	control.add(quit, BorderLayout.EAST);
     	
    	pack();
    }

    /**
     * update the status of the board's DotButton instances based on the current game model
     */

    public void update(){
    }

}