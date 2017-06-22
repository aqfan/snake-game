/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    // Initializes a panel to switch the menus
    public static JPanel menus = new JPanel();
    public static CardLayout cards = new CardLayout();
    public static String difficulty = "Easy";
    public static boolean gridOn = true;
    public static int score = 0;
    
    public void run() {
        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Snake");
        frame.setLocation(300, 300);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        
        // Initializes the game court
        final GameLogic game_court = new GameLogic();
        
        // Initializes the window size for various menus
        int height = game_court.getHeight();
        int width = game_court.getWidth();
        
        // Initializes the start menu
        final StartMenu start_menu = new StartMenu(height, width);
        
        // Initializes the help menu
        final HelpMenu help_menu = new HelpMenu(height, width);
        
        // Initializes the settings menu
        final SettingsMenu settings = new SettingsMenu(height, width);
        
        // Initializes the end menu
        final EndMenu end_menu = new EndMenu(height, width);
        
        menus.setLayout(cards);
        menus.add(start_menu, "StartMenu");
        menus.add(help_menu, "HelpMenu");
        menus.add(settings, "Settings");
        menus.add(game_court, "GameCourt");
        menus.add(end_menu, "EndMenu");
        
        frame.add(menus, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /*
     * Main method run to start and run the game Initializes the GUI elements
     * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
     * this in the final submission of your game.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
