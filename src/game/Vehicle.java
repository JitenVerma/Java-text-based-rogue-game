package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;

public class Vehicle extends Item{

	public Vehicle(GameMap map, String direction) {
		super("Vehicle", 'V', false);
		// TODO Auto-generated constructor stub
		this.allowableActions.add(new MoveActorAction(map.at(1,1), direction));
	}
}
