package classes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.File;

import javax.imageio.*;

import acm.program.*;
import acm.graphics.*;

public class Title_screen extends Map implements KeyListener {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	GImage blue = new GImage("title_screen_bluebackground.jpg");
	// GLabel x = new GLabel("Turnt Up Tofu!", WINDOW_WIDTH/2, 50);
	GLabel y = new GLabel("Press Spacebar to Start", 190, 545);
	private Play_game program;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "menu.mp3" };
	Song test = Song.getInstance();
	// function to identify map
	private int identifier = 0;

	int get_identifier() {
		return identifier;
	}

	public void run() {
		requestFocus();
		add(blue);
		// Title of Game
		System.out.println("title");
		playBackgroundNoise();

		GImage title = new GImage("turntuptofu!.png", 50, 10);
		add(title);

		GImage tofuGuy = new GImage("Burgur Bun Vinyl.png", 200, 140);
		add(tofuGuy);

		GImage logo = new GImage("World's Hardest Games Logo.png", -45, 480);
		add(logo);

		y.setColor(Color.white);
		y.setFont("Arial-40");
		add(y);
		addKeyListeners();
		// area for creating a title screen once we start communicating between classes
		// "Turnt Up Tofu" at the top of the screen
		// Picture of a record player with a spinning burger bun instead of a vinyl at
		// the center of the screen
		// "Press space bar to start!" underneath record player
		// "World's Hardest Games" symbol in the bottom right
	}

	public void stop() {

		test.stopSound(MUSIC_FOLDER, SOUND_FILES[0]);

	}

	@Override
	public void keyPressed(KeyEvent s) {
		int keyCode = s.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE) {
			// takes you to menu screen
			map_track = 1;
			title.stop();
			current = level_1;
			menu.start();

		}
	}

	private void playBackgroundNoise() {

		test.playSound(MUSIC_FOLDER, SOUND_FILES[0], true);
	}

	public static void main(String[] args) {
		Title_screen title = new Title_screen();
		new Title_screen().start();
		// File jpgOriginal = new File("title_screen_bluebackground.jpg");
		// File jpgResized = new File("resized.jpg");
		// resizeImage(jpgOriginal, jpgResized, WINDOW_WIDTH, WINDOW_HEIGHT, "png");
	}
}