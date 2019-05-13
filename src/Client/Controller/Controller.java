package Client.Controller;

import Client.Controller.MenuPrincipal.JPrincipalController;
import Client.Controller.Piano.PianoController;
import Client.Controller.Start.StartController;
import Client.Network.ClientConnection;
import Client.View.View;
import Model.Song;
import Model.User;
import org.jfugue.pattern.Pattern;

import java.util.ArrayList;

import static Client.Network.ClientConnection.*;

public class Controller {
    public static String OK = "ok";
    public static String KO = "ko";

    private View view;
    private ClientConnection network;
    private StartController startController;
    private JPrincipalController principalController;
    private PianoController pianoController;
    private ControllerJSocial controllerJSocial;
    private ControllerJSong controllerJSong;

    private Song song;
    private String songFile;

    public Controller(View view) {
        this.view = view;
        view.initStartView();
    }

    public void registerNetwork(ClientConnection network) {
        this.network = network;
        network.establishConnection();
    }

    public void registerController() {
        startController = new StartController(view, this);
        view.getStartView().registerController(startController);

        view.initRegisterView();
        view.getRegisterView().registerController(startController);
        closeRegister();

        view.initPrincipalView();
        principalController = new JPrincipalController(view, this);
        view.getPrincipalView().registerController(principalController);
        closePrincipal();

        view.initSocialView();
        controllerJSocial = new ControllerJSocial(view, this);
        view.getSocialView().registerController(controllerJSocial);
        closeSocial();

        view.initSongView();
        controllerJSong = new ControllerJSong(view, this);
        view.getSongView().getjSong().registerControllers(controllerJSong);
        closeSong();

        pianoController = new PianoController(view, this);
        closePiano();

    }

    public void openStart() {
        view.getStartView().setVisible(true);
    }

    public void closeStart() {
        view.getStartView().setVisible(false);
        view.getStartView().dispose();
    }

    public void openRegister() {
        view.getRegisterView().setVisible(true);
    }

    public void closeRegister() {
        view.getRegisterView().setVisible(false);
        view.getRegisterView().dispose();
    }

    public void openPrincipal() {
        view.getPrincipalView().setVisible(true);
    }

    public void closePrincipal() {
        view.getPrincipalView().setVisible(false);
        view.getPrincipalView().dispose();
    }

    public void openPiano() {
        view.getPianoView().setVisible(true);
    }

    public void closePiano() {
        view.getPianoView().setVisible(false);
        view.getPianoView().dispose();
    }

    public void setSongToSave(String songFile, Song song) {
        this.songFile = songFile;
        this.song = song;
    }

    public String getSongFileToSave() {
        return songFile;
    }

    public Song getSongToSave() {
        return song;
    }

    public void openSocial() {
        view.getSocialView().setVisible(true);
    }

    public void closeSocial() {
        view.getSocialView().setVisible(false);
        view.getSocialView().dispose();
    }

    public void openSong() {
        view.getSongView().setVisible(true);
    }

    public void closeSong() {
        view.getSongView().setVisible(false);
        view.getSongView().dispose();
    }

    public User getLogin() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < view.getStartView().getPassword().length; i++) {
            password.append(view.getStartView().getPassword()[i]);
        }
        return new User(view.getStartView().getLogin(), password.toString());
    }

    public User getRegister() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < view.getRegisterView().getPassword().length; i++) {
            password.append(view.getRegisterView().getPassword()[i]);
        }
        return new User(view.getRegisterView().getUsername(),
                view.getRegisterView().getEmail(), password.toString());
    }

    public String getSearchedUser() {
        return view.getSocialView().getjSocial().getSearchUser();
    }

    public void networkLogIn() {
        network.setNextFunc(LOGIN);
    }

    public void networkLogInResult(String petitionResult) {
        if (petitionResult.equals(OK)) {
            openPrincipal();
            closeStart();
        }
        if (petitionResult.equals(KO)) {
            view.getStartView().errorPopUp("login");
        }
    }

    public void networkRegister() {
        network.setNextFunc(REGISTER);
    }

    //Piano Network functions
    public void networkPiano() {
        network.setNextFunc(PIANO);
    }

    public void networkSelectSong() {
        network.setNextFunc(SELECT_SONG);
    }

    public void networkSelectSongResult(String petitionResult, ArrayList<Song> songs) {
        if (petitionResult.equals(OK)) {
            view.getSongView().updateSongs(songs);
            view.getSongView().updateControllersSongs(controllerJSong);
        }
        if (petitionResult.equals(KO)) {
            view.getSongView().errorPopUp();
        }
    }

    public void networkSaveSong(String song) {
        network.setNextFunc(SAVE_SONG);
    }

    public void networkSaveSongResult(String petitionResult) {
        view.getSongView().savePopUp(petitionResult);
    }

    public void networkRequestSong() {
        network.setNextFunc(REQUEST_SONG);
    }

    public String getSongToPlay() {
        return controllerJSong.getActualSong();
    }

    public void networkExitPiano(){
        network.setNextFunc(EXIT_PIANO);
    }

    //Social Network functions
    public void networkSocial() {
        network.setNextFunc(SOCIAL);
    }

    public void networkSearchSocial() {
        network.setNextFunc(SEARCH_USER);
    }

    public void networkSearchSocialResult(String petitionResult, User userToController) {
        if (petitionResult.equals(OK)) {
            view.getSocialView().showUserSearch( userToController.getNameUser(), "usuarioRandom.png", userToController.getPassword().equals("YES"));
            view.getSocialView().getjSocial().registerControllerAddFriend(controllerJSocial);
        }
        if (petitionResult.equals(KO)) {
            view.getSocialView().showUserNotFound();
        }
    }

    public void networkAddSocial() {
        network.setNextFunc(ADD_USER);
    }

    public void networkAddSocialResult(String petitionResult) {
        view.getSocialView().friendPopUp(petitionResult);
    }

    public void networkSignOut() {
        network.setNextFunc(LOG_OUT);
    }

    public void networkExitSocial() {
        network.setNextFunc(EXIT_SOCIAL);
    }

    public void networkDeleteAccount() {
        network.setNextFunc(DELETE_ACCOUNT);
    }

    public void networkDeleteAccountResult(String petitionResult) {
        view.getPrincipalView().deletedAccountPopUp(petitionResult);
    }

    public void networkClose() {
        network.closeConnection();
    }
}
