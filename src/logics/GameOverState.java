package logics;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverState extends GameState {

	
	private String informationText;
	private Color bgColor;
	private Color fontColor;
	
	
	public GameOverState(GameModel model) {
		super(model);
		
		informationText = "GAME OVER!\nPress Enter To Continue\nEscape to exit";
		bgColor = Color.BLUE;
		fontColor = Color.RED;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);
		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(informationText, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);

	}


	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ENTER) {
			model.switchState(new MenuState(model));
		} else if (key.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		}

	}

	@Override
	public void keyReleased(KeyEvent key) {}
	
	
	@Override
	public void activate() {}

	@Override
	public void deactivate() {}

}
