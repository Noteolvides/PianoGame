package View;

import javax.swing.*;

public class Prueba extends JFrame {
    private JSong pantalla;
    public Prueba () {
        pantalla = new JSong();
        add(pantalla);
        setSize(300,1000);
        setVisible(true);

    }
}
