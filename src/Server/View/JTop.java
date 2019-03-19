package Server.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class JTop {
    public JList songTop;

    void JTop(){
        ArrayList<String> topList = new ArrayList<>();
        topList.add("Watanabe Mayuuuuuu");
        topList.add("Mayu");
        topList.add("is");
        topList.add("love");
        topList.add("!");


        Border border = BorderFactory.createLineBorder(Color.RED);
        this.songTop = new JList(topList.toArray());
        songTop.setFont(songTop.getFont().deriveFont(22.0f));
        songTop.setBorder(border);
        songTop.setFixedCellWidth(300);

        JPanel panelTop = new JPanel();
        panelTop.add(songTop);

        //Compound border for Visual Effects
        Border compound, raisedbevel, loweredbevel, redLine;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        redLine = BorderFactory.createLineBorder(Color.YELLOW);

        //Adds up both raised and lowered
        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);

        //Adds the outer red line
        compound = BorderFactory.createCompoundBorder(redLine, compound);

        compound = BorderFactory.createTitledBorder(compound, "\uD83D\uDC51 TOP 5 - Popular Songs \uD83D\uDC51", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        panelTop.setBorder(compound);

        ((TitledBorder) compound).setTitleFont(((TitledBorder) compound).getTitleFont().deriveFont(25.00f));
        JFrame frameTop = new JFrame("TOP 5 - Popular Songs"); //Prova

        frameTop.setSize(390, 275);
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
