package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

//This class handles the outprint of the new enemie Babysurfer which enters in level 2. 
//
//It extends Snowboard since it shares the same functions in the game. 
//
//Hence it makes sence to override methods from the superclass Snowboard.

public class BabySurfer extends Snowboard {

	private Image babySurf;
	
	public BabySurfer(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			babySurf = new Image(new FileInputStream("surf-kid.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(babySurf, getX(), getY(), getSize(), getSize());
	}

}
