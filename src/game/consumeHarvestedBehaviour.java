package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.PickUpItemAction;
/**
 * A class that figures out when the Harvested Crop can be consumed by humans and players
 */
public class consumeHarvestedBehaviour implements Behaviour {


	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if(actor.getDisplayChar() == '@') {
			for(int i = 0; i < actor.getInventory().size(); i++) {
				if(HarvestedCrop.class.isInstance(actor.getInventory().get(i))) {
					return new consumeHarvestedAction((HarvestedCrop) actor.getInventory().get(i));
				}
			}
		}
		else if(actor.getDisplayChar() == 'H') {
			for(int i = 0; i < map.locationOf(actor).getItems().size(); i++) {
				if(map.locationOf(actor).getItems().get(i).getDisplayChar() == 'O') {
					Action pickUpItem = new PickUpItemAction((HarvestedCrop) map.locationOf(actor).getItems().get(i));
					pickUpItem.execute(actor, map);
					return new consumeHarvestedAction((HarvestedCrop)map.locationOf(actor).getItems().get(i) );
					}			
				}
		}
		
		return null;
	}
}
