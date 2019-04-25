package Server;

import Server.Controller.Controller;
import Server.Controller.JEvolutionController;
import Server.Controller.JTopController;
import Server.Controller.RegisterController;
import Server.View.JEvolution;
import Server.View.JTop;
import Server.View.View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame serverFrame = new JFrame("Server Functionalities");
        serverFrame.setLayout(new GridLayout(3, 1));

        JButton serverGraph, topSongs, registerUser;
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

        /*View view = new View();
        view.initRegisterView();
        RegisterController controller = new RegisterController(view);
        //view.getRegisterView().registerController(controller);

        JEvolution evolution = new JEvolution();
        evolution.JEvolution();
        JEvolutionController evoController = new JEvolutionController(evolution);
        JTop top = new JTop();
        top.JTop();
        JTopController topController = new JTopController(top);

        Controller control = new Controller(evoController, topController, controller);
        control.actionManager(evolution, top, view);*/
    }
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
