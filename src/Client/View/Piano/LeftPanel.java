package Client.View.Piano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LeftPanel extends JPanel {
    private JButton prevOctave;
    private JButton nextOctave;

    public LeftPanel(){
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        prevOctave = new JButton("→");
        nextOctave = new JButton("←");
        prevOctave.setBackground(Color.BLACK);
        nextOctave.setBackground(Color.BLACK);
        prevOctave.setForeground(Color.WHITE);
        nextOctave.setForeground(Color.WHITE);
        prevOctave.setMnemonic(KeyEvent.VK_SEMICOLON);
        prevOctave.setMnemonic(KeyEvent.VK_COLON);
        add(prevOctave);
        add(Box.createRigidArea(new Dimension(50,0)));
        add(nextOctave);
    }

    public JButton getPrevOctave() {
        return prevOctave;
    }

    public JButton getNextOctave() {
        return nextOctave;
    }
}
