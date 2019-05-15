package Server.Controller;

import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.View;

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
            Utils utils = new Utils();
            if (utils.confirmPassword(password, confirmPassword, view.getRegisterView().getUsername())) {
                try {
                    serviceBBDDServer.createUserFromNoUser(view.getRegisterView().getUsername(),password,"hola.txt",view.getRegisterView().getEmail());
                } catch (Exception e1) {
                    //TODO: Error, el usuario ya existe en la paltaforma
                }
                System.out.println("Registered");
            } else {
                view.getRegisterView().errorPasswordPopUp();
            }
        }
    }
}
