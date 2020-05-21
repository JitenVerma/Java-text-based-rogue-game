package edu.monash.fit2099.engine;

import java.util.List;

import game.Behaviour;
import game.Farmer;
import game.Player;

/**
 * Special Action that allows Farmers, Humans and Players to harvest crop.
 */
//Call execute when location of farmer and crop same
public class HarvestAction extends Action {
	private Crop ripecrop;
	public HarvestAction(Crop crop) {
		this.ripecrop = crop;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		//Get location of actor as for harvesting crop and player are in same location, remove ripe crop and add object of food.\
		Food newfood = new Food("food",'F',true);
		List<Item> items = map.locationOf(actor).getItems();
		for(int i = 0;i < items.size();i++) {
			if(Crop.class.isInstance(items.get(i)) && items.get(i).getDisplayChar() == 'R') {
				map.locationOf(actor).removeItem(this.ripecrop);
				if (actor.displayChar == 'F') {
					map.locationOf(actor).addItem(newfood);
				}
				if (actor.displayChar == 'H' || actor.displayChar == 'P') {
					//if actor is player and human then add food to inventory
					Player.addItemToInventory(newfood);
				}
			}
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