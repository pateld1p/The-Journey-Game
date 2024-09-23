package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdCollideFoodStation extends Command{
	private GameWorld gw;

	public CmdCollideFoodStation(GameWorld gw) {
		super("Collide with Food Station");
		this.gw = gw;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\n Ant has collided with food station.");
		gw.foodStationCollision();
	}
}
