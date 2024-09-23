package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdBrake extends Command{

	private GameWorld gw;

	public CmdBrake(GameWorld gw) {
		super("Brake");
		this.gw = gw;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nAnt has braked.");
		gw.decreaseSpeed();
	}
}
