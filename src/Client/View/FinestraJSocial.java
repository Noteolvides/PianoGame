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
    public void registerController (ControllerJSocial controllerJSocial) {
        jSocial.registerController(controllerJSocial);
    }

    public void showUserSearch (String name, String imageFile,boolean isFriend) {
        jSocial.showUserSearch(name,imageFile,isFriend);
    }
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
