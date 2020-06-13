package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;

public class FireShotgunAction extends Action{
	/**
	 * Submenu to allow player input for direction to fire
	 */
	private Menu subMenu = new Menu();
	/**
	 * the I/O object to which messages may be written
	 */
	private Display display;
	
	/**
	 * Constructor 
	 * 
	 * @param actor the Actor firing the shotgun
	 * @param map the map the shotgun is being fired on
	 * @param display the I/O object to which messages may be written
	 */
	public FireShotgunAction(Actor actor, GameMap map, Display display) {
		this.display = display;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		Location location = map.locationOf(actor);
		List<Exit> exits =  location.getExits();
		for (Exit exit: exits) {
			actions.add(new ShootAction(exit.getName()));
		}
		Actions actions1 = new Actions();
		actions1.add(actions);
		Action shotDirection = subMenu.showMenu(actor, actions1, display);
		
		//Decrement ammo from player
		Player player = (Player)actor;
		player.setAmmunition(player.getAmmunition() - 1);
		
		return shotDirection.execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " shoot shotgun";
	}
}
