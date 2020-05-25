package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class CraftZombieClubAction extends Action{

	public CraftZombieClubAction() {
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> myInventory = actor.getInventory();
		//Iterate through the inventory and find the index where Zombie Arm is stored
		int i = 0;
		for (Item item: myInventory) {
			if (item instanceof ZombieArm) {
				break;
			}
			i++;
		}
		ZombieClub zombieClub = new ZombieClub();
		Item zombieArm = myInventory.get(i);
		actor.removeItemFromInventory(zombieArm);
		actor.addItemToInventory(zombieClub);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts Zombie Club";
	}

}
