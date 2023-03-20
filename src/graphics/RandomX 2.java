package graphics;

import java.util.Random;
import static constants.Constants.SCREEN_WIDTH;

public class RandomX {

	public RandomX() {}
	
	public double makeRandomPersons() {
		Random rand = new Random();
		return rand.nextInt(SCREEN_WIDTH - 100);
	}

}
