package Server.Controller;

import Server.View.View;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private View view;

    public RegisterController(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("REGISTER")) {
            System.out.println("Registered");
        }
    }
}
