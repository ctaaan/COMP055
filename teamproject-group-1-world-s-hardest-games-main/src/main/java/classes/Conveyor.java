package classes;

import java.awt.event.KeyListener;

import acm.graphics.*;
import acm.program.*;

public class Conveyor extends GraphicsProgram implements KeyListener {
	public int length;
	public int noteSpeed;
	public boolean hardMode;
	int numConveyors;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int x1 = 0; // still need to initialize these
	public static final int y1 = 500;
	public static final int x2 = 400;
	public static final int y2 = 500;

	Conveyor(boolean currHard) {
		numConveyors = 1;
		if (currHard == true) {
			numConveyors = 2;
		}
	}

	public int getNumConveyors() {
		return numConveyors;
	}

	public void run() {
		if (numConveyors == 1) {
			GImage singleConveyor = new GImage("longconveyor.png", x1, y1);
			add(singleConveyor);
		}

		if (numConveyors == 2) {
			GImage conveyor1 = new GImage("conveyor2.png", x1, y1);
			add(conveyor1);

			GImage conveyor2 = new GImage("conveyor2.png", x2, y2);
			add(conveyor2);
		}

	}

	public static void main(String[] args) {
		new Conveyor(false).start();
	}
	// resizing?

}