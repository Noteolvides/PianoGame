package Server.Controller.Gestor;

import Model.Song;
import Server.Controller.BBDD.Resources.FieldsNoValidException;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.View;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GestorController implements MouseListener {
    private View view;
    private ServiceBBDDServer service;

    /**
     * Controller of JGestor
     * @param view
     */
    public GestorController(View view, ServiceBBDDServer service) {
        this.view = view;
        this.service = service;
        view.initGestorView(service.getListSongs());
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e){

    }

    /**
     * WHen the mouse is released we watch at what button clicked the user
     * @param e: event due to an interaction with an element of the screen
     */
    @Override
    public void mouseReleased(MouseEvent e){
        if (e.getSource() == view.getGestorView().getAddButton()) {
            new java.awt.FileDialog((java.awt.Frame) null).setVisible(true);

        } else if (e.getSource() == view.getGestorView().getBackButton()) {
            view.getGestorView().setVisible(false);
            view.getGestorView().dispose();

        } else if (e.getSource() == view.getGestorView().getRefreshButton()) {
            view.getGestorView().setVisible(false);
            view.getGestorView().dispose();
            //Todo ficar un revalidate en comptes d'eliminar tot
            view.initGestorView(service.getListSongs());
            view.getGestorView().registerController(this);

        } else {
            for (int i = 0; i < view.getGestorView().getSongsList().size(); i++) {
                if (e.getSource() == view.getGestorView().getSongsList().get(i).getDeleteButton()){
                    System.out.println("Delete" + i);
                    //TODO: canviar Pepe per l'autor de la canco
                    service.deleteSong(view.getGestorView().getSongsList().get(i).getTitleSong().getText(),
                            "pepe");

                }
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }

}
