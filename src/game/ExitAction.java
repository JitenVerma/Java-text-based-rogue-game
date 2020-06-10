package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/** @author Ayesha
 * Special Action that gives player the choice to exit
 */
public class ExitAction extends Action {



	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		map.removeActor(actor);
		return "";
	}
		
	


	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Game Over";
	}
	@Override
	public String hotkey() {
		return "E";
	}
}
