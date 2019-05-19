package Client.View;

import Client.Controller.ControllerJSocial;
import javax.swing.*;
import java.awt.*;

/**
 * Class that creates the Social Panel, containing the friends.
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
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

    /**
     * Constructor, it creates and adds all the jComponents and adds them to a jPanel.
     */
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
        ImageIcon searchImage = new ImageIcon("img/search.png");
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

        showStartUserSearch();

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

    /**
     * Function that adds and shows a welcoming panel to the view..
     */
    public void showStartUserSearch() {
        //Text to display when the screen is first run
        panelFriend = new JFriend("Bienvenido al panel Social!! Introduce un usuario a buscar :)");
        searchAndAnswerPanel.add(panelFriend,BorderLayout.CENTER);
    }

    /**
     * Function that adds and shows a panel of the found user to the view.
     * @param name Name of the found user.
     * @param photoFilename Name of the file where the photo of the found user is located.
     * @param isFriend Boolean that states if the found user is your friend.
     */
    public void showUserSearch (String name, String photoFilename,Boolean isFriend) {
        //We have to reinitialize the panel to display the new information (the information of the user)
        panelFriend = new JFriend (name,photoFilename,isFriend);
        panelFriend.setVisible(true);
        //We have to remove all the old information and to add the information that didn't change
        createAndRemoveSearchAndAnswer();
        //We repaint the panel with the new information
        repaintPanelFriend();
    }

    /**
     * Function that adds and shows a panel informing that the user wasn't found.
     */
    public void showUserNotFound () {
        //We have to reinitialize the panel to display the new information, in this case we only show the error message
        panelFriend = new JFriend();
        panelFriend.setVisible(true);
        //We have to remove all the old information and to add the information that didn't change
        createAndRemoveSearchAndAnswer();
        //We repaint the panel with the new information
        repaintPanelFriend();
    }

    /**
     * Function that updates things showed in the panel.
     */
    public void repaintPanelFriend () {
        searchAndAnswerPanel.add(panelFriend,BorderLayout.CENTER);
        add(searchAndAnswerPanel);
        add(backButton);
        //We remove the first back button, because otherwise it is higher than what we are interested in
        remove(1);
        revalidate();
        repaint();
    }

    /**
     * Function that resets the contents of the view.
     */
    public void createAndRemoveSearchAndAnswer () {
        //We remove the search bar and the JFriedn's panel
        searchAndAnswerPanel.removeAll();
        //So, we have to recolocate the old seacrh bar
        searchAndAnswerPanel = new JPanel();
        searchAndAnswerPanel.setLayout(new BorderLayout());
        searchAndAnswerPanel.add(searchPanel,BorderLayout.PAGE_START);
    }

    /**
     * Function that assings a controller to the components of the view.
     * @param controllerJSocial Controller of the social view.
     */
    public void registerController(ControllerJSocial controllerJSocial) {
        //We have to register all the controllers in  our buttons
        squareSeachFriend.addMouseListener(controllerJSocial);
        searchButton.addMouseListener(controllerJSocial);
        //We make this conditional to know if this button exists (because if it doesn't exists it will occur an exception)
        if (panelFriend != null) {
            registerControllerAddFriend(controllerJSocial);
        }
        registerControllerBackButton (controllerJSocial);
    }

    /**
     * Function that assigns a controller to the specific found friend add button.
     * @param controllerJSocial Controller of the Social view.
     */
    public void registerControllerAddFriend (ControllerJSocial controllerJSocial) {
        panelFriend.registerController(controllerJSocial);
    }

    /**
     * Function that assigns a controller to the back button.
     * @param controllerJSocial Controller of the Social view.
     */
    public void registerControllerBackButton (ControllerJSocial controllerJSocial) {
        backButton.addMouseListener(controllerJSocial);
    }

    public JLabel getSearchButton() {
        return searchButton;
    }

    public String getSearchUser() {
        return squareSeachFriend.getText();
    }

    public JTextField getSquareSeachFriend() {
        return squareSeachFriend;
    }

    public JLabel getBackButton() {
        return backButton;
    }

    public JFriend getPanelFriend() {
        return panelFriend;
    }

    public void deleteText() {
        //We don't put anything in the textfield text
        squareSeachFriend.setText("");
    }
    public void closePanel () {
        setVisible(false);
    }
}
