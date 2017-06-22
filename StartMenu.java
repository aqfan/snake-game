import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class StartMenu extends JPanel{
        private static final long serialVersionUID = 1L;
        
        private JButton start = new JButton("");
        private JButton settings = new JButton("");
        private JButton help = new JButton("");
        private JButton exit = new JButton ("");
        
        private ImageIcon start_button = new ImageIcon("button_start.png");
        private ImageIcon settings_button = new ImageIcon ("button_settings.png");
        private ImageIcon help_button = new ImageIcon("button_help.png");
        private ImageIcon exit_button = new ImageIcon("button_exit.png");
        
        private ImageIcon bkg = new ImageIcon("snake_background.png");
        private JLabel background = new JLabel("");
        private JPanel center = new JPanel();
        
        public StartMenu(int height, int width) {
            setPreferredSize(new Dimension(width, height));
            
            start.setIcon(start_button);
            settings.setIcon(settings_button);
            help.setIcon(help_button);
            exit.setIcon(exit_button);
            
            start.setBorder(BorderFactory.createEmptyBorder());
            settings.setBorder(BorderFactory.createEmptyBorder());
            help.setBorder(BorderFactory.createEmptyBorder());
            exit.setBorder(BorderFactory.createEmptyBorder());
            
            start.addMouseListener(new Clicked());
            settings.addMouseListener(new Clicked());
            help.addMouseListener(new Clicked());
            exit.addMouseListener(new Clicked());        
            
            center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
            center.setOpaque(false);
            center.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            center.add(Box.createVerticalStrut(60));
            center.add(start);
            center.add(Box.createVerticalStrut(20));
            center.add(settings);
            center.add(Box.createVerticalStrut(20));
            center.add(help);
            center.add(Box.createVerticalStrut(20));
            center.add(exit);
            
            setLayout(new GridBagLayout());
            
            background.setIcon(bkg);
            background.setLayout(new GridBagLayout());
            add(background);
            background.add(center);
        }

        class Clicked extends MouseAdapter{
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == start){
                    Game.cards.show(Game.menus, "GameCourt" );
                } else if (e.getSource() == settings) {
                    Game.cards.show(Game.menus, "Settings");
                } else if (e.getSource() == help) {
                    Game.cards.show(Game.menus, "HelpMenu");
                } else if (e.getSource() == exit) {
                    System.exit(0);
                }
            }
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);            
            repaint();
        }

}
