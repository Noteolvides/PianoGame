package Client.Controller;

import Client.Controller.MenuPrincipal.JPrincipalController;
import Client.Controller.Piano.PianoController;
import Client.Controller.Start.StartController;
import Client.Network.ClientConnection;
import Client.View.View;
import Model.Song;
import Model.User;

import java.util.ArrayList;

import static Client.Network.ClientConnection.*;

/**
 * Client Father Controller class, it manages the Views and the model
 * of the client, using sub-controllers assigned to the different views.
 */
public class Controller {
    private View view;
    private ClientConnection network;
    private StartController startController;
    private JPrincipalController principalController;
    private PianoController pianoController;
    private ControllerJSocial controllerJSocial;
    private ControllerJSong controllerJSong;
    private ControllerSaveSong controllerSaveSong;
    private String songMidi;
    private Song song;

    /**
     * Controller constructor, it assigns the view and starts showing the firts
     * view of the program.
     * @param view Class with all the views.
     */
    public Controller(View view) {
        this.view = view;
        view.initStartView();
    }

    /**
     * Function that links the controller with the clientconnection instance.
     * @param network ClientConnection class which manages the communication with the server
     */
    public void registerNetwork(ClientConnection network) {
        this.network = network;
        network.establishConnection();
    }

    /**
     * Function that calls the register ActionListeners functions in order to link the
     * corresponding sub-controller with it's view
     */
    public void registerController() {
        startController = new StartController(view, this);
        view.getStartView().registerController(startController);

        view.initRegisterView();
        view.getRegisterView().registerController(startController);

        view.initPrincipalView();
        principalController = new JPrincipalController(view, this);
        view.getPrincipalView().registerController(principalController);

        view.initSocialView();
        controllerJSocial = new ControllerJSocial(view, this);
        view.getSocialView().registerController(controllerJSocial);

        view.initSongView();
        controllerJSong = new ControllerJSong(view, this);
        view.getSongView().getjSong().registerControllers(controllerJSong);

        pianoController = new PianoController(view, this);

        view.initSaveSongView();
        controllerSaveSong = new ControllerSaveSong(view, this);
        view.getSaveSongView().registerController(controllerSaveSong);
    }

    /**
     * Function that show the start view.
     */
    public void openStart() {
        view.getStartView().setVisible(true);
    }

    /**
     * Function that hides the start view.
     */
    public void closeStart() {
        view.getStartView().setVisible(false);
        view.getStartView().dispose();
    }

    /**
     * Function that shows the register view.
     */
    public void openRegister() {
        view.getRegisterView().setVisible(true);
    }

    /**
     * Function that hides the register view.
     */
    public void closeRegister() {
        view.getRegisterView().setVisible(false);
        view.getRegisterView().dispose();
    }

    /**
     * Function that shows the principal view.
     */
    public void openPrincipal() {
        view.getPrincipalView().setVisible(true);
    }

    /**
     * Function that hides the principal view.
     */
    public void closePrincipal() {
        view.getPrincipalView().setVisible(false);
        view.getPrincipalView().dispose();
    }

    /**
     * Function that shows the piano view and sets the
     * next action to do for the ClientConnection as PIANO
     */
    public void openPiano() {
        view.getPianoView().setVisible(true);
        network.setNextFunc(PIANO);
    }

    /**
     * Function that hides the piano view and sets the
     * next action to do for the ClientConnection as EXIT_PIANO
     */
    public void closePiano() {
        view.getPianoView().setVisible(false);
        view.getPianoView().dispose();
        network.setNextFunc(EXIT_PIANO);
    }

    /**
     * Function that saves the midi string of the song played in the piano.
     * @param songMidi String of midi of the song played.
     */
    public void setMidiToSave(String songMidi) {
        this.songMidi = songMidi;
    }

    /**
     * Function that creates and saves the file that is going to be saved in the database.
     */
    public void setSongToSave () {
        song = new Song();
        song.setTitle(view.getSaveSongView().getAddSongName().getText());
        song.setDescription(view.getSaveSongView().getSongDescription().getText());
        song.setPrivacity(view.getSaveSongView().getWannaPrivate().isSelected());
    }

    public Song getSongToSave() {
        return song;
    }

    public String getSongMidi() {
        return songMidi;
    }

    /**
     * Function that shows the social view and sets the
     * next action to do for the ClientConnection as SOCIAL
     */
    public void openSocial() {
        view.getSocialView().setVisible(true);
        network.setNextFunc(SOCIAL);
    }

    /**
     * Function that hides the social view.
     */
    public void closeSocial() {
        view.getSocialView().setVisible(false);
        view.getSocialView().dispose();
    }

    /**
     * Function that shows the song view and sets the
     * next action to do for the ClientConnection as SELECT_SONG
     */
    public void openSong() {
        view.getSongView().setVisible(true);
        network.setNextFunc(SELECT_SONG);
    }

    /**
     * Function that hides the song view.
     */
    public void closeSong() {
        view.getSongView().setVisible(false);
        view.getSongView().dispose();
    }

    public void openSaveSong() {
        view.getSaveSongView().setVisible(true);
    }

    public void closeSaveSong() {
        view.getSaveSongView().setVisible(false);
        view.getSaveSongView().dispose();
    }

