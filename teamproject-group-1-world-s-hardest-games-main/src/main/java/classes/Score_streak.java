package classes;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score_streak extends GraphicsProgram {

	private int streak = 0; // initializes streak to start at 0

	private int score = 0; // user start at 0 points
	private int failCount = 0; // user starts at 0 fail count

	// accessor
	int get_score() {
		return score;
	}

	int get_streak() {
		return streak;
	}

	int get_failCount() {
		return failCount;
	}

	// increments score by one for each successive combo
	void incrementScore() {
		score++;
	}

	// add to fail count
	void incrementFail() {
		failCount++;
	}

	// reset fail count
	void reset_fail() {
		failCount = 0;
	}

	// increments streak by one for each successive combo
	void incrementStreak() {
		streak++;
	}

	// score becomes 0 each level
	void reset_score() {
		score = 0;
	}

	// streak resets after failed streak.
	void reset_streak() {
		streak = 0;

	}

	//
	int streak_multiplier;

	void reset() {
		reset_streak();
		reset_score();
		reset_fail();
	}
	// GRAPHICS
	// score streak

	int score_streak_SIZE_x = 100;
	int score_streak_SIZE_y = 100;
	int score_streak_loc_x = 0;
	int score_streak_loc_y = 0;
	int score_streak_ms = 50;
	// 0,40,60,50;
	GRect score_streak = new GRect(score_streak_loc_x, score_streak_loc_y, score_streak_SIZE_x, score_streak_SIZE_y);
	Timer score_streak_graphic = new Timer(score_streak_ms, this);
	GLabel score1;
	GLabel streak1;
	GLabel fail;

	public void run() {
		add(score_streak);
		score1 = new GLabel("Score: " + get_score(), 0, 20);
		streak1 = new GLabel("Streak: " + get_streak(), 0, 40);
		int fail_x = 0;
		fail = new GLabel("Fail: ", 0, 60);
		add(fail);
		fail_x += 25;
		for (int i = 0; i < +get_failCount(); i++) {
			fail = new GLabel("X ", fail_x, 60);
			fail_x += 10;
			add(fail);
		}

		add(score1);
		add(streak1);

	}

	public static void main(String args[]) {
		new Score_streak().start();
	}

}