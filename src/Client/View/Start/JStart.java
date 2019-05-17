package Client.View.Start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     * Function that shows a popup when the login is called.
     * @param process Strign that indicates if there's an error or not.
     */
    public void errorPopUp(String process) {
        if (process.equals("login")) {
            JOptionPane.showMessageDialog(this, "Error, Login wasn't successful.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        if (process.equals("register")) {
            JOptionPane.showMessageDialog(this, "Error. Register wasn't successful.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
