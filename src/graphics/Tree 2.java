package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tree extends GameObjects {

	private Image tree;
	private Rectangle2D rect;
	
	public Tree(double x, double y, double size) {
		super(x, y, size);
		
		try {
			tree = new Image(new FileInputStream("tree.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		if (getY() < -100) {
			// The position is exiting the screen, so we reset it
			this.setX(new RandomX().makeRandomPersons());
			this.setY(800);
		}
		
		g.drawImage(tree, getX(), getY(), getSize(), getSize());
	}

	@Override
	public void update() {	
		this.setY(getY() - 10);
		rect = new Rectangle2D(getX(), getY(), getSize()-20, getSize()-20);
	}

	public Image getTree() {
		return tree;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	@Override
	public void updateSlow() {
		this.setY(getY() - 5);
		rect = new Rectangle2D(getX(), getY(), getSize()-20, getSize()-20);
		
	}
	
	
	
}
