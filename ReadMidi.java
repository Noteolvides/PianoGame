import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.Read;

import java.util.Vector;

public class ReadMidi {
    public static void main(String[] args) {
        Score theScore = new Score("Temporary score");
        Read.midi(theScore, "PianoPhase.mid");
        Part phrase = theScore.getPart(0);
        Note[] notes =  phrase.getPhrase(0).getNoteArray();
        System.out.println();
    }
}
