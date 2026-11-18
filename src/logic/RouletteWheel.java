package logic;

/**
 * Class that simulates the roulette wheel by giving 
 * a randomized value between 0 and 35 (both included)
 * @author Sergio Fernández López 
 * UO225811@uniovi.es
 *
 */
public class RouletteWheel {
	public static int getValue() {
		return (int)(Math.random()*36);
	}
}
