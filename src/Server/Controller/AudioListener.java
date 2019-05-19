package Server.Controller;


import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Class that complements with PlaySong Class
 * It determines whether a song should be played
 * or stopped
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
public class AudioListener implements LineListener {
    private boolean done = false;

    /**
     * Determines the line of the song that is currently playing
     * After the last line it stops the song
     * @param event: Line fo the song
     */
    @Override
    public synchronized void update(LineEvent event) {
        LineEvent.Type eventType = event.getType();
        if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
            done = true;
            notifyAll();
        }
    }

    /**
     * Waits until the song is finished
     * @throws InterruptedException
     */
    public synchronized void waitUntilDone() throws InterruptedException {
        while (!done) { wait(); }
    }
}
