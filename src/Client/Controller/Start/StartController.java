package Client.Controller.Start;

import Client.Controller.Controller;
import Client.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class StartController implements ActionListener, WindowListener {
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

    private boolean confirmPassword(String password, String confirmPassword) {
        boolean validPassword;
        validPassword = password.equals(confirmPassword);
        if (validPassword) {
            validPassword = password.length() >= 8;
            if (validPassword) {
                validPassword = !password.contains(view.getRegisterView().getUsername());
                if (validPassword) {
                    validPassword = specialCharacters(password);
                }
            }
        }

        return validPassword;
    }

    private boolean specialCharacters(String password) {
        int num = 0;
        if (password.matches("(?=.*[a-z])")) {
            num++;
        }
        if (password.matches("(?=.*[A-Z])")) {
            num++;
        }
        if (password.matches("(?=.*[@#$%^&+=])")) {
            num++;
        }
        if (password.matches("(?=.*d)")) {
            num++;
        }
        return num >= 2;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (JOptionPane.showConfirmDialog(view.getStartView(),
                "Are you sure you want to close this window?", "Close Window?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            view.getStartView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            controller.networkClose();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
