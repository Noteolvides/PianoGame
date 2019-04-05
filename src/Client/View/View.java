package Client.View;

import Client.View.*;
import Client.View.Piano.JPiano;

public class View {
    private JPiano pianoView;
    private JFriend friendsView;
    private JPrincipal principalView;
    private JRegister registerView;
    private JSocial socialView;
    private JStart startView;

    public JPiano getPianoView() {
        return pianoView;
    }

    public JFriend getFriendsView() {
        return friendsView;
    }

    public JPrincipal getPrincipalView() {
        return principalView;
    }

    public JRegister getRegisterView() {
        return registerView;
    }

    public JSocial getSocialView() {
        return socialView;
    }

    public JStart getStartView() {
        return startView;
    }
}
