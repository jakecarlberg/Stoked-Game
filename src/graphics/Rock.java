package graphics;

//This class handles the outprint of the new enemy Rock which enter the game in level 2. 
//
//It extends Tree since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from the superclass Tree.

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rock extends Tree {

	private Image rock;
	
	public Rock(double x, double y, double size) {
		super(x, y, size);
		
		try {
			rock = new Image(new FileInputStream("rock.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(rock, getX(), getY(), getSize(), getSize());
	}

}
