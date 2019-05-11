package Client.Controller;

import Client.View.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerJSocial implements MouseListener {
    private View view;
    private Controller controller;


    public ControllerJSocial (View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            if (e.getSource() == view.getSocialView().getjSocial().getSearchButton()) {
                System.out.println("You are trying to find someone...");
                controller.networkSearchSocial();
            }
            else {
                if (e.getSource() == view.getSocialView().getjSocial().getBackButton()) {
                    System.out.println("Going back to the previous menu...");
                    controller.closeSocial();
                    controller.openPrincipal();
                }
                else {
                    controller.networkAddSocial();
                    System.out.println("You have tried to add a new friend");
                }
            }
        }
        else {
            view.getSocialView().getjSocial().deleteText();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