    /**
     * Function to get the object User who wants to login.
     * @return User object with the username and password entered on the start view.
     */
    public User getLogin() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < view.getStartView().getPassword().length; i++) {
            password.append(view.getStartView().getPassword()[i]);
        }
        return new User(view.getStartView().getLogin(), password.toString());
    }

    /**
     * Function to get the User who wants to register.
     * @return User object with the username, email and password entered on the register view.
     */
    public User getRegister() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < view.getRegisterView().getPassword().length; i++) {
            password.append(view.getRegisterView().getPassword()[i]);
        }
        return new User(view.getRegisterView().getUsername(),
                 password.toString(),view.getRegisterView().getEmail());
    }

    /**
     * Functin to get the searched username.
     * @return String with the username entered in the social view.
     */
    public String getSearchedUser() {
        return view.getSocialView().getjSocial().getSearchUser();
    }

    /**
     * Function that sets the next action to do for the ClientConnection as LOGIN
     */
    public void networkLogIn() {
        network.setNextFunc(LOGIN);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of the login, makes the program to login and go to the principal screen or show an error popup.
     * @param petitionResult ClientConnection Transmission result code.
     */
    public void networkLogInResult(int petitionResult) {
        if (petitionResult == OK) {
            openPrincipal();
            closeStart();
        } else {
            view.getStartView().errorPopUp("login", petitionResult);
        }
    }

    /**
     * Function that sets the next action to do for the ClientConnection as REGISTER
     */
    public void networkRegister() {
        network.setNextFunc(REGISTER);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of the register, makes the program to close the register window and go to
     * the login screen or show an error popup.
     * @param petitionResult ClientConnection Transmission result code.
     */
    public void networkRegisterResult(int petitionResult) {
        if (petitionResult == OK) {
            closeRegister();
        }
        view.getStartView().errorPopUp("register", petitionResult);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of getting the song from the database, makes the program to updates the songs in the view or show an error popup.
     * @param petitionResult ClientConnection Transmission result code.
     * @param songs ArrayList of the song from the database
     */
    public void networkSelectSongResult(int petitionResult, ArrayList<Song> songs) {
        if (petitionResult == OK) {
            view.getSongView().updateSongs(songs);
            view.getSongView().updateControllersSongs(controllerJSong);
        } else {
            view.getSongView().errorPopUp(petitionResult);
        }
    }

    /**
     * Function that sets the next action to do for the ClientConnection as SAVE_SONG
     */
    public void networkSaveSong() {
        network.setNextFunc(SAVE_SONG);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of the saving song process, the program to notifies if the song was saved correctly.
     * @param petitionResult ClientConnection Transmission result code.
     */
    public void networkSaveSongResult(int petitionResult) {
        view.getPianoView().savePopUp(petitionResult);
    }

    /**
     * Function that sets the next action to do for the ClientConnection as REQUEST_SONG
     */
    public void networkRequestSong() {
        network.setNextFunc(REQUEST_SONG);
    }

    public String getSongToPlay() {
        return controllerJSong.getActualSong();
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of requesting a song, notifies if the song was correctly acquired.
     * @param petitionResult ClientConnection Transmission result code.
     * @param midi String with the song in midi format.
     */
    public void networkRequestSongResult(int petitionResult, String midi) {
        view.getSongView().requestPopUp(petitionResult);
        pianoController.playSong(midi);
    }

    /**
     * Function that sets the next action to do for the ClientConnection as SEARCH_USER
     */
    public void networkSearchSocial() {
        network.setNextFunc(SEARCH_USER);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of searching user, makes the program to show the found user if there's one or show an error popup.
     * @param petitionResult ClientConnection Transmission result code.
     * @param userToController User found in the database, that will be shown.
     */
    public void networkSearchSocialResult(int petitionResult, User userToController) {
        if (petitionResult == OK) {
            view.getSocialView().showUserSearch(userToController.getNameUser(), "usuarioRandom.png", userToController.getPassword().equals("YES"));
            view.getSocialView().getjSocial().registerControllerAddFriend(controllerJSocial);
            if (userToController.getPassword().equals("YES")) {
                view.getSocialView().getjSocial().getPanelFriend().setButtonAddAsDisabled(controllerJSocial);
            }
        } else {
            view.getSocialView().errorPopUp(petitionResult);
            view.getSocialView().showUserNotFound();
        }
    }

    /**
     * Function that sets the next action to do for the ClientConnection as ADD_USER
     */
    public void networkAddSocial() {
        network.setNextFunc(ADD_USER);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of adding a friend, notifies if the friend was added successfully.
     * @param petitionResult ClientConnection Transmission result code.
     */
    public void networkAddSocialResult(int petitionResult) {
        view.getSocialView().friendPopUp(petitionResult, controllerJSocial);
    }

    /**
     * Function that sets the next action to do for the ClientConnection as LOG_OUT
     */
    public void networkLogOut() {
        network.setNextFunc(LOG_OUT);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of the logout,notifies if the logout was done successfully.
     * @param petitionResult ClientConnection Transmission result code.
     */
    public void networkLogOutResult(int petitionResult) {
        view.getPrincipalView().logOutPopUp(petitionResult);
    }

    /**
     * Function that sets the next action to do for the ClientConnection as EXIT_SOCIAL
     */
    public void networkExitSocial() {
        network.setNextFunc(EXIT_SOCIAL);
    }

    /**
     * Function that sets the next action to do for the ClientConnection as DELETE_ACCOUNT
     */
    public void networkDeleteAccount() {
        network.setNextFunc(DELETE_ACCOUNT);
    }

    /**
     * Functions that depending of the result of the ClientConnection transmission
     * of deleting an account, notifies if the deletion was done successfully.
     * @param petitionResult ClientConnection Transmission result code.
     */
    public void networkDeleteAccountResult(int petitionResult) {
        view.getPrincipalView().deletedAccountPopUp(petitionResult);
    }

    /**
     * Ends the connection with the ClientConnection
     */
    public void networkClose() {
        network.closeConnection();
    }
}
