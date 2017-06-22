import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.*;

public class EndMenu extends JPanel {
    
    private static final long serialVersionUID = -3893041218932094419L;
    private HighScoreUpdater highscores;
    
    private JButton exit = new JButton ("");
    private JButton save = new JButton("");
    private JButton show_scores = new JButton("");
    
    private JTextField enter = new JTextField("");
    
    private ImageIcon exit_button = new ImageIcon("button_exit.png");
    private ImageIcon save_button = new ImageIcon("button_save.png");
    private ImageIcon show_button = new ImageIcon("button_show-highscores.png");
    
    private ImageIcon bkg = new ImageIcon("snake_background.png");
    
    private JLabel background = new JLabel("");
    private JLabel enter_text = new JLabel("Enter Username: ");
   
    private JPanel center = new JPanel();
    private JPanel vertical = new JPanel();
    private JPanel userInput = new JPanel();
    
    private String username;
    
    public EndMenu(int height, int width){
        setPreferredSize(new Dimension(width, height));
        
        try {
            highscores = new HighScoreUpdater();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HighScoreUpdater.FormatException e) {
            e.printStackTrace();
        }
        
        enter_text.setForeground(Color.WHITE);
        enter_text.setFont(new Font("Verdana", Font.BOLD, 10));
        
        userInput.setLayout(new BoxLayout(userInput, BoxLayout.X_AXIS));
        userInput.setOpaque(false);
        userInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        userInput.add(enter_text);
        userInput.add(enter);
        
        save.setIcon(save_button);
        exit.setIcon(exit_button);
        show_scores.setIcon(show_button);
        
        save.setBorder(BorderFactory.createEmptyBorder());
        exit.setBorder(BorderFactory.createEmptyBorder());
        show_scores.setBorder(BorderFactory.createEmptyBorder());
        
        save.addMouseListener(new Clicked());
        exit.addMouseListener(new Clicked());
        show_scores.addMouseListener(new Clicked());
        
        show_scores.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
        center.setOpaque(false);
        center.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        center.add(save);
        center.add(Box.createHorizontalStrut(20));
        center.add(exit);
        
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));
        vertical.setOpaque(false);
        
        vertical.add(userInput);
        vertical.add(Box.createVerticalStrut(20));
        vertical.add(center);
        vertical.add(Box.createVerticalStrut(20));
        vertical.add(show_scores);
        
        setLayout(new GridBagLayout());
        
        background.setIcon(bkg);
        background.setLayout(new GridBagLayout());
        add(background);
        background.add(vertical);
    }
    
    class Clicked extends MouseAdapter{
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == save){
                saveScore();
            } else if (e.getSource() == exit) {
                System.exit(0);
            } else if (e.getSource() == show_scores){
                showScore();
            }
        }
    }

    public void saveScore() {
        try {
            username = enter.getText();
            highscores.write(username, Game.score, Game.difficulty);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HighScoreUpdater.FormatException e) {
            e.printStackTrace();
        }
    }
    
    public void showScore() {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        if (Game.difficulty.equals("Easy")){
            map = highscores.getEasy();
        } else if (Game.difficulty.equals("Medium")) {
            map = highscores.getMedium();
        } else if (Game.difficulty.equals("Hard")) {
            map = highscores.getHard();
        }
        Set<Entry<String, Integer>> set = map.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        
        String output = "";
        
        int size = 5;
        if(list.size() < size){
            size = list.size();
        }
        
        for(int i = 1; i <= size; i++){
            output = output + "#" + i + ": " + list.get(i-1).getKey() + ", " + list.get(i-1).getValue() + "\n";
        }
        JOptionPane.showMessageDialog(this, output, "Top Highscores", JOptionPane.PLAIN_MESSAGE);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        repaint();
    }
}
