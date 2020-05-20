package game;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Crop;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.FertiliseNewAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.HarvestAction;
import edu.monash.fit2099.engine.Location;

public class Farmer extends Human {
	//private int value = (int) (Math.random()*101);,find probability if in range then sow
	private Behaviour behaviour = new WanderBehaviour();
	private char dirt = '.'; 
	private ArrayList<Integer> possiblelocation = new ArrayList<Integer>(); //An array which has possible locations of sowing crop
	public Farmer(String name,char displayChar, int hitPoints) {
		super(name, 'F', 50);
	}
	public void sow(Location currentLocation) {
		int x = currentLocation.x();//lets x,y coordinate of farmer
		int y = currentLocation.y();
		GameMap map = currentLocation.getMap();
		Integer[] otherList = new Integer[] {x, y+1, x-1, y, x+1, y, x, y+1, x+1, y+1, x-1, y-1};//stores possible locations in arraylist
		possiblelocation.addAll(Arrays.asList(otherList));
		for(int i = 0; i < possiblelocation.size(); i++) { //if a location doesnt have dirt then remove it from possible locations
			if (map.at(possiblelocation.get(i), possiblelocation.get(i + 1)).getDisplayChar() != dirt) {
				possiblelocation.remove(i);
				possiblelocation.remove(i + 1);
			}
		}
		Crop newcrop = new Crop("unripe",'U',true,20);
		map.at(possiblelocation.get(0),possiblelocation.get(1)).addItem(newcrop);
	}
	public void ripeCrop(Location currentLocation, Crop unripe) {
		if (unripe.getTurns() == 0) {
			currentLocation.removeItem(unripe);
			Crop ripe = new Crop("ripe",'R',true,0);
			currentLocation.addItem(ripe);
		}
	}


	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for(int i = 0;i < actions.actions.size();i++) {
			if (FertiliseNewAction.class.isInstance(actions.actions.get(i)))
				return actions.actions.get(i);
			else if (FertiliseNewAction.class.isInstance(lastAction) && HarvestAction.class.isInstance(actions.actions.get(i))) {
				return actions.actions.get(i);
			}
			
		}
		return new DoNothingAction();	
	}
}