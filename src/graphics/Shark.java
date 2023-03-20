package graphics;

//This class handles the outprint of the new enemy Shark which enter the game in level 2. 
//
//It extends Snowboard since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from the superclass Snowboard.

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

public class Shark extends Snowboard {

	private Image shark;
	
	public Shark(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			shark = new Image(new FileInputStream("shark.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(shark, getX(), getY(), getSize(), getSize());
	}

}
