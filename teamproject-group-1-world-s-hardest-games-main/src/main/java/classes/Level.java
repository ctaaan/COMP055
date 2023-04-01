package classes;

import java.util.ArrayList;

public class Level {

	Conveyor conveyorBelt;
	Song song;
	HitCircle hitCircle;

	String s;
	ArrayList<Food> food_items;
	ArrayList<Food> food_items_3;

	Level(String string, Conveyor currBelt, HitCircle currCircle, ArrayList<Food> list, ArrayList<Food> list2) {

		conveyorBelt = currBelt;
		// song = currSong;
		hitCircle = currCircle;

		s = string;
		food_items = list;
		food_items_3 = list2;

	}

	public ArrayList<Food> getFoodList() {
		return food_items;
	}

	public Conveyor getConveyorBelt() {
		return conveyorBelt;
	}

	public Song getSong() {
		return song;
	}

	public HitCircle getHitCircle() {
		return hitCircle;
	}

	public String get_string() {
		return s;
	}

	public ArrayList<Food> getFoodList3() {
		return food_items_3;
	}

}