package Client.Controller.Piano;

import Client.View.Piano.Key;
import Client.View.View;
import com.sun.glass.events.KeyEvent;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PianoController {
	private View view;
	private int actualOctave; //TODO : Revision of this atribute
	private RealtimePlayer realtimePlayer;

	public static void main(String[] args) {
		View view = new View();
		PianoController pianoController = new PianoController(view);
	}

	public PianoController(View view) {
		this.view = view;
		try {
			realtimePlayer = new RealtimePlayer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		initController();
	}

	private void initController() {
		view.initPianoView();
		view.getPianoView().getLeftOption().getNextOctave().addActionListener(e -> {
			if (actualOctave > 0) {
				actualOctave--;
				view.getPianoView().getPiano().goOctave(actualOctave);
			}
		});
		view.getPianoView().getLeftOption().getPrevOctave().addActionListener(e -> {
			if (actualOctave < 6) {
				actualOctave++;
				view.getPianoView().getPiano().goOctave(actualOctave);
			}
		});

		final Boolean[] activado = {false};
		view.getPianoView().getPiano().getKeys().get(0).getIm().put(KeyStroke.getKeyStroke("A"), "A");
		view.getPianoView().getPiano().getKeys().get(0).getIm().put(KeyStroke.getKeyStroke("released A"), "A released");
		view.getPianoView().getPiano().getKeys().get(0).getAm().put("A", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!activado[0]){
					realtimePlayer.startNote(new Note("A"));
					activado[0] = true;
				}
			}
		});

		view.getPianoView().getPiano().getKeys().get(0).getAm().put("A released", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				activado[0] = false;
				realtimePlayer.stopNote(new Note("A"));
			}
		});

	}
}
