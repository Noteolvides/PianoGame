package View;

import javax.swing.*;

public class Prueba extends JFrame {
    private JSocial jsocial;
    public Prueba () {
        setSize(400,420);
        jsocial = new JSocial();
        add(jsocial);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
