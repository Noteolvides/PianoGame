package Client.View;

import javax.swing.*;

public class PruebaJSocial extends JFrame {
    private JSocial jsocial;
    public PruebaJSocial() {
        setSize(400,405);
        jsocial = new JSocial();
        add(jsocial);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
