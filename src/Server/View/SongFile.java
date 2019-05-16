package Server.View;

import Server.Controller.Gestor.GestorController;

import javax.swing.*;
import java.awt.*;


public class SongFile extends JPanel {
    //Atributes of the SongFile class
    private JPanel panelPlayInfo;
    private JPanel groupTitleAuthor;
    private JLabel titleSong;
    private JLabel description;
    private JLabel author;
    private JLabel musicIcon;
    private JLabel deleteButton;
    private JLabel infoIcon;
    private JLabel privacity; //true -> private
                              //false -> public
    //TODO: Afegir author

    public SongFile(String title, String description, String author, Boolean privacity) {
        setLayout(new BorderLayout());

        //To obtain the resource directly without the full path
        ImageIcon musicImage = new ImageIcon("img/musicFile_icon.png");
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


        //We make a button to Delete the song
        deleteButton = new JLabel ();
        ImageIcon deleteImage = new ImageIcon("img/delete_button.png");
        //We scale the image because it's too big
        ImageIcon delete_scaled = new ImageIcon(deleteImage.getImage().getScaledInstance(deleteImage.getIconWidth() / 28, deleteImage.getIconHeight() / 28, Image.SCALE_SMOOTH));
        deleteButton.setIcon(delete_scaled);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //We create a panel to add the play button and the info button
        panelPlayInfo = new JPanel();
        panelPlayInfo.setLayout(new BoxLayout(panelPlayInfo,BoxLayout.X_AXIS));
        panelPlayInfo.add(deleteButton);
        panelPlayInfo.add(infoIcon);

        titleSong = new JLabel(title);
        titleSong.setFont(new Font("Sans Serif", Font.BOLD,14));
        this.description = new JLabel (description);
        this.description.setFont(new Font("Sans Serif", Font.PLAIN,10));
        this.privacity = new JLabel("[" + privacity + "]");
        this.privacity.setFont(new Font("Sans Serif", Font.PLAIN,10));


        //We create a panel to add the title and her description
        groupTitleAuthor = new JPanel();
        groupTitleAuthor.setLayout(new BoxLayout(groupTitleAuthor, BoxLayout.Y_AXIS));
        groupTitleAuthor.add(titleSong);
        this.author = new JLabel(author);
        groupTitleAuthor.add(this.author);
        groupTitleAuthor.add(this.privacity);
        //We make an empty border to down the group of elements
        groupTitleAuthor.setBorder(BorderFactory.createEmptyBorder(7,7,7,7));


        //Adding the elements to the JPanel
        add(musicIcon,BorderLayout.LINE_START);
        add(groupTitleAuthor,BorderLayout.CENTER);
        add(panelPlayInfo,BorderLayout.LINE_END);

        //We make a border to separate the songs
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    public void registerController(GestorController gc) {
        deleteButton.addMouseListener(gc);
        infoIcon.setToolTipText(description.getText());
    }

    public JPanel getPanelPlayInfo() {
        return panelPlayInfo;
    }

    public void setPanelPlayInfo(JPanel panelPlayInfo) {
        this.panelPlayInfo = panelPlayInfo;
    }

    public JPanel getGroupTitleAuthor() {
        return groupTitleAuthor;
    }

    public void setGroupTitleAuthor(JPanel groupTitleDescription) {
        this.groupTitleAuthor = groupTitleDescription;
    }

    public JLabel getTitleSong() {
        return titleSong;
    }

    public void setTitleSong(JLabel titleSong) {
        this.titleSong = titleSong;
    }

    public JLabel getDescription() {
        return description;
    }

    public void setDescription(JLabel description) {
        this.description = description;
    }

    public JLabel getMusicIcon() {
        return musicIcon;
    }

    public void setMusicIcon(JLabel musicIcon) {
        this.musicIcon = musicIcon;
    }

    public JLabel getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JLabel deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JLabel getInfoIcon() {
        return infoIcon;
    }

    public void setInfoIcon(JLabel infoIcon) {
        this.infoIcon = infoIcon;
    }

    public JLabel getPrivacity() {
        return privacity;
    }

    public void setPrivacity(JLabel privacity) {
        this.privacity = privacity;
    }
}
