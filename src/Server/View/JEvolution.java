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

        //Generation Active User per Hour
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("JPiano users", new double[][] {{ 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017 }, { 25, 29.1, 32.1, 32.9, 31.9, 25.5, 20.1, 18.4, 15.3, 11.4, 9.5 }});
        dataset.addSeries("Likes", new double[][] {{ 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017 }, { 67.7, 63.1, 60.2, 50.6, 41.1, 31.8, 27.6, 20.4, 17.3, 12.3, 8.1 }});
        dataset.addSeries("Dislikes", new double[][] {{ 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017 }, { 0.2, 6.4, 14.6, 25.3, 30.1, 34.3, 43.2, 47.3, 58.4 }});


        renderer.setSeriesStroke(0, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Active Users Countage", "Hour", "Active Users", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, 100);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'%'"));
        chart.getXYPlot().setRenderer(renderer);

        BufferedImage image = chart.createBufferedImage(600, 400);
        ImageIO.write(image, "png", new File("xy-chart.png"));

        /*JPanel graphicPanel = new JPanel();
        graphicPanel.setSize(600, 400);
        JLabel graphic = new JLabel(new ImageIcon(image.getScaledInstance(graphicPanel.getWidth(), graphicPanel.getHeight(), image.SCALE_FAST)));
        graphicPanel.add(graphic);*/


        evoPanel.add(timeSelect);
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