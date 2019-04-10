package Server.Controller;

import Server.View.JEvolution;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JEvolutionController implements ActionListener {
    private JEvolution jEvolution;

    public JEvolutionController(JEvolution jEvolution){
        this.jEvolution = jEvolution;
    }

    public void actionPerformed(ActionEvent event){
        String whichButton = ((JButton)event.getSource()).getText();

        switch (whichButton){
            case "Week":
                System.out.println("Monday");
                break;
            case "Month":
                System.out.println("January");
                break;
            case "Year":
                System.out.println("2019");
                break;
        }
    }
}
