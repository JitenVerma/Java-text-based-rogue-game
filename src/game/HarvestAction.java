package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;


/**@author Ayesha
 * Special Action that allows Farmers and Players to harvest crop.
 */

public class HarvestAction extends Action {
	/**
	 * Constructor.
	 * 
	 * @param locationHarvest location at which ripe crop will be harvested
	 */
	private Location locationHarvest;
	public HarvestAction(Location locationHarvest) {
		this.locationHarvest = locationHarvest;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		//Get location of actor as for harvesting crop and player are in same location, remove ripe crop and add object of food.\
		if (actor.getDisplayChar() == 'F') {
			this.locationHarvest.addItem(new HarvestedCrop());
		}
		if (actor.getDisplayChar() == '@') {
		//if actor is player then add food to inventory
			Action pickUpItem = new PickUpItemAction(new HarvestedCrop());
			pickUpItem.execute(actor, map);
		}
		return menuDescription(actor);
		
		// TODO Auto-generated method stub 
		
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " harvested crop";
	}



}