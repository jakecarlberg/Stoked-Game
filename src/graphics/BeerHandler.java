package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logics.PlayState;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

//Beerhandlder is a separate class that is managing the powerup-bar in the bottom of the screen. 

public class BeerHandler {

	private PlayState playState;
	private Image beer;
	private Image beerBnW;
	private Image coconut;
	private Image coconutBnW;
	private Image redbull;
	private Image redbullBnW;
	
	public BeerHandler(PlayState playState) {
		this.playState = playState;
		
		try {
			beer = new Image(new FileInputStream("beer.png"));
			beerBnW = new Image(new FileInputStream("beer-BnW.png"));
			coconut = new Image(new FileInputStream("coconut.png"));
			coconutBnW = new Image(new FileInputStream("coconut-BnW.png"));
			redbull = new Image(new FileInputStream("redbull.png"));
			redbullBnW = new Image(new FileInputStream("redbull-BnW.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	public void draw(GraphicsContext g) {
		if (playState.isLevelUp1()) {
			if (playState.getBeersAdded().size() == 0) {
				g.drawImage(coconutBnW, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconutBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconutBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 1) {
				g.drawImage(coconut, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconutBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconutBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 2) {
				g.drawImage(coconut, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconut, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconutBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 3) {
				g.drawImage(coconut, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconut, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(coconut, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
//				playState.getBeersAdded().removeAll(playState.getBeersAdded());
				playState.setGoSlow(true);
			} 
		} else if (playState.isLevelUp2()) {
			if (playState.getBeersAdded().size() == 0) {
				g.drawImage(redbullBnW, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbullBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbullBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 1) {
				g.drawImage(redbull, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbullBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbullBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 2) {
				g.drawImage(redbull, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbull, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbullBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 3) {
				g.drawImage(redbull, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbull, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(redbull, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
				playState.setGoSlow(true);
			}
		} else {
			if (playState.getBeersAdded().size() == 0) {
				g.drawImage(beerBnW, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beerBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beerBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 1) {
				g.drawImage(beer, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beerBnW, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beerBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 2) {
				g.drawImage(beer, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beer, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beerBnW, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
			} else if (playState.getBeersAdded().size() == 3) {
				g.drawImage(beer, SCREEN_WIDTH-150, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beer, SCREEN_WIDTH-100, SCREEN_HEIGHT-200, 50, 50);
				g.drawImage(beer, SCREEN_WIDTH-50, SCREEN_HEIGHT-200, 50, 50);
				playState.setGoSlow(true);
			} 
		}
		

	}
	
}
