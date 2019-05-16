package Client.View.Piano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyConfigurationVisual extends JPanel {
    private JLabel name;
    private JTextField key;

    public KeyConfigurationVisual(String name, char keyArg) {
        setLayout(new FlowLayout());
        this.name = new JLabel(name);
        this.key = new JTextField();
        this.key.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (key.getText().length() == 1) {
                    e.consume();
                }
            }
        });
        this.key.setText(String.valueOf(keyArg));
        this.key.setColumns(1);

        add(this.name);
        add(this.key);
    }


    public JTextField getKey() {
        return key;
    }

    public void setKey(JTextField key) {
        this.key = key;
    }
}
