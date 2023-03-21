package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

import static constants.Constants.SCREEN_HEIGHT;

//This class has is managing the outprint and updating of powerup Bomb's position. 

//It calls on the class RandomX to update its "spawn"-position.

public class Bomb extends GameObjects {

	private Image bomb;
	private Rectangle2D rect;
	private PlayState playState;
	
	public Bomb(double x, double y, double size, PlayState playState) {
		super(x, y, size);
		this.playState = playState;

		try {
			bomb = new Image(new FileInputStream("bomb.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(bomb, getX(), getY(), getSize(), getSize());
	}

	@Override
	public void update() {
		if (getY() < -100) {
			// The position is exiting the screen, so we reset it
			this.setX(new RandomX().makeRandomPersons());
			this.setY(SCREEN_HEIGHT+2000);
		} else {
			this.setY(getY() - 10);
			rect = new Rectangle2D(getX(), getY(), getSize()-10, getSize()-10);
		}
		
		
		//BOMBMODE
		if (rect.intersects(playState.getPlayer().getRect())) {
			for (GameObjects e : playState.getEnemies()) {
				e.setY(e.getY()+SCREEN_HEIGHT*2);
				e.setX(new RandomX().makeRandomPersons());
			}
			for (GameObjects p : playState.getPowerUps()) {
				p.setY(SCREEN_HEIGHT*3);
				p.setX(new RandomX().makeRandomPersons());
			}
			for (GameObjects bb : playState.getBombs()) {
				bb.setY(SCREEN_HEIGHT*4);
				bb.setX(new RandomX().makeRandomPersons());
			}
		}
		
	}
	
	public void updateSlow() {} //Empty so you cannot collect bombs while in slow-mode.

	@Override
	public Rectangle2D getRect() {
		return rect;
	}

}
