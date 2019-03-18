package Server.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class JTop {
    public JList songTop;

    void JTop(){
        ArrayList<String> topList = new ArrayList<>();
        topList.add("Watanabe");
        topList.add("Mayu");
        topList.add("is");
        topList.add("love");
        topList.add("!");


        Border border = BorderFactory.createLineBorder(Color.GREEN);
        this.songTop = new JList(topList.toArray());
        songTop.setFont(songTop.getFont().deriveFont(22.0f));

        JPanel panelTop = new JPanel();
        panelTop.add(songTop);

        panelTop.setBorder(border);
        JFrame frameTop = new JFrame("TOP 5 - Popular Songs"); //Prova

        frameTop.setSize(325, 500);
        frameTop.setVisible(true);
        frameTop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTop.add(panelTop);
    }

    public static void main(String[] args) {
        JTop h = new JTop();
        h.JTop();
    }

    /*void registerController(Controller controller){

    }*/
}
