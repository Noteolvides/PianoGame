package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.text.AttributedCharacterIterator;

public class SongView extends JPanel {
    //This is the View that allows us to show an icon of the music

    //This is the View that allows us to play a concrete song
    private JButton playButton;
    private JLabel musicIcon;
    private JLabel infoIcon;

    public SongView () {
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        //We inicialitialize the attributes
        playButton = new JButton ();
        playButton.setText("hola");

        //To obtain the resource directly without the full path
        ImageIcon musicImage = new ImageIcon(getClass().getResource("Images/music_1.png"));
        //We scale the image because it's too big
        ImageIcon musicImage_scaled = new ImageIcon(musicImage.getImage().getScaledInstance(musicImage.getIconWidth() / 8, musicImage.getIconHeight() / 8, Image.SCALE_SMOOTH));

        musicIcon = new JLabel();
        musicIcon.setIcon(musicImage_scaled);




        ImageIcon infoImage = new ImageIcon(getClass().getResource("Images/info_1.png"));
        //We scale the image because it's too big
        ImageIcon infoImage_scaled = new ImageIcon(infoImage.getImage().getScaledInstance(infoImage.getIconWidth() / 12, infoImage.getIconHeight() / 12, Image.SCALE_SMOOTH));
        infoIcon = new JLabel();
        infoIcon.setIcon(infoImage_scaled);



        //Adding the elements to the JPanel
        add(playButton);
        add(musicIcon);
        add(infoIcon);

    }
}
