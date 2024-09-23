package com.mycompany.a2;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;

public class Game extends Form {
	
	private GameWorld gw;
	private MapView mv; 
	private ScoreView sv;
	
	//private char hotKey;
	
	public Game(){
		gw = new GameWorld(); // create “Observable” GameWorld
		mv = new MapView(gw); // create an “Observer” for the map
		sv = new ScoreView(gw); //create an “Observer” for the game/ant state data
	
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer

		this.setLayout(new BorderLayout());
		
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		Toolbar.setOnTopSideMenu(false);
		myToolbar.setTitle("The Journey Game");
		
		mv.getAllStyles().setBgTransparency(255);
		mv.getAllStyles().setBgColor(ColorUtil.WHITE);
		mv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
		
		
		sv.getAllStyles().setPadding(Component.LEFT, 365);
		sv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		Container left = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		left.getAllStyles().setPadding(Component.TOP, 100);
		left.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));

		Container right = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		right.getAllStyles().setPadding(Component.TOP, 100);
		right.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		Container bottom = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottom.getAllStyles().setPadding(Component.LEFT, 450);
		bottom.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		// key binding 
				Command accelerate = new CmdAccelerate(gw);
				addKeyListener('a', accelerate);
				
				Command brake = new CmdBrake(gw);
				addKeyListener('b', brake);
				
				Command leftTurn = new CmdLeftTurn(gw);
				addKeyListener('l', leftTurn);
				
				Command rightTurn = new CmdRightTurn(gw);
				addKeyListener('r', rightTurn);
				
				Command flagCollide = new CmdCollideFlag(gw);
				
				Command foodStationCollide = new CmdCollideFoodStation(gw);
				addKeyListener('f', foodStationCollide);
				
				Command spiderCollide = new CmdCollideSpider(gw);
				addKeyListener('g', spiderCollide);
				
				Command tick = new CmdTick(gw);
				addKeyListener('t', tick);
				
				Command exit = new CmdExit(gw);
				addKeyListener('x', exit);
				
				Command sound = new CmdSound(gw);
				CheckBox soundBox = new CheckBox("Sound");
				soundBox.setCommand(sound);
				soundBox.getAllStyles().setBgTransparency(255);
				soundBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
				
				Command about = new CmdAbout();
				
				Command help = new CmdHelp();
				
				// Add commands to side menu and toolbar
				myToolbar.addComponentToSideMenu(soundBox);
				myToolbar.addCommandToSideMenu(accelerate);
				myToolbar.addCommandToSideMenu(about);
				myToolbar.addCommandToSideMenu(help);
				myToolbar.addCommandToRightBar(help);
				myToolbar.addCommandToSideMenu(exit);
				
				// Create buttons for the command objects, then add to their allocated
				// containers 
				CmdButton accelButton = new CmdButton();
				accelButton.setCommand(accelerate);
				left.add(accelButton);
				
				CmdButton leftTurnButton = new CmdButton();
				leftTurnButton.setCommand(leftTurn);
				left.add(leftTurnButton);
				
				CmdButton brakeButton = new CmdButton();
				brakeButton.setCommand(brake);
				right.add(brakeButton);
				
				CmdButton rightTurnButton = new CmdButton();
				rightTurnButton.setCommand(rightTurn);
				right.add(rightTurnButton);
				
				CmdButton flagCollideButton = new CmdButton();
				flagCollideButton.setCommand(flagCollide);
				bottom.add(flagCollideButton);
				
				CmdButton spiderCollideButton = new CmdButton();
				spiderCollideButton.setCommand(spiderCollide);
				bottom.add(spiderCollideButton);
				
				CmdButton foodStationCollideButton = new CmdButton();
				foodStationCollideButton.setCommand(foodStationCollide);
				bottom.add(foodStationCollideButton);
				
				CmdButton tickButton = new CmdButton();
				tickButton.setCommand(tick);
				bottom.add(tickButton);
				
				// containers to their allocated region 
				this.add(BorderLayout.CENTER, mv)
					.add(BorderLayout.NORTH, sv)
					.add(BorderLayout.WEST, left)
					.add(BorderLayout.EAST, right)
					.add(BorderLayout.SOUTH, bottom);
				
		this.show();
		
		// width and height
		gw.setMapWidth(mv.getWidth());
		gw.setMapHeight(mv.getHeight());
		
		//game world intialized
		gw.init();
		
		sv.update(gw, gw.getAnt());
		mv.update(gw, gw.getCollection());
		
	}
	
/*	private void setKey(char key)
	{
		this.hotKey=key;
	}
	private char getKey() {
		return this.hotKey;
	}
*/
	
	private void play() {
		Label myLabel=new Label("Enter a Command:"); 
		this.addComponent(myLabel);
		final TextField myTextField = new TextField(); 
		this.addComponent(myTextField); 
		this.show();
			myTextField.addActionListener(evt -> {
			String sCommand = myTextField.getText().toString(); 
			 myTextField.clear();
			/*
			 if(sCommand.length() != 0)
				switch (sCommand.charAt(0)) { 
					case 'a':
						gw.increaseSpeed();
						break;
					case 'b':
						gw.decreaseSpeed();
						break;
					case 'l':
						gw.turnLeft();
						break;
					case 'r':
						gw.turnRight();
						break;
					case 'c':
						gw.setFoodConsumptionRate();
						break;
					case '1':
						gw.flagCollision(1);
						break;
					case '2':
						gw.flagCollision(2);
						break;
					case '3':
						gw.flagCollision(3);
						break;
					case '4':
						gw.flagCollision(4);
						break;
					case '5':
						gw.flagCollision(5);
						break;
					case '6':
						gw.flagCollision(6);
						break;
					case '7':
						gw.flagCollision(7);
						break;
					case '8':
						gw.flagCollision(8);
						break;
					case '9':
						gw.flagCollision(9);
						break;
					case 'f':
						gw.foodStationCollision();
						break;
					case 'g':
						gw.spiderCollision();
						break;
					case 't':
						gw.gameTick();
						break;
					case 'd':
						gw.display();;
						break;
					case 'm':
						gw.map();
						break;
					case 'x':
						setKey(sCommand.charAt(0));
						System.out.println("Do you want to exit this game?");
						break;
					 case 'n':
						if(getKey() == 'x') 
							System.out.println("Game continue");
						else {
							System.out.println("Invalid input");
						}
							break;
					 case 'y':
						 if(getKey() == 'x')
							 gw.exit();
						 else {
							 System.out.println("Invalid input");
						 }
						 break;
					default:
						System.out.println("\nPlease enter valid command\n");
						break;
				}
				*/
			} 
			 //actionPerformed
		);
		//addActionListener
	}//play
}
