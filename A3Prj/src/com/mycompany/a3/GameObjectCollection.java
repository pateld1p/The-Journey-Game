package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class GameObjectCollection implements ICollection {

	private ArrayList<GameObject> collection;
	
	//private Vector<GameObject> collection;
	
	public GameObjectCollection() {
		//collection = new Vector<GameObject>();
		collection = new ArrayList<GameObject>();	
	}

	//adds an Object to the collection.
	public void add(Object newObject) {
		//collection.addElement((GameObject)newObject);
		
		if (newObject instanceof GameObject) {
			collection.add((GameObject) newObject);
		}
	}

	 // returns an IIterator using the private class
	public IIterator getIterator() {
		return new GameObjectIterator();
	}

	// clears the GameObjectCollection 
	public void clear() {
		collection.clear();		
	}
	//This class acts as an Iterator for GameObjects.
	private class GameObjectIterator implements IIterator{
		private int currIndex;
		
		public GameObjectIterator() {
			currIndex = -1;
		}
		
		public int getCurrIndex() {
			return currIndex;
		}
		public boolean hasNext() {
			//if the collection is empty --> false
			if(collection.size() <= 0) {
				return false;
			}
			//if the current index is at the last element --> false
			if(currIndex == collection.size() - 1) {
				return false;
			}
			
			return true;
		}
		
		/*
		 * Gets and returns the next object in the collection
		 */
		public GameObject getNext() {
			currIndex++;
			return(collection.get(currIndex));
		}
		
	}
}