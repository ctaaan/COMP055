package classes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import acm.graphics.*;

public class Pass_screen extends Map {
	// private Play_game program;
	// function to identify map
	private Level level;
	private int index;

	public Pass_screen(Level l) {
		this.level = l;
		index = level_arr.indexOf(l);
	}

	private int identifier = 2;

	int get_identifier() {
		return identifier;
	}

	public int levelCount = 0;

	Song test = Song.getInstance();
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "Hurray.mp3" };

	public Pass_screen(Play_game app) {
		super();
	}

	public void stop() {
		test.stopSound(MUSIC_FOLDER, SOUND_FILES[0]);
	}

	public void run() {

		curr_level_num++;

		if (index < 2) {
			level = level_arr.get(index + 1);
		} else {
			level = level_1;
		}
		// game.stop();
		playBackgroundNoise();

		GImage green = new GImage("greenbackground.jpg", 0, 0);
		add(green);

		GImage baby = new GImage("babymeme.png", 525, 290);
		add(baby);

		GImage x = new GImage("GreatWork.png", 70, 200);
		add(x);

		GLabel y = new GLabel("Press Spacebar to move onto the next level!", 0, 80);
		y.setColor(Color.white);
		y.setFont("Arial-40");
		add(y);

		GLabel z = new GLabel("Press Enter to return back to the menu", 0, 180);
		z.setColor(Color.white);
		z.setFont("Arial-40");
		add(z);

		GImage logo = new GImage("World's Hardest Games Logo.png", -25, 475);
		add(logo);
		addKeyListeners();
	}

	@Override
	public void keyPressed(KeyEvent r) {
		int keyCode = r.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER) {
			menu.start();
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			if (curr_level_num == 2) {
				menu.start();
			} else {

				Play_game g = new Play_game(level);
				g.run();
			}
		}
	}

	private void playBackgroundNoise() {
		test.playSound(MUSIC_FOLDER, SOUND_FILES[0], false);
	}

	public static void main(String[] args) {
		new Pass_screen(game).start();
	}

}