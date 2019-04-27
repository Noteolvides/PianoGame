package Server.Controller.BBDD;




import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ControllerProva {
    @Autowired
    private ServiceBBDDServer service;




    //TODO: Comprovar si amistad existe

    public void crearUsuario () {
        try {
            /*if (service.checkUserRelationship("josep","pepe")) {
                Syst.out.println("YEAH BOY!!");
            }*/

            //User user = service.getInstanceOfAUser("josep","roig");
            //Syst.out.println(user.getEmail());
            //List <Song> songs = new ArrayList<Song>();
            //Song s = new Song("Malament", 12, "Versio Catalana cullons",0, "malamene.mp3",user);
            //songs.add(s);
            //service.insertSongFromUser(s);
            //user.setSongs(songs);
            //user.addNewFriend(service.getInstanceOfAUser("pepe","rodolfo"));
            //service.updateInformationUser(user);

            service.deleteUserByName("pepe");

            List <Song> songs = service.getTop5Songs();

            for (int i = 0; i < songs.size();i++) {
                System.out.println(songs.get(i).getTitle());
            }

        } catch (Exception e) {
            System.out.println("Hola, error!!!");
            e.printStackTrace();
        }
    }


    //Getters and setters
    public ServiceBBDDServer getService() {
        return service;
    }

    public void setService(ServiceBBDDServer service) {
        this.service = service;
    }


}
