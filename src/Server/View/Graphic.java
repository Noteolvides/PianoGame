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
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);                                                      //Graph Background Colour

        int xScale = (getWidth() - (3) / (users.size() - 1));
        int yScale = (getHeight() - 2) / (getMaxUsers() - getMinUsers());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            int x1 = (i * xScale);
            int y1 = ((getMaxUsers() - users.get(i)) * yScale);
            graphPoints.add(new Point(x1, y1));
        }

        //Secciona l'eix Y i introdueix valors
        for (int i = 0; i < numberYDivisions + 1; i++) {
            //int x0 = padding + labelPadding;
            int x1 = pointWidth;
            int y0 = (getHeight() - (i * getHeight()) / numberYDivisions);
            int y1 = y0;
            if (users.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(1 + pointWidth, y0, getWidth(), y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinUsers() + (getMaxUsers() - getMinUsers()) *
                        ((i * 8.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, labelWidth - 6, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(0, y0, x1, y1);
        }

        //Secciona l'eix X i introdueix valors
        for (int i = 0; i < users.size(); i++) {
            if (users.size() > 1) {
                int x0 = i * getWidth()/ (users.size() - 1);
                int x1 = x0;
                int y0 = getHeight();
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((users.size() / 8.0)) + 3)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - 1 - pointWidth, x1, 0);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

    }




    /**Obtenir mínim usauris**/
    public int getMinUsers() {
        Integer minUser = Integer.MAX_VALUE;
        for (Integer user : users) {
            minUser = Math.min(minUser, user);
        }
        return minUser;
    }

    /**Obtenir pic usuaris**/
    public int getMaxUsers() {
        Integer maxUser = Integer.MIN_VALUE;
        for (Integer user : users) {
            maxUser = Math.min(maxUser, user);
        }
        return maxUser;
    }
}
