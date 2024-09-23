package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

//Class that creates and styles command buttons on the Game form

public class CmdButton extends Button {
	
	public CmdButton() {
		super();
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
	
		this.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		this.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		this.getDisabledStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLUE));

		this.getAllStyles().setPadding(Component.TOP, 5);
		this.getAllStyles().setPadding(Component.LEFT, 1);
		this.getAllStyles().setPadding(Component.RIGHT, 1);
		this.getAllStyles().setPadding(Component.BOTTOM, 5);
	}

}