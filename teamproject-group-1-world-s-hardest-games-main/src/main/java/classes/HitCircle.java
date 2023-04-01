package classes;

import acm.graphics.*;
import acm.program.*;

public class HitCircle extends GraphicsProgram {
	
	Level current;
	HitCircle(Level l){
		this.current = l;
	}
	final int h1 = 550; // will be for HitCircle on large conveyor
	final int w1 = 400; // will be for HitCircle on large conveyornb
	final int h2 = 275; // will be for first HitCircle on smaller conveyor
	final int w2 = 400; // will be for first HitCircle on smaller conveyor
	final int h3 = 425; // will be for second HitCircle on smaller conveyor
	final int w3 = 400; // will be for second HitCircle on smaller conveyor
	public int numHitCircles;
	public int firstx;
	public int y;

	HitCircle(int z) {
		numHitCircles = z;

	}

	public void create_hit_circle() {
	
		// int numHitCircles;
		if (current.getHitCircle().returnNHC() == 1) {
			GImage singleHitCircle = new GImage("hitcircle.png", h1, w1);
			add(singleHitCircle);
		}
		if (current.getHitCircle().returnNHC() == 2) {
			GImage hitCircle1 = new GImage("hitcircle.png", h2, w2);
			add(hitCircle1);
			GImage hitCircle2 = new GImage("hitcircle.png", h3, w3);
			add(hitCircle2);
		}
	}

	public void run() {


	}

	public int returnNHC() {
		return numHitCircles;
	}

}