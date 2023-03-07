package logics;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class HighscoreState extends GameState {
	
	private Color fontColor;
	private ArrayList<Score> scoreList = new ArrayList<Score>();
	private Image highscore;
	
	public HighscoreState(GameModel model) {
		super(model);
		fontColor = Color.WHITE;
		
		try {
			highscore = new Image(new FileInputStream("highscore.png"));
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
		g.drawImage(highscore, -251, -5, SCREEN_WIDTH*1.85, SCREEN_HEIGHT);
		g.setFill(fontColor);
		g.fillText("Press:\n\"escape\" to go back to the menu", SCREEN_WIDTH * 1 / 14, SCREEN_HEIGHT * 4 / 5);
	
		int i = 0;
		while (scoreList.size() > i) {
			g.fillText(scoreList.get(i).getName() + "  -  " + String.valueOf(scoreList.get(i).getScore()),
					SCREEN_WIDTH / 2 - 120, ((i + 1) * 50) + 130);

			i++;
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getCode() == KeyCode.ESCAPE) {
			model.switchState(new MenuState(model));
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
	}

	@Override
	public void activate() {
		try {
			load();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deactivate() {
	}

	
	public void addScore(Score newScore) {

		scoreList.add(newScore);
		scoreList.sort(Comparator.comparing(Score::getScore).reversed());

		if (scoreList.size() > 10) {
			scoreList.remove(10);
		}
		save();

	}

	public void topTen(Score newScore) {

		try {
			load();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if (scoreList.size() < 10) {
			addScore(newScore);
		} else if (newScore.getScore() >= scoreList.get(9).getScore()) {
			addScore(newScore);
		}
	}

	public void save() {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File("saveHighscoreList.txt")));
			out.writeObject(scoreList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	public void load() throws IOException, ClassNotFoundException {
		try {
			ObjectInputStream in;
			in = new ObjectInputStream(new FileInputStream(new File("saveHighscoreList.txt")));
			this.scoreList = (ArrayList<Score>) in.readObject();

		} catch (FileNotFoundException e) {
			save();
		}
	}

}
