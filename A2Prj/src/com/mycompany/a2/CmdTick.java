package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdTick extends Command {

	private GameWorld gw;

	public CmdTick(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.print("\nClock has ticked.");
		gw.gameTick();
	}


}
