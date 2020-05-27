package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author Ayesha
 * Special Action that allows humans and players to consume crop
 */
//
public class ConsumeHarvestedAction extends Action {
	/**
	 * Constructor.
	 *
	 * @param crop crop which has to be harvested
	 */
	private HarvestedCrop crop;
	
	public ConsumeHarvestedAction(HarvestedCrop crop) {
		this.crop = crop;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		//checks if food is in humans,players inventory if it is then call heal method and remove food from inventory
		
		actor.removeItemFromInventory(crop);
		actor.heal(20);
		return menuDescription(actor);
		
		// TODO Auto-generated method stub 
		
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " consumes harvested crop to heal 20 hitpoints";
		
	}



}

