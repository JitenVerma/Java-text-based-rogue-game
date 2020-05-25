package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class CraftBehaviour implements Behaviour{

	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> myInventory = actor.getInventory();
		for (Item item: myInventory) {
			if (item instanceof ZombieArm) {
				return new CraftZombieClubAction();
			}
			else if (item instanceof ZombieLeg) {
				return new CraftZombieMaceAction();
			}
		}
		return null;
	}
}
