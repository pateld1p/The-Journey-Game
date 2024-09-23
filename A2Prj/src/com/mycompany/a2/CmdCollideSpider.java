package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdCollideSpider extends Command{
	private GameWorld gw;

	public CmdCollideSpider(GameWorld gw) {
		super("Collide with Spider");
		this.gw = gw;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\n Ant has collided with spider.");
		gw.spiderCollision();
	}
}
