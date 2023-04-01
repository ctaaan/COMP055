package classes;
//The main screen where everything is displayed

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import acm.graphics.GImage;

public class Play_game extends Map implements ActionListener {
	private Level level;

	Play_game(Level l) {
		this.level = l;
	}

	private Timer spawnerTimer;
	// screen windows
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	// title screen background music
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "level1.mp3", "level2.mp3", "level3.mp3", "tutorial.mp3" };

	Conveyor belt;
	private int count;
	private Spawner spawner;
	Map_graphics map_graphics;

	public void run() {

		spawnerTimer = new Timer(500, this);
		// spawnerTimer.start();

		map_graphics = new Map_graphics(level);
		map_graphics.map_track = 3;
		map_graphics.start();

		// belt = map_graphics.current.getConveyorBelt();
		playBackgroundNoise();

		// spawner.setGame(map_graphics.game);
	}

	public void add(GImage img) {
		map_graphics.add(img);
	}

	private void playBackgroundNoise() {
		Song test = Song.getInstance();
		test.playSound(MUSIC_FOLDER, level.get_string());
	}

	public void stopMusic() {
		Song test = Song.getInstance();
		test.stopSound(MUSIC_FOLDER, level.get_string());
	}
}