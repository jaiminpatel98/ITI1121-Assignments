import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out the actual game and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {
	GameModel model;
	GameController gameController;

	ImageIcon greyN = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\N\ball-0.png");
	ImageIcon orangeN = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\N\ball-1.png");
	ImageIcon blueN = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\N\ball-2.png");
	ImageIcon greenN = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\N\ball-3.png");
	ImageIcon purpleN = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\N\ball-4.png");
	ImageIcon redN = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\N\ball-5.png");

	ImageIcon greyM = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\M\ball-0.png");
	ImageIcon orangeM = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\M\ball-1.png");
	ImageIcon blueM = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\M\ball-2.png");
	ImageIcon greenM = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\M\ball-3.png");
	ImageIcon purpleM = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\M\ball-4.png");
	ImageIcon redM = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\M\ball-5.png");

	ImageIcon greyS = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\S\ball-0.png");
	ImageIcon orangeS = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\S\ball-1.png");
	ImageIcon blueS = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\S\ball-2.png");
	ImageIcon greenS = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\S\ball-3.png");
	ImageIcon purpleS = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\S\ball-4.png");
	ImageIcon redS = new ImageIcon("Users\jaxbl\Desktop\ITI1121 Assignments\A3\data\S\ball-5.png");

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
    	JLabel input = new JLabel(model.toString());
		add(input);
    	setBackground(Color.WHITE);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    	JPanel board = new JPanel();
    	add(board, BorderLayout.NORTH);
    	setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
    	if (size>25) {
    		board.setSize(size*11, size*11);
    	}
    	if (size<=25 && size>=10) {
    		board.setSize(size*28, size*28);
    	}
    	if (size<10) {
    		board.setSize(size*40, size*40);
    	}
    	board.setLayout(new GridLayout(size,size));
    	//this nested for loop goes through the entire board,
    	//and adds an icon to the space on the grid,
    	//depending on their colour and size of the board.
    	for (int i=0; i<size; i++) {
    		for (int j=0; j<size; j++) {

    			if (board[i][j].getColor() == 0) {
    				if (size>=10 && size<=25) {
    					board.add(greyN);
   					}
   					if (size<10) {
   						board.add(greyM);
   					}
   					if (size>25) {
   						board.add(greyS);
   					}
    			}
    			if (board[i][j].getColor() == 1) {
    				if(size>=10 && size<=25) {
    					board.add(orangeN);
    				}
    				if (size<10) {
    					board.add(orangeM);
    				}
    				if (size>25) {
    					board.add(orangeS);
    				}
    			}
    			if (board[i][j].getColor() == 2) {
    				if (size>=10 && size<=25) {
    					board.add(blueN);
    				}
    				if (size<10) {
    					board.add(blueM);
    				}
    				if (size>25) {
    					board.add(blueS);
    				}
    			}
    			if (board[i][j].getColor() == 3) {
    				if (size>=10 && size<=25) {
    					board.add(greenN);
    				}
    				if (size<10) {
    					board.add(greenM);
    				}
    				if (size>25) {
    					board.add(greenS);
    				}   			
    			}
    			if (board[i][j].getColor() == 4) {
    				if (size>=10 && size<=25) {
    					board.add(purpleN);
    				}
    				if (size<10) {
    					board.add(purpleM);
    				}
    				if (size>25) {
    					board.add(purpleS);
    				}				
    			}
    			if (board[i][j].getColor() == 5) {
    				if (size>=10 && size<=25) {
    					board.add(redN);
    				}
    				if (size<10) {
    					board.add(redM);
    				}
    				if (size>25) {
    					board.add(redS);
    				}
    			}
    		}
    	}

    	for (int g=0; g<size; g++) {
    		for (int h=0; h<size; h++) {

    		}
    	}

    	JPanel colorsel = new JPanel();
    	add(colorsel, BorderLayout.CENTER);
    	colorsel.setLayout(new GridLayout(6,1));
    	colorsel.setSize(168, 28);
    	//this adds the selecter color buttons
    	for (int i=1; i<=6; i++) {
    		if (i==1) {
    			selButton = new DotButton(greyN);
    			selbutton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==2) {
    			selButton = new DotButton(orangeN);
    			selbutton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==3) {
    			selButton = new DotButton(blueN);
    			selbutton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==4) {
    			selButton = new DotButton(greenN);
    			selbutton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==5) {
    			selButton = new DotButton(purpleN);
    			selbutton.setBorder(BorderFactory.createEmptyBorder());
  				selButton.setContentAreaFilled(false);
    			colorsel.add(selButton);
    		}
    		if (i==6) {
    			selButton = new DotButton(redN);
    			selbutton.setBorder(BorderFactory.createEmptyBorder());
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