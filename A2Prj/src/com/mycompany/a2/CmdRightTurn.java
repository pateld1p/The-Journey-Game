package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdRightTurn extends Command {
	private GameWorld gw;


	public CmdRightTurn(GameWorld gw) {
		super("Right Turn");
		this.gw = gw;
	}
	

	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("Player Ant has turned 5 degrees to the left.");
		gw.turnRight();
	}
}
