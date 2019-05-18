package Server.Controller.Gestor;

import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Scanner;


public class GestorController implements MouseListener {
    private View view;
    private ServiceBBDDServer service;
    private Point point;
    private JFileChooser jFileChooser;
    private StringBuilder path;

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
     * @param event: event due to an interaction with an element of the screen
     */
    @Override
    public void mouseReleased(MouseEvent event){
        if (event.getSource() == view.getGestorView().getAddButton()) {

            JFileChooser selectedFile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Txt files", "txt");
            selectedFile.setFileFilter(filter);

            int returnVal = selectedFile.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String direction =  "FilesBBDD/Public/ServerPC";
                File directorio = new File(direction);
                directorio.mkdir();
                try {
                    BufferedWriter writer = new BufferedWriter
                            (new FileWriter(directorio + "/" + selectedFile.getSelectedFile().getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        } else if (event.getSource() == view.getGestorView().getBackButton()) {
            view.getGestorView().setVisible(false);

        } else if (event.getSource() == view.getGestorView().getRefreshButton()) {
            refreshView();

        } else {
            for (int i = 0; i < view.getGestorView().getSongsList().size(); i++) {
                if (event.getSource() == view.getGestorView().getSongsList().get(i).getDeleteButton()){
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
