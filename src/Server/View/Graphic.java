package Server.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graphic extends JPanel {
    private static final long serialVersionUID = 1L;
    private Integer constant;
    private Integer constant2;
    private Integer constant3;
    private int labelPadding = 20;
    /** Change the line color to the best you want*/
    private Color lineColor = new Color(255, 28, 0);
    private Color pointColor = new Color(134, 255, 216);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static int pointWidth = 11;
    private List<Integer> users;
    private int padding = 30;                                                                                           //Quantitat de farcida esquerra i dreta

    public Graphic(List<Integer> users) {
        this.users = users;
    }

    /**
     * Painting the user graph to the panel in JEvolution
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(users.size() == 7) {
            constant = 5;
            constant2 = 1;
        }else{
            if(users.size() == 12){
                constant = 3;
                constant2 = 1;
            }else{
                constant = 1;
                constant2 = 1;
            }
        }
        double xScale = ((double) getWidth() - (padding * constant2)- labelPadding) / (users.size() - 1) - constant;
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxUsers() - getMinUsers());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            int x1 = (int) (i * xScale +  constant2 * padding + labelPadding);
            int y1 = (int) ((getMaxUsers() - users.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        g2.setColor(Color.WHITE);


        //Secciona l'eix Y i introdueix valors
        for (int i = 0; i < getMaxUsers() + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            if(getMaxUsers() != 0){
                constant3 = getMaxUsers();
            }else{
                constant3 = 1;
            }
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / (constant3) + padding + labelPadding);
            int y1 = y0;
            if (users.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = i + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        //Secciona l'eix X i introdueix valors
        for (int i = 0; i < users.size() + 1; i++) {
            if (users.size() > 0) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (users.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;

                g2.setColor(gridColor);
                g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                g2.setColor(Color.BLACK);
                String xLabel = i + 1 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(xLabel);
                g2.drawString(xLabel, x0 - labelWidth, y0 + metrics.getHeight() + 6);


                g2.drawLine(x0, y0, x1, y1);
            }
        }



        //Dibuixa els dos eixos de la gràfica
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);                                     //Eix Y
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);      //Eix X

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);                                                                                                                                      /* Color de la unió dels punts */
        g2.setStroke(GRAPH_STROKE);

        //Matches all the points together, painting lines from point to point (segement by segment)
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1;
            if(getMaxUsers() != 0){
                y1 = graphPoints.get(i).y;
            }else{
                y1 = 670;
            }
            int x2 = graphPoints.get(i + 1).x;
            int y2;
            if(getMaxUsers() != 0){
                y2 = graphPoints.get(i + 1).y;
            }else{
                y2 = 670;
            }
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);

        //Plotting of points in the graphic
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y;
            if(getMaxUsers() != 0){
                y = graphPoints.get(i).y - pointWidth / 2;
            }else{
                y = 665;
            }
            int circleW = pointWidth;
            int circleH = pointWidth;
            g2.fillOval(x, y, circleW, circleH);
        }
    }

    /**
     *Gets the lowest peak of users to determine the lowest number
     */
    private Integer getMinUsers() {
        Integer minUsers = Integer.MAX_VALUE;
        for (Integer Users : users) {
            minUsers = Math.min(minUsers, Users);
        }
        return minUsers;
    }

    /**
     *Gets the peak number of users to determine the peak number
     */
    private Integer getMaxUsers() {
        Integer maxUsers = Integer.MIN_VALUE;
        for (Integer Users : users) {
            maxUsers = Math.max(maxUsers, Users);
        }
        return maxUsers;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }
}
