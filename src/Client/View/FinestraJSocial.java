package Client.View;
import Client.Controller.ControllerJSocial;

import javax.swing.*;

import static Client.Network.ClientConnection.*;

/**
 * Class that creates the Frame of the Social view.
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class FinestraJSocial extends JFrame {
    private JSocial jSocial;

    /**
     * Constructor that creates and shows the frame of the Social view.
     */
    public FinestraJSocial () {
        jSocial = new JSocial();
        setSize(400,405);
        add(jSocial);
        //We make the frame not resizable
        setResizable(false);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
    public JSocial getjSocial() {
        return jSocial;
    }

    public void setjSocial(JSocial jSocial) {
        this.jSocial = jSocial;
    }

    /**
     * Method to put the JSocial Controller in all the avaiable elements in the JSocial panel
     * @param controllerJSocial Controller that we want to register
     */
    public void registerController (ControllerJSocial controllerJSocial) {
        jSocial.registerController(controllerJSocial);
    }

    /**
     * Method to show the result of the search in the JSocial view
     * @param name Name of the user that we want to show
     * @param imageFile The image file that we want to show in the result
     * @param isFriend Boolean that indicates if the actual user and the user searched are friends
     */
    public void showUserSearch (String name, String imageFile,boolean isFriend) {
        jSocial.showUserSearch(name,imageFile,isFriend);
    }

    /**
     * Method we resort to when the searched user has not been found
     */
    public void showUserNotFound () {
        jSocial.showUserNotFound();
    }

    /**
     * Function that shows a PopUp with the information of the adding a friend.
     * @param added Type of error.
     * @param controllerJSocial Controller of Social view.
     */
    public void friendPopUp(int added, ControllerJSocial controllerJSocial) {
        if (added == OK) {
            JOptionPane.showMessageDialog(this, "You are friends now.", "Friend", JOptionPane.INFORMATION_MESSAGE);
            jSocial.getPanelFriend().setButtonAddAsDisabled(controllerJSocial);
        }
        if (added == KO) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (added == ERROR_BBDD) {
            JOptionPane.showMessageDialog(this, "There was a problem with the database.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (added == ERROR_OBJECT) {
            JOptionPane.showMessageDialog(this, "You are not friends.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Function that shows a PopUp with the search error.
     * @param petitionResult Type of error.
     */
    public void errorPopUp(int petitionResult) {
        if (petitionResult == KO) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_BBDD) {
            JOptionPane.showMessageDialog(this, "User not found in the database!", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_OBJECT) {
            JOptionPane.showMessageDialog(this, "The searched parameter is not correct!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
