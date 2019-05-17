package Client.View.Start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Client.Network.ClientConnection.*;

/**
 * Class that creates the start view with the login and to enter the register.
 */
public class JStart extends JFrame {
    private JTextField emailLogin;
    private JPasswordField passwordField;
    private JButton buttonSignIn;
    private JButton buttonRegister;

    /**
     * Constructor, it creates and adds all the jComponents and jPanels to the jFrame.
     */
    public JStart (){
        JPanel start = new JPanel();
        start.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        start.setLayout(new BoxLayout(start,BoxLayout.PAGE_AXIS));
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
        start.add(fields);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttonSignIn = new JButton("Sign In");
        buttons.add(buttonSignIn);
        buttonRegister = new JButton("Register");
        buttons.add(buttonRegister);
        start.add(buttons);

        getContentPane().add(start);
        setSize(300, 300);
        setTitle("Smart Piano");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public String getLogin() {
        return emailLogin.getText();
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    /**
     * Function that assigns the Controller/ActionListener to the view.
     * @param c Controller/ActionListener
     */
    public void registerController(ActionListener c) {
        buttonSignIn.setActionCommand("SIGN-IN");
        buttonSignIn.addActionListener(c);
        buttonRegister.setActionCommand("GO-REGISTER");
        buttonRegister.addActionListener(c);
        this.addWindowListener((WindowListener) c);
    }

    /**
     * Function that show a PopUp to verify if the login or the register
     * were done successfully.
     * @param process String to see if is login or register.
     * @param petitionResult Type of error.
     */
    public void errorPopUp(String process, int petitionResult) {
        if (process.equals("login")) {
            if (petitionResult == ERROR_OBJECT) {
                JOptionPane.showMessageDialog(this, "Error. Login wasn't successful. The fields are not correct.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            if (petitionResult == ERROR_BBDD) {
                JOptionPane.showMessageDialog(this, "Error, Login wasn't successful.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (process.equals("register")) {
            if (petitionResult == OK) {
                JOptionPane.showMessageDialog(this, "Register was successful.", "Registered", JOptionPane.INFORMATION_MESSAGE);
            }
            if (petitionResult == KO) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
            }
            if (petitionResult == ERROR_OBJECT) {
                JOptionPane.showMessageDialog(this, "Register wasn't successful. The fields are not correct.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            if (petitionResult == ERROR_BBDD) {
                JOptionPane.showMessageDialog(this, "Error. Register wasn't successful.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
