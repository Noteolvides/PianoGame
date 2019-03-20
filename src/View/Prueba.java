package View;

import View.Images.SongPrueba;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Prueba extends JFrame {
    private JSong pantalla;
    public Prueba () {


        pantalla = new JSong(simulationOfController());
        add(pantalla);
        setSize(300,1000);
        setVisible(true);

    }
    public ArrayList <SongPrueba> simulationOfController () {
        //This array simulates the return of the controller's call
        ArrayList <SongPrueba> array = new ArrayList<SongPrueba>();
        SongPrueba s = new SongPrueba(1,"Himno Urss","Stallin");
        SongPrueba s1 = new SongPrueba(2,"Melgui:The Song","MelguiUser");
        SongPrueba s2 = new SongPrueba(3,"Yahoo:The Song","Jiahui");
        SongPrueba s3 = new SongPrueba(4,"La Lechuga ta' pocha","Anonymous");
        SongPrueba s4 = new SongPrueba(5,"Los coches chocones","NeilUser");
        SongPrueba s5 = new SongPrueba(6,"Kermitt BSO","Gutavo");
        SongPrueba s6 = new SongPrueba(7,"Las Zanahorias estan rebien","Pepe");
        SongPrueba s7 = new SongPrueba(8,"Javadabadooh","El papu picapiedra");
        SongPrueba s8 = new SongPrueba(9,"Intel y gentes: Musica tite'","Dani y los otros");
        array.add(s);
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);
        array.add(s5);
        array.add(s6);
        array.add(s7);
        array.add(s8);
        return array;
    }
}
