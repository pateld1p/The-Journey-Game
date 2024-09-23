package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

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
	public Spider(int size, Point location, int heading, int speed) {
		super(size, location, 0, 0, 0, heading, speed);	
	}
	
	
	public void move() {
		this.setHeading(super.getHeading()+rand.nextInt(5));
		
	}
	
	
	@Override
	public void move(int heading, int speed, int width, int height, int elapsedTime) {
		float distance = this.getSpeed()*(elapsedTime/1000);
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

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int base = (this.getSize()/2);
				
		int topX = (int)pCmpRelPrnt.getX() + (int)this.getLocation().getX();
		int topY = (int)pCmpRelPrnt.getY() + (int)this.getLocation().getY() + base;
		int bottomLeftX = (int)pCmpRelPrnt.getX() + (int)this.getLocation().getX() - base;
		int bottomLeftY = (int)pCmpRelPrnt.getY() + (int)this.getLocation().getY() - base;
		int bottomRightX = (int)pCmpRelPrnt.getX() + (int)this.getLocation().getX() + base;
		int bottomRightY = (int)pCmpRelPrnt.getY() + (int)this.getLocation().getY() - base;

		int[] xPoints = {topX, bottomLeftX, bottomRightX};
		int[] yPoints = {topY,bottomLeftY, bottomRightY};
			 
		g.setColor(this.getColor());
		g.drawPolygon(xPoints, yPoints, 3);		
	}

}
	