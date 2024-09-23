package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer {
	private Label timeVal = new Label("");
	private Label livesLeftVal = new Label("");
	private Label foodLevelVal = new Label("");
	private Label healthLevelVal = new Label("");
	private Label soundVal = new Label("");
	private Label lastFlagReachedVal = new Label("");
		
	/**
	 * Constructor for ScoreView. Adds ScoreView as an observer. 
	 * 
	 * Then creates the labels and sets the color and padding of them, 
	 * and finally adds it to the container. 
	 * @param o
	 */
	public ScoreView(Observable o) {
		o.addObserver(this);
		
		// For each label, divide it into two parts, text and value. Set the padding for each
		// value label so that the labels stay stable during game play. 
		Label time = new Label("Time:");
		time.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeVal.getAllStyles().setPadding(LEFT, 2);
		timeVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(time);
		this.add(timeVal);

		Label livesLeft = new Label(" Lives Left:");
		livesLeft.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftVal.getAllStyles().setPadding(LEFT, 2);
		livesLeftVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(livesLeft);
		this.add(livesLeftVal);
		
		Label lastFlagReached = new Label(" Last Flag Reached:");
		lastFlagReached.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagReachedVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagReachedVal.getAllStyles().setPadding(LEFT, 2);
		lastFlagReachedVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(lastFlagReached);
		this.add(lastFlagReachedVal);
		
		Label foodLevel = new Label(" Food Level:");
		foodLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelVal.getAllStyles().setPadding(LEFT, 2);
		foodLevelVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(foodLevel);
		this.add(foodLevelVal);
		
		Label healthLevel = new Label(" Health Level:");
		healthLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelVal.getAllStyles().setPadding(LEFT, 5);
		healthLevelVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(healthLevel);
		this.add(healthLevelVal);
		
		Label sound = new Label(" Sound:");
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundVal.getAllStyles().setPadding(LEFT, 2);
		soundVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(sound);
		this.add(soundVal);
	}

	/**
	 * update the value labels for ScoreView based on current game/ant state data. 
	 * Also calls display method from GameWorld. 
	 */
	@Override
	public void update(Observable observable, Object data) {
		System.out.print("\nDisplaying current game and Ant state values: \n");
		
		timeVal.setText("" + ((GameWorld) observable).getGameClock());
		livesLeftVal.setText("" + ((GameWorld) observable).getAnt().getLife());
		foodLevelVal.setText("" + ((GameWorld) observable).getAnt().getFoodLevel());
		healthLevelVal.setText("" + ((GameWorld) observable).getAnt().getHealthLevel());
		lastFlagReachedVal.setText("" + ((GameWorld) observable).getAnt().getLastFlagReached());
		soundVal.setText("" + ((GameWorld) observable).getSound());
		
	
		String soundValStr;
		if (((GameWorld) observable).getSound() == true) 
			soundValStr = "ON";
		else 
			soundValStr = "OFF";
		soundVal.setText("" + soundValStr);
		
		repaint();
		
		
		((GameWorld) observable).display();
	}
}