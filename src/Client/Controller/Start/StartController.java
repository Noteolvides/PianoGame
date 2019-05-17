package Client.Controller.Start;

import Client.Controller.Controller;
import Client.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Controller class for the Start and Register View
 * that implements ActionListener and WindowListener.
 */
public class StartController implements ActionListener, WindowListener {
    private View view;
    private Controller controller;

    /**
     * Start Controller constructor, it assigns the view and the father controller
     * @param view Class with all the views.
     * @param controller  Class Controller Father that manages all the subcontrollers.
     */
    public StartController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    /**
     * Method implemented from the ActionListener.
     * @param e Action done in the View.
     */
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
            String password = new String(view.getRegisterView().getPassword());
            String confirmPassword = new String(view.getRegisterView().getPasswordVerify());
            if (confirmPassword(password, confirmPassword, view.getRegisterView().getUsername()) && confirmEmail(view.getRegisterView().getEmail())) {
                System.out.println("Registered");
                controller.networkRegister();
            } else {
                view.getRegisterView().errorPasswordPopUp();
            }
        }
    }

    /**
     * Function that confirms if the password meets the MIT requirements.
     * @param password Password inputted in the view.
     * @param confirmPassword Verifing password inputted in the view.
     * @param username Username inputted in the view.
     * @return Returns if the password meets the requirements.
     */
    private boolean confirmPassword(String password, String confirmPassword, String username) {
        boolean validPassword;
        validPassword = password.equals(confirmPassword);
        if (validPassword) {
            validPassword = password.length() >= 8;
            if (validPassword) {
                validPassword = !password.contains(username);
                if (validPassword) {
                    validPassword = specialCharacters(password);
                }
            }
        }

        return validPassword;
    }

    /**
     * Function that checks the requirement involving special characters.
     * @param password Password needed to check.
     * @return Returns if it meets the special characters requirement.
     */
    private boolean specialCharacters(String password) {
        int num = 0;
        if (password.matches(".*[a-z].*")) {
            num++;
        }
        if (password.matches(".*[A-Z].*")) {
            num++;
        }
        if (password.matches("(.*[@#$%^&+=].*)")) {
            num++;
        }
        if (password.matches("(.*[0-9]*.)")) {
            num++;
        }
        return num >= 2;
    }

    public boolean confirmEmail(String email){
        return email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    /**
     * Implemented function, action to do when window opens.
     * @param e Window event.
     */
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Implemented function, action to do when window closes.
     * @param e Window event.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if (JOptionPane.showConfirmDialog(view.getStartView(),
                "Are you sure you want to close this window?", "Close Window?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            view.getStartView().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            controller.networkClose();
        }
    }

    /**
     * Implemented function, action to do when window is closed.
     * @param e Window event.
     */
    @Override
    public void windowClosed(WindowEvent e) {

    }

    /**
     * Implemented function, action to do when window is iconified.
     * @param e Window event.
     */
    @Override
    public void windowIconified(WindowEvent e) {

    }

    /**
     * Implemented function, action to do when window is deiconified.
     * @param e Window event.
     */
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    /**
     * Implemented function, action to do when window is activated.
     * @param e Window event.
     */
    @Override
    public void windowActivated(WindowEvent e) {

    }

    /**
     * Implemented function, action to do when window is deactivated.
     * @param e Window event.
     */
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
