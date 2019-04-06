package Client.View;
import Client.Controller.ControllerJSocial;

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
    public void registerController (ControllerJSocial controllerJSocial) {
        jSocial.registerController(controllerJSocial);
    }

    public static void main(String[] args) {
        FinestraJSocial fj = new FinestraJSocial();
        ControllerJSocial controllerJSocial = new ControllerJSocial(fj);
        fj.registerController(controllerJSocial);
    }
}
