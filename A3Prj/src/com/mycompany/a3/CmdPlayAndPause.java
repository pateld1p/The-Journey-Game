package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdPlayAndPause extends Command {
	private GameWorld gw;
	private Game game;
	public CmdPlayAndPause(GameWorld gw, Game gm) {
		super("Pause");
		this.gw = gw;
		game = gm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){ //depending on status of the game being paused take action
		if (game.getIsPaused()) {
			game.setIsPaused(false);
			game.getPlayPauseButton().setText("Pause");
			game.enableCommands();
			
			if (gw.getSoundStatus() == true) {
				gw.getBGSound().run();
			}
			
		
			game.getTimer().schedule(20, true, game);
		} else {
			game.setIsPaused(true);
			game.getPlayPauseButton().setText("Play");
			gw.getBGSound().pause();
			game.disableCommands();
			
			game.getTimer().cancel();
		}
		
	}
}
