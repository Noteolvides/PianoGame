package Server.Controller.Gestor;

import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GestorController implements MouseListener {
    private View view;
    private ServiceBBDDServer service;
    private Point point;

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

    //TODO: Fer que es guardi una canço en el servidor i en la BBDD. -> Ficar finestra de Jiahui aquí tb
    //TODO: Fer que quan s'elimini una canço en la bbdd, tb s'elimini l'arxiu del servidor
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

        } else if (e.getSource() == view.getGestorView().getRefreshButton()) {
            refreshView();

        } else {
            for (int i = 0; i < view.getGestorView().getSongsList().size(); i++) {
                if (e.getSource() == view.getGestorView().getSongsList().get(i).getDeleteButton()){
                    try {
                        service.deleteSong(view.getGestorView().getSongsList().get(i).getTitleSong().getText(),
                                view.getGestorView().getSongsList().get(i).getAuthor().getText());
                    } catch (BBDDException e1) {
                        System.out.println("System song cannot be deleted");
                    }
                    view.getGestorView().getSongsList().remove(view.getGestorView().getSongsList().get(i));
                    refreshView();

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

    private void refreshView() {
        view.getGestorView().setVisible(false);
        point = view.getGestorView().getLocation();
        view.getGestorView().dispose();
        view.initGestorView(service.getListSongs());
        view.getGestorView().registerController(this);
        view.getGestorView().setLocation(point);
        view.getGestorView().setVisible(true);
    }

}
