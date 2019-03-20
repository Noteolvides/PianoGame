package Client.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JPrincipal extends JFrame {
    private BufferedImage iTitle;

    private JLabel jGameTitle;

    private JButton jPiano;
    private JButton jSocial;
    private JButton jSignOut;
    private JButton jNoMeAcuerdo;

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

        //Definim propietats de la finestra
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        //setMinimumSize(getSize());
        setTitle("LSGym");
        setVisible(true);
    }
}
