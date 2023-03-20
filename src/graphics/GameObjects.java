package graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

//This class i Abstract and has it's main responsibility is to store the position of different objects.

//It gives every subclass the responsibility to update positions etc.

public abstract class GameObjects {

	private double x, y, size;
	private Rectangle2D rect;
	

	public GameObjects(double x, double y, double size) {
		this.x    = x;
		this.y    = y;
		this.size = size;
	}
	
	public abstract void draw(GraphicsContext g);
	
	public abstract void update();
	
	public abstract void updateSlow();

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
	public Rectangle2D getRect() {
		return rect;
	}
	

	
}