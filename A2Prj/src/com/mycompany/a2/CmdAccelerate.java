package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdAccelerate extends Command{
	private GameWorld gw;

	public CmdAccelerate(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nPlayer Ant has accelerated.");
		gw.increaseSpeed();
	}
}
