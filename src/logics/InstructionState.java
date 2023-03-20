package logics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

/**
 * This state represents the Instructions of the Game. The main responsibility of this
 * class is to allow the user to be able to see how to play the game.
 * After this state you can go back to menu.
 */

public class InstructionState extends GameState {

	private Image instructions;
	
	public InstructionState(GameModel model) {
		super(model);
		
		try {
			instructions = new Image(new FileInputStream("instruction.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void update() {}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(instructions, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.fillText("PRESS ESCAPE TO RETURN TO MENU", SCREEN_WIDTH/17, SCREEN_HEIGHT/15);
	}


	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");

		if (key.getCode() == KeyCode.ESCAPE)
			model.switchState(new MenuState(model));
	}
	
	@Override
	public void keyReleased(KeyEvent key) {}

	@Override
	public void activate() {}

	@Override
	public void deactivate() {}

}
