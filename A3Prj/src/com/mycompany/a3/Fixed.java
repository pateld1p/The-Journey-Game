package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject {
	private boolean selected;

	public Fixed(int size, Point location, int r, int g, int b) {
		super(size, location, r, g, b);
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int objectSize = getSize();
		
		// Capture the pointer's location relative to the parent's origin
		float pointerX = pPtrRelPrnt.getX();
		float pointerY = pPtrRelPrnt.getY();
		
		// Capture the shape location relative to parent's origin
		float objectX = this.getX() + pCmpRelPrnt.getX() - objectSize/2;
		float objectY = this.getY() + pCmpRelPrnt.getY() - objectSize/2;
		
		
		boolean leftSide = pointerX >= objectX;
		
		boolean rightSide = pointerX <= objectX + objectSize;
		
		boolean topSide = pointerY >= objectY;
		
		boolean bottomSize = pointerY <= objectY + objectSize;
		
		
		// Here we combine all of the booleans to see if the pointer was between all of those sides
		if (leftSide && rightSide && topSide && bottomSize) {
			
			return true;
			
		} else {
			return false;
			
		}
		
	}
}
