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
        setSize(400,1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        SongPrueba s9 = new SongPrueba(10,"Hola bon dia","El SinMerk3000");
        SongPrueba s10 = new SongPrueba(11,"Bella Ciao Remix","Martin Garrix");
        SongPrueba s11 = new SongPrueba(12,"Urss Anthem Techno Remix","DJ Marx");
        SongPrueba s12 = new SongPrueba(13,"El baile del TCP/IP","TimoTronic");
        SongPrueba s13 = new SongPrueba(14,"TimoTronic","Yahoo");
        SongPrueba s14 = new SongPrueba(15,"Illo eso ka' sido","Pingu");
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
}
