package Client.Controller.Start;

import Client.View.Start.JRegister;
import Client.View.Start.JStart;
import Client.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {
    private View view;
    public StartController(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SIGN-IN")){
            System.out.println(view.getStartView().getLogin());
            System.out.println(view.getStartView().getPassword());
            System.out.println("Sing in");
        }
        if (e.getActionCommand().equals("GO-REGISTER")){
            System.out.println("Register");
            view.initRegisterView();
            view.getRegisterView().registerController(this);
        }
        if (e.getActionCommand().equals("REGISTER")) {
            System.out.println(view.getRegisterView().getEmail());
            System.out.println(view.getRegisterView().getUsername());
            System.out.println(view.getRegisterView().getPassword());
            System.out.println(view.getRegisterView().getPasswordVerify());
            System.out.println("Registered");
        }
    }
}
