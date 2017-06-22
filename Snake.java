/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;

/**
 * A basic game object displayed as a black square, starting in the upper left
 * corner of the game court.
 * 
 */
public class Snake extends GameObj {
    public Snake(int pos_x, int pos_y, int size,
            int board_width, int board_height, Direction d, Type t) {
        super(pos_x, pos_y, size, board_width - 1,
                board_height - 1, d, t);
    }

    @Override
    public Point nextLocation() {
        Snake temp = new Snake(pos_x, pos_y, size, 
            max_x, max_y, dir, type);
        temp.move();
        return new Point(temp.pos_x, temp.pos_y);
    }
    
    @Override
    public void move() {
            if (dir == Direction.LEFT){
                pos_x--;
            }
            else if (dir == Direction.RIGHT){
                pos_x++;
            }
            else if (dir == Direction.DOWN){
                pos_y++;
            }
            else if (dir == Direction.UP){
                pos_y--;
            }
    }
    
    @Override
    public void draw(int x, int y, Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, size, size);
        
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
        
        if (type == Type.HEAD) {
            int EYE_RADIUS = size / 5;
            switch(dir) {
            case UP: {
                g.fillOval(x + EYE_RADIUS - 2, y + EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                g.fillOval(x + size - EYE_RADIUS - 2, y + EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                break;
            }
                
            case DOWN: {
                g.fillOval(x + EYE_RADIUS - 2, y + size - EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                g.fillOval(x + size - EYE_RADIUS - 2, y + size - EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                break;
            }
            
            case LEFT: {
                g.fillOval(x + EYE_RADIUS - 2, y + EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                g.fillOval(x + EYE_RADIUS - 2, y + size - EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                break;
            }
                
            case RIGHT: {
                g.fillOval(x + size - EYE_RADIUS - 2, y + EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                g.fillOval(x + size - EYE_RADIUS - 2, y + size - EYE_RADIUS - 2, EYE_RADIUS, EYE_RADIUS);
                break;
            }
            
            }
        } 
    }

}
