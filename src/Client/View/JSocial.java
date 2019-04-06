package Client.View;

import Client.Controller.ControllerJSocial;

import javax.swing.*;
import java.awt.*;

public class JSocial extends JPanel {
    //This attribute is the title of the frame
    private JLabel panelTitle;
    //Panel that will contains the text field and the button to search
    private JPanel searchPanel;
    //This is the square t¡where we would search someone by her code
    private JTextField squareSeachFriend;
    //This is attribute would be visible when the user fins someone or when you don't find anyone (it would appear a mesaage)
    private JFriend panelFriend;
    //This is the button to search new friends
    private JLabel searchButton;
    //This is the attribute that contains the search button and the reuslt of the search
    private JPanel searchAndAnswerPanel;
    //This is the button to go back to another frame
    private JLabel backButton;

    public JSocial () {
        //Initialization of the Layout
        setLayout(new FlowLayout(FlowLayout.LEFT));
        panelTitle = new JLabel();
        searchPanel = new JPanel();
        squareSeachFriend = new JTextField();
        backButton = new JLabel ();
        //We define a maximum size for the textfield
        squareSeachFriend.setMaximumSize(new Dimension(400,28));
        //Definition of the title
        ImageIcon titleImage = new ImageIcon("img/facebus.jpg");
        //We scale the image because it's too big
        ImageIcon title_scaled = new ImageIcon(titleImage.getImage().getScaledInstance(400, titleImage.getIconHeight() / 5, Image.SCALE_SMOOTH));
        panelTitle.setIcon(title_scaled);
        panelTitle.setHorizontalAlignment(JLabel.LEFT);


        //Initialization of the searchPanel
        searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.X_AXIS));
        //We define the maximmum length of the text field
        squareSeachFriend.setColumns(10);
        //Make the square rounded to become more attractive
        squareSeachFriend.setBorder(new RoundedBorder(8));

        //Initialization of search button
        searchButton = new JLabel ();
        ImageIcon searchImage = new ImageIcon("img/refresh.png");
        //We scale the image because it's too big
        ImageIcon search_scaled = new ImageIcon(searchImage.getImage().getScaledInstance(searchImage.getIconWidth() / 18, searchImage.getIconHeight() / 18, Image.SCALE_SMOOTH));
        searchButton.setIcon(search_scaled);

        //We add all the necessary elements to the search panel
        searchPanel.add(squareSeachFriend);
        searchPanel.add(searchButton);


        //Unification of the search panel and the friend panel
        searchAndAnswerPanel = new JPanel();
        searchAndAnswerPanel.setLayout(new BorderLayout());
        searchAndAnswerPanel.add(searchPanel,BorderLayout.PAGE_START);
        panelFriend.setVisible(false);


        showUserSearch("Anonymous","usuarioRandom.png",true);


        searchButton = new JLabel ();
        ImageIcon backButton = new ImageIcon("img/back.png");
        //We scale the image because it's too big
        ImageIcon backButton_scaled = new ImageIcon(backButton.getImage().getScaledInstance(backButton.getIconWidth() / 18, backButton.getIconHeight() / 18, Image.SCALE_SMOOTH));
        this.backButton.setIcon(backButton_scaled);
        this.backButton.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        //Added the image title
        add(panelTitle);
        //Added the panel to search persons
        add(searchAndAnswerPanel);
        add(this.backButton);




    }
    public void showUserSearch (String name, String photoFilename,Boolean isFriend) {
        panelFriend = new JFriend (photoFilename,name,isFriend);
        searchAndAnswerPanel.add(panelFriend,BorderLayout.CENTER);
        panelFriend.setVisible(true);
    }
    public void showUserNotFound () {
        panelFriend = new JFriend();
        searchAndAnswerPanel.add(panelFriend,BorderLayout.CENTER);
        panelFriend.setVisible(true);
    }

    public void registerController(ControllerJSocial controllerJSocial) {
        squareSeachFriend.addMouseListener(controllerJSocial);
        searchButton.addMouseListener(controllerJSocial);
        panelFriend.registerController(controllerJSocial);
        backButton.addMouseListener(controllerJSocial);
    }
}
