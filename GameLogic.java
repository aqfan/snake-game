
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameLogic extends JPanel {

    private FoodObj food; // the food object
    private int randomXPosition = (int) (Math.random() * (((BOARD_NUM_COL - 1) - MIN_SNAKE_LENGTH) + 1)) + MIN_SNAKE_LENGTH;
    private int randomYPosition = (int) (Math.random() * (((BOARD_NUM_ROW - 1) - MIN_SNAKE_LENGTH) + 1)) + MIN_SNAKE_LENGTH;

    private Board board;
    private InfoPanel info;
    
    private boolean playing = false; // whether the game is running or over
    private boolean newGame = true; // whether it's a new game

    private Direction direction;

    // list of snake joints
    private ArrayList<Snake> snake;

    // Game constants
    private static final int BOARD_NUM_ROW = 25;
    private static final int BOARD_NUM_COL = 30;
    private static final int OBJECT_SIZE = 20;
    private static final int COURT_WIDTH = BOARD_NUM_COL * OBJECT_SIZE;
    private static final int COURT_HEIGHT = BOARD_NUM_ROW * OBJECT_SIZE;
    private static final int INFO_HEIGHT = Math.abs(COURT_WIDTH - COURT_HEIGHT);
    
    private static final int MIN_SNAKE_LENGTH = 3;
    private static final Direction START_DIRECTION = Direction.RIGHT;

    // Update interval for timer, in milliseconds
    private static int INTERVAL = 100;

    private static Timer timer;
    
    public GameLogic() {
        setPreferredSize(new Dimension(COURT_WIDTH, COURT_HEIGHT + INFO_HEIGHT));
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // The timer is an object which triggers an action periodically
        // with the given INTERVAL. One registers an ActionListener with
        // this timer, whose actionPerformed() method will be called
        // each time the timer triggers. We define a helper method
        // called tick() that actually does everything that should
        // be done in a single timestep.
        timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        setFocusable(true);
        // This key listener allows the square to move as long
        // as an arrow key is pressed, by changing the snake's
        // direction accordingly. (The tick method below actually
        // moves the snake.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    if (playing) {
                        if (direction != Direction.DOWN) {
                            direction = Direction.UP;
                        }
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if (playing) {
                        if (direction != Direction.UP) {
                            direction = Direction.DOWN;
                        }
                    }
                    break;

                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (playing ) {
                        if (direction != Direction.RIGHT) {
                            direction = Direction.LEFT;
                        }
                    }
                    break;

                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if (playing) {
                        if (direction != Direction.LEFT) {
                            direction = Direction.RIGHT;
                        }
                    }
                    break;
                
                case KeyEvent.VK_ENTER:
                    if (!playing && newGame) {
                        playing = true;
                        newGame = false;
                    } else if (!playing && !newGame){
                        Game.cards.show(Game.menus, "EndMenu");
                    }
                    break;
                
                case KeyEvent.VK_ESCAPE:
                    if(playing){
                        playing = false;
                        newGame = false;
                        repaint();
                    } else if (!playing && !newGame){
                        System.exit(0);
                    }
                    break;
                }
            }

        });
    }

    /**
     * (Re-)set the game at its initial state.
     */
    public void reset() {
        if (Game.difficulty.equals("Easy")){
            timer.setDelay(100);
        } else if (Game.difficulty.equals("Medium")){
            timer.setDelay(75);
        } else if (Game.difficulty.equals("Hard")){
            timer.setDelay(50);
        }
        
        board = new Board(BOARD_NUM_ROW, BOARD_NUM_COL, OBJECT_SIZE);
        info = new InfoPanel(Game.score, COURT_WIDTH, COURT_HEIGHT, INFO_HEIGHT);
        
        food = new FoodObj(randomXPosition, randomYPosition, OBJECT_SIZE, BOARD_NUM_COL - 1, BOARD_NUM_ROW - 1,
                START_DIRECTION, Type.FOOD);

        snake = new ArrayList<Snake>();
        snake.add(new Snake(MIN_SNAKE_LENGTH - 1, 0, OBJECT_SIZE, BOARD_NUM_COL, BOARD_NUM_ROW, START_DIRECTION,
                Type.HEAD));
        for (int i = MIN_SNAKE_LENGTH - 2; i >= 0; i--) {
            Snake s = new Snake(i, 0, OBJECT_SIZE, BOARD_NUM_COL, BOARD_NUM_ROW, START_DIRECTION, Type.BODY);
            snake.add(s);
        }
        updateBoard();
        
        Game.score = 0;
        playing = false;
        newGame = true;

        direction = START_DIRECTION;

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            // advance the snake in its current direction.
            checkGameStatus();
            // updates the board
            updateBoard();
            repaint();
        } else if (newGame){
            reset();
        }
    }

    // moves the objects on the board to the next location
    public void moveObjects() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            Snake s = snake.get(i);
            s.move();
            snake.set(i, s);
        }
    }

    // updates the directions of the objects on the board
    public void updateDirection() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            Snake s = snake.get(i);
            if (i == 0) {
                s.dir = direction;
            } else {
                s.dir = snake.get(i - 1).dir;
            }
            snake.set(i, s);
        }
    }

    // check if the snake bites itself or eats something and then changes their
    // position
    public void checkGameStatus() {
        updateDirection();
        Snake head = snake.get(0);
        if (head.willHitWall()) {
            playing = false;
            newGame = false;
        } else {
            Point next_loc = head.nextLocation();
            GameObj nextObject = board.getObject(next_loc.x, next_loc.y);
            if (nextObject != null) {
                switch (nextObject.type) {
                case FOOD:
                    Game.score++;
                    Snake lastJoint = snake.get(snake.size()-1);
                    Snake newJoint = new Snake(lastJoint.pos_x, lastJoint.pos_y, OBJECT_SIZE, BOARD_NUM_COL - 1,
                            BOARD_NUM_ROW - 1, START_DIRECTION, Type.BODY);
                    moveObjects();
                    snake.add(newJoint);
                    updateBoard();
                    respawnFood();
                    break;
                case BODY:
                    playing = false;
                    newGame = false;
                    break;
                case HEAD:
                    break;
                }
            } else {
                moveObjects();
            }
        }
    }

    public void respawnFood() {
        // finds number of empty spaces
        int numEmptySpaces = BOARD_NUM_ROW * BOARD_NUM_COL - snake.size();

        // generates a random nth free index on the board from 0 to numEmptySpaces
        int randomIndex = (int) (Math.random() * numEmptySpaces);

        /*
         * Finds the nth free space on the board and assigns the position to the
         * food.
         */
        for (int i = 0; i < BOARD_NUM_COL; i++) {
            for (int j = 0; j < BOARD_NUM_ROW; j++) {
                GameObj object = board.getObject(i, j);
                if (object == null) {
                    randomIndex--;
                    if (randomIndex == -1) {
                        food.pos_x = i;
                        food.pos_y = j;
                        break;
                    } 
                }
            }
        }

    }

    public void updateBoard() {
        board.resetBoard();
        board.setObject(food);
        for (int i = snake.size() - 1; i >= 0; i--) {
            Snake s = snake.get(i);
            board.setObject(s);
        }
        info.setScore(Game.score);
    }

    public int getScore() {
        return Game.score;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        info.draw(g);
        board.draw(g);
        if(newGame){
            g.setColor(new Color(47, 79, 79, 179));
            g.fillRect(0, 0, COURT_WIDTH, COURT_HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Verdana", Font.BOLD, 20));
            String message = "Press Enter to start!";
            g.drawString(message, 
             COURT_WIDTH / 2 - g.getFontMetrics().stringWidth(message)/2, COURT_HEIGHT / 2);
        }
        if(!playing && !newGame) {
            g.setColor(new Color(47, 79, 79, 179));
            g.fillRect(0, 0, COURT_WIDTH, COURT_HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Verdana", Font.BOLD, 20));
            String message = "Game over!";
            g.drawString(message, 
                    COURT_WIDTH / 2 - g.getFontMetrics().stringWidth(message)/2, COURT_HEIGHT / 2 - 50);
            message = "Press 'Enter' to save your score.";
            g.drawString(message, 
                    COURT_WIDTH / 2 - g.getFontMetrics().stringWidth(message)/2, COURT_HEIGHT / 2);
            message = "Press 'ESC' to quit.";
            g.drawString(message, 
                    COURT_WIDTH / 2 - g.getFontMetrics().stringWidth(message)/2, COURT_HEIGHT / 2 + 50);
        }
    }
}
