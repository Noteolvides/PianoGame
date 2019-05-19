package Client.Controller;

import Client.View.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller that manages the Social View.
 */
public class ControllerJSocial implements MouseListener {
    private View view;
    private Controller controller;

    /**
     * Constructor that assigns the view and the controller father.
     * @param view View father.
     * @param controller Controller father.
     */
    public ControllerJSocial (View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    /**
     * Implemented function from MouseListener when mouse is clicked.
     * @param e Action done with the mouse in the View.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Implemented function from MouseListener when mouse is pressed.
     * @param e Action done with the mouse in the View.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            if (e.getSource() == view.getSocialView().getjSocial().getSearchButton()) {
                controller.networkSearchSocial();
            }
            else {
                if (e.getSource() == view.getSocialView().getjSocial().getBackButton()) {
                    controller.networkExitSocial();
                    controller.closeSocial();
                    controller.openPrincipal();
                }
                else {
                    controller.networkAddSocial();
                }
            }
        }
        else {
            view.getSocialView().getjSocial().deleteText();
        }
    }

    /**
     * Implemented function from MouseListener when mouse is released.
     * @param e Action done with the mouse in the View.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Implemented function from MouseListener when mouse is entered.
     * @param e Action done with the mouse in the View.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Implemented function from MouseListener when mouse is exited.
     * @param e Action done with the mouse in the View.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
