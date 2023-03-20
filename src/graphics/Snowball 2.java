package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
		if (getY() > 600) {
			// The position is exiting the screen, so we reset it
//			playstate.getWeapons.remove(this);
		}
		
		g.drawImage(snowball, getX(), getY(), 20, 20);

	}

	@Override
	public void update() {
		this.setY(getY() + 50);
		rect = new Rectangle2D(getX(), getY(), 20, 20);
	}
	
	

	public Rectangle2D getRect() {
		return rect;
	}

	@Override
	public void updateSlow() {
		this.setY(getY() + 50);
		rect = new Rectangle2D(getX(), getY(), 20, 20);
		
	}

	
	
	
}
