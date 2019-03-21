package Server.View;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class JEvolution {

    void JEvolution() throws IOException {
        JFrame evoPanel = new JFrame("Active Users Countage");

        ImageIcon icon = new ImageIcon("C:/Users/Jiahu/Downloads/jewels.png");
        evoPanel.setIconImage(icon.getImage());

        //Frame title
        GridBagConstraints constraints = new GridBagConstraints();

        //Time Selection
        JButton week = new JButton("Week");
        JButton month = new JButton("Month");
        JButton year = new JButton("Year");
        evoPanel.setSize(1920, 1080);


        evoPanel.setLayout(new GridBagLayout());
        /*JPanel aux = showGraphic();
        aux.setSize(400, 300);
        evoPanel.add(aux);*/

        JLabel graphTitle = new JLabel("Active Users Countage");
        /*evoPanel.add(graphTitle);
        JPanel aux = showGraphic();
        aux.setPreferredSize(new Dimension(400, 300));
        evoPanel.add(aux);
        evoPanel.add(new JButton("Hello"));*/
        constraints.gridx = 1;
        constraints.gridy = 0;
        evoPanel.add(graphTitle, constraints);


        constraints.gridx = 1;
        constraints.gridy = 1;

        evoPanel.add(showGraphic(), constraints);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(week);
        buttonsPanel.add(month);
        buttonsPanel.add(year);
        constraints.gridx = 1;
        constraints.gridy = 2;
        evoPanel.add(buttonsPanel, constraints);

        //evoPanel.add(timeSelect);
        //evoPanel.add(graphicPanel);
        evoPanel.setVisible(true);
        evoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel showGraphic() {                                      //Quan cridem aquesta funció li passarem una llista de quantitat d'usuaris
        ArrayList<Double> users = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 10;
        int maxScore = 100;
        for (int i = 0; i < maxDataPoints; i++) {
            users.add(random.nextDouble() * maxScore);
        }


        Graphic graphic = new Graphic(users);
        graphic.setPreferredSize(new Dimension(1280, 720));
        JPanel graphics = new JPanel();
        graphics.add(graphic);
        return graphics;
    }


    public static void main(String[] args) throws IOException {
        JEvolution evo = new JEvolution();
        evo.JEvolution();

    }
    /*void registerController(Controller controller){

    }*/
}