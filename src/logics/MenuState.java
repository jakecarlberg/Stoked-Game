package logics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This state represents the menu of the Game. The main responsibility of this
 * class is to allow the user to swap state to the PlayState and start to play, while 
 * also be able to look at instructions in instruction state, or watch the highscore in
 * highscore state. You can also exit the program from here.
 */
public class MenuState extends GameState {

	private String informationText;
	private Color fontColor;
	// A PlayState, so we can change to the PlayState from the menu.
	private PlayState playState;
	
	
	private static Image menu;

	public MenuState(GameModel model) {
		super(model);
		playState = new PlayState(model);
		informationText = "PRESS ENTER TO PLAY\n  I FOR INSTRUCTIONS\n   H FOR HIGHSCORE\n     ESCAPE TO EXIT";
		fontColor = Color.WHITE;
		
		
		try {
			menu = new Image(new FileInputStream("menu.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
	}


	/**
	 * Draws information text to the screen
	 */
	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(menu, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		g.setFill(fontColor);
		g.setFont(new Font(30)); // Big letters
		// Print the information text, centered on the canvas
		g.fillText(informationText, SCREEN_WIDTH / 3 - 70, SCREEN_HEIGHT - 350);
	}

	/**
	 *
	 * @param key KeyEvent representing the pressed key
	 *
	 *            This function prints the pressed key to the console it's used to
	 *            show that a change of state has been made
	 *
	 *            For more information see GameState
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ENTER) {
			model.switchState(playState);
		} else if (key.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		} else if (key.getCode() == KeyCode.I) {
			model.switchState(new InstructionState(model));
		} else if (key.getCode() == KeyCode.H) {
			model.switchState(new HighscoreState(model));
		}
	}
	
	@Override
	public void keyReleased(KeyEvent key) {}

	/**
	 * We have nothing to update in the menu, no moving objects etc. so we leave the
	 * update method empty.
	 */
	@Override
	public void update() {
	}

	/**
	 * We currently don't have anything to activate in the MenuState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void activate() {

	}

	/**
	 * We currently don't have anything to deactivate in the MenuState so we leave
	 * this method empty in this case.
	 */

	@Override
	public void deactivate() {

	}

}


