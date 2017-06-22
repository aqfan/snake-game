import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Board
 * 
 * The class that displays and manages the score
 */
public class InfoPanel{

    private int score;
    private int width; //width of the board
    private int height; //height of the board
    private int boardHeight;
    
    /**
     * Creates a new Board instance.
     */
    public InfoPanel(int score, int width, int height, int h) {
        this.score = score;
        this.width = width;
        this.boardHeight = height;
        this.height = h;
    }
      
    public void setScore(int s) {
        score = s;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, boardHeight, width, height);
        
        g.setColor(Color.WHITE);
        
        int y = 30 + boardHeight; //the y coordinate of the first string printed
        
        //Draws the title
        g.setFont(new Font("Verdana", Font.BOLD, 20));
        String title = "Snake Game";
        g.drawString(title, width / 2 - g.getFontMetrics().stringWidth(title) / 2, y);
        
        //Draws the headers
        g.setFont(new Font("Verdana", Font.BOLD, 15));
        
        String level = "Difficulty: " + Game.difficulty;
        g.drawString(level, width / 2 - g.getFontMetrics().stringWidth(level) / 2, y += 25);
        
        String totalScore = "Total Score: " + score;
        g.drawString(totalScore, width / 2 - g.getFontMetrics().stringWidth(totalScore) / 2, y += 25);
    }
}
