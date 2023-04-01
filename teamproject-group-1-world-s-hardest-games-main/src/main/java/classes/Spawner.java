package classes;

import acm.program.*;

import java.util.ArrayList;
import java.util.Scanner;

import acm.graphics.*;

public class Spawner extends Map {

	long start;
	int l_count = 0;
	int r_count = 0;
	ArrayList<GImage> left_images = new ArrayList<GImage>();
	ArrayList<GImage> right_images = new ArrayList<GImage>();
	Level current;

	public Spawner(Level level, long s, int left_count, int right_count, ArrayList<GImage> l_images,
			ArrayList<GImage> r_images) {
		this.start = s;
		this.l_count = left_count;
		this.r_count = right_count;
		this.left_images = l_images;
		this.right_images = r_images;
		this.current = level;

	}

	public void setGame(Play_game game) {
		this.game = game;
	}

	Boolean hardMode;
	int spawnerX;
	int spawnerY;

	void spawn_food_left() {
		long end = System.currentTimeMillis();
		int i = 0;
		ArrayList<Food> items = current.getFoodList();

		for (Food f : items) {
			long elapsed = end - start;

			if (elapsed > f.getDuration() && l_count == i) {
				GImage image = creates_new_image(f);
				add(image);
				left_images.add(image);
				l_count++;

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

			if (elapsed > f.getDuration() && r_count == i) {
				GImage image = creates_new_image(f);
				add(image);
				right_images.add(image);
				r_count++;

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
		}
		if (type == FoodType.KETCHUP) {
			item = new GImage("ketchup.png", x, y);

		}
		if (type == FoodType.TOFU) {
			item = new GImage("tofu.png", x, y);

		}
		if (type == FoodType.TOMATO) {
			item = new GImage("tomato.png", x, y);
		}
		return item;
	}

	public void run() {

	}

	public static void main(String args[]) {

		// new Spawner().start();

	}
}