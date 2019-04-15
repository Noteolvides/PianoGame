package Client.Controller.MenuPrincipal;

import Client.Controller.Controller;
import Client.View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Client.View.JPrincipal.*;

public class JPrincipalController implements ActionListener {
    private View view;
    private Controller controller;

    //Main provisional
    public static void main(String[] args) {
        View view = new View();
        //JPrincipalController controller = new JPrincipalController(view);
        //view.getPrincipalView().registerController(controller);
    }

    public JPrincipalController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        //Obtenim el missatge de comanda d'acció per saber quina ha estat l'acció realitzada
        String action = e.getActionCommand();

        if (action.equals(JPIANO)) {
            controller.openPiano();
            controller.closePrincipal();

        } else if (action.equals(JSOCIAL)) {
            /*
            FinestraJSocial fj = new FinestraJSocial();
            ControllerJSocial controllerJSocial = new ControllerJSocial(fj);
            fj.registerController(controllerJSocial);
             */

            controller.openSocial();
            controller.closePrincipal();

        } else if (action.equals(SIGN_OUT)) {
            /*
            view.initStartView();
            StartController controller = new StartController(view);
            view.getStartView().registerController(controller);
            */

            controller.openStart();
            controller.closePrincipal();

        } else if (action.equals(DELETE_ACCOUNT)) {
            System.out.println("SERAS RETRASADO");

        }
    }

}
