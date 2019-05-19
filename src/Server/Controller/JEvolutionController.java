package Server.Controller;

import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.JEvolution;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the JEvolution Panel
 * It maps each button [Week | Month | Year] with its graphic
 * display
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class JEvolutionController implements ActionListener {
    private JEvolution jEvolution;
    private ServiceBBDDServer service;

    /**
     * Controller for the JEvolution
     * Maps each button to its graphic to be shown
     * @param jEvolution: JEvolution View
     */
    public JEvolutionController(JEvolution jEvolution){
        this.jEvolution = jEvolution;
        this.service = jEvolution.getService();
    }

    /**
     * Determines what graphic tò show
     * @param event: Which button is clicked
     */
    public void actionPerformed(ActionEvent event){
        String whichButton = ((JButton)event.getSource()).getText();

        switch (whichButton){
            case "Week":
                jEvolution.setUsers(service.getLastWeekConnections());
                jEvolution.setGraphic(jEvolution.getUsers());
                jEvolution.setEvoVisible(true);
                System.out.println("Monday");
                break;
            case "Month":
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
