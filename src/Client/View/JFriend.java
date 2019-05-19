package Client.View;

import Client.Controller.ControllerJSocial;

import javax.swing.*;
import java.awt.*;

/**
 * Class that creates the panel for the user information.
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class JFriend extends JPanel {
    //In this attribute we are going to show the photo of the friend that we find
    private JLabel profileImageFriend;
    //In this view we are going to show the different characteristics of the user
    private JPanel groupCharacteristics;
    //With this button we can add this friend or not
    private JLabel buttonAddFriend;
    private JLabel notFoundIm;
    private JLabel text;

    /**
     * Constructor that creates an empty user.
     * @param inicializacion String with an initialization.
     */
    public JFriend (String inicializacion) {
        text = new JLabel();
        setLayout(new FlowLayout());
        text.setText(inicializacion);
        setPreferredSize(new Dimension(390,125));
        add(text);
    }

    /**
     * Constructor that creates a panel with the information of a User.
     * @param name Name of the User.
     * @param nombreImagen Name of the path of the User.
     * @param isFriend Boolean stating if the user is friend.
     */
    public JFriend (String name, String nombreImagen, Boolean isFriend) {
        //Initialization of the Layout
        setLayout(new GridBagLayout());
        setVisible(false);
        GridBagConstraints gbc = new GridBagConstraints();
        //We put a size to always have the same
        setPreferredSize(new Dimension(390,125));
        //Initialization of the attributes
        profileImageFriend = new JLabel();
        groupCharacteristics = new JPanel();
        buttonAddFriend = new JLabel();

        groupCharacteristics.setLayout(new BorderLayout());

        //Initialization of the image
        //Definition of the title
        ImageIcon foto_perfil = new ImageIcon("img/" + nombreImagen);
        //We scale the image because it's too big
        ImageIcon foto_perfil_scaled = new ImageIcon(foto_perfil.getImage().getScaledInstance(foto_perfil.getIconWidth()/5, foto_perfil.getIconHeight() / 5, Image.SCALE_SMOOTH));
        profileImageFriend.setIcon(foto_perfil_scaled);


        //Then, we put the button to add this friend
        //Initialization of the image
        //Definition of the title
        ImageIcon addFriend = new ImageIcon("img/addFriend.png");
        //We scale the image because it's too big
        ImageIcon addFriend_scaled = new ImageIcon(addFriend.getImage().getScaledInstance(addFriend.getIconWidth()/28, addFriend.getIconHeight() / 28, Image.SCALE_SMOOTH));
        buttonAddFriend.setIcon(addFriend_scaled);

        //Later we put the name of the user, with a random phrase
        JLabel j = new JLabel(name);
        j.setFont(new Font("Gotham",Font.BOLD,20));
        JPanel jp = adquirirJPanel(isFriend);
        groupCharacteristics.add(j,BorderLayout.PAGE_START);
        groupCharacteristics.add(jp,BorderLayout.CENTER);
        groupCharacteristics.add(buttonAddFriend,BorderLayout.PAGE_END);
        groupCharacteristics.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        gbc.fill = GridBagConstraints.VERTICAL;
        //We put the image in the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(profileImageFriend,gbc);

        //We put the description of the user in the right of the image
        gbc.gridx = 1;
        add(groupCharacteristics,gbc);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));


    }

    /**
     * Constructor that creates a panel with an error information.
     */
    public JFriend () {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(390,125));
        notFoundIm = new JLabel();
        text = new JLabel();
        text.setFont(new Font ("Sans Serif",Font.BOLD,18));

        //We make an image that shows us that it was an error
        ImageIcon notFoundImage = new ImageIcon("img/notFound.png");
        //We scale the image because it's too big
        ImageIcon notFound_scaled = new ImageIcon(notFoundImage.getImage().getScaledInstance(notFoundImage.getIconWidth() / 18, notFoundImage.getIconHeight() / 18, Image.SCALE_SMOOTH));
        notFoundIm.setIcon(notFound_scaled);

        //We make a text to indicate the error
        text.setText("ERROR π, USER NOT FOUND");



        //Adding to the panel the image and the text
        add(notFoundIm);

        add(text);

    }

    /**
     * Function that makes a panel with a user.
     * @param isFriend Boolean that states if is friend or not.
     * @return Panel of the friend.
     */
    private JPanel adquirirJPanel (boolean isFriend) {
        JPanel panel = new JPanel ();
        panel.setLayout(new FlowLayout());
        JLabel j2 = new JLabel();
        JLabel j3 = new JLabel();
        if (isFriend) {
            j2.setText("You are already friends");
            ImageIcon boton_verde  = new ImageIcon("img/green.png");
            //We scale the image because it's too big
            ImageIcon boton_verde_scaled = new ImageIcon(boton_verde.getImage().getScaledInstance(boton_verde.getIconWidth()/80, boton_verde.getIconHeight() / 80, Image.SCALE_SMOOTH));
            j3.setIcon(boton_verde_scaled);
            panel.add(j3);
            panel.add(j2);

        }
        else {
            j2.setText("You are not friends");
            ImageIcon boton_rojo  = new ImageIcon("img/red.png");
            //We scale the image because it's too big
            ImageIcon boton_rojo_scaled = new ImageIcon(boton_rojo.getImage().getScaledInstance(boton_rojo.getIconWidth()/50, boton_rojo.getIconHeight() / 50, Image.SCALE_SMOOTH));
            j3.setIcon(boton_rojo_scaled);
            panel.add(j3);
            panel.add(j2);
        }
        return panel;
    }

    /**
     * Function that assings the controller to the panel.
     * @param controllerJSocial Social controller.
     */
    public void registerController (ControllerJSocial controllerJSocial) {
        if (buttonAddFriend != null) {
            buttonAddFriend.addMouseListener(controllerJSocial);
        }
    }

    /**
     * Functions that changes the icon and disables the add button of the friend.
     * @param controllerJSocial Controller of the Social view.
     */
    public void setButtonAddAsDisabled(ControllerJSocial controllerJSocial) {
        ImageIcon notAdd = new ImageIcon("img/delete_button.png");
        buttonAddFriend.setDisabledIcon(new ImageIcon(notAdd.getImage().getScaledInstance(notAdd.getIconWidth()/23, notAdd.getIconHeight() / 23, Image.SCALE_SMOOTH)));
        buttonAddFriend.setEnabled(false);
        buttonAddFriend.removeMouseListener(controllerJSocial);
    }
}
