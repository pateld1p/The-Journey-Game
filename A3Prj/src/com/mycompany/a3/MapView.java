package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class MapView extends Container implements Observer {
	
	private GameWorld gw;
	
	public MapView(Observable o, GameWorld gw) {
		o.addObserver(this);
		this.gw = gw;
	}
	
	
	public void update (Observable o, Object arg) {
		System.out.print("\nOutputting current map...\n");
		((GameWorld) o).map();
		
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point p;
		p = new Point(getX(), getY());
		
		GameObjectCollection collection = gw.getCollection();
		IIterator elements = collection.getIterator() ;
		while ( elements.hasNext() ) { 
			GameObject obj = (GameObject)elements.getNext();
			obj.draw(g,p);
		}
	}
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		GameObjectCollection collection = gw.getCollection();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());		
		IIterator selectables = collection.getIterator();
		
		if (gw.isPaused()) {
			while (selectables.hasNext()) {
				GameObject hold = selectables.getNext();				
				if (hold instanceof Fixed && ((Fixed) hold).isSelected()) {
					Point p = new Point(pPtrRelPrnt.getX() - this.getX(), pPtrRelPrnt.getY() - this.getY());
					((Fixed) hold).setLocation(p.getX(), p.getY());
					gw.setPosition(false);
				}
			}
		} else {
			selectables = gw.getCollection().getIterator();
			
			while (selectables.hasNext()) {
				GameObject hold = selectables.getNext();
				if (hold instanceof Fixed) {
					if (((Fixed) hold).contains(pPtrRelPrnt, pCmpRelPrnt)) 
						((Fixed) hold).setSelected(true);
					else 
						((Fixed) hold).setSelected(false);
				} 
			}
		}			  
		this.repaint();		
	}
}
