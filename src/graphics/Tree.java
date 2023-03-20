package graphics;

//This class handles the enemy Tree. Main responsibilities are updating positions, via randomX.

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import static constants.Constants.SCREEN_HEIGHT;

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
		g.drawImage(tree, getX(), getY(), getSize(), getSize());
	}

	@Override
	public void update() {	
		if (getY() < -100) {
			// The position is exiting the screen, so we reset it
			this.setX(new RandomX().makeRandomPersons());
			this.setY(SCREEN_HEIGHT);
		} else {
			this.setY(getY() - 10);
			rect = new Rectangle2D(getX(), getY(), getSize()-25, getSize()-25);
		}
	}

	
	@Override
	public void updateSlow() {
		if (getY() < -100) {
			// The position is exiting the screen, so we reset it
			this.setX(new RandomX().makeRandomPersons());
			this.setY(SCREEN_HEIGHT);
		} else {
			this.setY(getY() - 5);
			rect = new Rectangle2D(getX(), getY(), getSize()-25, getSize()-25);
		}
	}

	@Override
	public Rectangle2D getRect() {
		return rect;
	}
	
	
	
}
