package edu.monash.fit2099.engine;

import java.util.List;

import game.Behaviour;
import game.Farmer;

/**
 * Special Action that allows Farmers to drop ferlise.
 */
//Call execute when location of farmer and crop same
public class FertiliseNewAction extends Action {
	protected Crop crop;
	public FertiliseNewAction(Crop crop) {
		this.crop = crop;
	}
	
	@Override
	public String execute(Actor farmer, GameMap map) {
		// TODO Auto-generated method stub
		List<Item> items = map.locationOf(farmer).getItems();
		for(int i = 0;i < items.size();i++) {
			if(Crop.class.isInstance(items.get(i)) && items.get(i).getDisplayChar() == 'U') {
				this.crop.setTurns(Math.max(this.crop.getTurns() - 10, 0));
				return menuDescription(farmer);
			}
		}
		return null;
	}
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "farmer fertilises crop";
	}

}