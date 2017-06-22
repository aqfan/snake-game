/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;

/**
 * A basic game object displayed as a yellow circle, starting in the upper left
 * corner of the game court.
 * 
 */
public class FoodObj extends GameObj {
	public FoodObj(int pos_x, int pos_y, int size,
	            int board_width, int board_height, Direction d, Type t) {
	        super(pos_x, pos_y, size, board_width,
	                board_height, d, t);
	    }
	
	@Override
	public void draw(int x, int y, Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(x + 2, y + 2, size - 4, size - 4);
	}
}
