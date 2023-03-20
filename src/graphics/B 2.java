package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class B extends Beer {

	private Image car;
	
	public B(double x, double y, double size) {
		super(x, y, size);
		// TODO Auto-generated constructor stub
		
		try {
			car = new Image(new FileInputStream("car.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
//		super.draw(g);
		g.drawImage(car, getX(), getY(), getSize(), getSize());	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	@Override
	public void updateSlow() {
		// TODO Auto-generated method stub
		super.updateSlow();
	}

	@Override
	public Rectangle2D getRect() {
		// TODO Auto-generated method stub
		return super.getRect();
	}

	
}
