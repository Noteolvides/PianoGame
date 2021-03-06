package Client.View.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class that creates the view of the register.
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class JRegister extends JFrame {
    private JTextField usernameRegister;
    private JTextField emailRegister;
    private JPasswordField passwordRegister;
    private JPasswordField passwordRegisterVerify;
    private JButton buttonRegister;

    /**
     * Constructor, it creates and adds all the jComponents and jPanels to the jFrame.
     */
    public JRegister() {
        JPanel register = new JPanel();
        register.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        register.setLayout(new BoxLayout(register, BoxLayout.PAGE_AXIS));
        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
        JLabel usernameLabel = new JLabel("Your Username:");
        fields.add(usernameLabel);
        fields.add(Box.createVerticalStrut(8));
        usernameRegister = new JTextField();
        fields.add(usernameRegister);
        fields.add(Box.createVerticalStrut(8));
        JLabel emailLabel = new JLabel("Your email:");
        fields.add(emailLabel);
        fields.add(Box.createVerticalStrut(8));
        emailRegister = new JTextField();
        fields.add(emailRegister);
        fields.add(Box.createVerticalStrut(8));
        JLabel passwordLabel = new JLabel("Your password:");
        fields.add(passwordLabel);
        fields.add(Box.createVerticalStrut(8));
        passwordRegister = new JPasswordField();
        fields.add(passwordRegister);
        fields.add(Box.createVerticalStrut(8));
        JLabel confirmationLabel = new JLabel("Confirm your password:");
        fields.add(confirmationLabel);
        fields.add(Box.createVerticalStrut(8));
        passwordRegisterVerify = new JPasswordField();
        fields.add(passwordRegisterVerify);
        fields.add(Box.createVerticalStrut(4));
        register.add(fields);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonRegister = new JButton("Register");
        buttonPanel.add(buttonRegister);
        register.add(buttonPanel);

        getContentPane().add(register);
        setSize(250, 300);
        setTitle("Register");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

    /**
     * Function that assigns the Controller/ActionListener to the view.
     * @param c Controller/ActionListener
     */
    public void registerController(ActionListener c) {
        buttonRegister.setActionCommand("REGISTER");
        buttonRegister.addActionListener(c);
    }

    /**
     * Function that shows a popup when the password is incorrect.
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
