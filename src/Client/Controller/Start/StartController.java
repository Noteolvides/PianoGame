package Client.Controller.Start;

import Client.Controller.Controller;
import Client.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {
    private View view;
    private Controller controller;
    public StartController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SIGN-IN")){
            System.out.println(view.getStartView().getLogin());
            System.out.println(view.getStartView().getPassword());
            System.out.println("Sing in");
            controller.networkLogIn();
        }
        if (e.getActionCommand().equals("GO-REGISTER")){
            System.out.println("Register");
            controller.openRegister();
        }
        if (e.getActionCommand().equals("REGISTER")) {
            System.out.println(view.getRegisterView().getEmail());
            System.out.println(view.getRegisterView().getUsername());
            System.out.println(view.getRegisterView().getPassword());
            System.out.println(view.getRegisterView().getPasswordVerify());
            System.out.println("Registered");
            controller.networkRegister();
            controller.closeRegister();
        }
    }
}
