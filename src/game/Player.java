package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	//private consumeHarvestedBehaviour behaviour = new consumeHarvestedBehaviour();
	
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		//If the zombie contains a Zombie Arm or Leg it can craft into new weapons
		List<Item> myInventory = this.getInventory();
		for (Item item: myInventory) {
			if (item instanceof ZombieArm) {
				actions.add(new CraftZombieClubAction());
			}
			if (item instanceof ZombieLeg) {
				actions.add(new CraftZombieMaceAction());
			}
			if (item instanceof HarvestedCrop) {
				HarvestedCrop harvestedCrop = (HarvestedCrop)item;
				actions.add(new ConsumeHarvestedAction(harvestedCrop));
			}
			if (item instanceof RipeCrop) {
				HarvestedCrop harvestedCrop = new HarvestedCrop();
				actions.add(new ConsumeHarvestedAction(harvestedCrop));
				this.removeItemFromInventory(harvestedCrop);
			}
		}
		actions.add(new ExitAction());
		
		
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		
		return menu.showMenu(this, actions, display);
		
	}
}
	
	
