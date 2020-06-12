package game;
import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

/**
 * Class representing the game world, including the locations of all Actors, the
 * player, and the playing grid.It is overrides World so that it can end the game 
 * when there are no more humans and no more zombies and mambo marie.
 * This class also spawns Mambo Marie.
 * @author Ayesha
 */
public class Endgame extends World {

	public Endgame(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}
		GameMap compound = gameMaps.get(0);
		// This loop is basically the whole game
		while (stillRunning() && !(checkHuman(compound,display)) && !((checkZombies(compound,display)))) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
				placeMamboMarie(playersMap,display);
			}
			
			if (checkHuman(compound,display)){
				display.println("Player lost, Humans extinct");
			}
			if (checkZombies(compound,display)){
				display.println("Player won, Zombies and Mambo Marie extinct");
			}


		}


		display.println(endGameMessage());
	}
	/**
	 * Checks if there are any humans in map except for player.If there aren't it returns true else false. 
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return checkHuman if Humans exists or not, returns true if it does not else false
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
	 * Checks if there are any zombies and mambo marie in map.If there aren't it returns true else false. 
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return zombieCheck if zombies and mambo marie exists or not, returns true if it does not else false
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
	@Override
	protected boolean stillRunning() {
		
		return actorLocations.contains(player);
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

}
