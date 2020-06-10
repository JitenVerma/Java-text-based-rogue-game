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
		if(checkHuman(map, display)) {
			display.println("Humans are extinct, Player has lost");
			map.removeActor(this);
		}
		if(checkZombies(map, display)) {
			display.println("Zombies and Mambo Marie are extinct, Player has won");
			map.removeActor(this);
		}
		placeMamboMarie(map,display);
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
	/**
	 * Checks if there are any humans in map except for player.If there aren't the game is over and player has lost. 
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 */
	private boolean checkHuman(GameMap map, Display display) {
		boolean checkHuman = true;
		for(int i = 0;i < map.getXRange().max() ;i++) {
			for(int j = 0;j < map.getYRange().max() ;j++) {
				if ((map.at(i,j).getActor() instanceof Human) && !(map.at(i,j).getActor() instanceof Player)){
					checkHuman = false;
					break;
					}
				}
		}
		return checkHuman;
	}
	/**
	 * Checks if there are any zombies and Mambo Marie in map.If there aren't the game is over and player has won. 
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 */
	private boolean checkZombies(GameMap map, Display display) {
		boolean zombieCheck = true;
		for(int i = 0;i < map.getXRange().max() ;i++) {
			for(int j = 0;j < map.getYRange().max() ;j++) {
				if (map.at(i,j).getActor() instanceof Zombie || map.at(i,j).getActor() instanceof MamboMarie){
					zombieCheck = false;
					break;
					}
				}
		}
		return zombieCheck;
	}
	/**
	 * Checks if there is Mambo Marie in map.If there is it returns true else returns false. 
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return MarioCheck if MamboMambie exists or not
	 */
	private boolean checkMamboMarie(GameMap map, Display display) {
		boolean MarioCheck = true;
		for(int i = 0;i < map.getXRange().max() ;i++) {
			for(int j = 0;j < map.getYRange().max() ;j++) {
				if (map.at(i,j).getActor() instanceof MamboMarie){
					MarioCheck = false;
					break;
					}
				}
		}
		return MarioCheck;
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
	
}

		

	
	
