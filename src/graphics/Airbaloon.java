package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

//This class handles the outprint of the new enemie Airballon which enter the game in level 3. 
//
//It extends Snowboard since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from the superclass Snowboard.

public class Airbaloon extends Snowboard {

	private Image airbaloon;
	
	public Airbaloon(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			airbaloon = new Image(new FileInputStream("airbaloon.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(airbaloon, getX(), getY(), getSize(), getSize());
	}
	

}
