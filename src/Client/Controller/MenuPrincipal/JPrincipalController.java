package Client.Controller.MenuPrincipal;

import Client.Controller.Controller;
import Client.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Client.View.JPrincipal.*;

/**
 * Controller of piano this class implements all the controllers to the keys,playing and saving
 *
 * @version 1.0
 * @since 2019-05-16
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class JPrincipalController implements ActionListener {
    private View view;
    private Controller controller;

    /**
     * Main menu controller
     * @param view: Main menu view
     * @param controller: Parent controller
     */
    public JPrincipalController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    /**
     * Depending the button selected this functions activates different functionalities
     * @param e: event of the button
     */
    public void actionPerformed(ActionEvent e) {
        //We get the action command message to know what action has been done
        String action = e.getActionCommand();

        if (action.equals(JPIANO)) {
            controller.openPiano();
            controller.closePrincipal();

        } else if (action.equals(JSOCIAL)) {
            controller.openSocial();
            controller.closePrincipal();

        } else if (action.equals(SIGN_OUT)) {
            controller.networkLogOut();
            controller.closePrincipal();
            controller.openStart();

        } else if (action.equals(DELETE_ACCOUNT)) {
            if (view.getPrincipalView().confirmPopUp() == JOptionPane.YES_OPTION) {
                controller.networkDeleteAccount();
                controller.closePrincipal();
                controller.openStart();
            }
        }
    }

}
