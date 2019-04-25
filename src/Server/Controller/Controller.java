package Server.Controller;

import Server.View.JEvolution;
import Server.View.JTop;
import Server.View.View;

public class Controller {
    private JEvolutionController jEvolutionController;
    private JTopController jTopController;
    private RegisterController registerController;

    public Controller(JEvolutionController jEvolutionController, JTopController jTopController, RegisterController registerController){
        this.jEvolutionController = jEvolutionController;
        this.jTopController = jTopController;
        this.registerController = registerController;
    }

    public void actionManager(JEvolution jEvolution, JTop jTop, View view){
        jEvolution.registerController(jEvolutionController);
        jTop.registerController(jTopController);
        view.getRegisterView().registerController(registerController);
    }
}
