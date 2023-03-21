package graphics;

//This class handles the outprint of the new powerup redbull which enter the game in level 3. 
//
//It extends Beer since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from the superclass Beer.

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

public class Redbull extends Beer {

	private Image redbull;
	
	public Redbull(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			redbull = new Image(new FileInputStream("redbull.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(redbull, getX(), getY(), getSize(), getSize());
	}

}
