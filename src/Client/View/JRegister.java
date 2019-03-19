package Client.View;

import javax.swing.*;

public class JRegister extends JPanel {
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.getContentPane().add(new JRegister());
        test.setSize(200, 200);
        test.setTitle("JRegister Test");
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
    }

    private JTextField usernameRegister;
    private JTextField emailRegister;
    private JPasswordField passwordRegister;
    private JPasswordField passwordRegisterVerify;
    private JButton buttonRegister;

    public JRegister() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        usernameRegister = new JTextField();
        add(usernameRegister);
        emailRegister = new JTextField();
        add(emailRegister);
        passwordRegister = new JPasswordField();
        add(passwordRegister);
        passwordRegisterVerify = new JPasswordField();
        add(passwordRegisterVerify);
        buttonRegister = new JButton("Confirm");
        add(buttonRegister);
    }
}
