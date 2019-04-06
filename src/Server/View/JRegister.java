package Server.View;

import javax.swing.*;
import java.awt.*;


public class JRegister extends JPanel {
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.getContentPane().add(new JRegister());
        test.setSize(250, 300);
        test.setTitle("Register");
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setResizable(false);
        test.setVisible(true);
    }

    private JTextField usernameRegister;
    private JTextField emailRegister;
    private JPasswordField passwordRegister;
    private JPasswordField passwordRegisterVerify;
    private JButton buttonRegister;

    public JRegister() {
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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
        add(fields);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonRegister = new JButton("Register");
        buttonPanel.add(buttonRegister);
        add(buttonPanel);
    }
}
