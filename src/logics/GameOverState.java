package logics;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameOverState extends GameState {

	private Color fontColor;
	private double score;
	private String playerName = "";
	private Score newScore;
	private HighscoreState highscoreState;
	private Image gameOver;
	
	
	public GameOverState(GameModel model, double score) {
		super(model);
		
		this.score = score;
		highscoreState = new HighscoreState(model);
		fontColor = Color.WHITE;
		
		try {
			gameOver = new Image(new FileInputStream("game-over.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(GraphicsContext g) {
		g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setFill(fontColor);
		g.drawImage(gameOver, -251, -5, SCREEN_WIDTH*1.85, SCREEN_HEIGHT);
		g.fillText(String.valueOf(score), SCREEN_WIDTH/2-50, SCREEN_HEIGHT/3);
		g.fillText("NAME: " + playerName, SCREEN_WIDTH / 4, SCREEN_HEIGHT * 0.72);
		

	}


	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ENTER) {
			newScore = new Score(playerName, score);
			highscoreState.topTen(newScore);
			model.switchState(highscoreState);
		} else if (key.getCode() == KeyCode.ESCAPE) {
			model.switchState(new MenuState(model));
		} else if (key.getCode().isLetterKey()) {
			playerName += key.getCode();
		} else if (key.getCode() == KeyCode.BACK_SPACE) {
			if (playerName.length() != 0) {
				playerName = playerName.substring(0, playerName.length() - 1);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent key) {}
	
	
	@Override
	public void activate() {}

	@Override
	public void deactivate() {}

}
