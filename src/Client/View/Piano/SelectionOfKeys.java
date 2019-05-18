package Client.View.Piano;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Frame that contains all the posible keys that can be mapped in the piano
 *
 * @author  Gerard
 * @author Gustavo
 * @author Neil
 * @author Jiahui
 * @author Josep
 * @version 1.0
 * @since 2019-05-16
 */
public class SelectionOfKeys extends JFrame {
    private ArrayList<KeyConfigurationVisual> list;
    private JButton change;


    /**
     * Initialization
     */
    public SelectionOfKeys() {
        setTitle("Change Keys");
        list = new ArrayList<>();
        setLayout(new FlowLayout());
        change = new JButton("Change");
        setVisible(false);
    }

    public ArrayList<KeyConfigurationVisual> getList() {
        return list;
    }

    public void setList(ArrayList<KeyConfigurationVisual> list) {
        this.list = list;
    }

    /**
     * Actualization of keyConfiguration
     */
    public void acctKeyConfiguration(){
        for (KeyConfigurationVisual i: list) {
            add(i);
        }
        add(change);
        setSize(600,200);
        setLocationRelativeTo(null);
        revalidate();
    }

    public JButton getChange() {
        return change;
    }

    public void setChange(JButton change) {
        this.change = change;
    }
}
