package Server.Controller.Gestor;

import Model.Song;
import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.View;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * Class that has the mission to manage all the songs from server. Can delete, add songs
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class GestorController implements MouseListener {
    private View view;
    private ServiceBBDDServer service;
    private Point point;

    /**
     * Controller of JGestor
     * @param view: JGestor View
     * @param service: Service that allows comunication with bbdd
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
     * @param event: event due to an interaction with an element of the screen
     */
    @Override
    public void mouseReleased(MouseEvent event){
        if (event.getSource() == view.getGestorView().getAddButton()) {
            JFileChooser selectedFile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Midi files", "mid");
            selectedFile.setFileFilter(filter);

            int returnVal = selectedFile.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String direction =  "FilesBBDD/Public/System";
                File directorio = new File(direction);
                directorio.mkdirs();
                String path = selectedFile.getSelectedFile().getName();
                try {
                    Pattern pattern = MidiFileManager.loadPatternFromMidi(selectedFile.getSelectedFile());
                    BufferedWriter writer = new BufferedWriter
                            (new FileWriter(directorio + "/" + path.substring(0, path.lastIndexOf('.'))));
                    String songFile = pattern.toString();
                    writer.write(songFile);
                    writer.close();

                    service.insertSongFromSystem(path.substring(0, path.lastIndexOf('.')), 12, " ",
                            0, "FilesBBDD/Public/System/" + path.substring(0, path.lastIndexOf('.')),
                            service.getInstanceOfSystem());

                } catch (IOException e) {
                    view.getGestorView().cantSaveMsg();
                } catch (InvalidMidiDataException e) {
                    view.getGestorView().cantSaveMsg();
                } catch (Exception e) {
                    view.getGestorView().cantSaveMsg();
                }
            }

            refreshView();

        } else if (event.getSource() == view.getGestorView().getBackButton()) {
            view.getGestorView().setVisible(false);

        } else if (event.getSource() == view.getGestorView().getRefreshButton()) {
            refreshView();

        } else {
            for (int i = 0; i < view.getGestorView().getSongsList().size(); i++) {
                if (event.getSource() == view.getGestorView().getSongsList().get(i).getDeleteButton()){
                    try {
                        //Deleted of the BBDD
                        service.deleteSong(view.getGestorView().getSongsList().get(i).getTitleSong().getText(),
                                view.getGestorView().getSongsList().get(i).getAuthor().getText());

                        //Deleted of server
                        String direction =  "FilesBBDD/"
                                + view.getGestorView().getSongsList().get(i).getPrivacity().getText() + "/"
                                + view.getGestorView().getSongsList().get(i).getAuthor().getText() + "/"
                                + view.getGestorView().getSongsList().get(i).getTitleSong().getText();

                        File file = new File(direction);
                        boolean deleted = file.delete();

                        if (deleted) {
                            view.getGestorView().deletedMsg();
                        }

                    } catch (BBDDException e1) {
                        view.getGestorView().cantDeleteMsg();
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

    /**
     * Refresh the view with the songs of the server and bbdd
     */
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
