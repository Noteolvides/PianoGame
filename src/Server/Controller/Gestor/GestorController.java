package Server.Controller.Gestor;

import Client.View.Images.SongPrueba;
import Server.View.ViewServer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class GestorController implements MouseListener {
    private ViewServer view;

    //Temporal main
    public static void main(String[] args) {
        ViewServer view = new ViewServer();
        GestorController controller = new GestorController(view);
        view.getGestorView().registerController(controller);
    }

    /**
     * Controller of JGestor
     * @param view
     */
    public GestorController(ViewServer view) {
        this.view = view;
        view.initGestor(simulationOfController());
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
            //Todo, esto no me gusta
            new java.awt.FileDialog((java.awt.Frame) null).setVisible(true);


        } else if (e.getSource() == view.getGestorView().getBackButton()) {
            view.getGestorView().setVisible(false);
            view.getGestorView().dispose();

        } else if (e.getSource() == view.getGestorView().getRefreshButton()) {
            view.getGestorView().setVisible(false);
            view.getGestorView().dispose();
            view.initGestor(simulationOfController2());
            view.getGestorView().registerController(this);

        }/* else if () {

        } else if (){

        }*/
    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }

    public static ArrayList<SongPrueba> simulationOfController() {
        //This array simulates the return of the controller's call
        ArrayList <SongPrueba> array = new ArrayList<SongPrueba>();
        SongPrueba s = new SongPrueba(1,"Himno Urss","Stallin", "public");
        SongPrueba s1 = new SongPrueba(2,"Melgui:The Song","MelguiUser", "public");
        SongPrueba s2 = new SongPrueba(3,"Yahoo:The Song","Jiahui", "private");
        SongPrueba s3 = new SongPrueba(4,"La Lechuga ta' pocha","Anonymous", "private");
        SongPrueba s4 = new SongPrueba(5,"Los coches chocones","NeilUser", "private");
        SongPrueba s5 = new SongPrueba(6,"Kermitt BSO","Gutavo", "private");
        SongPrueba s6 = new SongPrueba(7,"Las Zanahorias estan rebien","Pepe", "private");
        SongPrueba s7 = new SongPrueba(8,"Javadabadooh","El papu picapiedra", "private");
        SongPrueba s8 = new SongPrueba(9,"Intel y gentes: Musica tite'","Dani y los otros", "private");
        SongPrueba s9 = new SongPrueba(10,"Hola bon dia","El SinMerk3000", "private");
        SongPrueba s10 = new SongPrueba(11,"Bella Ciao Remix","Martin Garrix", "private");
        SongPrueba s11 = new SongPrueba(12,"Urss Anthem Techno Remix","DJ Marx", "private");
        SongPrueba s12 = new SongPrueba(13,"El baile del TCP/IP","TimoTronic", "private");
        SongPrueba s13 = new SongPrueba(14,"TimoTronic","Yahoo", "private");
        SongPrueba s14 = new SongPrueba(15,"Illo eso ka' sido","Pingu", "private");

        array.add(s);
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);
        array.add(s5);
        array.add(s6);
        array.add(s7);
        array.add(s8);
        array.add(s9);
        array.add(s10);
        array.add(s11);
        array.add(s12);
        array.add(s13);
        array.add(s14);
        return array;
    }

    public static ArrayList<SongPrueba> simulationOfController2() {
        //This array simulates the return of the controller's call
        ArrayList <SongPrueba> array = new ArrayList<SongPrueba>();
        SongPrueba s = new SongPrueba(1,"Himno Urss","Stallin", "public");
        SongPrueba s1 = new SongPrueba(2,"Melgui:The Song","MelguiUser", "public");
        SongPrueba s2 = new SongPrueba(3,"Yahoo:The Song","Jiahui", "private");
        SongPrueba s3 = new SongPrueba(4,"La Lechuga ta' pocha","Anonymous", "private");
        SongPrueba s4 = new SongPrueba(5,"Los coches chocones","NeilUser", "private");
        SongPrueba s5 = new SongPrueba(6,"Kermitt BSO","Gutavo", "private");
        SongPrueba s6 = new SongPrueba(7,"Las Zanahorias estan rebien","Pepe", "private");
        SongPrueba s7 = new SongPrueba(8,"Javadabadooh","El papu picapiedra", "private");
        SongPrueba s8 = new SongPrueba(9,"Intel y gentes: Musica tite'","Dani y los otros", "private");
        SongPrueba s9 = new SongPrueba(10,"Hola bon dia","El SinMerk3000", "private");
        SongPrueba s10 = new SongPrueba(11,"Bella Ciao Remix","Martin Garrix", "private");
        SongPrueba s11 = new SongPrueba(12,"Urss Anthem Techno Remix","DJ Marx", "private");
        SongPrueba s12 = new SongPrueba(13,"El baile del TCP/IP","TimoTronic", "private");
        SongPrueba s13 = new SongPrueba(14,"TimoTronic","Yahoo", "private");
        SongPrueba s14 = new SongPrueba(15,"Illo eso ka' sido","Pingu", "private");
        SongPrueba s15 = new SongPrueba(16,"Pokimones' sido","Pika mamame la polla", "private");

        array.add(s);
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);
        array.add(s5);
        array.add(s6);
        array.add(s7);
        array.add(s8);
        array.add(s9);
        array.add(s10);
        array.add(s11);
        array.add(s12);
        array.add(s13);
        array.add(s14);
        array.add(s15);
        return array;
    }



}
