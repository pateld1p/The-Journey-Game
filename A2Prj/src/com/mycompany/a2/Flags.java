package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Flags extends Fixed{
	private int sequenceNumber;
	
	public Flags(float x, float y, int numb) {
		super(10, x, y, 0, 0, 255);	
		this.sequenceNumber = numb;
	}
	public int getSequenceNumber(){
        return this.sequenceNumber;
    }
    public void setSequenceNumber(int sequenceNumber) {
    	this.sequenceNumber = sequenceNumber;
    }
    public void setColor(int color){
    }
    public String toString() {
		String description = "Flag: " + "loc=" + Math.round(getX()*10.0)/10.0 + "," + Math.round(getY()*10.0)/10.0 + 
				" color = [" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]" + 
				" size = " + this.getSize() +
				" sequence number = " + this.getSequenceNumber() + "\n"; 
		
		return description;
	}
}
