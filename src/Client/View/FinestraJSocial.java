package Client.View;
import Client.Controller.ControllerJSocial;

import javax.swing.*;

import static Client.Network.ClientConnection.OK;

public class FinestraJSocial extends JFrame {
    private JSocial jSocial;
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

    public void friendPopUp(int added, ControllerJSocial controllerJSocial) {
        if (added == OK) {
            JOptionPane.showMessageDialog(this, "You are friends now.", "Friend", JOptionPane.INFORMATION_MESSAGE);
            jSocial.getPanelFriend().setButtonAddAsDisabled(controllerJSocial);
        }
        if (added == ERROR) {
            JOptionPane.showMessageDialog(this, "You are not friends.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void errorPopUp() {
        JOptionPane.showMessageDialog(this, "An error ocurred!", "Error", JOptionPane.WARNING_MESSAGE);
    }
}
