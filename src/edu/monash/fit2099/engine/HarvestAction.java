package edu.monash.fit2099.engine;

import game.Behaviour;
import game.Farmer;
import game.Player;

/**
 * Special Action that allows Farmers to drop ferlise.
 */
//Call execute when location of farmer and crop same
public class HarvestAction extends Action {
	private Crop unripecrop;
	public HarvestAction(Crop crop) {
		this.unripecrop = crop;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Location currentlocation = map.locationOf(actor);
		currentlocation.removeItem(this.unripecrop);
		Food newfood = new Food("food",'F',true);
		currentlocation.addItem(newfood);
		if (actor.name == "Player" || actor.name == "Human") {
			Player.addItemToInventory(newfood);
		}
		return menuDescription(actor);
		
		// TODO Auto-generated method stub 
		
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.name + "harvested crop";
	}

}