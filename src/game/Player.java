package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;


/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private int ammunition;
	private int timeSpentAiming;
	
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
		this.ammunition = 0;
		this.timeSpentAiming = 0;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		List<Item> myInventory = this.getInventory();
		for (Item item: myInventory) {
			if (item instanceof ZombieArm) {
				actions.add(new CraftZombieClubAction());
			}
			if (item instanceof ZombieLeg) {
				actions.add(new CraftZombieMaceAction());
			}
			if (item instanceof Shotgun) {
				if(getAmmunition() != 0) {
					actions.add(new FireShotgunAction(this, map, display));
				}
			}
			if (item instanceof SniperRifle) {
				if(getAmmunition() != 0) {
					if(lastAction instanceof UseSniperRifleAction) {
						actions.add(lastAction);
					}
					else {
						setTimeSpentAiming(0);
						actions.add(new UseSniperRifleAction(this, map, display, this.timeSpentAiming));
					}
				}
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

	/**
	 * A getter for ammunition
	 * @return integer value of remaining ammunition
	 */
	public int getAmmunition() {
		return this.ammunition;
	}
	/**
	 * a setter for ammunition 
	 * @param newAmmunition the new amount of ammunition
	 */
	public void setAmmunition(int newAmmunition) {
		this.ammunition = newAmmunition;
	}
	/**
	 * A getter for timeSpentAiming
	 * @return integer value of the amount of turns spent aiming
	 */
	public int getTimeSpentAiming() {
		return this.timeSpentAiming;
	}
	
	/**
	 * A setter for timeSpentAiming
	 * @param newTimeSpentAiming the new value of timeSpentAiming
	 */
	public void setTimeSpentAiming(int newTimeSpentAiming) {
		this.timeSpentAiming = newTimeSpentAiming;
	}
	
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		this.timeSpentAiming = 0;
	}
	
}

		

	
	
