package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class Spider extends Moveable {
	 
	private Random rand = new Random();
	
	/*
	public Spider() {
		super(ColorUtil.rgb(0, 0, 0));
		int min_size = 10;
		int max_size = 50;
		//super.setSize(rand.nextInt(max_size - min_size) + min_size);
		super.setHeading(rand.nextInt(360));
		super.setSpeed(rand.nextInt(5) + 5); //speed of spider between 5 and 10
	}
	*/
	public Spider(int size, float x, float y, int heading, int speed) {
		super(size, x, y, 0, 0, 0, heading, speed);	
	}
	
	public void move() {
		this.setHeading(super.getHeading()+rand.nextInt(5));
		
	}
	@Override
	public void move(int heading, int speed, int width, int height) {
		float deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
		float deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
		
		float newX = this.getLocation().getX() + deltaX;
		float newY = this.getLocation().getY() + deltaY;
		
		if (newX <= 0.0 || newX >= width) {
			if (heading < 180)
				heading += 180;
			else if (heading > 180)
				heading -= 180;
			else if (heading == 180)
				heading = 0;
			
			deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
			deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
			
			newX = this.getLocation().getX() + deltaX;
			newY = this.getLocation().getY() + deltaY;
		}
		
		if (newY <= 0.0 || newY >= height) {
			if (heading < 180)
				heading += 180;
			else if (heading > 180)
				heading -= 180;
			else if (heading == 180)
				heading = 0;
			
			deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
			deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
			
			newX = this.getLocation().getX() + deltaX;
			newY = this.getLocation().getY() + deltaY;
		}
		
		this.setLocation(newX, newY);
	}
	public void setSize(int size) {
		
	}

	public void setColor(int color){
		
	}
	
	//checking the boundry of the soider
	public void checkSpiderBoundry() {
		if ((this.getSize() + this.getX()) > 1000 || (this.getX() + this.getSize() > 0)) {
			this.setHeading(rand.nextInt(180));
			
		}
		if ((this.getSize() + this.getY()) > 1000 || (this.getSize() + this.getY()) < 0 ) {
			this.setHeading(rand.nextInt(180));
		}
	}
	
	public String toString() {
		String description = "Spider: " + "loc=" + Math.round(getX()*10.0)/10.0 + "," + Math.round(getY()*10.0)/10.0 + 
				" color = [" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
				" size = " + this.getSize() +
				" heading = " + this.getHeading() + 
				" speed = " + this.getSpeed() +  "\n";
		
		return description;
	}

}
	