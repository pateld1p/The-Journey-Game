package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	private int size;
	private Point point; 
	private int color;

	public GameObject(int size, float x, float y, int r, int g, int b) {
		this.size = size;
		this.point = new Point(x, y);
		this.color = ColorUtil.rgb(r, g, b);
	}

	public float getX() {
		return point.getX();
	}
	public float getY() {
		return point.getY();
	}
	public int getColor() {
		return color;
	}
	public int getSize() {
		return size;
	}
	
	public void setX(float lociX) {
		point.setX(lociX);
	}
	public void setY(float lociY) {
		point.setY(lociY);
	}
	
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}
	
	//public void setSize(int size) {
	//	this.size = size;
	//}
	public Point getLocation() {
		return this.point;
	}
	
	 // Setter method for Point. 
	public void setLocation(float x, float y) {
		this.point.setX(x);
		this.point.setY(y);
	}
	public String toString() {
		String description = "loc=" + Math.round(getX()*10.0)/10.0 + "," + Math.round(getY()*10.0)/10.0 + " color = [" + 
				ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]" + " size = " + getSize();
		return description;
	}
}
