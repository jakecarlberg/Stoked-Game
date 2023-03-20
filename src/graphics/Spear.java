package graphics;

//This class handles the outprint of the new weapon Spear which enter the game in level 2. 
//
//It extends Snowball since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from the superclass Snowball.

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Spear extends Snowball {

	private Image spear;
	
	public Spear(double x, double y, double size) {
		super(x, y, size);
		
		try {
			spear = new Image(new FileInputStream("spear.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(spear, getX(), getY(), getSize(), getSize());
	}

}
