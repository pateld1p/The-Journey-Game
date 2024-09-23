package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CmdExit extends Command{

	private GameWorld gw;
	
	public CmdExit(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		String exitPrompt = "Do you want to exit the game?";
		
		boolean yesExit = Dialog.show("Confirm Exit", exitPrompt, "Yes", "No");
		
		if (yesExit)
			System.exit(0);
	}

}
