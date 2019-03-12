import jm.JMC;
import jm.constants.ProgramChanges;
import jm.music.data.*;
import jm.util.Write;

public final class WriteMidi implements JMC {

    public static void main(String[] args) {
        Score score = new Score();
        Part part = new Part("Piano1", ProgramChanges.PIANO,0);
        Phrase phrase1 = new Phrase();
        for (int i = 0; i <50 ; i++) {
            phrase1.addNote(new Note((int)(Math.random() * 120 + 1),MP));
        }
        part.addPhrase(phrase1);
        score.add(part);
        Write.midi(score, "PianoPhase.mid");
    }
}