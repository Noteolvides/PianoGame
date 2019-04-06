package Client.View;

import javax.swing.*;

public class FinestraJStart extends JFrame {
    public FinestraJStart () {
        getContentPane().add(new JStart());
        setSize(300, 300);
        setTitle("Smart Piano");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
