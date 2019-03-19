package Client.View;

import javax.swing.*;

public class JStart extends JPanel {
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.getContentPane().add(new JStart());
        test.setSize(200, 200);
        test.setTitle("JStart Test");
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
    }

    private JTextField emailLogin;
    private JPasswordField passwordField;
    private JButton buttonSignIn;
    private JButton buttonRegister;

    public JStart (){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel emailLabel = new JLabel("Input your UserName or Email:");
        add(emailLabel);
        emailLogin = new JTextField();
        add(emailLogin);
        JLabel passwordLabel = new JLabel("Input your password:");
        add(passwordLabel);
        passwordField = new JPasswordField();
        add(passwordField);
        buttonSignIn = new JButton("Sign In");
        add(buttonSignIn);
        buttonRegister = new JButton("Register");
        add(buttonRegister);
    }

}
