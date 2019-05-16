package Client.View;

import Client.View.Piano.JPiano;
import Client.View.Start.JRegister;
import Client.View.Start.JStart;

/**
 * View Father class that manages all the individual views.
 */
public class View {
    private JPiano pianoView;
    private JPrincipal principalView;
    private JRegister registerView;
    private FinestraJSocial socialView;
    private JStart startView;
    private FinestraJSong songView;
    private SaveSong saveSong;

    public JPiano getPianoView() {
        return pianoView;
    }
    
    public JPrincipal getPrincipalView() {
        return principalView;
    }

    public JRegister getRegisterView() {
        return registerView;
    }

    public FinestraJSocial getSocialView() {
        return socialView;
    }

    public JStart getStartView() {
        return startView;
    }

    public FinestraJSong getSongView() {
        return songView;
    }

    public SaveSong getSaveSongView(){
        return saveSong;
    }

    /**
     * Initializes piano view.
     */
    public void initPianoView() {
        pianoView = new JPiano();
    }

    /**
     * Initializes start view.
     */
    public void initStartView() {
        startView = new JStart();
    }

    /**
     * Initializes register view.
     */
    public void initRegisterView() {
        registerView = new JRegister();
    }

    /**
     * Initializes principal view.
     */
    public void initPrincipalView() {
        principalView = new JPrincipal();
    }

    /**
     * Initializes social view.
     */
    public void initSocialView() {
        socialView = new FinestraJSocial();
    }

    /**
     * Initializes song view.
     */
    public void initSongView() {
        songView = new FinestraJSong();
    }

    /**
     * Initializes save view.
     */
    public void initSaveSongView(){
        saveSong = new SaveSong();
    }
}
