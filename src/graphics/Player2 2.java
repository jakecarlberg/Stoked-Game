package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

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
