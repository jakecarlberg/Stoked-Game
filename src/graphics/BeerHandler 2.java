package graphics;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

public class BeerHandler {

private PlayState playstate;
	
	private Image beerBnW;
	private Image beer;

	public BeerHandler(PlayState playstate) {
		this.playstate = playstate;
		
		try {
			beer = new Image(new FileInputStream("beer.png"));
			beerBnW = new Image(new FileInputStream("beer-BnW.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	public void draw(GraphicsContext g) {
		if (playstate.getBeersAdded().size() == 0) {
			g.drawImage(beerBnW, SCREEN_WIDTH-150, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beerBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beerBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-150, 50, 50);
		} else if (playstate.getBeersAdded().size() == 1) {
			g.drawImage(beer, SCREEN_WIDTH-150, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beerBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beerBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-150, 50, 50);
		} else if (playstate.getBeersAdded().size() == 2) {
			g.drawImage(beer, SCREEN_WIDTH-150, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beer, SCREEN_WIDTH-100, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beerBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-150, 50, 50);
		} else if (playstate.getBeersAdded().size() == 3) {
			g.drawImage(beer, SCREEN_WIDTH-150, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beer, SCREEN_WIDTH-100, SCREEN_HEIGHT-150, 50, 50);
			g.drawImage(beer, SCREEN_WIDTH-50, SCREEN_HEIGHT-150, 50, 50);
			playstate.getBeersAdded().removeAll(playstate.getBeersAdded());
			playstate.setGoSlow(true);
		}
		
	}
	
}
