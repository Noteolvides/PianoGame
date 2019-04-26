package Server.View;

import javax.swing.*;
import java.awt.*;


public class SongView extends JPanel {
    //Atributes of the SongView class
    private JPanel panelPlayInfo;
    private JPanel groupTitleDescription;
    private JLabel titleSong;
    private JLabel description;
    private JLabel musicIcon;
    private JLabel playButton;
    private JLabel infoIcon;
    private boolean playing = false;

    public SongView (int idSong, String title, String description) {
        setLayout(new BorderLayout());

        //To obtain the resource directly without the full path
        switch (idSong){
            case 1:
                ImageIcon musicImage = new ImageIcon("img/1.png");
                ImageIcon musicImage_scaled = new ImageIcon(musicImage.getImage().getScaledInstance(musicImage.getIconWidth() / 8, musicImage.getIconHeight() / 8, Image.SCALE_SMOOTH));
                musicIcon = new JLabel();
                musicIcon.setIcon(musicImage_scaled);
                break;
            case 2:
                ImageIcon musicImage2 = new ImageIcon("img/2.png");
                ImageIcon musicImage_scaled2 = new ImageIcon(musicImage2.getImage().getScaledInstance(musicImage2.getIconWidth() / 8, musicImage2.getIconHeight() / 8, Image.SCALE_SMOOTH));
                musicIcon = new JLabel();
                musicIcon.setIcon(musicImage_scaled2);
                break;
            case 3:
                ImageIcon musicImage3 = new ImageIcon("img/3.png");
                ImageIcon musicImage_scaled3 = new ImageIcon(musicImage3.getImage().getScaledInstance(musicImage3.getIconWidth() / 8, musicImage3.getIconHeight() / 8, Image.SCALE_SMOOTH));
                musicIcon = new JLabel();
                musicIcon.setIcon(musicImage_scaled3);
                break;
            case 4:
                ImageIcon musicImage4 = new ImageIcon("img/4.png");
                ImageIcon musicImage_scaled4 = new ImageIcon(musicImage4.getImage().getScaledInstance(musicImage4.getIconWidth() / 8, musicImage4.getIconHeight() / 8, Image.SCALE_SMOOTH));
                musicIcon = new JLabel();
                musicIcon.setIcon(musicImage_scaled4);
                break;
            case 5:
                ImageIcon musicImage5 = new ImageIcon("img/5.png");
                ImageIcon musicImage_scaled5 = new ImageIcon(musicImage5.getImage().getScaledInstance(musicImage5.getIconWidth() / 8, musicImage5.getIconHeight() / 8, Image.SCALE_SMOOTH));
                musicIcon = new JLabel();
                musicIcon.setIcon(musicImage_scaled5);
                break;
        }




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

        //We create a panel to add the title and her description
        groupTitleDescription = new JPanel();
        groupTitleDescription.setLayout(new BoxLayout(groupTitleDescription,BoxLayout.Y_AXIS));
        groupTitleDescription.add(titleSong);
        groupTitleDescription.add(this.description);
        //We make an empty border to down the group of elements
        groupTitleDescription.setBorder(BorderFactory.createEmptyBorder(7,7,7,7));


        //Adding the elements to the JPanel
        add(musicIcon,BorderLayout.LINE_START);
        add(groupTitleDescription,BorderLayout.CENTER);
        add(panelPlayInfo,BorderLayout.LINE_END);

        //We make a border to separate the songs
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    public JLabel getMusicIcon() {
        return musicIcon;
    }

    public JLabel getInfoIcon() {
        return infoIcon;
    }

    public JLabel getPlayButton() {
        return playButton;
    }

    public JLabel getTitleSong() {
        return titleSong;
    }

    public JLabel getDescription() {
        return description;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
