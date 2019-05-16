package Client.View.Piano;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectionOfKeys extends JFrame {
    private ArrayList<KeyConfigurationVisual> list;
    private JButton change;

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
