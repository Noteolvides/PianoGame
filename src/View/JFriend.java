package View;

import javax.swing.*;
import java.awt.*;

public class JFriend extends JPanel {
    //In this attribute we are going to show the photo of the friend that we find
    private JLabel profileImageFriend;
    //In this view we are going to show the different characteristics of the user
    private JPanel groupCharacteristics;
    //With this button we can add this friend or not
    private JLabel buttonAddFriend;

    public JFriend (String nombreImagen, String name) {
        //Initialization of the Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Initialization of the attributes
        profileImageFriend = new JLabel();
        groupCharacteristics = new JPanel();
        buttonAddFriend = new JLabel();

        //Initialization of the image
        //Definition of the title
        ImageIcon foto_perfil = new ImageIcon(getClass().getResource("Images_JSocial/" + nombreImagen));
        //We scale the image because it's too big
        ImageIcon foto_perfil_scaled = new ImageIcon(foto_perfil.getImage().getScaledInstance(400, foto_perfil.getIconHeight() / 5, Image.SCALE_SMOOTH));
        profileImageFriend.setIcon(foto_perfil_scaled);

        //Later we put the name of the user, with a random phrase
        JLabel j = new JLabel(name);
        JLabel j2 = generateRandomPhrase(name);
        groupCharacteristics.add(j);
        groupCharacteristics.add(j2);


        //Then, we put the button to add this friend
        //Initialization of the image
        //Definition of the title
        ImageIcon addFriend = new ImageIcon(getClass().getResource("Images_JSocial/addFriend.png"));
        //We scale the image because it's too big
        ImageIcon addFriend_scaled = new ImageIcon(addFriend.getImage().getScaledInstance(addFriend.getIconWidth()/6, addFriend.getIconHeight() / 6, Image.SCALE_SMOOTH));
        profileImageFriend.setIcon(addFriend_scaled);





        //We put the image in the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        add(profileImageFriend);

        gbc.weighty = 0;
        gbc.gridx = 1;
        add(groupCharacteristics);

        gbc.gridy = 1;
        add(buttonAddFriend);


    }
    private JLabel generateRandomPhrase (String name) {
        double randomNumber = Math.random();
        JLabel j2 = new JLabel();
        if (randomNumber <= 0.2) {
            j2.setText (name + " parece una persona simpatica!!");
        }
        else {
            if (randomNumber <= 0.4) {
                j2.setText (name + " parece ser una persona con un gran talento!!");
            }
            else {
                if (randomNumber <= 0.6) {
                    j2.setText(name + " parece ser una persona con una gran elegancia con el piano!!");
                }
                else {
                    j2.setText(name + " parece ser una persona con un gran gusto musical!!");
                }
            }
        }
        return j2;
    }
}
