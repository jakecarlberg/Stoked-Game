package graphics;

//This class handled the enemy Snowboard. Main responsibilities are updating position and seeing if it collides with a weapon.

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;
import static constants.Constants.SCREEN_HEIGHT;

public class Snowboard extends GameObjects {

	private Image snowboard;
	private Rectangle2D rect;
	private PlayState playState;
	
	public Snowboard(double x, double y, double size, PlayState playState) {
		super(x, y, size);
		this.playState = playState;
		
		try {
			snowboard = new Image(new FileInputStream("snowboarder.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(snowboard, getX(), getY(), getSize(), getSize());
	}

	@Override
	public void update() {
		if (getY() < -100) {
			// The position is exiting the screen, so we reset it
			this.setX(new RandomX().makeRandomPersons());
			this.setY(SCREEN_HEIGHT);
		} else {
			this.setY(getY() - 15);
			rect = new Rectangle2D(getX(), getY(), getSize()-20, getSize()-20);
		}
		
		for (GameObjects w : playState.getWeapons()) {
			if (rect.intersects(w.getRect())) {
				this.setX(new RandomX().makeRandomPersons());
				this.setY(SCREEN_HEIGHT);
				playState.getWeapons().remove(w); //Skapar error!!!!
				if (playState.getScore() > 900 && playState.getScore() < 1000) {
					playState.levelUp1();
				} else if (playState.getScore() > 1900 && playState.getScore() < 2000) {
					playState.levelUp2();
				}
				playState.setScore(playState.getScore() + 100);
			}
		}
	}

	
	@Override
	public void updateSlow() {
		if (getY() < -100) {
			// The position is exiting the screen, so we reset it
			this.setX(new RandomX().makeRandomPersons());
			this.setY(SCREEN_HEIGHT);
		} else {
			this.setY(getY() - 7.5);
			rect = new Rectangle2D(getX(), getY(), getSize()-20, getSize()-20);
		}
		
		for (GameObjects w : playState.getWeapons()) {
			if (rect.intersects(w.getRect())) {
				this.setX(new RandomX().makeRandomPersons());
				this.setY(SCREEN_HEIGHT);
				playState.getWeapons().remove(w); //Creates error!!!!
				if (playState.getScore() >= 900 && playState.getScore() <= 1000) {
					playState.levelUp1();
				} else if (playState.getScore() > 1900 && playState.getScore() < 2000) {
					playState.levelUp2();
				}
				playState.setScore(playState.getScore() + 100);
			}
		}
	}
	
	

	@Override
	public Rectangle2D getRect() {
		return rect;
	}

	
	
	
}
