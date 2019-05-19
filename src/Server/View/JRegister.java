package Server.View;

import Server.Controller.RegisterController;

import javax.swing.*;
import java.awt.*;


public class JRegister extends JFrame {
    private JTextField usernameRegister;
    private JTextField emailRegister;
    private JPasswordField passwordRegister;
    private JPasswordField passwordRegisterVerify;
    private JButton buttonRegister;

    /**
     * JRegister View
     */
    public JRegister() {
        JPanel register = new JPanel();

        //Adding the view's icon
        ImageIcon icon = new ImageIcon("img/registerIcon.png");
        setIconImage(icon.getImage());

        //Adding all the Register form fields
        register.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        register.setLayout(new BoxLayout(register, BoxLayout.PAGE_AXIS));
        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
        JLabel usernameLabel = new JLabel("Username:");
        fields.add(usernameLabel);
        fields.add(Box.createVerticalStrut(8));
        usernameRegister = new JTextField();
        fields.add(usernameRegister);
        fields.add(Box.createVerticalStrut(8));
        JLabel emailLabel = new JLabel("Email:");
        fields.add(emailLabel);
        fields.add(Box.createVerticalStrut(8));
        emailRegister = new JTextField();
        fields.add(emailRegister);
        fields.add(Box.createVerticalStrut(8));
        JLabel passwordLabel = new JLabel("Password:");
        fields.add(passwordLabel);
        fields.add(Box.createVerticalStrut(8));
        passwordRegister = new JPasswordField();
        fields.add(passwordRegister);
        fields.add(Box.createVerticalStrut(8));
        JLabel confirmationLabel = new JLabel("Confirm password:");
        fields.add(confirmationLabel);
        fields.add(Box.createVerticalStrut(8));
        passwordRegisterVerify = new JPasswordField();
        fields.add(passwordRegisterVerify);
        fields.add(Box.createVerticalStrut(4));
        register.add(fields);

        //Adding the Register button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonRegister = new JButton("Register");
        buttonPanel.add(buttonRegister);
        register.add(buttonPanel);

        //General attributes of the View
        getContentPane().add(register);
        setSize(250, 300);
        setTitle("Register");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(false);
    }

    public String getUsername() {
        return usernameRegister.getText();
    }

    public String getEmail() {
        return emailRegister.getText();
    }

    public char[] getPassword() {
        return passwordRegister.getPassword();
    }

    public char[] getPasswordVerify() {
        return passwordRegisterVerify.getPassword();
    }

    public void setUsernameRegister() {
        this.usernameRegister.setText("");
    }

    public void setEmailRegister() {
        this.emailRegister.setText("");
    }

    public void setPasswordRegister() {
        this.passwordRegister.setText("");
    }

    public void setPasswordRegisterVerify() {
        this.passwordRegisterVerify.setText("");
    }

    /**
     * Mapping the Register Controller to the Register View
     * @param controller: Controller
     */

    public void registerController(RegisterController controller) {
        buttonRegister.setActionCommand("REGISTER");
        buttonRegister.addActionListener(controller);
    }

    public void setVisible(){
        setVisible(true);
    }
    public void setInvisible(){
        setVisible(false);
    }

    /**
     * If the password doesn't match with the authentication pattern it shows a pop-up
     */
    public void errorPasswordPopUp() {
        JOptionPane.showMessageDialog(this, "The password doesn't meet the requirements or the confirm doesn't match!" +
                "\n\t\t\t\t\t\tThe password must at least be 8 caracters long." +
                "\n\t\t\t\t\t\tCan not contain your username." +
                "\n\t\t\t\t\t\tAnd at least have 2 of these:" +
                "\n\t\t\t\t\t\t\t\t\t\t\t\tLower case characters, " +
                "Upper case characters, " +
                "\n\t\t\t\t\t\t\t\t\t\t\t\tSpecial characters or " +
                "Numbers", "Bad password", JOptionPane.WARNING_MESSAGE);
    }

}
