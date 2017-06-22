import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class SettingsMenu extends JPanel {

    private static final long serialVersionUID = -1656799120775742049L;

    private JButton easy = new JButton("");
    private JButton medium = new JButton("");
    private JButton hard = new JButton("");
    
    private ImageIcon easy_button = new ImageIcon("button_easy.png");
    private ImageIcon medium_button = new ImageIcon("button_medium.png");
    private ImageIcon hard_button = new ImageIcon("button_hard.png");
    
    private ImageIcon bkg = new ImageIcon("level_background.png");
    private JLabel background = new JLabel("");
    
    private JPanel center = new JPanel();
    
    public SettingsMenu(int height, int width){
        setPreferredSize(new Dimension(width, height));

        easy.setIcon(easy_button);
        medium.setIcon(medium_button);
        hard.setIcon(hard_button);
        
        easy.setBorder(BorderFactory.createEmptyBorder());
        medium.setBorder(BorderFactory.createEmptyBorder());
        hard.setBorder(BorderFactory.createEmptyBorder());
        
        easy.addMouseListener(new Clicked());
        medium.addMouseListener(new Clicked());
        hard.addMouseListener(new Clicked());

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);
        center.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        center.add(Box.createVerticalStrut(60));
        center.add(easy);
        center.add(Box.createVerticalStrut(20));
        center.add(medium);
        center.add(Box.createVerticalStrut(20));
        center.add(hard);

        setLayout(new GridBagLayout());
        
        background.setIcon(bkg);
        background.setLayout(new GridBagLayout());
        add(background);
        background.add(center);
    }
    
    class Clicked extends MouseAdapter{
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == easy){
                Game.difficulty = "Easy";
                Game.cards.show(Game.menus, "StartMenu" );
            } else if (e.getSource() == medium){
                Game.difficulty = "Medium";
                Game.cards.show(Game.menus, "StartMenu");
            } else if (e.getSource() == hard) {
                Game.difficulty = "Hard";
                Game.cards.show(Game.menus, "StartMenu");
            } 
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        repaint();
    }
}
