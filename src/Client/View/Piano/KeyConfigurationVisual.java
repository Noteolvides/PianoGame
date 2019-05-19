package Client.View.Piano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Component in view ChangeKeys, that contais a textField and name
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class KeyConfigurationVisual extends JPanel {
    private JLabel name;
    private JTextField key;

    /**
     * Initialization of keyConfiguration
     * @param name Name of key to change
     * @param keyArg Key that it is going to be change to
     */
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
