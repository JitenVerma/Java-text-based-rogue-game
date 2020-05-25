package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	//private consumeHarvestedBehaviour behaviour = new consumeHarvestedBehaviour();
	private Behaviour[] behaviours = {
			new consumeHarvestedBehaviour(),
			new CraftBehaviour()
	};
	
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(ZombieCapability.PLAYER);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
//			if (consumeHarvestedAction.class.isInstance(action)) {
//				return action;
//			}
//			if (action instanceof CraftZombieClubAction) {
//				return action;
//			}
//			if (action instanceof CraftZombieMaceAction) {
//				return action;
//			}
//			if (action != null) {
//				return action;
//			}
			if (lastAction.getNextAction() != null)
				return lastAction.getNextAction();
		}
		List<Item> myInventory = this.getInventory();
		for (Item item: myInventory) {
			if (item instanceof ZombieArm) {
				actions.add(new CraftZombieClubAction());
			}
			else if (item instanceof ZombieLeg) {
				actions.add(new CraftZombieMaceAction());
			}
		}
		
		return menu.showMenu(this, actions, display);
		
	}
}
