package View;

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

    //This attribute includes the information of the user and the button to add it
    private JPanel informationUser;

    public JFriend (String nombreImagen, String name) {
        //Initialization of the Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //We put a size to always have the same
        setSize(400,200);
        //Initialization of the attributes
        profileImageFriend = new JLabel();
        groupCharacteristics = new JPanel();
        buttonAddFriend = new JLabel();
        informationUser = new JPanel();

        groupCharacteristics.setLayout(new BorderLayout());

        //Initialization of the image
        //Definition of the title
        ImageIcon foto_perfil = new ImageIcon(getClass().getResource("Images_JSocial/" + nombreImagen));
        //We scale the image because it's too big
        ImageIcon foto_perfil_scaled = new ImageIcon(foto_perfil.getImage().getScaledInstance(foto_perfil.getIconWidth()/5, foto_perfil.getIconHeight() / 5, Image.SCALE_SMOOTH));
        profileImageFriend.setIcon(foto_perfil_scaled);




        //Then, we put the button to add this friend
        //Initialization of the image
        //Definition of the title
        ImageIcon addFriend = new ImageIcon(getClass().getResource("Images_JSocial/addFriend.png"));
        //We scale the image because it's too big
        ImageIcon addFriend_scaled = new ImageIcon(addFriend.getImage().getScaledInstance(addFriend.getIconWidth()/28, addFriend.getIconHeight() / 28, Image.SCALE_SMOOTH));
        buttonAddFriend.setIcon(addFriend_scaled);

        //Later we put the name of the user, with a random phrase
        JLabel j = new JLabel(name);
        j.setFont(new Font("Gotham",Font.BOLD,20));
        JLabel j2 = generateRandomPhrase(name);
        j2.setFont(new Font ("Sans Serif",Font.PLAIN,10));
        groupCharacteristics.add(j,BorderLayout.PAGE_START);
        groupCharacteristics.add(j2,BorderLayout.CENTER);
        groupCharacteristics.add(buttonAddFriend,BorderLayout.PAGE_END);
        groupCharacteristics.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        gbc.fill = GridBagConstraints.VERTICAL;
        //We put the image in the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(profileImageFriend,gbc);


        gbc.gridx = 1;
        add(groupCharacteristics,gbc);



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
                    j2.setText(name + " parece ser un futuro profesional del piano!");
                }
                else {
                    j2.setText(name + " parece ser una persona con un gran gusto musical!!");
                }
            }
        }
        return j2;
    }
}
