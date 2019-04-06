package Client.View;

import javax.swing.*;

public class FinestraJStart extends JFrame {
    public FinestraJStart () {
        JFrame test = new JFrame();
        test.getContentPane().add(new JStart());
        test.setSize(300, 300);
        test.setTitle("Smart Piano");
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setResizable(false);
        test.setVisible(true);
    }
}
