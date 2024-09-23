package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

public class GameWorld extends Observable {
	private boolean sound = false;
	private boolean pause;
	private int mapHeight;
	private int mapWidth;
	private int lives;
	private int counter = 0;
	private Ant ant;
	private int gameClock = 0;
	private boolean position = false;
	//private boolean soundChecked = false;

	// flag objects
	private Flags flag1;
	private Flags flag2;
	private Flags flag3;
	private Flags flag4;
	private Flags flag5;
	private Spider firstSpider;
	private Spider secondSpider;
	private Spider thirdSpider;
	private FoodStation firstStation;
	private FoodStation secondStation;
	private FoodStation thirdStation;
	private Point location;
	
	private Sound spiderSound;
	private Sound flagSound;
	private Sound foodSound;
	private BGSound backGroundSound;
	
	private Point2D point = new Point2D(0,0);
	// total flags at start
	private int numbFlags;

	private boolean isExit = true;

	GameObjectCollection gameObjectList;
	private Random rand = new Random();

	// private ArrayList<GameObject> gameObjectList;

	public GameWorld() {
		sound = false;
		gameObjectList = new GameObjectCollection();
	}

	public void init() {
		this.gameClock = 0;
		this.pause = false;
		// creating 5 flags with given location
		flag1 = new Flags(new Point((float) 302.1, (float) 592.7), 1);
		gameObjectList.add(flag1);
		flag2 = new Flags(new Point((float) 429.7, (float) 617.2), 2);
		gameObjectList.add(flag2);
		flag3 = new Flags(new Point((float) 888.1, (float) 233.6), 3);
		gameObjectList.add(flag3);
		flag4 = new Flags(new Point((float) 563.0, (float) 789.3), 4);
		gameObjectList.add(flag4);
		flag5 = new Flags(new Point((float) 89.9, (float) 342.4), 5);
		gameObjectList.add(flag5);

		// total flags at start of game
		numbFlags = 5;

		firstSpider = new Spider(rand.nextInt(100) + 30, new Point((rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight)), rand.nextInt(360), rand.nextInt(6) + 5);
		gameObjectList.add(firstSpider);
		secondSpider = new Spider(rand.nextInt(100) + 30, new Point((rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight)), rand.nextInt(360), rand.nextInt(6) + 5);
		gameObjectList.add(secondSpider);
		thirdSpider = new Spider(rand.nextInt(100) + 30, new Point((rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight)), rand.nextInt(360), rand.nextInt(6) + 5);
		gameObjectList.add(thirdSpider);
		
		//Create 3 FoodStation objects and add them all into the ArrayList - can change number later 
		firstStation = new FoodStation(rand.nextInt(80) + 10, new Point((rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight)));
		gameObjectList.add(firstStation);
		secondStation = new FoodStation(rand.nextInt(80) + 10,new Point((rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight)));
		gameObjectList.add(secondStation);
		thirdStation = new FoodStation(rand.nextInt(80) + 10, new Point((rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight)));
		gameObjectList.add(thirdStation);
		
		/*
		int numbOfFoodStations = 2 + rand.nextInt(5);
		int numbOfSpiders = 2 + rand.nextInt(5);
		for (int i = 0; i < numbOfFoodStations; i++) {
			gameObjectList.add(new FoodStation());
		}
		for (int i = 0; i < numbOfSpiders; i++) {
			gameObjectList.add(new Spider());
		}
*/
		ant = ant.getAnt(new Point((float) 502.1, (float) 394.9), 0, 1);
		gameObjectList.add(ant);
		//gameObjectList.add(new Ant());
		
		createSound();
		
		setChanged();
		notifyObservers(this);

	}

	// return sound value
	public boolean getSoundStatus() {
		return sound;
	}
	
	//change sound value and notify scoreview
	public void setSoundStatus(boolean sound) {
		this.sound = sound;
		setChanged();
		notifyObservers(this);
		
	}
	public GameObjectCollection getCollection() {
		return gameObjectList;
	}

	public Ant getAnt() {
		return this.ant;
	}

	public int getLastFlagNumb() {
		return this.numbFlags;
	}

	public int getGameClock() {
		return this.gameClock;
	}

	// x
	public void exit() {
		if (isExit) {
			System.exit(0);
		}
	}
	public boolean getPosition() {
		return position;
	}
	
	// This method set the position variable
	public void setPosition(boolean position) {
		this.position = position;
	}
	public void revertPosition() {
		
		// Check to see if the position is currently set to true
		if (position == true) {
			
			// Update it to be false (not allow the object to be moved)
			position = false;
			
		} else {
			
			// Otherwise, we just allow the object to be moved
			position = true;
			
		}
		
	}
	
	public int getFoodLevel() {
		
		IIterator iterator = gameObjectList.getIterator();
						
		while(iterator.hasNext()) {
			
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Ant) {
				
				// Return the current food level of the ant object that we got using the iterator
				return ((Ant) tempObject).getFoodLevel();
				
			}
		
		}
		
