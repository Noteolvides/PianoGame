package Client.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class JPrincipal extends JFrame {
    private BufferedImage iTitle;

    private JLabel jGameTitle;

    private JButton buttonPiano;
    private JButton buttonAmics;
    private JButton buttonSignOut;
    private JButton buttonDeleteAccount;

    /**
     * Constructor encarregat de mostrar la finestra principal deicada a l'accés a les funcionalitats principals
     * del Client.
     */
    public JPrincipal() {
        try {
            iTitle = ImageIO.read(new File("Project_IMG/Piano_Test.png"));
            ImageIcon imageicon=new ImageIcon(iTitle);
            JLabel label=new JLabel(imageicon);
            this.getContentPane().add(label, BorderLayout.PAGE_START);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error, no 'sha pogut accdedir a la iamtge interna");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JPanel vButtons = new JPanel();
        vButtons.setLayout(new BoxLayout(vButtons, BoxLayout.Y_AXIS));

        buttonPiano = new JButton("Piano");
        vButtons.add(buttonPiano);
        buttonAmics = new JButton("Social");
        vButtons.add(buttonAmics);

        JPanel vOut = new JPanel();
        vOut.setLayout(new FlowLayout());

        buttonSignOut = new JButton("Sign Out");
        vOut.add(buttonSignOut);
        buttonDeleteAccount = new JButton("Delete Account");
        vOut.add(buttonDeleteAccount);


        this.getContentPane().add(vButtons, BorderLayout.CENTER);
        this.getContentPane().add(vOut, BorderLayout.PAGE_END);
        pack();

        //Definim propietats de la finestra
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(600,600);
        //setMinimumSize(getSize());
        setTitle("Smart Piano");
        setVisible(true);
    }
}
