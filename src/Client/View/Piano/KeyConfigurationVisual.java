package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class KeyConfigurationVisual extends JPanel {
    private JLabel name;
    private JTextField key;

    public KeyConfigurationVisual(String name, char key) {
        setLayout(new FlowLayout());
        this.name = new JLabel(name);
        this.key = new JTextField();
        this.key.setText(String.valueOf(key));
        this.key.setColumns(1);
    }
}
