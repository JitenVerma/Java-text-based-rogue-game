package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action that allows humans and players to consume crop
 */
//
public class consumeHarvestedAction extends Action {
	/**
	 * Constructor.
	 *
	 * @param crop crop which has to be harvested
	 */
	private HarvestedCrop crop;
	public consumeHarvestedAction(HarvestedCrop crop) {
		this.crop = crop;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		//checks if food is in humans,players inventory if it is then call heal method and remove food from inventory
		
		actor.heal(20);
		actor.removeItemFromInventory(crop);
		return menuDescription(actor);
		
		// TODO Auto-generated method stub 
		
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " healed 20 hitpoints";
		
	}



}