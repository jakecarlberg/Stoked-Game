package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

public class BabySurfer extends Snowboard {

	private Image babySurf;
	
	public BabySurfer(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			babySurf = new Image(new FileInputStream("surf-kid.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(babySurf, getX(), getY(), getSize(), getSize());
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
