package game;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


public class Farmer extends Human {
	//private int value = (int) (Math.random()*101);,find probability if in range then sow
	private Behaviour behaviour = new WanderBehaviour();
	private char dirt = '.'; 
	private ArrayList<Integer> possiblelocation = new ArrayList<Integer>(); //An array which has possible locations of sowing crop
	public Farmer(String name) {
		super(name, 'F', 50);
		
	}
	public void sow(Location currentLocation) {
		//store possible locations in arraylist, if it doesnt have character dirt then remove x and y coordinates and place unripe crop at first x and y coordinate
		int x = currentLocation.x();//lets x,y coordinate of farmer
		int y = currentLocation.y();
		GameMap map = currentLocation.getMap();
		Integer[] otherList = new Integer[] {x, y+1, x-1, y, x+1, y, x, y+1, x+1, y+1, x-1, y-1};//stores possible locations in arraylist
		possiblelocation.addAll(Arrays.asList(otherList));
		for(int i = 1; i < possiblelocation.size(); i++) { //if a location doesnt have dirt then remove it from possible locations
			if (map.at(possiblelocation.get(i-1), possiblelocation.get(i)).getDisplayChar() != dirt) {
				possiblelocation.remove(i-1);
				possiblelocation.remove(i);
			}
		}
		Crop newcrop = new Crop("unripe",'U',true,20);
		//place newcrop if there are possible locations
		if(possiblelocation.size() > 1) {
			map.at(possiblelocation.get(0),possiblelocation.get(1)).addItem(newcrop);
		}
		Location unripecroplocation = new Location(map,possiblelocation.get(1),possiblelocation.get(0));
		//if turns are 0 then call ripeCrop method which ripes crop
		if(newcrop.getTurns() == 0) {
			ripeCrop(unripecroplocation, newcrop);
		}
	}
	public void ripeCrop(Location currentLocation, Crop unripe) {
		//removes unripe crop and places
		currentLocation.removeItem(unripe);
		Crop ripe = new Crop("ripe",'R',true,0);
		currentLocation.addItem(ripe);
		
	}


	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		//checks all actions, checks if there is object of FertiliseNewAction, if yes then returned
		for(int i = 0;i < actions.actions.size();i++) { //not sure how to add location part
			if (FertiliseNewAction.class.isInstance(actions.actions.get(i)) && sowAction.class.isInstance(lastAction)) {
				return actions.actions.get(i);
			}
			else if (HarvestAction.class.isInstance(actions.actions.get(i)) && FertiliseNewAction.class.isInstance(lastAction)){
				return actions.actions.get(i);
			}
			else if (sowAction.class.isInstance(actions.actions.get(i))) {
				return actions.actions.get(i);

			}

			
		}
		return new DoNothingAction();	
	}
}