import java.awt.Color;
import java.awt.Graphics;

/**
 * Board
 * 
 * The class that displays and manages the Objects of the game
 */
public class Board{

    public int COL_NUM;
    public int ROW_NUM;
    public int OBJECT_SIZE;
      
    private GameObj[][] objects;
    
    /**
     * Creates a new Board instance.
     */
    public Board(int row, int col, int objSize) {
        ROW_NUM = row;
        COL_NUM = col;
        OBJECT_SIZE = objSize;
        objects = new GameObj[ROW_NUM] [COL_NUM];
    }
    
    /**
     * Sets the tile at the desired location.
     * @param col The column coordinate of the object.
     * @param row The row coordinate of the object.
     * @param o The game object to place.
     */
    public void setObject(GameObj o) {
        objects[o.pos_y][o.pos_x] = o;
    }
    
    /**
     * Gets the tile at the desired coordinate.
     * @param x The x coordinate of the tile.
     * @param y The y coordinate of the tile.
     * @return
     */
    public GameObj getObject(int x, int y) {
        return objects[y][x];
    }
    
    public void resetBoard() {
        for(int i = 0; i < COL_NUM; i++) {
            for(int j = 0; j < ROW_NUM; j++) {
                objects[j][i] = null;
            }
        }
    }
    
    public void draw(Graphics g) {
        int width = COL_NUM * OBJECT_SIZE;
        int height = ROW_NUM * OBJECT_SIZE;
        
        //Draw the grid on the window.
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
         
        g.setColor(Color.GRAY);
        g.drawRect(0, 1, width - 1, height - 1);
        
        // Draw the objects
        for(int i = 0; i < COL_NUM; i++) {
            for(int j = 0; j < ROW_NUM; j++) {
                GameObj o = getObject(i, j);
                if(getObject(i, j) != null) {
                    o.draw(i * OBJECT_SIZE, j * OBJECT_SIZE, g);
                }
            }
        }
    }
}
