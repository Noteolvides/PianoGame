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


        evoPanel.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel graphTitle = new JLabel("Active Users Countage");
        constraints.gridx = 1;
        constraints.gridy = 0;
        evoPanel.add(graphTitle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        evoPanel.add(week, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        evoPanel.add(month, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        evoPanel.add(year, constraints);



        //evoPanel.add(timeSelect);
        evoPanel.setSize(700, 500);
        //evoPanel.add(graphicPanel);
        evoPanel.setVisible(true);
        evoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showGraphic(){
        ArrayList<Integer> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 10;
        int maxScore = 100;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((Integer) random.nextInt() * maxScore);
        }


        Graph
    }


    public static void main(String[] args) throws IOException {
        JEvolution evo = new JEvolution();
        evo.JEvolution();
    }

    /*void registerController(Controller controller){

    }*/
}