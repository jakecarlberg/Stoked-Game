package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

public class Snowboard extends GameObjects {

	private Image snowboard;
	private Rectangle2D rect;
	private PlayState playState;
	
	public Snowboard(double x, double y, double size, PlayState playState) {
		super(x, y, size);
		this.playState = playState;
		
		try {
			snowboard = new Image(new FileInputStream("snowboard.png"));
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
		
		g.drawImage(snowboard, getX(), getY(), getSize(), getSize());

	}

	@Override
	public void update() {
		this.setY(getY() - 15);
		rect = new Rectangle2D(getX(), getY(), getSize()-20, getSize()-20);
		
		for (GameObjects w : playState.getWeapons()) {
			if (rect.intersects(w.getRect())) {
				this.setX(new RandomX().makeRandomPersons());
				this.setY(800);
				playState.getWeapons().remove(w); //Skapar error!!!!
				playState.setScore(playState.getScore() + 100);
			}
		}
	}

	public Rectangle2D getRect() {
		return rect;
	}

	@Override
	public void updateSlow() {
		this.setY(getY() - 7.5);
		rect = new Rectangle2D(getX(), getY(), getSize()-20, getSize()-20);
		
		for (GameObjects w : playState.getWeapons()) {
			if (rect.intersects(w.getRect())) {
				this.setX(new RandomX().makeRandomPersons());
				this.setY(800);
				playState.getWeapons().remove(w); //Skapar error!!!!
				playState.setScore(playState.getScore() + 100);
			}
		}
		
	}

	
	
	
}
