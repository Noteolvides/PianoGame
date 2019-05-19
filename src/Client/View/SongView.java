package Client.View;

import Client.Controller.ControllerJSong;
import javax.swing.*;
import java.awt.*;

/**
 * Class that formats the display of each song in the list
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class SongView extends JPanel {
    //Atributes of the SongView class
    private JPanel panelPlayInfo;
    private JPanel groupTitleDescription;
    private JLabel titleSong;
    private JLabel description;
    private JLabel author;
    private JLabel musicIcon;
    private JLabel playButton;
    private JLabel infoIcon;
    public SongView (String title, String description, String author) {
        setLayout(new BorderLayout());

        //To obtain the resource directly without the full path
        ImageIcon musicImage = new ImageIcon("img/music_1.png");
        //We scale the image because it's too big
        ImageIcon musicImage_scaled = new ImageIcon(musicImage.getImage().getScaledInstance(musicImage.getIconWidth() / 8, musicImage.getIconHeight() / 8, Image.SCALE_SMOOTH));
        //Initializing the JLabel of music
        musicIcon = new JLabel();
        musicIcon.setIcon(musicImage_scaled);



        //Localizing the image in the project
        ImageIcon infoImage = new ImageIcon("img/info_1.png");
        //We scale the image because it's too big
        ImageIcon infoImage_scaled = new ImageIcon(infoImage.getImage().getScaledInstance(infoImage.getIconWidth() / 24, infoImage.getIconHeight() / 24, Image.SCALE_SMOOTH));
        //Initializing the JLabel of info
        infoIcon = new JLabel();
        infoIcon.setIcon(infoImage_scaled);


        //We make a button to Play the song
        playButton = new JLabel ();
        ImageIcon playImage = new ImageIcon("img/play_button.png");
        //We scale the image because it's too big
        ImageIcon play_scaled = new ImageIcon(playImage.getImage().getScaledInstance(playImage.getIconWidth() / 28, playImage.getIconHeight() / 28, Image.SCALE_SMOOTH));
        playButton.setIcon(play_scaled);
        playButton.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //We create a panel to add the play button and the info button
        panelPlayInfo = new JPanel();
        panelPlayInfo.setLayout(new BoxLayout(panelPlayInfo,BoxLayout.X_AXIS));
        panelPlayInfo.add(playButton);
        panelPlayInfo.add(infoIcon);

        titleSong = new JLabel(title);
        titleSong.setFont(new Font("Sans Serif",Font.BOLD,14));
        this.description = new JLabel (description);
        this.description.setFont(new Font("Sans Serif",Font.PLAIN,10));
        this.author = new JLabel(author);
        this.author.setFont(new Font("Sans Serif",Font.PLAIN,10));

        //We create a panel to add the title and her description
        groupTitleDescription = new JPanel();
        groupTitleDescription.setLayout(new BoxLayout(groupTitleDescription,BoxLayout.Y_AXIS));
        groupTitleDescription.add(titleSong);
        groupTitleDescription.add(this.description);
        groupTitleDescription.add(this.author);
        //We make an empty border to down the group of elements
        groupTitleDescription.setBorder(BorderFactory.createEmptyBorder(7,7,7,7));


        //Adding the elements to the JPanel
        add(musicIcon,BorderLayout.LINE_START);
        add(groupTitleDescription,BorderLayout.CENTER);
        add(panelPlayInfo,BorderLayout.LINE_END);

        //We make a border to separate the songs
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    public JLabel getTitleSong() {
        return titleSong;
    }

    public JLabel getDescription() {
        return description;
    }

    public JLabel getAuthor() {
        return author;
    }

    public JLabel getMusicIcon() {
        return musicIcon;
    }

    public JLabel getPlayButton() {
        return playButton;
    }

    public JLabel getInfoIcon() {
        return infoIcon;
    }

    /**
     * Registers the controller to the buttons of the view
     * @param controllerJSong
     */
    public void registerButtonController (ControllerJSong controllerJSong) {
        playButton.addMouseListener(controllerJSong);
        //Now, we add the tool tip text too
        infoIcon.setToolTipText(description.getText());
    }
}
