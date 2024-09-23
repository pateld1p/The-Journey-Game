package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class Flags extends Fixed{
	private int sequenceNumber;
	
	public Flags(Point location, int sequenceNumber) {
		super(60, location, 0, 0, 255);	
		this.sequenceNumber = sequenceNumber;
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
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int base = (this.getSize()/2);
		
		int topX = (int)pCmpRelPrnt.getX() + (int)this.getLocation().getX();
		int topY = (int)pCmpRelPrnt.getY() + (int)this.getLocation().getY() + base;
		int bottomLeftX = (int)pCmpRelPrnt.getX() + (int)this.getLocation().getX() - base;
		int bottomLeftY = (int)pCmpRelPrnt.getY() + (int)this.getLocation().getY() - base;
		int bottomRightX = (int)pCmpRelPrnt.getX() + (int)this.getLocation().getX() + base;
		int bottomRightY = (int)pCmpRelPrnt.getY() + (int)this.getLocation().getY() - base;
		int[] xPoints = {topX,bottomLeftX,bottomRightX};
		int[]yPoints = {topY, bottomLeftY, bottomRightY};

		
		g.setColor(this.getColor());
		
		if (this.isSelected()==true) {
			// Draws unfilled triangle
			g.drawPolygon(xPoints, yPoints, 3);
		} else {
			// Draws filled triangle
			g.fillPolygon(xPoints, yPoints, 3);
		}
		
		Font thisFont = g.getFont();
		String flagNumber = ""+this.getSequenceNumber();
		int w = thisFont.stringWidth(flagNumber);
		int h = thisFont.getHeight();
		
		int xLoc = (int)pCmpRelPrnt.getX() + (int)this.getLocation().getX() - (this.getSize()/2); //Center.x - width/2 relative to mapView.
		int yLoc = (int)pCmpRelPrnt.getY() + (int)this.getLocation().getY() - (this.getSize()/2);
		
		
		
		g.drawString(flagNumber, xLoc + (this.getSize()/2 - w/2), yLoc + (this.getSize()/2 - h/2) );
		
		g.setColor(ColorUtil.WHITE);
		g.drawString(flagNumber, xLoc + (this.getSize()/2 - w/2), yLoc + (this.getSize()/2 - h/2) );
	}

}
