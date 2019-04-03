package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    public JButton prevOctave;
    private JButton nextOctave;

    public LeftPanel(){
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        prevOctave = new JButton("→");
        nextOctave = new JButton("←");
        prevOctave.setBackground(Color.BLACK);
        nextOctave.setBackground(Color.BLACK);
        prevOctave.setForeground(Color.WHITE);
        nextOctave.setForeground(Color.WHITE);
        add(prevOctave);
        add(Box.createRigidArea(new Dimension(50,0)));
        add(nextOctave);
    }
}
