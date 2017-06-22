/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.Graphics;
import java.awt.Point;

/** An object in the game. 
 *
 *  Game objects exist in the game court. They have a position, 
 *  velocity, size and bounds. Their velocity controls how they 
 *  move; their position should always be within their bounds.
 */
public class GameObj {
    
    public Type type;
    
    public int size;

	/** Current position of the object (in terms of graphics coordinates)
	 *  
	 * Coordinates are given by the upper-left hand corner of the object.
	 * This position should always be within bounds.
	 *  0 <= pos_x <= max_x 
	 *  0 <= pos_y <= max_y 
	 */
	public int pos_x; 
	public int pos_y;
	
	/** Upper bounds of the area in which the object can be positioned.  
	 *    Maximum permissible x, y positions for the upper-left 
	 *    hand corner of the object
	 */
	public int max_x;
	public int max_y;
	
	/** Direction: the direction of the object */
	public Direction dir;
	
	/**
	 * Constructor
	 */
	public GameObj(int pos_x, int pos_y, int size, 
	        int board_width, int board_height, Direction d, Type t){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size = size;
		this.dir = d;
		this.type = t;
		
		this.max_x = board_width;
		this.max_y = board_height;
	}
	
	/**
     * Determine whether this game object will hit the wall in the
     * next time step, assuming that both objects continue with their 
     * current velocity.
     * 
     * @return whether an intersection will occur.
     */
    public boolean willHitWall(){
        Point p = nextLocation();
        return ((p.x < 0) || (p.x > max_x)
         || (p.y < 0) || (p.y > max_y));
    }
    
    /**
     * Determine whether this game object will hit the wall in the
     * next time step, assuming that both objects continue with their 
     * current velocity.
     * 
     * @return whether an intersection will occur.
     */
    public boolean hitsWall(){
        return ((pos_x < 0) || (pos_x > max_x)
         || (pos_y < 0) || (pos_y > max_y));
    }
	
    /**
     * Determines the next location of the object. 
     * Will be overridden by child classes.
     * 
     */
    public Point nextLocation() {
        return null;
    }
    
    
    /**
     * Default move method that provides how the object should move
     * in the GUI. This method does not do anything. Subclass should 
     * override this method based on how their object should move.
     */
    public void move(){
        
    }
    
	/**
	 * Default draw method that provides how the object should be drawn 
	 * in the GUI. This method does not draw anything. Subclass should 
	 * override this method based on how their object should appear.
	 * 
	 * @param g 
	 *	The <code>Graphics</code> context used for drawing the object.
	 * 	Remember graphics contexts that we used in OCaml, it gives the 
	 *  context in which the object should be drawn (a canvas, a frame, 
	 *  etc.)
	 */
	public void draw(int x, int y, Graphics g) {
	}
	
}
