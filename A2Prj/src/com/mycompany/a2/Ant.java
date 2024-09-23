package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Ant extends Moveable implements IFoodie{
	private int maximumSpeed;
	private int foodConsumptionRate;
	private int foodLevel;
	private int healthLevel;
	private int lastFlagReached;
	private int life;
	private Flags flags;
	private static Ant ant;
	

	 // Ant constructor, inherits from MovableGameObject class. Also 
	 // initialized private fields of the class. Made private to follow
	 // Singleton design pattern 
	private Ant(float x, float y, int heading, int speed) {
		super(20, x, y, 255, 0, 0, heading, speed);	
		this.maximumSpeed = 40;
		this.foodLevel = 20;
		this.foodConsumptionRate = 1;
		this.healthLevel = 10;
		this.lastFlagReached = 1;
		this.life =  3;
	}
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	} 
	public int getHealthLevel() {
		return healthLevel;
	}
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	public static Ant getAnt(float x, float y, int heading, int speed) {
		if (ant == null) 
			ant = new Ant(x, y, heading, speed); 	// create the Ant if not created already 
		return ant;
	}
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	public int getFoodLevel() {
		return foodLevel;
	}
	public void setLife(int x)
	{
		life = x;
	}
	public int getLife() {
		return life;
	}
	
	public void decreaseSpeed() {
		int currentSpeed = getSpeed();
		if (currentSpeed > 0)
		{
			this.setSpeed(--currentSpeed);
		}
	}
	public void increaseSpeed() {
		int currentSpeed = getSpeed();
		if (!isMaximumSpeed()) {
			this.setSpeed(++currentSpeed);
		}	
	}
	public boolean isMaximumSpeed() {
		if (this.getSpeed() >= maximumSpeed)
		{
			System.out.println("You cannot cross maximum speed");
			return true;
		}
			return false;
		
	}
	
	public void decreaseHealthLevel() {
		this.healthLevel--;
	}
	public void changeHealthLevel() {
		if(healthLevel == 0) {
			this.setSpeed(0);
		}
		else if(healthLevel <= 10  && healthLevel > 0 ) {
			this.setMaximumSpeed((this.healthLevel/10) * this.getMaximumSpeed());
		}
	}
	public void decreaseFoodLevel() {
		this.setFoodLevel(this.getFoodLevel()- this.getFoodConsumptionRate());
	}
	public void increaseFoodLevel(int foodStationCapacity) {
		this.setFoodLevel(this.getFoodLevel() +foodStationCapacity );
	}
	public void turnLeft() {
			this.setHeading(this.getHeading() - 5);
	}
	
	public void turnRight() {
		this.setHeading(this.getHeading() + 5);
	}
	
	public void move(int heading, int speed, int width, int height) {
		float deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
		float deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
		
		float newX = this.getLocation().getX() + deltaX;
		float newY = this.getLocation().getY() + deltaY;
		
		if (newX <= 0.0) 
			newX = (float) 0.0;
		else if (newX >= width)
			newX = width;
		
		if (newY <= 0.0)
			newY = (float) 0.0;
		else if (newY >= height)
			newY = height;
		
		this.setLocation(newX, newY);
	}
	public void resetAnt(float x, float y) {
		this.setX(x);
		this.setY(y);
		this.setHeading(0);
		this.maximumSpeed= 50;
		this.foodConsumptionRate = 1;
		this.healthLevel = 10;
		this.lastFlagReached = flags.getSequenceNumber();
		life--;
	}
	
	public String toString() {
		String description = "Ant: " + "loc=" + Math.round(getX()*10.0)/10.0 + "," + Math.round(getY()*10.0)/10.0 + 
				" color = [" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
				" size = " + this.getSize() +
				" heading = " + this.getHeading() + 
				" speed = " + this.getSpeed() +
				" foodConsumptionRate = " + this.getFoodConsumptionRate() +
				" max speed = " + this.getMaximumSpeed() + "\n";
		
		return description;
	}
}
