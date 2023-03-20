package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

//This class handles the outprint of the new player Player2 which enter the game in level 2. 
//
//It extends Player since it shares the same functions in the game. 
//
//Hence it makes sense to override methods from the superclass Player.

public class Player2 extends Player {

	private Image surf;
	
	public Player2(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			surf = new Image(new FileInputStream("surf.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(surf, getX(), getY(), getSize(), getSize());
	}

}
