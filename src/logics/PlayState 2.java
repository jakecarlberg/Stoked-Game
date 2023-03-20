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
import java.util.ArrayList;

import graphics.*;

/**
 * This state represents the Playing State of the Game The main responsibility
 * of this class is to; - create Game Objects - update Game Objects - draw Game
 * Objects Game Objects are for instance; players, enemies, npc's, etc...
 *
 * The PlayState can also be thought off as a blue print where data is loaded
 * into some container from a file or some other type of data storage.
 *
 * It can also be created by some class responsible for object creation and then
 * passed to the PlayState as a parameter. This means all the PlayState has to
 * do is receive a list of objects, store them in some container and then for
 * every object in that container update and render that object.
 *
 * This way you can let the user define different Levels based on what
 * parameters are passed into the PlayState.
 */
public class PlayState extends GameState {
	/*
	 * The following three variables are just used to show that a change of state
	 * can be made. The same variables also exist in the MenuState, can you think of
	 * a way to make this more general and not duplicate variables?
	 
		
		private String informationText;
		private Color bgColor;
		private Color fontColor;

		/* Class only used for testing */
//		private Tester tester;
		
	private String informationText;
	private Color fontColor;
	
	private ArrayList<GameObjects> enemies;
	private ArrayList<GameObjects> weapons;
	private ArrayList<GameObjects> powerUps;
	private ArrayList<GameObjects> beersAdded;
	private ArrayList<GameObjects> bombs;
	
	private Player player;
		
	private Image slope;
//	private Image beer;
//	private Image beerBnW;

	private double score;
	private BeerHandler beerHandler;
		
		
		
	private boolean goSlow = false;
	private double slowCounter = 0;
		

	public PlayState(GameModel model) {
		super(model);
		informationText = "Press Escape To Return To The Menu";
		fontColor = Color.BLUE;

		try {
			slope = new Image(new FileInputStream("slope.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
			
			
			
			
			
//			tester = new Tester();
			
		player   = new Player(this);
		enemies  = new ArrayList<GameObjects>();
		weapons  = new ArrayList<GameObjects>();
		powerUps = new ArrayList<GameObjects>();
		beersAdded = new ArrayList<GameObjects>();
		bombs = new ArrayList<GameObjects>();

		enemies.add(new Tree(50, 800, 100));
		enemies.add(new Snowboard(400, 800, 100, this));
			
		powerUps.add(new Beer(600, 800, 50));
		bombs.add(new Bomb(SCREEN_WIDTH-200, SCREEN_HEIGHT-500, 50));
			
		beerHandler = new BeerHandler(this);	
			
		score = 0;
	}

		/**
		 * Draws information text to the screen.
		 */
		@Override
	public void draw(GraphicsContext g) {
		//drawBg(g, bgColor);
		g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.drawImage(slope, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

		g.setFill(fontColor);
		g.setFont(new Font(20)); // Big letters
		g.fillText(informationText, SCREEN_WIDTH / 4, SCREEN_HEIGHT-100);
			
		g.fillText(String.valueOf(score), SCREEN_WIDTH-200, SCREEN_HEIGHT/6);
			

			
		player.draw(g);
		for (GameObjects e : enemies) {
			e.draw(g);
		}
		for (GameObjects w : weapons) {
			w.draw(g);
		}
		for (GameObjects p : powerUps) {
			p.draw(g);
		}
		beerHandler.draw(g);
		

	}

	public boolean isGoSlow() {
			return goSlow;
		}

		public void setGoSlow(boolean goSlow) {
			this.goSlow = goSlow;
		}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");

		if (key.getCode() == KeyCode.ESCAPE)
			model.switchState(new MenuState(model));
			
			
			
		if (key.getCode() == KeyCode.LEFT) {
			player.moveLeft();
		} else if (key.getCode() == KeyCode.RIGHT) {
			player.moveRight();
		} else if (key.getCode() == KeyCode.SPACE) {
			weapons.add(new Snowball(player.getX(), player.getY(), 20));
		}
	}

	@Override
	public void update() {
			// Here one would probably instead move the player and any
			// enemies / moving obstacles currently active.
			
//			tester.update();
			
		if (goSlow == false) {
			player.update();
			for (GameObjects e : enemies) {
				e.update();
			}
			for (GameObjects w : weapons) {
				w.update();
			}
			for (GameObjects p : powerUps) {
				p.update();
			}
		} else {
			player.update();
			for (GameObjects e : enemies) {
				e.updateSlow();
			}
			for (GameObjects w : weapons) {
				w.updateSlow();
			}
			for (GameObjects p : powerUps) {
				p.updateSlow();
			}
				
			if (slowCounter <= 100) {
				slowCounter += 0.5;
			} else {
				slowCounter = 0;
				goSlow = false;
			}
		}

				
			Score(getName(), 0,5);
	}

		/**
		 * We currently don't have anything to activate in the PlayState so we leave
		 * this method empty in this case.
		 */
	@Override
	public void activate() {
			
	}

		/**
		 * We currently don't have anything to deactivate in the PlayState so we leave
		 * this method empty in this case.
		 */
	@Override
	public void deactivate() {
			
	}

		
	public void switchState() {
		model.switchState(new GameOverState(model));
	}
		
		
		
		
//		GETTERS AND SETTERS
		
	public ArrayList<GameObjects> getWeapons() {
		return weapons;
	}

	public void setWeapons(ArrayList<GameObjects> weapons) {
		this.weapons = weapons;
	}

	public ArrayList<GameObjects> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<GameObjects> enemies) {
		this.enemies = enemies;
	}

	public ArrayList<GameObjects> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(ArrayList<GameObjects> powerUps) {
		this.powerUps = powerUps;
	}

	public ArrayList<GameObjects> getBeersAdded() {
		return beersAdded;
	}

	public void setBeersAdded(ArrayList<GameObjects> beersAdded) {
		this.beersAdded = beersAdded;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
		
		
		

}






