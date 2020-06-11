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
		placeMamboMarie(map,display);
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
	 * Checks if there is Mambo Marie in map.If there is it returns true if it doesnt exist else returns false. 
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return MarioCheck if MamboMambie exists or not, returns true if it does not else false
	 */
	private boolean checkMamboMarie(GameMap map, Display display) {
		
		for(int i = 0;i < map.getXRange().max() ;i++) {
			for(int j = 0;j < map.getYRange().max() ;j++) {
				if (map.at(i,j).getActor() instanceof MamboMarie){
					return false;
					}
				}
		}
		return true;
	}
	/**
	 * Returns possible locations at which Mambo Marie can be placed. 
	 * @param map        the map containing the Actor
	 * @return a random edge possible location in which Mambo Marie can be placed
	 */
	private Location validLocation(GameMap map){
		ArrayList<Location> validLocations = new ArrayList<>();
		for(int i = 0;i < map.getXRange().max() ;i++) {
			for(int j = 0;j < map.getYRange().max() ;j++) {
				if(i == 0 || i == map.getXRange().max() - 1 || j == 0 || j == map.getYRange().max() - 1) {
					Location newLocation = map.at(i,j);
					validLocations.add(newLocation);
				}
			}
		}
		int randInt2 = new Random().nextInt(validLocations.size() - 1);
		return validLocations.get(randInt2);
	}
	/**
	 * Places Mambo Marie on map if ramdom integer from 0 to 5 generated and no other conscious Mambo Marie instance exists on map. 
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 */
	void placeMamboMarie(GameMap map,Display display) {
		int randomNumber = new Random().nextInt(100);
		MamboMarie Mambo = new MamboMarie();
		if(randomNumber >= 0 && randomNumber <= 5) {
			Location mmLocation = validLocation(map);
			if(checkMamboMarie(map, display) && Mambo.isConscious()) {
				mmLocation.addActor(Mambo);
			}
			
		}
	}
	
	public int getAmmunition() {
		return this.ammunition;
	}
	
	public void setAmmunition(int newAmmunition) {
		this.ammunition = newAmmunition;
	}
	
	public int getTimeSpentAiming() {
		return this.timeSpentAiming;
	}
	
	public void setTimeSpentAiming(int newTimeSpentAiming) {
		this.timeSpentAiming = newTimeSpentAiming;
	}
	
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		this.timeSpentAiming = 0;
	}
	
}

		

	
	
