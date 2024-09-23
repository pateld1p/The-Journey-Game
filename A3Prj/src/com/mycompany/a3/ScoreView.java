package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	
	private Label timeVal;
	private Label livesLeftVal;
	private Label foodLevelVal;
	private Label healthLevelVal;
	private Label soundVal;
	private Label lastFlagRechedVal;
		
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
		healthLevelVal.getAllStyles().setPadding(LEFT, 2);
		healthLevelVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(healthLevel);
		this.add(healthLevelVal);
		
		Label lastFlag = new Label(" Last Flag Reached:");
		lastFlag.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagRechedVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagRechedVal.getAllStyles().setPadding(LEFT, 2);
		lastFlagRechedVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(lastFlag);
		this.add(lastFlagRechedVal);
		
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
		
		GameObjectCollection gameObjects = gw.getCollection();
		IIterator theElements = gameObjects.getIterator() ;
		Ant ant = null;
		while (theElements.hasNext()) { //finding the ant object
			Object obj = theElements.getNext();
			if(obj instanceof Ant) {
				 ant = (Ant)obj;
				break;
			}
		}
		
		livesLeftVal.setText(" " + gw.getLives());
		timeVal.setText(" " + gw.getGameClock());
		lastFlagRechedVal.setText(" " + gw.getLastFlagNumb());
		foodLevelVal.setText(" " + gw.getFoodLevel());
		healthLevelVal.setText(" " + gw.getHealthLevel());
		
		if(gw.getSoundStatus()==true) {
			this.soundVal.setText("Sound: ON");
		}
		else {
			this.soundVal.setText("Sound: OFF");
		}
		
		// Update the screen
		this.revalidate();
				
	}
}