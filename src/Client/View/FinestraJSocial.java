package Client.View;
import javax.swing.*;

public class FinestraJSocial extends JFrame {
    private JSocial jSocial;
    public FinestraJSocial () {
        jSocial = new JSocial();
        JFrame test = new JFrame();
        test.setSize(400,405);
        test.add(jSocial);
        test.setVisible(true);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public JSocial getjSocial() {
        return jSocial;
    }

    public void setjSocial(JSocial jSocial) {
        this.jSocial = jSocial;
    }
}
