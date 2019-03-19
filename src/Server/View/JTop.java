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

        JPanel panelTop = new JPanel();
        panelTop.add(songTop);

        Border compound, raisedbevel, loweredbevel;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();

        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
        compound = BorderFactory.createTitledBorder(compound, "\uD83D\uDC51 TOP 5 - Popular Songs \uD83D\uDC51", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        ((TitledBorder) compound).setTitleFont(((TitledBorder) compound).getTitleFont().deriveFont(25.00f));
        panelTop.setBorder(compound);
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
