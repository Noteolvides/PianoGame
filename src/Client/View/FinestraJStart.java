package Client.View;

import Client.View.Start.JStart;

import javax.swing.*;

public class FinestraJStart extends JFrame {
    public FinestraJStart () {
        getContentPane().add(new JStart());
        setSize(300, 300);
        setTitle("Smart Piano");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
