package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CmdHelp extends Command{

	public CmdHelp() {
		super("Help");
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		Dialog.show("How to Play", "To play the game, use the buttons on the screen or the following keys: \n'a' = Accelerate Ant\n'b' = Brake Ant Ant\n'l' = Turn Ant Left\n'r' = Turn Ant Right\n'f' = Collide with FoodStation\n'g' = Collide with Spider\n't' = Tick the Clock\n'x' = Exit the Game");
	}

}
