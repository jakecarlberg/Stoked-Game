package graphics;

//This class handles the weapon Snowball which is responsible of updating the position.

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import static constants.Constants.SCREEN_HEIGHT;

public class Snowball extends GameObjects {

	private Image snowball;
	private Rectangle2D rect;
	
	public Snowball(double x, double y, double size) {
		super(x+50, y+100, size);
		
		try {
			snowball = new Image(new FileInputStream("snowball.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(snowball, getX(), getY(), getSize(), getSize());
	}

	@Override
	public void update() {
		if (getY() > SCREEN_HEIGHT) {
//          The position is exiting the screen, so we reset it
//			playstate.getWeapons.remove(this);
		} else {
			this.setY(getY() + 50);
			rect = new Rectangle2D(getX(), getY(), getSize(), getSize());
		}
	}
		

	@Override
	public void updateSlow() {
		this.update();
	}

	@Override
	public Rectangle2D getRect() {
		return rect;
	}

	
	
	
}