		// Return the base value for the foodLevel if there is no value found
		return 10;
		
	}

	
	// This method allows clients to get the healthLevel without accessing the Ant object
	public int getHealthLevel() {

		IIterator iterator = gameObjectList.getIterator();
		
		while(iterator.hasNext()) {
			
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Ant) {
			
				return ((Ant) tempObject).getHealthLevel();
				
			}
		
		}
		
		// Return the base value for the healthLevel if there is no value found
		return 10;
		
	}
	public void quitGame() {
		System.out.println("Do you wish to exit? (y/n)");
		isExit = true;
	}

	public void dontQuit() {
		isExit = false;
	}

	public int getMapHeight() {
		return this.mapHeight;
	}

	public int getMapWidth() {
		return this.mapWidth;
	}

	public void setMapHeight(int height) {
		this.mapHeight = height;
		System.out.println("" + height);
	}

	public void setMapWidth(int width) {
		this.mapWidth = width;
	}

	public BGSound getBGSound() {
		return backGroundSound;
	}
	
	public void createSound() {		       
		spiderSound = new Sound("spiderCollision.wav");
		flagSound = new Sound("flagCollision.wav");	
		foodSound = new Sound("foodStationCollision.wav");
		backGroundSound = new BGSound("bgSound.wav");
	}
	public void setSound(boolean sound) {
		this.sound = sound;

		this.setChanged();
		this.notifyObservers();

		if (sound == true)
			System.out.println("Sound has been set on.");
		else
			System.out.println("Sound has been set off.");
	}
	
	public boolean isPaused() {
		return this.pause;
	}
	
	public void setPaused() {
		
		if (pause) {
			this.pause = false;
			System.out.println("Game has been set to play mode.");
		} else {
			this.pause = true;
			System.out.println("Game has been set to pause mode.");
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	// Game Methods

	// a - ant accelerates
	public void increaseSpeed() {
		System.out.println("ants speed increased");
		if (ant.getSpeed() + 2 < ant.getMaximumSpeed())
			ant.setSpeed(ant.getSpeed() + 2);
		else if (ant.getSpeed() + 2 >= ant.getMaximumSpeed())
			ant.setSpeed(ant.getMaximumSpeed());
	//		ant.move(ant.getHeading(), ant.getSpeed(), this.mapWidth, this.mapHeight);

		
		this.setChanged();
		this.notifyObservers();
	}

	// b - ant brakes
	public void decreaseSpeed() {
		System.out.println("ant speed decreased");
		if (ant.getSpeed() - 2 > 0)
			ant.setSpeed(ant.getSpeed() - 2);
		else if (ant.getSpeed() - 2 <= 0)
			ant.setSpeed(0);
		
		this.setChanged();
		this.notifyObservers();
	}

	// l - ant turns Left
	public void turnLeft() {
		System.out.println("ant turned Left");
		ant.turnLeft();

		this.setChanged();
		this.notifyObservers();
	}

	// r
	public void turnRight() {
		System.out.println("ant turned Right");
		ant.turnRight();

		this.setChanged();
		this.notifyObservers();
	}

	// c
	public void setFoodConsumptionRate() {
		System.out.println("Food Consumption Rate has been set");
		ant.setFoodConsumptionRate(1);
		
		this.setChanged();
		this.notifyObservers();
	}

	//collision with flag
	//if it is the last flag, game ends
	public void flagCollision(Flags flag) {
		System.out.println("The collision with a flag has occured");
		if (getSoundStatus()) {
			flagSound.play();	
		}
		if (ant.getLastFlagReached() == flag.getSequenceNumber() - 1) {
			ant.setLastFlagReached(ant.getLastFlagReached() + 1);
			System.out.println("ant reached Flag " + ant.getLastFlagReached());

			if (ant.getLastFlagReached() == numbFlags) {
				System.out.print("Game Over!! WIN WIN \nTotal time: " + getGameClock());
				System.exit(0);
			}	
		}

		this.setChanged();
		this.notifyObservers();
	}

	// Press 'f' - collision with food station

	public void foodStationCollision(FoodStation station) {
		System.out.println("The collision with a food stations has occured");
		if (getSoundStatus()) {
			foodSound.play();	
		}
		if (station.getCapacity() != 0) {
			ant.setFoodLevel(ant.getFoodLevel() + station.getCapacity());
			station.setCapacity(0);
			station.setColor(175, 255, 175); 
			FoodStation newStation = new FoodStation(rand.nextInt(61) + 10,  new Point(rand.nextFloat() * 1000, rand.nextFloat() * 1000));
			gameObjectList.add(newStation);
		}
		this.setChanged();
		this.notifyObservers();
	}

	// g - collision with spider
	public void spiderCollision(GameObject obj) {
		ant = (Ant)obj;
		System.out.println("The collision between ant and Spider has occured!!!");
		if (getSoundStatus()) {
			spiderSound.play();	
		}
		ant.setHealthLevel(ant.getHealthLevel() - 1);
		
		ant.setColor(255, 127, 127);
		
		ant.setMaximumSpeed(ant.getHealthLevel() * (ant.getMaximumSpeed()/10));        //setting the max speed);
		
		if(ant.getSpeed()>ant.getMaximumSpeed()) {
			ant.setSpeed(ant.getMaximumSpeed());
		}

		if (ant.getSpeed() > ant.getMaximumSpeed())
			ant.setSpeed(ant.getMaximumSpeed());
		ant.setHeading(-2*ant.getHeading());
		

		// if the health level is 0
		if (ant.getHealthLevel() == 0) {
			// subtract one life
			lives--;
			// if there are no more lives, game over
			if (lives == 0) {
				gameObjectList.clear();
				System.out.println("\nHealth level is 0. 1 life lost. Remaining lives: " + lives);
				System.out.println("\nGame over!! LOSE LOSE");
				System.exit(0);
				// Otherwise, just restart. Clear the collection. Reset health. Reinitialize the
				// game.
			} else if (lives > 0) {
				gameObjectList.clear();
				System.out.println("\nHealth level is 0. 1 life lost. Remaining lives: " + lives);
				ant.setHealthLevel(10);

			}
		}

		this.setChanged();
		this.notifyObservers();
	}

	// t
	public void gameTick(int elapsedTime) {
		System.out.println("Game Clock has ticked");
		
		ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());

		if (ant.getFoodLevel() == 0) {
			lives--;

			// if there are no more lives, game over
			if (lives == 0) {
				gameObjectList.clear();
				System.out.println("\nFood level is 0. 1 life lost. Remaining lives: " + lives);
				System.out.println("\nGame over, you failed!");
				System.exit(0);
				// Otherwise, just restart. Clear the collection. Reset food level. Reinitialize
				// the game.
			} else if (lives > 0) {
				gameObjectList.clear();
				System.out.println("\nFood level is 0. 1 life lost. Remaining lives: " + lives);
				ant.setFoodLevel(20);
			}
		}
		
		IIterator element = gameObjectList.getIterator();
		while (element.hasNext()) {
			GameObject obj = element.getNext();
			if (obj instanceof Spider)
				((Spider) obj).setHeading(((Moveable) obj).getHeading() + rand.nextInt(5 + 5) - 5);
		}

		element = gameObjectList.getIterator();
		
		while (element.hasNext()) {
			
			GameObject obj = element.getNext();
			if (obj instanceof Moveable)
				((Moveable) obj).move(((Moveable) obj).getHeading(), ((Moveable) obj).getSpeed(), this.mapWidth, this.mapHeight, elapsedTime);
		}

		
		// handling objects going off screen
		   
        IIterator iterator = gameObjectList.getIterator();
        while(iterator.hasNext())
        {
            GameObject obj = (GameObject)iterator.getNext();
            if(obj instanceof Fixed)
            {
                continue;
            }
            Moveable movableObj = (Moveable) obj;
            double locX = obj.getLocation().getX();
            double locY = obj.getLocation().getY();
            
            Point newLoc = movableObj.getLocation();
            
            if(locX > point.getX())
            {
                newLoc.setX(0);
            }
            else if( locX < 0)
            {
                newLoc.setX((float)point.getX());
            }
            
            if(locY > point.getY())
            {
                newLoc.setY(0);
            }
            else if( locY < 0)
            {
                newLoc.setY((float)point.getY());
            }
            
            movableObj.setLocation(newLoc.getX(), newLoc.getY());
	   
        }
		//collision detection
		element = gameObjectList.getIterator();
		while(element.hasNext()) {
			ICollider curObj = (ICollider)element.getNext();
			IIterator element2 = gameObjectList.getIterator();
			while(element2.hasNext()) {
				ICollider otherObj = (ICollider)element2.getNext();				
				if(curObj.collidesWith(otherObj)) {

		                   curObj.handleCollision(otherObj, this);     
				}
			}		 
		}
		if (getSoundStatus()) {
			// If so, play the background music
			backGroundSound.play();
			
		} 
		gameClock++;
    	setChanged();
		notifyObservers(this);
	}

	// d - displays lines of text on the console describing the current game/ant
	// state values
	public void display() {
		System.out.println("Number of life left are: " + ant.getLife());
		System.out.println("Game clock timer is currently at: " + getGameClock());
		System.out.println("Last flag reached is: " + ant.getLastFlagReached());
		System.out.println("Current food level is: " + ant.getFoodLevel());
		System.out.println("Current Health Level is: " + ant.getHealthLevel());
	}

	// m - outputs the map
	public void map() {
		IIterator theObjects = gameObjectList.getIterator();
		while (theObjects.hasNext()) {
			System.out.println(theObjects.getNext().toString());
		}

		System.out.println();
	}

	public int getLives() {
		return this.lives;
	}
}