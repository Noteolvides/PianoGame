package Server.View;

import Server.Controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Server Graphical View Class (4 buttons for control)
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class ServerViews {
        private JButton serverGraph, topSongs, registerUser, gestor;

        /**
         * Server View, with 4 buttons for each functionality of the Server
         */
        public ServerViews(){
                JFrame serverFrame = new JFrame("Server Functionalities");
                serverFrame.setLayout(new GridLayout(4, 1));

                //Adding icons and title to the buttons
                registerUser = new JButton("Register User");
                ImageIcon iconRegister = new ImageIcon("img/registerIcon.png");
                registerUser.setIcon(resizeIcon(iconRegister, iconRegister.getIconWidth()/8, iconRegister.getIconHeight()/8));
                topSongs = new JButton("Top 5 Songs");
                ImageIcon iconTop = new ImageIcon("img/topicon.png");
                topSongs.setIcon(resizeIcon(iconTop, iconTop.getIconWidth()/7, iconTop.getIconHeight()/7));

                serverGraph = new JButton("User Graph");
                ImageIcon iconGraph = new ImageIcon("img/graphicicon.png");
                serverGraph.setIcon(resizeIcon(iconGraph, iconGraph.getIconWidth()/35, iconGraph.getIconHeight()/35));
                gestor = new JButton("Song Manager");
                ImageIcon iconManagar = new ImageIcon("img/managericon.jpg");
                gestor.setIcon(resizeIcon(iconManagar, iconManagar.getIconWidth()/15, iconManagar.getIconHeight()/15));
                serverFrame.add(registerUser);
                serverFrame.add(topSongs);
                serverFrame.add(gestor);
                serverFrame.add(serverGraph);

                //Server's View default settings
                serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                serverFrame.setSize(300, 350);
                serverFrame.setVisible(true);
        }

    /**
     * Resizing Icon Image
     * @param icon: Icon to be resized
     * @param resizedWidth: Width resize
     * @param resizedHeight: Height resize
     * @return the resized icon
     */
        private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
                Image img = icon.getImage();
                Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
                return new ImageIcon(resizedImage);
        }


    /**
     * Register each button of the Server View with each's controller
     * @param controller: Controller
     */
    public void registerController(Controller controller){
            serverGraph.addMouseListener(controller);
            topSongs.addMouseListener(controller);
            registerUser.addMouseListener(controller);
            gestor.addMouseListener(controller);
        }

    public JButton getServerGraph() {
        return serverGraph;
    }

    public JButton getTopSongs() {
        return topSongs;
    }

    public JButton getRegisterUser() {
        return registerUser;
    }

    public JButton getGestor() {
        return gestor;
    }
}
