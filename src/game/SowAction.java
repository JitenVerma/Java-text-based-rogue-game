package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.UnripeCrop;


/** @author Ayesha
 * Special Action that allows Farmers to drop sow crop.
 */
public class SowAction extends Action {
	/**
	 * Constructor.
	 *
	 * @param locationSow location at which crop will be sown
	 */
		private Location locationSow;
		public SowAction(Location locationSow) {
			this.locationSow = locationSow;
		}

		@Override
		public String execute(Actor actor, GameMap map) {
			// TODO Auto-generated method stub
			this.locationSow.addItem(new UnripeCrop());
			return menuDescription(actor);
		}

		@Override
		public String menuDescription(Actor actor) {
			// TODO Auto-generated method stub
			return actor + " sows crop";
		}

	}


