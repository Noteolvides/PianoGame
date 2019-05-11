package Client.Controller.MenuPrincipal;

import Client.Controller.Controller;
import Client.View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Client.View.JPrincipal.*;

public class JPrincipalController implements ActionListener {
    private View view;
    private Controller controller;

    public JPrincipalController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        //Obtenim el missatge de comanda d'acció per saber quina ha estat l'acció realitzada
        String action = e.getActionCommand();

        if (action.equals(JPIANO)) {
            controller.networkPiano();
            controller.openPiano();
            controller.closePrincipal();

        } else if (action.equals(JSOCIAL)) {
            controller.networkSocial();
            controller.openSocial();
            controller.closePrincipal();

        } else if (action.equals(SIGN_OUT)) {
            controller.networkSignOut();
            controller.closePrincipal();
            controller.openStart();

        } else if (action.equals(DELETE_ACCOUNT)) {
            System.out.println("SERAS RETRASADO");
            controller.networkDeleteAccount();

        }
    }

}
