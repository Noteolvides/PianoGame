package Client.View.Piano;

import javax.swing.*;
import java.awt.*;


/**
 * Auxiliar Component to JPiano that contains all submenus in the view
 *
 * @author  Gerard
 * @author Gustavo
 * @author Neil
 * @author Jiahui
 * @author Josep
 * @version 1.0
 * @since 2019-05-16
 */
public class TopPanel extends JMenuBar {
    private JMenu recordSong;
    private JMenuItem record;
    private JMenuItem save;
    private JMenuItem stop;
    private JMenu playSong;
    private JMenuItem selectSongInSystem;
    private JMenuItem muteSoundPlaying;
    private JMenu more;
    private JMenuItem changeKeys;
    private JMenuItem exitToMenu;

    /**
     * Initialization
     */
    public TopPanel(){
        setLayout(new FlowLayout());

        recordSong = new JMenu("Record Song");

        record = new JMenuItem("Record");
        recordSong.add(record);
        save = new JMenuItem("Save");
        save.setEnabled(false);
        recordSong.add(save);

        playSong = new JMenu("Play Song");
        selectSongInSystem = new JMenuItem("Select Song");
        playSong.add(selectSongInSystem);
        stop = new JMenuItem("Stop song");
        playSong.add(stop);
        stop.setEnabled(false);
        muteSoundPlaying = new JMenuItem("Mute Song Playing");
        muteSoundPlaying.setEnabled(false);
        playSong.add(muteSoundPlaying);


        more = new JMenu("More");
        exitToMenu = new JMenuItem("Exit to Menu");
        more.add(exitToMenu);
        changeKeys = new JMenuItem("Change Keys");
        more.add(changeKeys);

        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        add(recordSong);
        add(playSong);
        add(more);

    }

    public JMenuItem getRecord() {
        return record;
    }

    public JMenuItem getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(JMenuItem changeKeys) {
        this.changeKeys = changeKeys;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getStop() {
        return stop;
    }

    public void setStop(JMenuItem stop) {
        this.stop = stop;
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
