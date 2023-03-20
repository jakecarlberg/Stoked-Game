package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

public class Player3 extends Player {

	private Image flyer;
	
	public Player3(double x, double y, double size, PlayState playState) {
		super(x, y, size, playState);
		
		try {
			flyer = new Image(new FileInputStream("flyer.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(flyer, getX(), getY(), getSize(), getSize());
	}

//	@Override
//	public void moveLeft() {
//		super.moveLeft();
//	}
//
//	@Override
//	public void moveRight() {
//		super.moveRight();
//	}

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
