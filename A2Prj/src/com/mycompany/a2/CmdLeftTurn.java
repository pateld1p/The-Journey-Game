package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdLeftTurn extends Command {
	private GameWorld gw;

	public CmdLeftTurn(GameWorld gw) {
		super("Left Turn");
		this.gw = gw;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nAnt has turned 5 degrees to the left.");
		gw.turnLeft();
	}
}
