package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

import static constants.Constants.SCREEN_HEIGHT;

//This class is one of the powerups in level 1. Main responsibilities is to update it's position and image in the game.

public class Beer extends GameObjects {

	private Image beer;
	private Rectangle2D rect;
	private PlayState playState;
	
	public Beer(double x, double y, double size, PlayState playState) {
		super(x, y, size);
		this.playState = playState;
		
		try {
			beer = new Image(new FileInputStream("beer.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(beer, getX(), getY(), getSize(), getSize());
	}

	@Override
	public void update() {
		if (getY() < -100) {
			this.setX(new RandomX().makeRandomPersons());
			this.setY(SCREEN_HEIGHT);
		} else {
			this.setY(getY() - 10);
			rect = new Rectangle2D(getX(), getY(), getSize()-10, getSize()-10);
		}
		
		//BEER INTERSECTAR MED PLAYER OCH SAMARBETAR MED BEERHANDLER
		if (rect.intersects(playState.getPlayer().getRect())) {
			setX(new RandomX().makeRandomPersons());
			setY(SCREEN_HEIGHT);
			playState.getBeersAdded().add(this);
		}
	}

	@Override
	public void updateSlow() {} 

	@Override
	public Rectangle2D getRect() {
		return rect;
	}
	
	

}
