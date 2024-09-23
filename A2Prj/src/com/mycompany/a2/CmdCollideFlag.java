package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CmdCollideFlag extends Command {
	
	private GameWorld gw;

	public CmdCollideFlag(GameWorld gw) {
		super("Collide with Flag");
		this.gw = gw;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		Command cOk = new Command("OK");
		Command cCancel = new Command("Cancel");
		Command [] cmds = new Command[] {cOk, cCancel};
		TextField myTF = new TextField();
		
		int input = gw.getAnt().getLastFlagReached(); 	// initial value will be last base reached 
		String playerInputStr;
		
		Command c = Dialog.show("Enter Flag Number: ", myTF, cmds);
		
		if (c == cOk) {
			playerInputStr = myTF.getText();
			
			if (playerInputStr == null || playerInputStr.length() == 0) {
				boolean notice = Dialog.show("Notice", "Invalid input. Please enter a number 1-5.", "OK", null);
			} else {
				try {
					input = Integer.parseInt(playerInputStr);
					
					if (input >= gw.getLastFlagNumb() ) {
						boolean numRangeNotice = Dialog.show("Notice", "Invalid input. Please enter a number 1-5. There are only 5 flags in the game.", "OK", null);
					}

				} catch (NumberFormatException e) {
					boolean notice = Dialog.show("Notice", "Invalid input. Please enter a number 1-5.", "OK", null);
				}
	
				gw.flagCollision(input);
			}
			
		}
		
			
	}
}
