package Server.View;

import Server.Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ServerViews {
        private JButton serverGraph, topSongs, registerUser;

        public ServerViews(){
                JFrame serverFrame = new JFrame("Server Functionalities");
                serverFrame.setLayout(new GridLayout(3, 1));

                registerUser = new JButton("Register User");
                ImageIcon iconRegister = new ImageIcon("img/registerIcon.png");
                registerUser.setIcon(resizeIcon(iconRegister, iconRegister.getIconWidth()/8, iconRegister.getIconHeight()/8));
                topSongs = new JButton("Top 5 Songs");
                ImageIcon iconTop = new ImageIcon("img/topicon.png");
                topSongs.setIcon(resizeIcon(iconTop, iconTop.getIconWidth()/7, iconTop.getIconHeight()/7));
                serverGraph = new JButton("User Graph");
                ImageIcon iconGraph = new ImageIcon("img/graphicicon.png");
                serverGraph.setIcon(resizeIcon(iconGraph, iconGraph.getIconWidth()/35, iconGraph.getIconHeight()/35));

                serverFrame.add(registerUser);
                serverFrame.add(topSongs);
                serverFrame.add(serverGraph);

                serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                serverFrame.setSize(300, 350);
                serverFrame.setVisible(true);
        }
        private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
                Image img = icon.getImage();
                Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
                return new ImageIcon(resizedImage);
        }

        public void registerController(Controller controller){
            serverGraph.addMouseListener(controller);
            topSongs.addMouseListener(controller);
            registerUser.addMouseListener(controller);
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
}
