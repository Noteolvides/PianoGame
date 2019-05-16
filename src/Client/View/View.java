package Client.View;

import Client.View.Piano.JPiano;
import Client.View.Start.JRegister;
import Client.View.Start.JStart;

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

    public SaveSong getSaveSong(){
        return saveSong;
    }

    public void initPianoView() {
        pianoView = new JPiano();
    }

    public void initStartView() {
        startView = new JStart();
    }

    public void initRegisterView() {
        registerView = new JRegister();
    }

    public void initPrincipalView() {
        principalView = new JPrincipal();
    }

    public void initSocialView() {
        socialView = new FinestraJSocial();
    }

    public void initSongView() {
        songView = new FinestraJSong();
    }

    public void initSaveSongView(){
        saveSong = new SaveSong();
    }
}
