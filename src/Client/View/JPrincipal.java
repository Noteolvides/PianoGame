package Client.View;

import Client.Controller.MenuPrincipal.JPrincipalController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Client.Network.ClientConnection.*;

/**
 * Class that crates Main Menu View
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class JPrincipal extends JFrame {
    public static final String JPIANO= "Piano";
    public static final String JSOCIAL = "Social";
    public static final String SIGN_OUT = "Sign Out";
    public static final String DELETE_ACCOUNT = "Delete Account";

    private BufferedImage iTitle;

    private JButton buttonPiano;
    private JButton buttonAmics;
    private JButton buttonSignOut;
    private JButton buttonDeleteAccount;
    private JLabel userCodePanel;

    private JPanel imagePanel;

    /**
     * Constructor encarregat de mostrar la finestra principal deicada a l'accés a les funcionalitats principals
     * del Client.
     */
    public JPrincipal() {
        try {
            iTitle = ImageIO.read(new File("img/SmartPiano.png"));
            ImageIcon imageicon=new ImageIcon(iTitle);
            imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(300,110, Image.SCALE_SMOOTH));
            JLabel label= new JLabel(imageicon);
            imagePanel = new JPanel();
            imagePanel.add(label);
            imagePanel.setBackground(Color.WHITE);
            add(imagePanel, BorderLayout.NORTH);

        } catch (IOException ex) {
            System.out.println("Error, no 'sha pogut accedir a la imatge interna");
        }
        JPanel vButtons = new JPanel();
        vButtons.setLayout(new BoxLayout(vButtons, BoxLayout.Y_AXIS));

        JPanel vApp = new JPanel();
        //vApp.setLayout(new BoxLayout(vApp, BoxLayout.Y_AXIS));
        vApp.setLayout(new GridLayout(2,0));


        buttonPiano = new JButton("Piano");
        buttonPiano.setPreferredSize(new Dimension(250, 40));
        vApp.add(buttonPiano);

        buttonAmics = new JButton("Social");
        buttonAmics.setPreferredSize(new Dimension(250, 40));
        vApp.add(buttonAmics);

        vApp.setBorder(BorderFactory.createEmptyBorder(0,70,0,70));
        vButtons.add(vApp);

        JPanel vOut = new JPanel();
        vOut.setLayout(new FlowLayout());

        buttonSignOut = new JButton("Sign Out");
        buttonSignOut.setPreferredSize(new Dimension(125, 40));
        vOut.add(buttonSignOut);
        buttonDeleteAccount = new JButton("Delete Account");
        buttonDeleteAccount.setPreferredSize(new Dimension(125, 40));
        vOut.add(buttonDeleteAccount);
        vOut.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        vButtons.add(vOut);
        userCodePanel = new JLabel("Your code: ");
        vButtons.add(userCodePanel);

        add(vButtons, BorderLayout.CENTER);

        vApp.setBackground(Color.WHITE);
        vOut.setBackground(Color.WHITE);
        vButtons.setBackground(Color.WHITE);

        //Definim propietats de la finestra
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400,300);
        setMaximumSize(new Dimension(400, 300));
        setTitle("Smart Piano");
        setVisible(false);
    }

    public void registerController(JPrincipalController pc) {
        buttonPiano.setActionCommand(JPIANO);
        buttonPiano.addActionListener(pc);

        buttonAmics.setActionCommand(JSOCIAL);
        buttonAmics.addActionListener(pc);

        buttonSignOut.setActionCommand(SIGN_OUT);
        buttonSignOut.addActionListener(pc);

        buttonDeleteAccount.setActionCommand(DELETE_ACCOUNT);
        buttonDeleteAccount.addActionListener(pc);
    }

    public void setUserCode(String userCode) {
        userCodePanel.setText("Your code: " + userCode);
    }

    /**
     * Function that creates a prompt to confirm the account deletion.
     * @return Returns the option selected in the prompt.
     */
    public int confirmPopUp() {
        return JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the account?", "Delete Account Confirmation", JOptionPane.YES_NO_OPTION);
    }

    /**
     * Function that shows a PopUp with the information about the error of the account deletion.
     * @param deleted Type of error.
     */
    public void deletedAccountPopUp(int deleted) {
        if (deleted == KO) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (deleted == ERROR_OBJECT) {
            JOptionPane.showMessageDialog(this, "An error occurred the account wasn't deleted! User incorrect.", "Error",JOptionPane.WARNING_MESSAGE);
        }
        if (deleted == ERROR_BBDD) {
            JOptionPane.showMessageDialog(this, "An error occurred the account wasn't deleted!", "Error",JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Function that shows a PopUp with the information about the error of the logout.
     * @param petitionResult Type of error.
     */
    public void logOutPopUp(int petitionResult) {
        if (petitionResult == KO) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_BBDD){
            JOptionPane.showMessageDialog(this, "An error occurred the LogOut was unsuccessful!", "Error",JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_OBJECT) {
            JOptionPane.showMessageDialog(this, "An error occurred the LogOut was unsuccessful! User incorrect.", "Error",JOptionPane.WARNING_MESSAGE);
        }
    }
}
