package graphics;

import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

public class Player {

	private double x, y, size;
	private Image player;
	private Rectangle2D playerRect;
	private PlayState playState;
	
	
	public Player(PlayState playState) {
		x    = 400;
		y    = 0;
		size = 100;
		this.playState = playState;
		
		try {
			player = new Image(new FileInputStream("skier.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
	}
	
	
	public void draw(GraphicsContext g) {
		g.drawImage(player, x, y, size, size);
	}
	
	public void moveLeft() {
		if (this.getX() < 0) {		
			x = SCREEN_WIDTH - 100;
		} else {
			x -= 30;
		}
	}
	
	public void moveRight() {
		if (this.getX() > SCREEN_WIDTH - 100) {
			x = 0;
		} else {
			x += 30;
		}	
	}

	public void update() {
		playerRect = new Rectangle2D(getX(), getY(), size-20, size-20);
		
		for (GameObjects e : playState.getEnemies()) {
			if (playerRect.intersects(e.getRect())) {
				playState.switchState();
			}
		}
		for (GameObjects p : playState.getPowerUps()) {
			if (playerRect.intersects(p.getRect())) {
//				playState.getPowerUps().remove(p); //Skapar Error!!!
				p.setX(new RandomX().makeRandomPersons());
				p.setY(800);
				playState.getBeersAdded().add(p);
//				playState.getPowerUps().remove(p);
			}
		}
		//player intersect bomb
	}

	
	public double getSize() {
		return size;
	}

	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}


	public Image getPlayer() {
		return player;
	}


	public Rectangle2D getPlayerRect() {
		return playerRect;
	}


	
	
	
	
	
}
