package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

//This class handles the outprint of the new enemie Bird which enters in level 3. 
//
//It extends Snowboard since it shares the same functions in the game. 
//
//Hence it makes sence to override methods from the superclass Snowboard.

public class Bird extends Snowboard {

	private Image bird;
	
	public Bird(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			bird = new Image(new FileInputStream("bird.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(bird, getX(), getY(), getSize(), getSize());
	}

}
