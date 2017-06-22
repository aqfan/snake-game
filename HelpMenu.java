import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class HelpMenu extends JPanel {
    
    private static final long serialVersionUID = -2837861830664741390L;
    
    private JButton back = new JButton("");
    private ImageIcon back_button = new ImageIcon("button_back.png");
    
    private ImageIcon bkg = new ImageIcon("help_background.png");
    private JLabel background = new JLabel("");
    
    private JPanel center = new JPanel();
    
    public HelpMenu(int height, int width){
        setPreferredSize(new Dimension(width, height));
        
        back.setIcon(back_button);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                Game.cards.show(Game.menus, "StartMenu");
            }
        });
        
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);
        center.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        center.add(Box.createVerticalStrut(380));
        center.add(back);
        
        setLayout(new GridBagLayout());
        
        background.setIcon(bkg);
        background.setLayout(new GridBagLayout());
        add(background);
        background.add(center);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        repaint();
    }
}
