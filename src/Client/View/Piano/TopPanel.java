package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JMenuBar {
    private JMenu recordSong;
    private JMenuItem record;
    private JMenuItem play;
    private JMenuItem stop;
    private JMenuItem save;
    private JMenu playSong;
    private JMenuItem playSongInSystem;
    private JMenuItem selectSongInSystem;
    private JMenuItem muteSoundPlaying;
    private JMenu more;
    private JMenuItem exitToMenu;


    public TopPanel(){
        setLayout(new FlowLayout());

        recordSong = new JMenu("Record Song");

        record = new JMenuItem("Record");
        recordSong.add(record);
        play = new JMenuItem("Play");
        recordSong.add(play);
        //stop = new JMenuItem("Stop");
        //recordSong.add(stop);
        save = new JMenuItem("Save");
        save.setEnabled(false);
        recordSong.add(save);

        playSong = new JMenu("Play Song");
        selectSongInSystem = new JMenuItem("Select Song");
        playSong.add(selectSongInSystem);
        //playSongInSystem = new JMenuItem("Play Song");
        //playSong.add(playSongInSystem);
        muteSoundPlaying = new JMenuItem("Mute Song Playing");
        muteSoundPlaying.setEnabled(false);
        playSong.add(muteSoundPlaying);


        more = new JMenu("More");
        exitToMenu = new JMenuItem("Exit to Menu");
        more.add(exitToMenu);

        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        add(recordSong);
        add(playSong);
        add(more);

    }

    public JMenuItem getRecord() {
        return record;
    }

    public JMenuItem getStop() {
        return stop;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getPlay() {
        return play;
    }

    public JMenuItem getPlaySongInSystem() {
        return playSongInSystem;
    }

    public JMenuItem getSelectSongInSystem() {
        return selectSongInSystem;
    }

    public JMenuItem getMuteSoundPlaying() {
        return muteSoundPlaying;
    }

    public JMenuItem getExitToMenu() {
        return exitToMenu;
    }
}
