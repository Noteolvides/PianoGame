package Server.Controller;

import Model.Song;
import Server.Controller.Gestor.GestorController;
import Server.View.JEvolution;
import Server.View.JTop;
import Server.View.ServerViews;
import Server.View.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * The Biggest controller fot the server
 * It controls the views from the server
 * and it maps with each function
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
public class Controller implements MouseListener {
    private JEvolutionController jEvolutionController;
    private JTopController jTopController;
    private RegisterController registerController;
    private GestorController gestorController;

    private JTop jTop;
    private JEvolution jEvolution;
    private View view;

    private ServerViews serverViews;

    /**
     * Generates the controller for all the views of the Server Side
     * @param jEvolutionController: Controller
     * @param jTopController: Controller
     * @param registerController: Controller
     * @param gestorController: Controller
     * @param jTop: View
     * @param jEvolution: View
     * @param view: View
     * @param serverViews: View
     */
    public Controller(JEvolutionController jEvolutionController, JTopController jTopController, RegisterController registerController,
                        GestorController gestorController,
                        JTop jTop, JEvolution jEvolution, View view, ServerViews serverViews){
        this.jEvolutionController = jEvolutionController;
        this.jTopController = jTopController;
        this.registerController = registerController;
        this.gestorController = gestorController;

        this.jTop = jTop;
        this.jEvolution = jEvolution;
        this.view = view;
        this.serverViews = serverViews;
    }

    /**
     * Adding each controller to its view from the Server
     * @param jEvolution: Veiw
     * @param jTop: View
     * @param view: View
     */
    public void actionManager(JEvolution jEvolution, JTop jTop, View view){
        jEvolution.registerController(jEvolutionController);
        jTop.registerController(jTopController);
        view.getRegisterView().registerController(registerController);
        view.getGestorView().registerController(gestorController);
    }

    /**
     * Action, shows and pops the panel to be displayed
     * @param event: Which button is clicked
     */
    @Override
    public void mouseClicked(MouseEvent event){
        JButton whichButton = (JButton) event.getSource();

        if(serverViews.getRegisterUser().equals(whichButton)){
            view.getRegisterView().setVisible();
        }
        if(serverViews.getTopSongs().equals(whichButton)){
            /*for(int i = 0; i < 5; i++){
                jTop.refreshSongs(0);
            }*/
            jTop.getFrameTop().dispose();
            jTop.JTop();
            jTop.setSongs((ArrayList<Song>)jTopController.getService().getTop5Songs());
            /*jTopController.refresh(jTop.getSongs());
            jTopController.addAllTheSongs(jTop.getSongsList());*/
            //jTopController.refresh(jTop.getSongs());
            jTop.getFrameTop().revalidate();
            jTop.setVisible();
        }
        if(serverViews.getServerGraph().equals(whichButton)){
            jEvolution.setVisible();
        }
        if(serverViews.getGestor().equals(whichButton)){
            view.getGestorView().setVisible();
        }

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void mousePressed(MouseEvent event){

    }


}
