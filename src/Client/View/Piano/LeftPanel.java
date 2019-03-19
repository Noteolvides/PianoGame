package Client.View.Piano;

import javax.swing.*;

public class LeftPanel extends JPanel {
    public JButton prevOctave;
    private JButton nextOctave;

    public LeftPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        prevOctave = new JButton("↑");
        nextOctave = new JButton("↓");
        add(prevOctave);
        add(nextOctave);
    }
}
