package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

//This class handles the outprint of the new player Player3 which enter the game in level 3. 
//
//It extends Player since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from the superclass Player.

public class Player3 extends Player {

	private Image flyer;
	
	public Player3(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			flyer = new Image(new FileInputStream("flyer.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(flyer, getX(), getY(), getSize(), getSize());
	}

}
