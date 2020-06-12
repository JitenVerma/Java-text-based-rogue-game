package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.NumberRange;

public class UseSniperRifleAction extends Action{
	private int timeSpentAiming;
	private Menu subMenu = new Menu();
	private Display display;
	private Actor target;
	
	public UseSniperRifleAction(Actor actor, GameMap map, Display display, int timeSpentAiming) {
		this.timeSpentAiming = 0;
		this.display = display;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		//Determine target
		if (this.target == null) {
			this.target = selectAim(actor, map);
		}
		
		//Provide options to aim or shoot
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new ShootSniperAction(this.target, this.timeSpentAiming));
			//Player can only aim up to 2 times
		if (this.timeSpentAiming < 2) {
			actions.add(new AimSniperRifleAction(this.target, this.timeSpentAiming));
		}
		Actions actions1 = new Actions();
		actions1.add(actions);
		Action selection = subMenu.showMenu(actor, actions1, display);
			//Increment timeSpentAiming or reset it
		if(selection instanceof AimSniperRifleAction) {
			incrementTimeSpentAiming();
		}
		else if (selection instanceof ShootSniperAction) {
			this.timeSpentAiming = 0;
		}
		
		String message = selection.execute(actor, map);
		
		return message;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " uses sniper rifle";
	}
	
	public Actor selectAim(Actor actor, GameMap map) {
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getXRange();
		ArrayList<Actor> targets = new ArrayList<Actor>();
		for (int x = xRange.min(); x <= xRange.max(); x++) {
			for (int y = yRange.min(); y <= yRange.max(); y++) {
				Location checkLocation = new Location(map, x, y);
				if (map.isAnActorAt(checkLocation)) {
					if (map.getActorAt(checkLocation).hasCapability(ZombieCapability.UNDEAD)) {
						targets.add(map.getActorAt(checkLocation));
					}
				}
			}
		}
		ArrayList<Action> actions = new ArrayList<Action>();
		for (Actor target: targets) {
			actions.add(new SelectTargetAction(target));
		}
		Actions actions1 = new Actions();
		actions1.add(actions);
		Action selection = subMenu.showMenu(actor, actions1, display);
		SelectTargetAction selectedTargetAction = (SelectTargetAction)selection;
		return selectedTargetAction.getTarget();
	}
	
	public int getTimeSpentAiming() {
		return this.timeSpentAiming;
	}
	
	public void incrementTimeSpentAiming() {
		this.timeSpentAiming += 1;
	}
}
