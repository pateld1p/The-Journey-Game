package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CmdAbout extends Command{
		
	public CmdAbout() {
		super("About");
	}
	
	/**
	 * Method to override the parent method and perform the 
	 * give about info command
	 * 
	 * Displays a dialog for name, Course info, Name and Version number of the game. 
	 */
	@Override 
	public void actionPerformed(ActionEvent ev) {
		String info = "Name: Dip Patel \nCourse: CSC 133 - Object Oriented Computer Graphics";
		
		boolean c = Dialog.show("About", info, "OK", null);
	}

}
