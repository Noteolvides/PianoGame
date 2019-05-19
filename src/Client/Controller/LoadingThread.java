package Client.Controller;

import Client.View.FinestraJSong;

import java.util.ArrayList;

/**
 * This is the responsible class to show the loading bar, at the same time of the list of songs, avoiding with this
 * the sleep of the general view
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class LoadingThread extends Thread{
    FinestraJSong finestraJSong;
    ControllerJSong controllerJSong;

    /**
     * This is the constructor that initializates the Loading thread view
     * @param finestraJSong The reference to the JSong frame
     * @param controllerJSong The controller of JSong
     */
    public LoadingThread (FinestraJSong finestraJSong,ControllerJSong controllerJSong) {
        this.finestraJSong = finestraJSong;
        this.controllerJSong = controllerJSong;
    }

    /**
     * This is the process we we go when the refresh button is clicked, it shows the animation duriong 1500ms
     */
    public void run () {
        finestraJSong.showLoading();
        try {
            Thread.sleep(1500);
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
