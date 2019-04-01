package Client.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JStart extends JPanel {
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.getContentPane().add(new JStart());
        test.setSize(300, 300);
        test.setTitle("Smart Piano");
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setResizable(false);
        test.setVisible(true);
    }

    private JTextField emailLogin;
    private JPasswordField passwordField;
    private JButton buttonSignIn;
    private JButton buttonRegister;

    public JStart (){
        setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("img/SmartPiano.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon icon = null;
        if (img != null) {
            icon = new ImageIcon(img);
            icon = new ImageIcon(icon.getImage().getScaledInstance(304,112, Image.SCALE_SMOOTH));
        }
        JLabel imagen = new JLabel();
        imagen.setIcon(icon);
        fields.add(imagen);

        JLabel emailLabel = new JLabel("Input your UserName or Email:");
        fields.add(emailLabel);
        fields.add(Box.createVerticalStrut(8));
        emailLogin = new JTextField();
        fields.add(emailLogin);
        fields.add(Box.createVerticalStrut(8));
        JLabel passwordLabel = new JLabel("Input your password:");
        fields.add(passwordLabel);
        fields.add(Box.createVerticalStrut(8));
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
