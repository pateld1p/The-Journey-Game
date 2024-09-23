package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;

public class CmdSound extends Command{

	private GameWorld gw;
	public CmdSound(GameWorld gw) {
		super("Sound");
		this.gw=gw;
	}
	public void actionPerformed(ActionEvent ev) {
		if (((CheckBox) ev.getComponent()).isSelected()) {
			gw.setSound(true);
		} else {
			gw.setSound(false);
		}
	}
}
