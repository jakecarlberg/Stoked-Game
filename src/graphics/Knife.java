package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void updateSlow() {
		super.updateSlow();
	}

	@Override
	public Rectangle2D getRect() {
		return super.getRect();
	}
	
	

}
