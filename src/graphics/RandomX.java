package graphics;

//This class is respnsible for randomizing numbers to different objects when updating posiitions.

//This class makes the game though and enjoyable since the user has no idea of where the enemies will come up next.

import java.util.Random;
import static constants.Constants.SCREEN_WIDTH;

public class RandomX {

	public RandomX() {}
	
	public int makeRandomPersons() {
		Random rand = new Random();
		return rand.nextInt(SCREEN_WIDTH - 100);
	}

}
