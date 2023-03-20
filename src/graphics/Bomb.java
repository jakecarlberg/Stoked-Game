package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import static constants.Constants.SCREEN_HEIGHT;

//This class has is managing the outprint and updating of powerup Bomb's position. 

//It calls on the class RandomX to update its "spawn"-position.

public class Bomb extends GameObjects {

	private Image bomb;
	private Rectangle2D rect;
	
	public Bomb(double x, double y, double size) {
		super(x, y, size);

		try {
			bomb = new Image(new FileInputStream("bomb.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(bomb, getX(), getY(), getSize(), getSize());
	}

	@Override
	public void update() {
		if (getY() < -100) {
			// The position is exiting the screen, so we reset it
			this.setX(new RandomX().makeRandomPersons());
			this.setY(SCREEN_HEIGHT+2000);
		} else {
			this.setY(getY() - 10);
			rect = new Rectangle2D(getX(), getY(), getSize()-10, getSize()-10);
		}
	}
	
	@Override
	public void updateSlow() {} //Empty so you cannot collect bombs while in slow-mode.

	@Override
	public Rectangle2D getRect() {
		return rect;
	}

}
