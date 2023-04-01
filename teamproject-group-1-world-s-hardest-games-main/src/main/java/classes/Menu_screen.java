package classes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import acm.graphics.*;

public class Menu_screen extends Map {
	// function to identify map

	GLabel x;
	private int identifier = 1;

	int get_identifier() {
		return identifier;
	}

	Song test = Song.getInstance();
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "menu.mp3" };

	private int count;

	public Menu_screen(Play_game app) {
		super();
	}

	public void run() {
		requestFocus();
		playBackgroundNoise();
		current = level_1;

		GImage blue = new GImage("bluemenubackground.jpg", -167, -50);
		add(blue);

		GImage titleshadow = new GImage("smallblacktut!.png", 304, 54);
		add(titleshadow);

		GImage title = new GImage("smalltut!.png", 300, 50);
		add(title);

		GImage Lbutton = new GImage("yellowbutton.png", 350, 160);
		add(Lbutton);
		GImage spacebutton = new GImage("yellowbutton.png", 350, 265);
		add(spacebutton);
		GImage escbutton = new GImage("yellowbutton.png", 350, 370);
		add(escbutton);
		GImage Llabel = new GImage("l=tutorial.png", 415, 182);
		add(Llabel);
		GImage spacelabel = new GImage("space=level1.png", 382, 287);
		add(spacelabel);
		GImage esclabel = new GImage("esc=quit.png", 445, 392);
		add(esclabel);

		GImage logo = new GImage("World's Hardest Games Logo.png", -45, 480);
		add(logo);

		addKeyListeners();
	}

	public void stop() {
		test.stopSound(MUSIC_FOLDER, SOUND_FILES[0]);
	}

	@Override
	public void keyPressed(KeyEvent s) {
		int keyCode = s.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE) {
			// plays first level
			menu.stop();
			game.run();
			count++;
			map_track = 3;
		}
		if (keyCode == KeyEvent.VK_L) {
			// plays the tutorial
			menu.stop();
			tutor.start();
			count++;
			map_track = 1;
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			// quits to title
			menu.stop();
			title.start();
		}
	}

	private void playBackgroundNoise() {
		test.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
}