package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdPosition extends Command{
	private GameWorld gw;
	private Game gm;
	
	public CmdPosition(GameWorld gw, Game game) {
		super("Position");
		this.gw = gw;
		gm = game;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gm.setPostionStatus(true);
		
	}
}
