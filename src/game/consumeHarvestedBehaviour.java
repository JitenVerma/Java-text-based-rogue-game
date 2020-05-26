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
		for (int i = 0; i < map.locationOf(actor).getItems().size(); i++) {
			if (map.locationOf(actor).getItems().get(i).getDisplayChar() == 'O') {
				Action pickUpItem = new PickUpItemAction((HarvestedCrop) map.locationOf(actor).getItems().get(i));
				pickUpItem.execute(actor, map);
				
				return new ConsumeHarvestedAction((HarvestedCrop) map.locationOf(actor).getItems().get(i));
			}
		}

		return null;
	}
}
