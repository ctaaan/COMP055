package classes;

public enum FoodType {

	BUN, TOMATO, KETCHUP, TOFU;

	public String toString() {
		switch (this) {
		case BUN:
			return "bun";
		case TOMATO:
			return "tomato";
		case KETCHUP:
			return "ketchup";
		case TOFU:
			return "tofu";
		}
		return "n/a";
	}

}
