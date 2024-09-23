package com.mycompany.a2;

public abstract class Moveable extends GameObject{
	private int heading;
	private int speed;
	private int foodLevel;

	public Moveable(int size, float x, float y, int r, int g, int b, int heading, int speed) {
		super(size, x, y, r, g, b);
		this.heading = heading;
		this.speed = speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	public int getFoodLevel() {
		return foodLevel;
	}
	public void setHeading(int heading) {
		this.heading = heading;
	}
	public int getHeading() {
		return heading;
	}
	
	public void move() {
		if(this.getFoodLevel() == 0) {
			this.setSpeed(0);
		}
		
		//updating the new location
		double radianLoci = (90 - this.getHeading()) * (float)Math.PI / 180 ;
		float newX = (float) (this.getX() + (float)Math.cos(radianLoci) * this.getSpeed());
		float newY = (float) (this.getY() + (float)Math.sin(radianLoci) * this.getSpeed());
		this.setX(newX);
		this.setY(newY);
	}
	public abstract void move(int heading, int speed, int width, int height);
}
