package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed{

	private int capacity;
	private int color;
	Random rand = new Random();
/*	
	public FoodStation() {
		super(ColorUtil.rgb(0, 255, 0)); //setting the color to green
		color = super.getColor();
		int min_size = 10;
    	int max_size = 50;
		//super.setSize(rand.nextInt(max_size - min_size) + min_size); 
    	this.capacity = this.getSize();	
	}
*/	
	public FoodStation(int size, float x, float y) {
		super(size, x, y, 0, 255, 0);
		this.capacity = 2*this.getSize();
	}

	public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity(){
        return capacity;
    }
   
    public void setColor(int color){
        this.color = color;
    }
   
    public boolean checkFoodStation()
    {
        if (capacity == 0)
            return false;
        else
            return true;
    }

    public String toString() {
		String description ="FoodStation:" + "loc=" + Math.round(getX()*10.0)/10.0 + "," + Math.round(getY()*10.0)/10.0 + 
				" color = [" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
				" size = " + this.getSize() +
				" capacity = " + this.getCapacity() + "\n";
		
		return description;
	}
}
