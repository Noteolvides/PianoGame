package Client.Controller;

import Client.View.FinestraJSocial;
import Client.View.FinestraJStart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerJSocial implements MouseListener {
    private FinestraJSocial finestraJSocial;

    //TODO:I don't know if we could have this here but i think it's needed
    private FinestraJStart finestraJStart;

    public ControllerJSocial (FinestraJSocial finestraJSocial) {
        this.finestraJSocial = finestraJSocial;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            if (e.getSource() == finestraJSocial.getjSocial().getSearchButton()) {
                System.out.println("You are trying to find someone...");
                if (Math.random()> 0.5) {
                    finestraJSocial.showUserNotFound();
                }
                else {
                    finestraJSocial.showUserSearch( "Anonymous", "usuarioRandom.png",Math.random()>0.5);
                    finestraJSocial.getjSocial().registerControllerAddFriend(this);
                }
            }
            else {
                if (e.getSource() == finestraJSocial.getjSocial().getBackButton()) {
                    finestraJStart = new FinestraJStart();
                    System.out.println("Going back to the previous menu...");
                    finestraJSocial.dispose();
                    finestraJSocial.repaint();
                }
                else {
                    System.out.println("You have tried to add a new friend");
                }
            }
        }
        else {
            finestraJSocial.getjSocial().deleteText();
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
