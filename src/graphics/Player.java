package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

//Player is the class responsible for positioning and printing the character of the game. 

//Also it checks which items the player is colliding with and calling on appropriate functions in playState.

public class Player extends GameObjects {

	private static Image player;
	private Rectangle2D rect;
	private PlayState playState;
	
	
	public Player(double x, double y, double size, PlayState playState) {
		super(x, y, size);
		this.playState = playState;
		
		try {
			player = new Image(new FileInputStream("skier.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
	}
	
	@Override
	public void draw(GraphicsContext g) {
		g.save();
		g.drawImage(player, getX(), getY(), getSize(), getSize());
		g.restore();
	}
	


	@Override
	public void update() {
		rect = new Rectangle2D(getX(), getY(), getSize()-20, getSize()-20);
		
		if (playState.isLeft()) {
			if (this.getX() < 0) {		
				this.setX(SCREEN_WIDTH - 100);
			} else {
				this.setX(this.getX() - 10);
			}
		} else if (playState.isRight()) {
			if (this.getX() > SCREEN_WIDTH - 100) {
				this.setX(0);
			} else {
				this.setX(this.getX() + 10);;
			}	
		}

		
		for (GameObjects e : playState.getEnemies()) {
			if (rect.intersects(e.getRect())) {
				playState.switchState();
			}
		}
		
		for (GameObjects p : playState.getPowerUps()) {
			if (rect.intersects(p.getRect())) {
//				playState.getPowerUps().remove(p); //Skapar Error!!!
				p.setX(new RandomX().makeRandomPersons());
				p.setY(SCREEN_HEIGHT);
				playState.getBeersAdded().add(p);
//				playState.getPowerUps().remove(p);
			}
		}
		
		for (GameObjects b : playState.getBombs()) {
			if (rect.intersects(b.getRect())) {
				playState.bombMode();
			}
		}
		
		// Player Intersectar med bomb
	}

	@Override
	public void updateSlow() {}

	@Override
	public Rectangle2D getRect() {
		return rect;
	}
	
	
}
