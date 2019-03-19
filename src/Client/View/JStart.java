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
    private JButton buttonSignUp;
    private JButton buttonRegister;

    public JStart (){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        emailLogin = new JTextField();
        add(emailLogin);
        passwordField = new JPasswordField();
        add(passwordField);
        buttonSignUp = new JButton("Sign Up");
        add(buttonSignUp);
        buttonRegister = new JButton("Register");
        add(buttonRegister);
    }

}
