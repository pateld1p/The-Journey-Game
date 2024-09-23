package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class GameObject implements IDrawable, ICollider {
	private int size;
	private Point location; 
	private int color;
	private boolean flag;


	public GameObject(int size, Point location, int r, int g, int b) {
		this.size = size;
		this.location = location;
		this.color = ColorUtil.rgb(r, g, b);
	}

	public float getX() {
		return location.getX();
	}
	public float getY() {
		return location.getY();
	}
	public int getColor() {
		return color;
	}
	public int getSize() {
		return size;
	}
	
	public void setX(float lociX) {
		location.setX(lociX);
	}
	public void setY(float lociY) {
		location.setY(lociY);
	}
	
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean b) {
		flag = b;
	}
	
	//public void setSize(int size) {
	//	this.size = size;
	//}
	public Point getLocation() {
		return this.location;
	}
	
	 // Setter method for Point. 
	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public boolean collidesWith(ICollider otherObject) {
		
		boolean result = false;
		float thisCenterX = this.getLocation().getX();
		float thisCenterY = this.getLocation().getY();
		float otherCenterX = ((GameObject) otherObject).getLocation().getX();
		float otherCenterY = ((GameObject) otherObject).getLocation().getY();
		float dx = thisCenterX - otherCenterX;
		float dy = thisCenterY - otherCenterY;
		float distBetweenCentersSqr = (dx*dx + dy*dy);
		
		float thisRadius = this.getSize()/2;
		float otherRadius = ((GameObject) otherObject).getSize()/2;
		float radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		if(distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		
		
		return result;
	}
	public void handleCollision(ICollider otherObject, GameWorld gw) {
		otherObject = (GameObject)otherObject;
		GameObject obj = (GameObject)this;
		if((obj instanceof Ant && otherObject instanceof Flags)) {	
			
			Flags flag = (Flags) otherObject;
			
			// Call the method to collide the ant with the Flag
			gw.flagCollision(flag);
			
	
		}
		
		else if((obj instanceof Ant && otherObject instanceof Spider)) {
			
			gw.spiderCollision(obj);
			
			
			
		}
		
		else if((obj instanceof Ant && otherObject instanceof FoodStation)) {
			
			// Capture the FoodStation object
			FoodStation station = (FoodStation) otherObject;
			
			if(station.getCapacity() != 0) {
			
				gw.foodStationCollision(station);
					
			}			 
		}
	}
	
	public String toString() {
		String description = "loc=" + Math.round(getX()*10.0)/10.0 + "," + Math.round(getY()*10.0)/10.0 + " color = [" + 
				ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]" + " size = " + getSize();
		return description;
	}
}
