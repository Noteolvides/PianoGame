package Client.Controller.Piano;

import Client.View.Piano.JPiano;
import Client.View.Piano.Key;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;
import org.jfugue.theory.Note;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * AuxiliarClass Of Controller Piano that puts the keys in the screen in order to play the song
 *
 * @version 1.0
 * @since 2019-05-16
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class PlayerSongPiano extends Thread {
    private JPiano piano;
    private Pattern song;
    private double timeSong = 0;
    private int y = 0;
    private ArrayList<Note> notesOfPiano;

    /**
     * PayerSongPiano constructor
     * @param piano: Piano view
     * @param song: Keys
     */
    public PlayerSongPiano(JPiano piano, Pattern song) {
        notesOfPiano = new ArrayList<>();
        this.piano = piano;
        this.song = song;
        initPlayer();
    }

    /**
     * Logical that thinks where to put the keys
     */
    private void initPlayer() {
        int i = 0;
        String search;
        int x = 0;
        int height = 0;
        JPanel k;
        boolean flag = false;
        double timeSinature = 0.0;
        double offset = 0;
        boolean isFirkey = true;
        String aux;
        for (Token t : song.getTokens()) {
            if (t.getType() == Token.TokenType.NOTE) {
                if (isFirkey){
                    isFirkey = false;
                }
                Note n = new Note(t.toString());
                height = (int) (n.getDuration() * 70);
                if (!flag){
                    y = (int) (35 - 80 * timeSong) - height;
                }else{
                    y = (int) (35 - 80 * timeSinature) - height;
                    flag = false;
                }
                timeSong += n.getDuration();
                if (!n.isRest()) {
                    search = n.getToneString();
                    if (search.charAt(1) == '#' || search.charAt(1) == 'b') {
                        if (search.charAt(1) == 'b') {
                            if (search.charAt(0) == 'B') {
                                search = "A#" + search.charAt(2);
                            } else {
                                if (search.charAt(0) == 'E') {
                                    search = "D#" + search.charAt(2);
                                }
                            }
                        }
                        for (Key kAux : piano.getPiano().getKeys()) {
                            if (kAux.getNumberOfKey().getText().contains(search.substring(0, 2))) {
                                x = kAux.getLocation().x;
                                break;
                            }
                        }
                        k = new JPanel();
                        k.setName(n.toString());
                        k.setSize(30, height);
                        if (search.length() == 2 || Integer.parseInt(String.valueOf(search.charAt(2))) % 2 == 0) {
                            k.setLocation(x, y);
                        } else {
                            k.setLocation(x + 350, y);
                        }
                        k.setBackground(Color.CYAN);
                        piano.getNotes().add(k);
                        piano.add(k);
                    } else {
                        for (Key kAux : piano.getPiano().getKeys()) {
                            if (kAux.getNumberOfKey().getText().contains(search.substring(0, 1))) {
                                x = kAux.getLocation().x;
                                break;
                            }
                        }
                        k = new JPanel();
                        k.setName(n.toString());
                        k.setSize(50, height);
                        if (Integer.parseInt(String.valueOf(search.charAt(1))) % 2 == 0) {
                            k.setLocation(x, y);
                        } else {
                            k.setLocation(x + 350, y);
                        }
                        k.setBackground(Color.YELLOW);
                        piano.getNotes().add(k);
                        piano.add(k);
                    }
                }
            }
            if (t.getType() == Token.TokenType.TRACK_TIME_BOOKMARK){
                aux = t.toString().replace('@','0').replace(',','0');
                if(isFirkey){
                    offset=Double.parseDouble(aux);
                    isFirkey=false;
                }else{
                    timeSinature = Double.parseDouble(aux);
                    timeSinature -= offset;
                    flag = true;
                }
            }
        }
        piano.revalidate();
        piano.repaint();
    }

    public double getTimeSong() {
        return timeSong;
    }

    public int getY() {
        return y;
    }
}



