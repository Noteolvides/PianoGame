package Server.View;

import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.Controller.JEvolutionController;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.List;

/**
 * JEvolution View refers to the graphic of the graphical
 * representation of the users connection to the game
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
public class JEvolution {
    //JEvolution attributes, buttons and sockets for data retrieval
    private ServiceBBDDServer service;
    private JFrame evoPanel = new JFrame("Active Users Countage");
    private List<Integer> users;
    private JButton year;
    private JButton month;
    private JButton week;
    private Graphic graphic;

    /**
     * Receives the service to retrieve information
     * @param service: Service
     */
    public JEvolution(ServiceBBDDServer service){
        this.service = service;
    }

    /**
     * JEvoltion View
     * @throws IOException Throws exception
     */
    public void JEvolution () throws IOException {

        //Adding the icon to the Frame border
        ImageIcon icon = new ImageIcon("img/graphicicon.png");
        evoPanel.setIconImage(icon.getImage());

        //Frame title
        GridBagConstraints constraints = new GridBagConstraints();

        //Time Selection
        week = new JButton("Week");
        month = new JButton("Month");
        year = new JButton("Year");
        evoPanel.setSize(1400, 835);


        evoPanel.setLayout(new GridBagLayout());

        //Títol al Top
        JLabel graphTitle = new JLabel("Active Users Countage");
        graphTitle.setFont(graphTitle.getFont().deriveFont(22.0f));
        constraints.gridx = 1;
        constraints.gridy = 0;
        evoPanel.add(graphTitle, constraints);


        //Grafica al mig
        constraints.gridx = 1;
        constraints.gridy = 1;
        evoPanel.add(showGraphic(), constraints);

        //Botons al Peu
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(week);
        buttonsPanel.add(month);
        buttonsPanel.add(year);
        constraints.gridx = 1;
        constraints.gridy = 2;
        evoPanel.add(buttonsPanel, constraints);
    }


    /**
     * Creates the graph to be mapped in the JEvolution frame according to the parameters (Users connections)
     * @return Return the jPanel with the graphics
     */
    public JPanel showGraphic() {
        users = service.getLastWeekConnections();

        graphic = new Graphic(users);
        graphic.setPreferredSize(new Dimension(1280, 720));
        JPanel graphics = new JPanel();
        graphics.add(graphic);
        return graphics;
    }

    /**
     * Registering controller
     * @param controller: Controller
     */
    public void registerController(JEvolutionController controller){
        year.addActionListener(controller);
        week.addActionListener(controller);
        month.addActionListener(controller);
    }

    public void setVisible(){
        evoPanel.setVisible(true);
    }

    public void setInvisible(){
        evoPanel.setVisible(false);
    }


    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }

    public ServiceBBDDServer getService() {
        return service;
    }

    public void setService(ServiceBBDDServer service) {
        this.service = service;
    }

    public void setGraphic(List<Integer> users) {
        graphic.setUsers(users);
    }

    public JFrame getEvoPanel() {
        return evoPanel;
    }


    //Sets the panel visible
    public void setEvoVisible(Boolean invisible) {
        evoPanel.setVisible(invisible);
        evoPanel.repaint();
    }
}