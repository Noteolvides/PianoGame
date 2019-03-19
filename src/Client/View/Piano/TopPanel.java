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
    private JMenuItem restartSongInSystem;
    private JMenu more;
    private JButton exitToMenu;


    public TopPanel(){
        setLayout(new FlowLayout());

        recordSong = new JMenu("Record Song");

        record = new JMenuItem("Record");
        recordSong.add(record);
        play = new JMenuItem("Play");
        recordSong.add(play);
        stop = new JMenuItem("Stop");
        recordSong.add(stop);
        save = new JMenuItem("Save");
        recordSong.add(save);

        playSong = new JMenu("Play Song");

        selectSongInSystem = new JMenuItem("Select Song");
        playSong.add(selectSongInSystem);
        playSongInSystem = new JMenuItem("Play Song");
        playSong.add(playSongInSystem);
        restartSongInSystem = new JMenuItem("Restart Song");
        playSong.add(restartSongInSystem);

        more = new JMenu("More");
        exitToMenu = new JMenuItem("Exit to Menu");
        more.add(exitToMenu);

        add(recordSong);
        add(playSong);
        add(more);

    }
}
