package Client.Controller.Piano;

import Client.View.Piano.JPiano;
import Client.View.Piano.Key;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;
import org.jfugue.theory.Note;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PlayerSongPiano extends Thread {
    private JPiano piano;
    private Pattern song;
    private double timeSong = 0;

    public PlayerSongPiano(JPiano piano, Pattern song) {
        this.piano = piano;
        this.song = song;
        initPlayer();
    }

    private void initPlayer() {
        int i = 0;
        String search;
        int x = 0;
        int y = 0;
        int height = 0;
        JPanel k;
        int leftOrRight = 0;
        for (Token t : song.getTokens()) {
            if (t.getType() == Token.TokenType.NOTE) {
                Note n = new Note(t.toString());
                timeSong += n.getDuration();
                height = (int) (n.getDuration() * 150);
                y = (int) (35 - 7.5 * timeSong) - height;
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
        }
        piano.revalidate();
        piano.repaint();
    }

    public double getTimeSong() {
        return timeSong;
    }
}



