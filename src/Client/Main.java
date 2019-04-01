package Client;

import Client.View.JGestor;
import Client.View.JSong;
import Client.View.Prueba;

public class Main {
    public static void main(String[] args) {
        Prueba controller = new Prueba();
        JGestor view = new JGestor(controller.simulationOfController());
    }
}
