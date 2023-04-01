package classes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class tutorial extends Map {
	int x_loc = 0;
	int y_loc = 435;
	int speed = 20;
	int track = 0;
	long start;
	long end;
	long elapsed;
	int count;
	int count1 = 0;
	int check;
	// score streak
	GRect Score_box;
	GLabel box_score, box_streak, box_fail;
	int score = 0, streak = 0, fail;

	Song test = Song.getInstance();
	Timer movement;
	GLabel W, A, S, D;
	GLabel w_label, a_label, s_label, s2_label, d_label;
	GImage bun, ketchup, tofu, tomato;
	GImage belt, background, hCircle;
	GImage[] food = new GImage[] {};
	int xlabel = 150, ylabel = 50;
	ArrayList<GImage> list = new ArrayList<GImage>();

	public void run() {
		playBackgroundNoise();
		belt = new GImage("longconveyor.png", 0, 500);
		background = new GImage("title_screen_bluebackground.jpg", 0, 0);
		hCircle = new GImage("hitcircle.png", 550, 400);

		bun = new GImage("bun.png", x_loc, y_loc);
		ketchup = new GImage("ketchup.png", x_loc, y_loc);
		tofu = new GImage("tofu.png", x_loc, y_loc);
		tomato = new GImage("tomato.png", x_loc, y_loc);
		food = new GImage[] { bun, ketchup, tofu, tomato };
		GImage wasd = new GImage("wasd2.png", 400, 100);
		Score_box = new GRect(0, 0, 100, 100);
		box_score = new GLabel("Score: " + score, 0, 20);
		box_streak = new GLabel("Streak: " + streak, 0, 40);
		box_fail = new GLabel("Fail: ", 0, 60);
		Score_box.setFillColor(Color.white);
		Score_box.setFilled(true);

		String label_font = "Arial-20";
		w_label = new GLabel("Press the correct Key for each item Item (e.g. W for Bun)", xlabel, ylabel);
		a_label = new GLabel("Pressing the correct button starts a streak and gives yout a point", xlabel, ylabel);
		s_label = new GLabel("Pressing the wrong button resets the streak, but you will keep your points", xlabel, ylabel);
		s2_label = new GLabel("3 Strikes and you're out!", xlabel, ylabel + 30);
		d_label = new GLabel("Enjoy the game! press ESC to return to menu", xlabel, ylabel);

		w_label.setColor(Color.white);
		a_label.setColor(Color.white);
		s_label.setColor(Color.white);
		s2_label.setColor(Color.red);
		d_label.setColor(Color.white);

		GLabel[] label_list = new GLabel[] { w_label, a_label, s_label, s2_label, d_label };
		for (int i = 0; i < label_list.length; i++) {
			GLabel label = label_list[i];
			label.setFont(label_font);
		}

		final int MS = 250;

		count = 0;
		check = 1000;
		start = System.currentTimeMillis();
		add(background);
		add(belt);
		add(hCircle);
		add(wasd);
		add(Score_box);
		add(box_score);
		add(box_streak);
		add(box_fail);

		movement = new Timer(MS, this);
		movement.start();
		addKeyListeners();

	}

	public void actionPerformed(ActionEvent e) {

		long change = 0, schange, echange;
		int i = 0;
		for (GImage f : list) {
			f.move(speed, 0);
			if (f.getX() > 600 && count1 == i) {
				label(count1);
				schange = System.currentTimeMillis();
				while (change < 2000) {
					echange = System.currentTimeMillis();
					change = echange - schange;
				}
				count1++;

			}
			i++;
		}
		end = System.currentTimeMillis();
		elapsed = end - start;

		if (elapsed > check && count < 4) {
			list.add(food[count]);

			add(food[count]);
			check += 4000;
			count += 1;
		}
		if (getElementAt(600, 450) != hCircle) {

			remove(getElementAt(600, 450));
			if (score != 3) {
				streak++;
				score++;
				remove(box_streak);
				remove(box_score);
				box_streak = new GLabel("Streak: " + streak, 0, 40);
				box_score = new GLabel("Score: " + score, 0, 20);
				add(box_streak);
				add(box_score);
			} else {
				streak = 0;
				//score = 0;
				fail = 1;
				remove(box_streak);
				remove(box_score);
				remove(box_fail);
				box_streak = new GLabel("Streak: " + streak, 0, 40);
				box_score = new GLabel("Score: " + score, 0, 20);
				box_fail = new GLabel("Fail: " + fail, 0, 60);
				add(box_streak);
				add(box_score);
				add(box_fail);
			}
		}

	}

	public void delay() {
		long s = System.currentTimeMillis();
		long e = System.currentTimeMillis();
		while (e - s < 4000) {
			e = System.currentTimeMillis();
		}

	}

	public void label(int num) {
		

		// W
		if (num == 0) {
			add(w_label);
			// delay();

		}
		// A
		else if (num == 1) {

			w_label.setVisible(false);
			add(a_label);
			delay();

		}
		// S
		else if (num == 2) {

			add(s_label);
			add(s2_label);
			delay();
			a_label.setVisible(false);

		}

		// D
		else if (num == 3) {
			s_label.setVisible(false);
			s2_label.setVisible(false);
			add(d_label);
			delay();
			// test.stopSound("sounds","tutorial.mp3");
			// menu.start();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			speed += 5;
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			if (speed > 1) {
				speed--;
			}

		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			test.stopSound("sounds", "tutorial.mp3");
			menu.start();
		}
	}

	private void playBackgroundNoise() {
		test.playSound("sounds", "tutorial.mp3");
	}

	public static void main(String args[]) {
		new tutorial().start();
	}

}