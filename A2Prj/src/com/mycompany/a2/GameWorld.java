package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class GameWorld extends Observable {
	private boolean sound = false;
	private int mapHeight;
	private int mapWidth;
	private int lives;
	private int counter = 0;
	private Ant ant;
	private int gameClock = 0;
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

	// total flags at start
	private int numbFlags;

	private boolean isExit = true;

	GameObjectCollection gameObjectList;
	private Random rand = new Random();

	// private ArrayList<GameObject> gameObjectList;

	public GameWorld() {
		gameObjectList = new GameObjectCollection();
		// ant = new ant();

	}

	public void init() {
		this.gameClock = 0;

		// creating 5 flags with given location
		flag1 = new Flags((float) 302.1, (float) 592.7, 1);
		gameObjectList.add(flag1);
		flag2 = new Flags((float) 429.7, (float) 617.2, 2);
		gameObjectList.add(flag2);
		flag3 = new Flags((float) 888.1, (float) 233.6, 3);
		gameObjectList.add(flag3);
		flag4 = new Flags((float) 563.0, (float) 789.3, 4);
		gameObjectList.add(flag4);
		flag5 = new Flags((float) 89.9, (float) 342.4, 5);
		gameObjectList.add(flag5);

		// total flags at start of game
		numbFlags = 5;

		firstSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjectList.add(firstSpider);
		secondSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjectList.add(secondSpider);
		thirdSpider = new Spider(rand.nextInt(61) + 10, (rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight), rand.nextInt(360), rand.nextInt(11) + 5);
		gameObjectList.add(thirdSpider);
		
		//Create 3 FoodStation objects and add them all into the ArrayList - can change number later 
		firstStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight));
		gameObjectList.add(firstStation);
		secondStation = new FoodStation(rand.nextInt(61) + 10,(rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight));
		gameObjectList.add(secondStation);
		thirdStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * mapWidth), (rand.nextFloat() * mapHeight));
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
		ant = ant.getAnt((float) 502.1, (float) 394.9, 0, 7);
		gameObjectList.add(ant);
		//gameObjectList.add(new Ant());

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

	public boolean getSound() {
		return this.sound;
	}

	/**
	 * Setter method for sound
	 * 
	 * @param sound
	 */
	public void setSound(boolean sound) {
		this.sound = sound;

		this.setChanged();
		this.notifyObservers();

		if (sound == true)
			System.out.println("Sound has been set on.");
		else
			System.out.println("Sound has been set off.");
	}
	// Game Methods

	// a - ant accelerates
	public void increaseSpeed() {
		System.out.println("ants speed increased");
		if (ant.getSpeed() + 2 < ant.getMaximumSpeed())
			ant.setSpeed(ant.getSpeed() + 2);
		else if (ant.getSpeed() + 2 >= ant.getMaximumSpeed())
			ant.setSpeed(ant.getMaximumSpeed());
		ant.move(ant.getHeading(), ant.getSpeed(), this.mapWidth, this.mapHeight);

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
		ant.move(ant.getHeading(), ant.getSpeed(), this.mapWidth, this.mapHeight);

		this.setChanged();
		this.notifyObservers();
	}

	// l - ant turns Left
	public void turnLeft() {
		System.out.println("ant turned Left");
		ant.turnLeft();
		ant.move(ant.getHeading(), ant.getSpeed(), this.mapWidth, this.mapHeight);

		this.setChanged();
		this.notifyObservers();
	}

	// r
	public void turnRight() {
		System.out.println("ant turned Right");
		ant.turnRight();
		ant.move(ant.getHeading(), ant.getSpeed(), this.mapWidth, this.mapHeight);

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
	public void flagCollision(int flag) {
		System.out.println("The collision with a flag has occured");

		if (ant.getLastFlagReached() == flag - 1) {
			ant.setLastFlagReached(flag);
			System.out.println("ant reached Flag " + ant.getLastFlagReached());
		}

		if (ant.getLastFlagReached() == numbFlags) {
			System.out.print("Game Over!! WIN WIN \nTotal time: " + getGameClock());
			System.exit(0);
		}

		this.setChanged();
		this.notifyObservers();
	}

	// Press 'f' - collision with food station

	public void foodStationCollision() {
		System.out.println("The collision with a food stations has occured");
		ArrayList<FoodStation> availableStations = new ArrayList<FoodStation>();
		FoodStation station;

		IIterator theObjects = gameObjectList.getIterator();
		while (theObjects.hasNext()) {
			GameObject checkObject = theObjects.getNext();
			if (checkObject instanceof FoodStation && ((FoodStation) checkObject).getCapacity() > 0)
				availableStations.add((FoodStation) checkObject);
		}

		station = availableStations.get(rand.nextInt(3));

		ant.setFoodLevel(ant.getFoodLevel() + station.getCapacity());
		station.setCapacity(0);
		station.setColor(175, 255, 175);
		FoodStation newStation = new FoodStation(rand.nextInt(61) + 10, (rand.nextFloat() * mapWidth),
				(rand.nextFloat() * mapHeight));
		gameObjectList.add(newStation);

		this.setChanged();
		this.notifyObservers();
	}

	// g - collision with spider
	public void spiderCollision() {
		System.out.println("The collision between ant and Spider has occured!!!");
		ant.setHealthLevel(ant.getHealthLevel() - 1);

		int addFade = (int) 25.5 * (10 - (ant.getHealthLevel()));
		ant.setColor(255, addFade, addFade);

		int lowerSpeedBy = 5 * (10 - ant.getHealthLevel());
		ant.setMaximumSpeed(ant.getMaximumSpeed() - lowerSpeedBy);

		if (ant.getSpeed() > ant.getMaximumSpeed())
			ant.setSpeed(ant.getMaximumSpeed());

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

				init();
			}
		}

		this.setChanged();
		this.notifyObservers();
	}

	// t
	public void gameTick() {
		System.out.println("Game Clock has ticked");
		IIterator spiders = gameObjectList.getIterator();
		while (spiders.hasNext()) {
			GameObject obj = spiders.getNext();
			if (obj instanceof Moveable && obj instanceof Spider)
				((Spider) obj).setHeading(rand.nextInt(5 + 5) - 5);
		}

		IIterator movables = gameObjectList.getIterator();
		while (movables.hasNext()) {
			GameObject obj = movables.getNext();
			if (obj instanceof Moveable)
				((Moveable) obj).move(((Moveable) obj).getHeading(), ((Moveable) obj).getSpeed(), this.mapWidth,
						this.mapHeight);
		}

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
				init();
			}
		}

		gameClock++;

		this.setChanged();
		this.notifyObservers();
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