package classes;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Map_Database extends Map {
	Level l;
	static ArrayList<Food> food_level1 = new ArrayList<Food>();
	static ArrayList<Food> food_level2 = new ArrayList<Food>();
	static ArrayList<Food> food_level3a = new ArrayList<Food>();
	static ArrayList<Food> food_level3b = new ArrayList<Food>();

	// intialize variable
	Map_Database() {
		initialize();
	}

	FoodType[] list = new FoodType[] { FoodType.KETCHUP, FoodType.TOFU, FoodType.BUN, FoodType.TOMATO };

	public static int getRandomValue(int Min, int Max) {
		return ThreadLocalRandom.current().nextInt(Min, Max + 1);
	}

	public void initialize() {
		int start1 = 5000;

		for (int i = 0; i < 30; i++) {
			Food f = new Food(list[getRandomValue(0, 3)], start1);
			food_level1.add(f);
			start1 += getRandomValue(1000, 4000);
		}

		int start2 = 14000;

		for (int j = 0; j < 30; j++) {
			Food f = new Food(list[getRandomValue(0, 3)], start2);
			food_level2.add(f);
			start2 += getRandomValue(1000, 1500);
		}

		int start3 = 0;

		for (int k = 0; k < 15; k++) {
			Food f = new Food(list[getRandomValue(0, 3)], start3);
			food_level3a.add(f);
			start3 += getRandomValue(3000, 4000);
		}
		start3 = 0;
		for (int k = 0; k < 15; k++) {
			Food f = new Food(list[getRandomValue(0, 3)], start3);
			food_level3b.add(f);
			start3 += getRandomValue(3000, 4000);
		}

	}

	public static Conveyor conveyor1 = new Conveyor(false);
	public static Conveyor conveyor2 = new Conveyor(false);
	public static Conveyor conveyor3 = new Conveyor(true);

	public Song song1 = Song.getInstance();
	public Song song2 = Song.getInstance();
	public Song song3 = Song.getInstance();

	public static HitCircle circle1 = new HitCircle(1);
	public static HitCircle circle2 = new HitCircle(1);
	public static HitCircle circle3 = new HitCircle(2);

	public static Level level1 = new Level("tutorial.mp3", conveyor1, circle1, food_level1, food_level3b);
	public static Level level2 = new Level("level2.mp3", conveyor2, circle2, food_level2, food_level3b);
	public static Level level3 = new Level("level3.mp3", conveyor3, circle3, food_level3a, food_level3b);

	// get level function
	Level get_level1() {
		return level1;
	}

	Level get_level2() {
		return level2;
	}

	Level get_level3() {
		return level3;
	}
	


}
