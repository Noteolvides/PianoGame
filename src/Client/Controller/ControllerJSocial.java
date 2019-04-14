package Client.Controller;

import Client.View.FinestraJSocial;
import Client.View.FinestraJStart;
import Client.View.JPrincipal;
import Client.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerJSocial implements MouseListener {
    private View view;
    private Controller controller;

    //TODO:I don't know if we could have this here but i think it's needed
    private JPrincipal jPrincipal;

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
                if (Math.random()> 0.5) {
                    view.getSocialView().showUserNotFound();
                }
                else {
                    view.getSocialView().showUserSearch( "Anonymous", "usuarioRandom.png",Math.random()>0.5);
                    view.getSocialView().getjSocial().registerControllerAddFriend(this);
                }
            }
            else {
                if (e.getSource() == view.getSocialView().getjSocial().getBackButton()) {
                    jPrincipal = new JPrincipal();
                    System.out.println("Going back to the previous menu...");
                    controller.closeSocial();
                    controller.openPrincipal();
                }
                else {
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
