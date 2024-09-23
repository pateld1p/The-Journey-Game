package com.mycompany.a3;

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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;

public class Game extends Form implements Runnable{
	
	private GameWorld gw;
	private MapView mv; 
	private ScoreView sv;
	private UITimer timer;
	private boolean isPaused = false;
	private Button playPause;
	private Button positionButton;
	private boolean positionStatus;
	private CmdButton accelerateButton, brakeButton, leftButton, rightButton;
	private CmdAccelerate accelerate;
	private CmdLeftTurn leftTurn;
	private CmdRightTurn rightTurn;
	private CmdBrake brake;
	private CmdSound soundCommand;
	private CmdPlayAndPause setPause;
	private Label checkStatusVal = new Label("OFF");

	//private char hotKey;
	
	public Game(){
		gw = new GameWorld(); // create “Observable” GameWorld
		mv = new MapView(gw, gw); // create an “Observer” for the map
		sv = new ScoreView(gw); //create an “Observer” for the game/ant state data
	
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer

		
		accelerate = new CmdAccelerate(gw);
		leftTurn = new CmdLeftTurn(gw);
		rightTurn = new CmdRightTurn(gw);
		brake = new CmdBrake(gw);
		soundCommand = new CmdSound(gw);
		this.setLayout(new BorderLayout());
		
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		//Toolbar.setOnTopSideMenu(false);
		myToolbar.setTitle("The Journey Game");
		
		//mv.getAllStyles().setBgTransparency(255);
		//mv.getAllStyles().setBgColor(ColorUtil.WHITE);
		//mv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
		
		
		//sv.getAllStyles().setPadding(Component.LEFT, 365);
		//sv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
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

		Command sound = new CmdSound(gw);
		CheckBox soundBox = new CheckBox("Sound");
		soundBox.setCommand(sound);
		soundBox.getAllStyles().setBgTransparency(255);
		soundBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		/* // create middle area and assign it to CENTER
		Container centerContainer = mv;
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.MAGENTA));
		add(BorderLayout.CENTER, centerContainer);
		*/
		// create bottom container
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		playPause = new Button("Pause");
		styleMe(playPause);
		bottomContainer.add(playPause);
		CmdPlayAndPause playPauseCommand = new CmdPlayAndPause(gw, this);
		playPause.setCommand(playPauseCommand);
		
		positionButton = new Button("Position");
		styleMe(positionButton);
		bottomContainer.add(positionButton);
		CmdPosition positionCommand = new CmdPosition(gw, this);
		positionButton.setCommand(positionCommand);
		
		
		Command about = new CmdAbout();
		
		Command help = new CmdHelp();
		
		Command exit = new CmdExit(gw);
		addKeyListener('x', exit);	
		
		// Add commands to side menu and toolbar
		myToolbar.addComponentToSideMenu(soundBox);
		myToolbar.addCommandToSideMenu(accelerate);
		myToolbar.addCommandToSideMenu(about);
		myToolbar.addCommandToSideMenu(help);
		myToolbar.addCommandToRightBar(help);
		myToolbar.addCommandToSideMenu(exit);
		
		// Create buttons for the command objects, then add to their allocated
		// containers 
		accelerateButton = new CmdButton();
		accelerateButton.setCommand(accelerate);
		left.add(accelerateButton);
		
		leftButton = new CmdButton();
		leftButton.setCommand(leftTurn);
		left.add(leftButton);
		
		brakeButton = new CmdButton();
		brakeButton.setCommand(brake);
		right.add(brakeButton);
		
		rightButton = new CmdButton();
		rightButton.setCommand(rightTurn);
		right.add(rightButton);
		
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
		
		// createSounds() to initialize the sounds
		gw.createSound();
		
		// Re-validate the current form
		this.revalidate();
		
		
		// Initialize the timer variable 
		timer = new UITimer(this);

		//sv.update(gw, gw.getAnt());
		//mv.update(gw, gw.getCollection());
		
		
		// Schedule the timer to have 20ms for each tick
		timer.schedule(40, true, this);
	}

	public void enableCommands() {
		this.addKeyListener('a', accelerate);
		this.addKeyListener('b', brake);
		this.addKeyListener('l', leftTurn);
		this.addKeyListener('r', rightTurn);
		this.addKeyListener('p', setPause);		// don't disable this key listener
		
		this.accelerate.setEnabled(true);
		this.soundCommand.setEnabled(true);
		this.accelerateButton.setEnabled(true);
		this.brakeButton.setEnabled(true);
		this.leftButton.setEnabled(true);
		this.rightButton.setEnabled(true);
	}
	
	public void disableCommands() {
		this.removeKeyListener('a', accelerate);
		this.removeKeyListener('b', brake);
		this.removeKeyListener('l', leftTurn);
		this.removeKeyListener('r', rightTurn);
		
		this.accelerate.setEnabled(false);
		this.soundCommand.setEnabled(false);
		this.accelerateButton.setEnabled(false);
		this.brakeButton.setEnabled(false);
		this.leftButton.setEnabled(false);
		this.rightButton.setEnabled(false);
	}
	@Override
	public void run() {
		gw.gameTick(40);
		
		if (gw.isPaused()) {
			this.disableCommands();
			this.positionButton.setEnabled(true);
			gw.getBGSound().pause();
		} else {
			this.enableCommands();
			this.positionButton.setEnabled(false);
			if (gw.getSoundStatus()) 
				gw.getBGSound().play();
		}	}
	
	public Label getCheckVal() {
		return checkStatusVal;
	}
	
	
	public void styleMe(Button b) {
		b.getUnselectedStyle().setBgTransparency(255);
	
		b.getAllStyles().setBgColor(ColorUtil.BLUE);
	    b.getAllStyles().setFgColor(ColorUtil.WHITE);
	    b.getAllStyles().setPadding(Component.TOP, 5);
	    b.getAllStyles().setPadding(Component.BOTTOM, 5);
	    b.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		
	}
	
	public boolean getIsPaused() {
		return isPaused;
	}
	public void setIsPaused(boolean b) {
		isPaused = b;
		
	}
	
	public Button getPlayPauseButton() {
		return playPause;
	}
	
	public UITimer getTimer() {
		return timer;
	}
	
	public void setPostionStatus(boolean b) {
		positionStatus = b;
		
	}
	
	public boolean getPostionStatus() {
		return positionStatus;
	}
/*	private void setKey(char key)
	{
		this.hotKey=key;
	}
	private char getKey() {
		return this.hotKey;
	}

	
	private void play() {
		Label myLabel=new Label("Enter a Command:"); 
		this.addComponent(myLabel);
		final TextField myTextField = new TextField(); 
		this.addComponent(myTextField); 
		this.show();
			myTextField.addActionListener(evt -> {
			String sCommand = myTextField.getText().toString(); 
			 myTextField.clear();
		
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
				
			} 
			 //actionPerformed
		);
		//addActionListener
	}//play
*/
	
} 
