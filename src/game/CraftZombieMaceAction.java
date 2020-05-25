package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class CraftZombieMaceAction extends Action{

	public CraftZombieMaceAction() {
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> myInventory = actor.getInventory();
		//Iterate through the inventory and find the index where Zombie Leg is stored
		int i = 0;
		for (Item item: myInventory) {
			if (item instanceof ZombieLeg) {
				break;
			}
			i++;
		}
		ZombieMace zombieMace = new ZombieMace();
		Item zombieLeg = myInventory.get(i);
		actor.removeItemFromInventory(zombieLeg);
		actor.addItemToInventory(zombieMace);
		return menuDescription(actor);
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts Zombie Mace";
	}

}
