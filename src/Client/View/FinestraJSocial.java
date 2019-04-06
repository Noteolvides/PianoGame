package Client.View;
import javax.swing.*;

public class FinestraJSocial extends JFrame {
    public FinestraJSocial () {
        JFrame test = new JFrame();
        test.setSize(400,405);
        test.add(new JSocial());
        test.setVisible(true);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
