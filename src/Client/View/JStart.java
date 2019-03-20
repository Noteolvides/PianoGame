package Client.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class JStart extends JPanel {
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.getContentPane().add(new JStart());
        test.setSize(300, 300);
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
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("SMART PIANO");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        titlePanel.add(title);
        add(titlePanel);

        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
        BufferedImage img = null;
        try {
            img = ImageIO.read(new URL(
                    "https://tse2.mm.bing.net/th?id=OIP.AalzUDPdSRN0Oo5wZZKgngHaCl&pid=Api&P=0&w=300&h=300"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(img);
        JLabel imagen = new JLabel();
        imagen.setIcon(icon);
        fields.add(imagen);

        JLabel emailLabel = new JLabel("Input your UserName or Email:");
        fields.add(emailLabel);
        emailLogin = new JTextField();
        fields.add(emailLogin);
        JLabel passwordLabel = new JLabel("Input your password:");
        fields.add(passwordLabel);
        passwordField = new JPasswordField();
        fields.add(passwordField);
        add(fields);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttonSignIn = new JButton("Sign In");
        buttons.add(buttonSignIn);
        buttonRegister = new JButton("Register");
        buttons.add(buttonRegister);
        add(buttons);
    }
}
