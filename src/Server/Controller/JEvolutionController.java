package Server.Controller;

import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.JEvolution;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JEvolutionController implements ActionListener {
    private JEvolution jEvolution;
    private ServiceBBDDServer service;

    public JEvolutionController(JEvolution jEvolution){
        this.jEvolution = jEvolution;
        this.service = jEvolution.getService();
    }

    public void actionPerformed(ActionEvent event){
        String whichButton = ((JButton)event.getSource()).getText();

        switch (whichButton){
            case "Week":
                //TODO: xDivions = 7
                jEvolution.setUsers(service.getLastWeekConnections());
                jEvolution.setGraphic(jEvolution.getUsers());
                jEvolution.setEvoVisible(true);
                System.out.println("Monday");
                break;
            case "Month":
                //TODO: xDivions = 12
                jEvolution.setUsers(service.getLastMonthConnections());
                jEvolution.setGraphic(jEvolution.getUsers());
                jEvolution.setEvoVisible(true);
                System.out.println("January");
                break;
            case "Year":
                jEvolution.setUsers(service.getLastYearConnections());
                jEvolution.setGraphic(jEvolution.getUsers());
                jEvolution.setEvoVisible(true);
                System.out.println("2019");
                break;
        }
    }
}
