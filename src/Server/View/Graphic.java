package Server.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graphic extends JPanel {

    /**Settings gràfica**/
    private Color lineColor = new Color(255,255,254);                       //Linia que uneix els punts
    private Color pointColor = new Color(134, 255, 216);                    //Color punts
    private Color gridColor = new Color(200, 200, 200, 200);             //Color grid
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static int pointWidth = 10;
    private int numberYDivisions = 20;
    private ArrayList<Integer> users;
    //private int padding = 20;

    /**Constructor per crear els punts dels users**/
    public Graphic(ArrayList<Integer> users){
        this.users = users;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);


    }
}
