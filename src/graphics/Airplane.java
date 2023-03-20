package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//This class handles the outprint of the new static enemie Airplane which enter the game in level 3. 
//
//It extends Tree since it shares the same functions in the game. 
//
//Hence it makes sence to override methods from the superclass Tree.

public class Airplane extends Tree {

	private Image airplane;
	
	public Airplane(double x, double y, double size) {
		super(x, y, size);
		
		try {
			airplane = new Image(new FileInputStream("airplane.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(airplane, getX(), getY(), getSize(), getSize());
	}


}
