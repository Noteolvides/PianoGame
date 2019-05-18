package Server.Controller;

import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController implements ActionListener {
    private View view;
    private ServiceBBDDServer serviceBBDDServer;

    public RegisterController(View view, ServiceBBDDServer serviceBBDDServer) {
        this.view = view;
        this.serviceBBDDServer = serviceBBDDServer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("REGISTER")) {
            String password = new String(view.getRegisterView().getPassword());
            String confirmPassword = new String(view.getRegisterView().getPasswordVerify());
            String email = view.getRegisterView().getEmail();
            Utils utils = new Utils();
            if (utils.confirmPassword(password, confirmPassword, view.getRegisterView().getUsername())) {
                if(!utils.confirmEmail(email)){
                    JOptionPane.showMessageDialog(null, "Email doesn't meet the requierements!", "Error", JOptionPane.WARNING_MESSAGE);
                }else{
                    try {
                        serviceBBDDServer.createUserFromNoUser(view.getRegisterView().getUsername(),password,view.getRegisterView().getEmail());
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog( null, "This UserName already exists", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    System.out.println("Registered");
                }
            } else {
                view.getRegisterView().errorPasswordPopUp();
            }
        }
    }
}
