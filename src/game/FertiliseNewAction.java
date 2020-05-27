package game;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;



/**@author Ayesha
 * Special Action that allows Farmers to fertilise a crop.
 */
public class FertiliseNewAction extends Action {
	/**
	 * Constructor.
	 *
	 * @param unripe unripe crop which has to be fertilised 
	 */
	UnripeCrop unripe;
	public FertiliseNewAction(UnripeCrop crop) {
		this.unripe = crop;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		unripe.executeFertilize();
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " fertilizes crop";
	}

}
