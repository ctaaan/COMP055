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
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import edu.pacific.comp55.starter.GraphicsApplication;

public class Map_graphics extends Map implements KeyListener {
	private Level current;

	public Map_graphics(Level c) {
		this.current = c;
	}

	// Extracts From Main Map database
	static Map_Database data = new Map_Database();
	// Creates Default Graphics
	static default_graphics dfg = new default_graphics();

	GraphicsApplication app = this;

	// Makes images
	GImage singleHitCircle;
	GImage hitCircle1;
	GImage hitCircle2;
	long start, end;
	int speed = 8;
	int count_left = 0;
	int count_right = 0;

	int index_right;
	int index_left;
	GImage overall_delete;
	GImage overall_delete1;

	// Creates List of Arrays
	ArrayList<GImage> spawned_list = new ArrayList<GImage>();
	ArrayList<GImage> spawned_list_right = new ArrayList<GImage>();
	ArrayList<GImage> passed_hit_circle = new ArrayList<GImage>();
	ArrayList<String> food_images = new ArrayList<String>();
	ArrayList<String> food_images_right = new ArrayList<String>();

	// Creates Score Box
	GRect score_streak;
	GLabel score1;
	GLabel streak1;
	GLabel fail1;
	GImage wasd = new GImage("wasd2.png", 200, 0);
	int fail_x = 0;
	Timer score_streak_graphic;

	// Spawners
	Spawner spawner = new Spawner(current, start, count_left, count_right, spawned_list, spawned_list_right);

	public void run() {
		// change speed
		speed = dfg.change_speed(current);
		start = System.currentTimeMillis();
		requestFocus();
		addKeyListeners();
		// play game background
		GImage blue = new GImage("title_screen_bluebackground.jpg", 0, 0);
		add(blue);

		// DJ
		GImage dj = new GImage("DJ 1.png", 270, 285);
		add(dj);

		// Score Streak box
		create_score_box();

		// Conveyor
		create_conveyor();

		// Hit Circle
		create_hit_circle();

		// Adds team Logo
		GImage logo = new GImage("World's Hardest Games Logo.png", 680, -20);
		logo.sendToFront();
		add(logo);

	}

	@Override

	public void keyPressed(KeyEvent e) {

		// To Leave the Game
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			box.reset_fail();
			dfg.stopMusic();
			reset();
			menu.start();
		}

		// Press W when a bun hits the middle of a hit circle(s)

		if (key == KeyEvent.VK_W) {
			if (current == level_3) {
				if (check_left() == "bun") {
					left_is_Hit();
				}
				if (check_right() == "bun") {
					right_is_Hit();
				}

			} else {
				if (check() == "bun") {
					left_is_Hit();

				}
			}

		}

		// Press A when a tofu hits the middle of a hit circle(s)
		if (key == KeyEvent.VK_A) {
			if (current == level_3) {
				if (check_left() == "tofu") {
					left_is_Hit();
				}
				if (check_right() == "tofu") {
					right_is_Hit();
				}

			} else {
				if (check() == "tofu") {
					left_is_Hit();

				}
			}

		}

		// Press S when a ketchup hits the middle of a hit circle(s)
		if (key == KeyEvent.VK_S) {
			if (current == level_3) {
				if (check_left() == "ketchup") {
					left_is_Hit();
				}
				if (check_right() == "ketchup") {
					right_is_Hit();
				}

			} else {
				if (check() == "ketchup") {
					left_is_Hit();

				}
			}

		}

		// Press D when a tomato hits the middle of a hit circle(s)
		if (key == KeyEvent.VK_D) {
			if (current == level_3) {
				if (check_left() == "tomato") {
					left_is_Hit();
				}
				if (check_right() == "tomato") {
					right_is_Hit();
				}

			} else {
				if (check() == "tomato") {
					left_is_Hit();

				}
			}

		}

		// To Check if Player Fails
		if (failed(box)) {
			box.reset_fail();
			dfg.stopMusic();
			reset();
			Fail_screen f = new Fail_screen(current);
			f.start();

		}

		// To Check if Player Passes
		if (passed(box.get_score())) {
			box.reset_fail();
			dfg.stopMusic();
			reset();
			Pass_screen p = new Pass_screen(current);
			p.start();
		}

	}

