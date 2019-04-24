package Server.View;

import Server.Controller.JEvolutionController;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class JEvolution {
    private ArrayList<Double> users;
    private JButton year;
    private JButton month;
    private JButton week;

    public void JEvolution () throws IOException {
        JFrame evoPanel = new JFrame("Active Users Countage");

        ImageIcon icon = new ImageIcon("img/graphicicon.png");
        evoPanel.setIconImage(icon.getImage());

        //Frame title
        GridBagConstraints constraints = new GridBagConstraints();

        //Time Selection
        week = new JButton("Week");
        month = new JButton("Month");
        year = new JButton("Year");
        evoPanel.setSize(1400, 835);


        evoPanel.setLayout(new GridBagLayout());

        //Títol al Top
        JLabel graphTitle = new JLabel("Active Users Countage");
        graphTitle.setFont(graphTitle.getFont().deriveFont(22.0f));
        constraints.gridx = 1;
        constraints.gridy = 0;
        evoPanel.add(graphTitle, constraints);


        //Grafica al mig
        constraints.gridx = 1;
        constraints.gridy = 1;
        evoPanel.add(showGraphic(), constraints);

        //Botons al Peu
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(week);
        buttonsPanel.add(month);
        buttonsPanel.add(year);
        constraints.gridx = 1;
        constraints.gridy = 2;
        evoPanel.add(buttonsPanel, constraints);

        evoPanel.setVisible(true);
        evoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel showGraphic() {                                      //Quan cridem aquesta funció li passarem una llista de quantitat d'usuaris
        users = new ArrayList<Double>();
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

        JEvolutionController controller = new JEvolutionController(evo);
        evo.registerController(controller);
    }

    public void registerController(JEvolutionController controller){
        year.addActionListener(controller);
        week.addActionListener(controller);
        month.addActionListener(controller);
    }
}