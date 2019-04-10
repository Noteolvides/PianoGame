package Server.Controller;

import Server.View.View;

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
            System.out.println(view.getRegisterView().getUsername());
            System.out.println(view.getRegisterView().getEmail());
            System.out.println(view.getRegisterView().getPassword());
            System.out.println(view.getRegisterView().getPasswordVerify());
            System.out.println("Registered");
        }
    }
}
