package edu.monash.fit2099.engine;

import java.util.Random;

import game.Farmer;

public abstract class sowAction extends Action {

	public String execute(Farmer farmer, GameMap map) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int int_random = rand.nextInt(101); 
		if (int_random >= 0 && int_random <=33) {
			farmer.sow(map.locationOf(farmer));
		}
		return menuDescription(farmer);
	}

	public String menuDescription(Farmer farmer) {
		// TODO Auto-generated method stub
		return "Farmer has sown crop";
	}

}
