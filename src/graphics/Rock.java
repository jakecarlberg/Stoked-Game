package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
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
