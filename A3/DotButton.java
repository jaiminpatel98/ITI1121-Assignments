import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * In the application <b>FloodIt</b>, a <b>DotButton</b> is a specialized color of
 * <b>JButton</b> that represents a dot in the game. It can have one of six colors
 * 
 * The icon images are stored in a subdirectory ``data''. We have 3 sizes, ``normal'',
 * ``medium'' and ``small'', respectively in directory ``N'', ``M'' and ``S''.
 *
 * The images are 
 * ball-0.png => grey icon
 * ball-1.png => orange icon
 * ball-2.png => blue icon
 * ball-3.png => green icon
 * ball-4.png => purple icon
 * ball-5.png => red icon
 *
 *  <a href=
 * "http://developer.apple.com/library/safari/#samplecode/Puzzler/Introduction/Intro.html%23//apple_ref/doc/uid/DTS10004409"
 * >Based on Puzzler by Apple</a>.
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {
    private int row;
    private int column;
    private int iconSize;
    private int color;
    /**
     * Constructor used for initializing a cell of a specified color.
     * 
     * @param row
     *            the row of this Cell
     * @param column
     *            the column of this Cell
     * @param color
     *            specifies the color of this cell
     * @param iconSize
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
     */

    public DotButton(int row, int column, int color, int iconSize) {
        this.row = row;
        this.column = column;
        this.iconSize = iconSize;
        this.color = color;
        setColor(color);
        setEnabled(false);
        setDisabledIcon(this.getIcon());
   }

 /**
     * Other constructor used for initializing a cell of a specified color.
     * no row or column info available. Uses "-1, -1" for row and column instead
     * 
     * @param color
     *            specifies the color of this cell
     * @param iconSize
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
     */   
    public DotButton(int color, int iconSize) {
        row = -1;
        column = -1;
        this.color = color;
        setColor(color);
        this.iconSize = iconSize;
        
    }
 


    /**
     * Changes the cell color of this cell. The image is updated accordingly.
     * 
     * @param color
     *            the color to set
     */

    public void setColor(int color) {
        if (iconSize == 0) {
            if (color == 0 && iconSize == 0) {
                setIcon(new ImageIcon("data/M/ball-0.png"));
            }
            else if (color == 1 && iconSize == 0) {
                setIcon(new ImageIcon("data/M/ball-1.png"));
            }
            else if (color == 2 && iconSize == 0) {
                setIcon(new ImageIcon("data/M/ball-2.png"));
            }
            else if (color == 3 && iconSize == 0) {
                setIcon(new ImageIcon("data/M/ball-3.png"));
            }
            else if (color == 4 && iconSize == 0) {
                setIcon(new ImageIcon("data/M/ball-4.png"));
            }
            else if (color == 5 && iconSize == 0) {
                setIcon(new ImageIcon("data/M/ball-5.png"));
            }
        }
        else if (iconSize == 1) {
            if (color == 0) {
                setIcon(new ImageIcon("data/S/ball-0.png"));
            }
            else if (color == 1) {
                setIcon(new ImageIcon("data/S/ball-1.png"));
            }
            else if (color == 2) {
                setIcon(new ImageIcon("data/S/ball-2.png"));
            }
            else if (color == 3) {
                setIcon(new ImageIcon("data/S/ball-3.png"));
            }
            else if (color == 4) {
                setIcon(new ImageIcon("data/S/ball-4.png"));
            }
            else if (color == 5) {
                setIcon(new ImageIcon("data/S/ball-5.png"));
            }
        }
        else if (iconSize==3) {
            if (color == 0) {
                setIcon(new ImageIcon("data/N/ball-0.png"));
            }
            else if (color == 1) {
                setIcon(new ImageIcon("data/N/ball-1.png"));
            }
            else if (color == 2) {
                setIcon(new ImageIcon("data/N/ball-2.png"));
            }
            else if (color == 3) {
                setIcon(new ImageIcon("data/N/ball-3.png"));
            }
            else if (color == 4) {
                setIcon(new ImageIcon("data/N/ball-4.png"));
            }
            else if (color == 5) {
                setIcon(new ImageIcon("data/N/ball-5.png"));
            }
        }
   }

    /**
     * Getter for color
     *
     * @return color
     */
    public int getColor(){
        return (color);
    }
 
    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
        return (row);
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
        return (column);
    }


// ADD YOUR PRIVATE METHODS HERE (IF USING ANY)


}