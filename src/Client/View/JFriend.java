package Client.View;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class JFriend extends JPanel {
    //In this attribute we are going to show the photo of the friend that we find
    private JLabel profileImageFriend;
    //In this view we are going to show the different characteristics of the user
    private JPanel groupCharacteristics;
    //With this button we can add this friend or not
    private JLabel buttonAddFriend;



    public JFriend (String nombreImagen, String name, Boolean isFriend) {
        //Initialization of the Layout
        setLayout(new GridBagLayout());
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

}
