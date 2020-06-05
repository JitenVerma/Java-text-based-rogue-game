package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;

public class World2 extends World {
	public World2(Display display) {
		super(display);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		System.out.println("ssssss");
		for(int i = 0;i < gameMaps.get(gameMaps.size() - 1).getXRange().max() ;i++) {
			System.out.println("sss");
			for(int j = 0;j < gameMaps.get(gameMaps.size() - 1).getYRange().max() ;j++) {
				System.out.println("ss");
				if (!(gameMaps.get(gameMaps.size() - 1).at(i,j).getActor() instanceof Human)){
					display.println("Humans got killed");
					display.println(endGameMessage());
					System.exit(0);
				}
			}
		}
	}

}
