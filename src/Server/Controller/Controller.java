package Server.Controller;

import Server.View.JEvolution;
import Server.View.JTop;
import Server.View.ServerViews;
import Server.View.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {
    private JEvolutionController jEvolutionController;
    private JTopController jTopController;
    private RegisterController registerController;

    private JTop jTop;
    private JEvolution jEvolution;
    private View view;

    private ServerViews serverViews;

    public Controller(JEvolutionController jEvolutionController, JTopController jTopController, RegisterController registerController,
                        JTop jTop, JEvolution jEvolution, View view, ServerViews serverViews){
        this.jEvolutionController = jEvolutionController;
        this.jTopController = jTopController;
        this.registerController = registerController;

        this.jTop = jTop;
        this.jEvolution = jEvolution;
        this.view = view;
        this.serverViews = serverViews;
    }

    public void actionManager(JEvolution jEvolution, JTop jTop, View view){
        jEvolution.registerController(jEvolutionController);
        jTop.registerController(jTopController);
        view.getRegisterView().registerController(registerController);
    }

    @Override
    public void mouseClicked(MouseEvent event){
        JButton whichButton = (JButton) event.getSource();

        if(serverViews.getRegisterUser().equals(whichButton)){
            view.getRegisterView().setVisible();
        }
        if(serverViews.getTopSongs().equals(whichButton)){
            jTop.setVisible();
        }
        if(serverViews.getServerGraph().equals(whichButton)){
            jEvolution.setVisible();
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
