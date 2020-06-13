package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;


/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		Endgame world = new Endgame(new Display());
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(42, 15));
		
	    // Place some random humans
		String[] humans = {"Carlton","May","Carl","Judy"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
			//test push
		}
		//place a farmer
		String[] farmernames = {"Fred","Fed","Mays","Mayson"};
		ArrayList<Farmer> farmers = new ArrayList<Farmer>();
		int fx, fy;
		for (String name : farmernames) {
			do {
				fx = (int) Math.floor(Math.random() * 20.0 + 30.0);
				fy = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(fx, fy).containsAnActor());
			farmers.add(new Farmer(name));
			gameMap.at(fx,  fy).addActor(new Farmer(name));	
		}
		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());
		gameMap.at(50, 20).addItem(new Plank());
		
		// FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	
		
		//Create town map
		List<String> map2 = Arrays.asList(
				"................................................................................",
				"................................................................................",
				"................................................+............########...........",
				"...............................................+++.........###......###.........",
				"...............................................+++.......###..........##........",
				"................................................+.......###............##.......",
				"......................................................###..............##.......",
				"....................................................###................##.......",
				"..................................................###..................##.......",
				"................................................###....................##.......",
				"................................................#......................##.......",
				"................................................#......................##.......",
				"................................................................................",
				"................................................................................",
				"................................................#...............................",
				"................................................#......................##.......",
				".................................................###...................##.......",
				"...................................................###.................##.......",
				".....................................................###...............##.......",
				"................................................+......###.............##.......",
				"...............................................+++.......###...........##.......",
				"...............................................+++.........###.........##.......",
				"................................................+.............###......##.......",
				"...............................................................########.........",
				"................................................................................");
		GameMap townMap = new GameMap(groundFactory, map2);
		world.addGameMap(townMap);
		
		//Add a vehicle to the original map
		gameMap.at(43, 22).addItem(new Vehicle(townMap, "to town"));
		
		//Add a vehicle to townmap to travel back to the compound
		townMap.at(30, 11).addItem(new Vehicle(gameMap, "to compound"));
		
		//Populate town map with humans
		String[] humans1 = {"Jose", "Bella", "Sara", "Priya", "Caitlin",
				"Samuel", "Charles", "John", "Otis", "Hamzah"};
		int x1, y1;
		for (String name : humans1) {
			do {
				x1 = (int) Math.floor(Math.random() * 11.0 + 59.0);
				y1 = (int) Math.floor(Math.random() * 15.0 + 5.0);
			} 
			while (townMap.at(x1, y1).containsAnActor());
			townMap.at(x1,  y1).addActor(new Human(name));	
		}
		
		//Populate town map with farmers
		String[] farmernames1 = {"Fred","Fed","Mays","Mayson"};
		ArrayList<Farmer> farmers1 = new ArrayList<Farmer>();
		int fx1, fy1;
		for (String name : farmernames1) {
			do {
				fx1 = (int) Math.floor(Math.random() * 20.0 + 30.0);
				fy1 = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(fx1, fy1).containsAnActor());
			farmers1.add(new Farmer(name));
			gameMap.at(fx1,  fy1).addActor(new Farmer(name));	
		}
		//Populate town map with zombies 
		townMap.at(30, 20).addActor(new Zombie("Groan"));
		townMap.at(30,  18).addActor(new Zombie("Boo"));
		townMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		townMap.at(10, 18).addActor(new Zombie("Mortalis"));
		townMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		townMap.at(12, 12).addActor(new Zombie("Aaargh"));	
		
		//Populate town map with weapons
		townMap.at(2, 5).addItem(new Plank());
		townMap.at(51, 3).addItem(new Shotgun(20));
		townMap.at(51, 20).addItem(new SniperRifle(40));
		townMap.at(52, 3).addItem(new Ammunition());
		townMap.at(52, 20).addItem(new Ammunition());
		townMap.at(1, 2).addItem(new Shotgun(20));
		townMap.at(2, 1).addItem(new SniperRifle(40));
		townMap.at(1, 3).addItem(new Ammunition());
		townMap.at(3, 1).addItem(new Ammunition());
		townMap.at(20, 5).addItem(new Ammunition());
		
		world.run();
	}
}
