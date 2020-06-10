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
	private Menu subMenu = new Menu();
	private Display display;
	
	public FireShotgunAction(Actor actor, GameMap map, Display display) {
		this.display = display;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		Location location = map.locationOf(actor);
		List<Exit> exits =  location.getExits();
		for (Exit exit: exits) {
			actions.add(new ShootAction(exit.getDestination(), exit.getName(), exit.getHotKey()));
		}
		Actions actions1 = new Actions();
		actions1.add(actions);
		Action shotDirection = subMenu.showMenu(actor, actions1, display);
		
		return shotDirection.execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " shoot shotgun";
	}

}
