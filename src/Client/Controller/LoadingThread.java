package Client.Controller;

import Client.View.FinestraJSong;

import java.util.ArrayList;

public class LoadingThread extends Thread{
    FinestraJSong finestraJSong;
    ControllerJSong controllerJSong;
    
    public LoadingThread (FinestraJSong finestraJSong,ControllerJSong controllerJSong) {
        this.finestraJSong = finestraJSong;
        this.controllerJSong = controllerJSong;
    }
    public void run () {
        finestraJSong.showLoading();
        try {
            Thread.sleep(1000);
            //We try to put a similar array (with one new song), to see what happens when touch the refresh
            //finestraJSong.updateSongs(simulationOfArray_2());


            //We update the controllers, because if we update the list we remove all the information of the old songs
            finestraJSong.updateControllersSongs(controllerJSong);
            //Then, we hide the loading bar
            finestraJSong.hideLoading();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
