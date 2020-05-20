package edu.monash.fit2099.engine;

import java.util.List;

import game.Behaviour;
import game.Human;
import game.Player;

/**
 * Special Action that allows humans and players to consume crop
 */
//
public class consumeHarvestedCrop extends Action {
	private Food crop;
	public consumeHarvestedCrop(Food crop) {
		this.crop = crop;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		//checks if food is in humans,players inventory if it is then call heal method and remove food from inventory
		if (actor.name == "Player" || actor.name == "Human") {
			List<Item> inventory = Actor.inventory;
			for (int i = 0;i < inventory.size();i++) {
				if(Food.class.isInstance(inventory.get(i)));
				{
					if(actor.maxHitPoints != actor.hitPoints) {
						actor.heal(20);
						actor.removeItemFromInventory(crop);
					}
				}
			}
		}
		return menuDescription(actor);
		
		// TODO Auto-generated method stub 
		
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.name + "healed";
	}

}