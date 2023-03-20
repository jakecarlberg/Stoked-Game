package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//This class handles the outprint of the new weapon Knife which enters in level 3. 
//
//It extends Snowball since it shares the same functions in the game. 
//
//Hence it makes sence to override methods from the superclass Snowball.

public class Knife extends Snowball {

	private Image knife;
	
	public Knife(double x, double y, double size) {
		super(x, y, size);
	
		try {
			knife = new Image(new FileInputStream("knife.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(knife, getX(), getY(), getSize(), getSize());
	}

}