//Background

	public void create_conveyor() {
		final int x1 = 0;
		final int y1 = 500;
		final int x2 = 400;
		final int y2 = 500;
		if (current.getConveyorBelt().getNumConveyors() == 1) {
			GImage singleConveyor = new GImage("longconveyor.png", x1, y1);
			add(singleConveyor);
		}

		if (current.getConveyorBelt().getNumConveyors() == 2) {
			GImage conveyor1 = new GImage("shortconveyor.png", x1, y1);
			add(conveyor1);

			GImage conveyor2 = new GImage("shortconveyor.png", x2, y2);
			add(conveyor2);
		}
	}

	public void create_hit_circle() {
		final int h1 = 550; // will be for HitCircle on large conveyor
		final int w1 = 400; // will be for HitCircle on large conveyor
		final int h2 = 275; // will be for first HitCircle on smaller conveyor
		final int w2 = 400; // will be for first HitCircle on smaller conveyor
		final int h3 = 425; // will be for second HitCircle on smaller conveyor
		final int w3 = 400; // will be for second HitCircle on smaller conveyor
		// int numHitCircles;
		if (current.getHitCircle().returnNHC() == 1) {
			singleHitCircle = new GImage("hitcircle.png", h1, w1);
			add(singleHitCircle);
		}
		if (current.getHitCircle().returnNHC() == 2) {
			hitCircle1 = new GImage("hitcircle.png", h2, w2);
			add(hitCircle1);
			hitCircle2 = new GImage("hitcircle.png", h3, w3);
			add(hitCircle2);
		}
	}

	public void create_score_box() {
		int score_streak_SIZE_x = 100;
		int score_streak_SIZE_y = 100;
		int score_streak_loc_x = 0;
		int score_streak_loc_y = 0;
		int score_streak_ms = 50;
		GRect score_streak = new GRect(score_streak_loc_x, score_streak_loc_y, score_streak_SIZE_x,
				score_streak_SIZE_y);
		score_streak.setFillColor(Color.white);
		score_streak.setFilled(true);

		score_streak_graphic = new Timer(score_streak_ms, this);
		add(score_streak);
		score1 = new GLabel("Score: " + box.get_score(), 0, 20);
		streak1 = new GLabel("Streak: " + box.get_streak(), 0, 40);
		fail1 = new GLabel("Fail: ", 0, 60);
		add(fail1);

		score_streak_graphic.start();
		add(score1);
		add(streak1);
		add(wasd);
	}

	public void reset() {
		overall_delete = null;
		spawned_list = new ArrayList<GImage>();
		passed_hit_circle = new ArrayList<GImage>();
		food_images = new ArrayList<String>();
		overall_delete1 = null;
		spawned_list_right = new ArrayList<GImage>();
		food_images_right = new ArrayList<String>();
		score_streak_graphic.stop();
	}

	boolean failed(Score_streak current) {
		// activates when buttons are pressed
		// looks at current map --> fail screen
		if (box.get_failCount() + passed_hit_circle.size() >= 3) {
			box.reset_fail();
			return true;
		}
		return false;
	}

	boolean passed(int score) {

		if (score == 15) {
			return true;
		}
		return false;

	}

	// Spawner
	void spawn_food() {
		long end = System.currentTimeMillis();
		int i = 0;
		ArrayList<Food> items = current.getFoodList();

		for (Food f : items) {
			long elapsed = end - start;

			if (elapsed > f.getDuration() && count_left == i) {
				GImage image = creates_new_image(f);
				add(image);
				spawned_list.add(image);
				count_left++;

			}
			i++;
		}
	}

	void spawn_food2() {
		long end = System.currentTimeMillis();
		int i = 0;
		ArrayList<Food> items = current.getFoodList3();

		for (Food f : items) {
			long elapsed = end - start;

			if (elapsed > f.getDuration() && count_right == i) {
				GImage image = creates_new_image_right(f);
				add(image);
				spawned_list_right.add(image);
				count_right++;

			}
			i++;
		}
	}

	GImage creates_new_image(Food food) {
		GImage item = null;
		int x = 0;
		int y = 435;
		FoodType type = food.getFoodType();
		if (type == FoodType.BUN) {
			item = new GImage("bun.png", x, y);
			food_images.add("bun");
		}
		if (type == FoodType.KETCHUP) {
			item = new GImage("ketchup.png", x, y);
			food_images.add("ketchup");

		}
		if (type == FoodType.TOFU) {
			item = new GImage("tofu.png", x, y);
			food_images.add("tofu");

		}
		if (type == FoodType.TOMATO) {
			item = new GImage("tomato.png", x, y);
			food_images.add("tomato");
		}
		return item;
	}

	GImage creates_new_image_right(Food food) {

		GImage item = null;
		int x = 700;
		int y = 435;
		FoodType type = food.getFoodType();
		if (type == FoodType.BUN) {
			item = new GImage("bun.png", x, y);
			food_images_right.add("bun");
		}
		if (type == FoodType.KETCHUP) {
			item = new GImage("ketchup.png", x, y);
			food_images_right.add("ketchup");

		}
		if (type == FoodType.TOFU) {
			item = new GImage("tofu.png", x, y);
			food_images_right.add("tofu");

		}
		if (type == FoodType.TOMATO) {
			item = new GImage("tomato.png", x, y);
			food_images_right.add("tomato");
		}
		return item;
	}

	String check_left() {
		int i = 0;

		for (GImage f : spawned_list) {
			if (f.getX() > 270 && f.getX() < 340) {
				String str = food_images.get(i);
				overall_delete = f;
				index_left = i;
				return str;
			}
			i++;
		}
		return "none";
	}

	String check_right() {
		int i = 0;
		for (GImage f : spawned_list_right) {
			if (f.getX() > 425 && f.getX() < 485) {

				String str = food_images_right.get(i);
				overall_delete1 = f;
				index_right = i;
				return str;
			}

		}
		return "none";
	}

	String check() {
		int i = 0;
		for (GImage f : spawned_list) {
			if (f.getX() > 575 && f.getX() < 625) {
				String str = food_images.get(i);
				overall_delete = f;
				index_left = i;
				return str;
			}
			i++;
		}

		return "nope";

	}

	public void actionPerformed(ActionEvent e) {

		int score_streak_SIZE_x = 100;
		int score_streak_SIZE_y = 100;
		int score_streak_loc_x = 0;
		int score_streak_loc_y = 0;
		score_streak = new GRect(score_streak_loc_x, score_streak_loc_y, score_streak_SIZE_x, score_streak_SIZE_y);
		score1.setLabel("Score: " + box.get_score());
		streak1.setLabel("Streak: " + box.get_streak());
		int fail_x = 0;
		int total = box.get_failCount() + passed_hit_circle.size();
		fail_x += 25;
		if (total == 1) {
			GLabel x1 = new GLabel("X ", fail_x, 60);
			add(x1);
		}
		fail_x += 10;
		if (total == 2) {
			GLabel x2 = new GLabel("X ", fail_x, 60);
			add(x2);
		}
		fail_x += 10;
		if (total == 3) {
			GLabel x3 = new GLabel("X ", fail_x, 60);
			add(x3);
		}

		if (current == level_3) {
			spawn_food2();
		}
		spawn_food();

		int count = 0;
		ArrayList<GImage> new_right = spawned_list_right;
		if (current == level_3) {
			for (int j = 0; j < new_right.size(); j++) {
				GImage i = new_right.get(j);
				i.move(-speed, 0);
				if (i.getX() < 450) {

					spawned_list_right.remove(i);
					food_images_right.remove(count);
					passed_hit_circle.add(i);
					remove(getElementAt((i.getX()), (i.getY())));
					box.reset_streak();
					if (failed(box)) {
						dfg.stopMusic();
						box.reset();
						reset();
						Fail_screen f = new Fail_screen(current);
						f.start();
						score_streak_graphic.stop();
					}
				}
				count++;
			}
			count = 0;
			ArrayList<GImage> new_list = spawned_list;

			for (int j = 0; j < new_list.size(); j++) {
				GImage i = new_list.get(j);
				i.move(speed, 0);
				if (i.getX() > 325) {
					new_list.remove(i);
					food_images.remove(count);
					passed_hit_circle.add(i);

					remove(getElementAt((i.getX()), (i.getY())));
					box.reset_streak();
					if (failed(box)) {
						dfg.stopMusic();
						box.reset();
						reset();
						Fail_screen f = new Fail_screen(current);
						f.start();
						score_streak_graphic.stop();
					}
				}
				count++;
			}

		} else {
			count = 0;
			ArrayList<GImage> new_list = spawned_list;

			for (int j = 0; j < new_list.size(); j++) {
				GImage i = new_list.get(j);
				i.move(speed, 0);
				if (i.getX() > 625) {
					new_list.remove(i);
					food_images.remove(count);
					passed_hit_circle.add(i);

					remove(getElementAt((i.getX()), (i.getY())));
					box.reset_streak();
					if (failed(box)) {
						dfg.stopMusic();
						box.reset();
						reset();
						Fail_screen f = new Fail_screen(current);
						f.start();
						score_streak_graphic.stop();
					}
				}
				count++;
			}
		}

	}

	void left_is_Hit() {
		spawned_list.remove(overall_delete);
		food_images.remove(index_left);
		remove(getElementAt(overall_delete.getX(), overall_delete.getY()));
		box.incrementScore();
		box.incrementStreak();
	}

	void right_is_Hit() {
		spawned_list_right.remove(overall_delete1);
		food_images_right.remove(index_right);
		remove(getElementAt(overall_delete1.getX(), overall_delete1.getY()));
		box.incrementScore();
		box.incrementStreak();
	}

}