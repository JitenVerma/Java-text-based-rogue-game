package game;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * Class representing the game world, including the locations of all Actors, the
 * player, and the playing grid.It is overrides World so that it can end the game 
 * when there are no more humans and no more zombies and mambo marie
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
		GameMap map = actorLocations.locationOf(player).map();
		// This loop is basically the whole game
		while (stillRunning() && !(checkHuman(map,display)) && !((checkZombies(map,display)))) {
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
			}
			if (checkHuman(map,display)){
				display.println("Player lost, Humans extinct");
			}
			if (checkZombies(map,display)){
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
	
	private boolean checkMamboMarie() {
		boolean MamboMarieCheck = true;
		for (GameMap map: gameMaps) {
			for(int i = 0;i < map.getXRange().max() ;i++) {
				for(int j = 0;j < map.getYRange().max() ;j++) {
					if (map.at(i,j).getActor().hasCapability(ZombieCapability.WITCH)){
						MamboMarieCheck = false;
						break;
					}
				}
			}
		}
		return MamboMarieCheck;
	}

}
