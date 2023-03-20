package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coconut extends Beer {

//This class handles the outprint of the new powerup Coconut which enter the game in level 2. 
//
//It extends Beer since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from it's superclass Beer.	
	
	private Image spear;
	
	public Coconut(double x, double y, double size) {
		super(x, y, size);
		
		try {
			spear = new Image(new FileInputStream("coconut.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(spear, getX(), getY(), getSize(), getSize());
	}

}
