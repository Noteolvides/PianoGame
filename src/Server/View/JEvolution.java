package Server.View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JEvolution {

    void JEvolution() throws IOException {
        JFrame evoPanel = new JFrame("Active Users Countage");

        ImageIcon icon = new ImageIcon("C:/Users/Jiahu/Downloads/jewels.png");
        evoPanel.setIconImage(icon.getImage());


        //Frame title
        GridBagConstraints constraints = new GridBagConstraints();

        //Time Selection
        //JPanel timeSelect = new JPanel();
        JButton week = new JButton("Week");
        JButton month = new JButton("Month");
        JButton year = new JButton("Year");

        /*timeSelect.add(week);
        timeSelect.add(month);
        timeSelect.add(year);*/
        evoPanel.setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel graphTitle = new JLabel("Active Users Countage");
        constraints.gridx = 0;
        constraints.gridy = 0;
        evoPanel.add(graphTitle, constraints);

        //constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 2;
        evoPanel.add(week, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        evoPanel.add(month, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        evoPanel.add(year, constraints);

        /*JPanel graphicPanel = new JPanel();
        graphicPanel.setSize(600, 400);
        JLabel graphic = new JLabel(new ImageIcon(image.getScaledInstance(graphicPanel.getWidth(), graphicPanel.getHeight(), image.SCALE_FAST)));
        graphicPanel.add(graphic);*/


        //evoPanel.add(timeSelect);
        evoPanel.setSize(700, 500);
        //evoPanel.add(graphicPanel);
        evoPanel.setVisible(true);
        evoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        JEvolution evo = new JEvolution();
        evo.JEvolution();
    }

    /*void registerController(Controller controller){

    }*/
}