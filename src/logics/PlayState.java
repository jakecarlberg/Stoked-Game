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
	 */
	private String informationText;
//	private Color bgColor;
	private Color fontColor;

	/* Class only used for testing */
//	private Tester tester;
	
	private ArrayList<GameObjects> enemies;
	private ArrayList<GameObjects> weapons;
	private ArrayList<GameObjects> powerUps;
	private ArrayList<GameObjects> bombs;
	private ArrayList<GameObjects> beersAdded;
	
	private Player player;
	private BeerHandler beerHandler;
	private Image slope;
	private Image wave;
	private Image sky;

	private double score;

	private boolean goSlow = false;
	private double slowCounter = 0;
	
	private boolean levelUp1 = false;
	private boolean levelUp2 = false;
	
	
	private boolean left;
	private boolean right;
	
	

	


	public PlayState(GameModel model) {
		super(model);
		informationText = "Press Escape To Return To The Menu";
//		bgColor = Color.BLACK;
		fontColor = Color.BLUE;

		try {
			slope = new Image(new FileInputStream("slope1.png"));
			wave = new Image(new FileInputStream("wave.png"));
			sky = new Image(new FileInputStream("sky.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		
		player   = new Player(SCREEN_WIDTH/2-50, 0, 100, this);
		enemies  = new ArrayList<GameObjects>();
		weapons  = new ArrayList<GameObjects>();
		powerUps = new ArrayList<GameObjects>();
		bombs    = new ArrayList<GameObjects>();
		beersAdded = new ArrayList<GameObjects>();

		enemies.add(new Tree(50, SCREEN_HEIGHT, 100));
		enemies.add(new Tree(450, SCREEN_HEIGHT*1.5, 100));
		enemies.add(new Snowboard(SCREEN_WIDTH/2, SCREEN_HEIGHT, 100, this));
		powerUps.add(new Beer(500, SCREEN_HEIGHT, 50));
		bombs.add(new Bomb(SCREEN_WIDTH-200, SCREEN_HEIGHT+500, 50));
		
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
		g.setFill(Color.RED);
		g.setFont(new Font(30)); // Big letters
		if (levelUp1 == true) {
			g.drawImage(wave, -250, -250, SCREEN_WIDTH+250, SCREEN_HEIGHT+250);
			g.fillText("LEVEL 2", SCREEN_WIDTH - 550, 120);
		} else if (levelUp2 == true) {
			g.drawImage(sky, -200, -200, SCREEN_WIDTH+200, SCREEN_HEIGHT+200);
			g.fillText("LEVEL 3", SCREEN_WIDTH - 550, 120);
		} else {
			g.drawImage(slope, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			g.fillText("LEVEL 1", SCREEN_WIDTH - 550, 120);
		}
		g.fillText("Score: " + String.valueOf(score), SCREEN_WIDTH-200, 120);

		g.setFill(fontColor);
		g.fillText(informationText, 40, SCREEN_HEIGHT - 110);
		
		
		
		
//		g.drawImage(beer1BnW, 850, 650, 50, 50);
//		g.drawImage(beer1BnW, 900, 650, 50, 50);
//		g.drawImage(beer1BnW, 950, 650, 50, 50);
		
		
		// Can also use:
		// g.setStroke(fontColor);
		// g.strokeText(informationText, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

		// This could be a call to all our objects that we want to draw.
		// Using the tester simply to illustrate how it could work.
		
//		tester.delegate(g);
		
		
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
		for (GameObjects b : bombs) {
			b.draw(g);
		}

		beerHandler.draw(g);
		
		if (goSlow == true) {
			g.fillText("DRUNK MODE", SCREEN_WIDTH/3, SCREEN_HEIGHT/2);
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");

		if (key.getCode() == KeyCode.ESCAPE)
			model.switchState(new MenuState(model));
		
		
		
		if (key.getCode() == KeyCode.LEFT) {
//			player.moveLeft();
			left = true;
		} else if (key.getCode() == KeyCode.RIGHT) {
//			player.moveRight();
			right = true;
		} else if (key.getCode() == KeyCode.SPACE) {
			if (levelUp1 == true) {
				weapons.add(new Spear(player.getX(), player.getY(), 40));
			} else if (levelUp2 == true) {
				weapons.add(new Knife(player.getX(), player.getY(), 40));
			} else {
				weapons.add(new Snowball(player.getX(), player.getY(), 20));
			}
		}
	}
	
	

	@Override
	public void keyReleased(KeyEvent key) {
		if (key.getCode() == KeyCode.LEFT) {
			left = false;
		} else if (key.getCode() == KeyCode.RIGHT) {
			right = false;
		}
	}

	@Override
	public void update() {
		// Here one would probably instead move the player and any
		// enemies / moving obstacles currently active.
		
//		tester.update();
		
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
			for (GameObjects b : bombs) {
				b.update();
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
			for (GameObjects b : bombs) {
				b.updateSlow();
				//b.setY(SCREEN_HEIGHT*2); //Gör att bomb inte fryser i bild under slow_mode
			}
			
			if (slowCounter <= 100) {
				slowCounter += 0.5;
			} else {
				slowCounter = 0;
				goSlow = false;
				beersAdded.removeAll(beersAdded);
			}
		}

			
			score += 0.5;
			if (score == 1000) {
				levelUp1();
			} else if (score == 2000) {
				levelUp2();
			}
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
	
	public void bombMode() {
		for (GameObjects e : enemies) {
			e.setY(e.getY()+SCREEN_HEIGHT*2);
			e.setX(new RandomX().makeRandomPersons());
		}
		for (GameObjects p : powerUps) {
			p.setY(SCREEN_HEIGHT*3);
			p.setX(new RandomX().makeRandomPersons());
		}
		for (GameObjects b : bombs) {
			b.setY(SCREEN_HEIGHT*4);
			b.setX(new RandomX().makeRandomPersons());
		}
	}
	
	public void levelUp1() {
		System.out.println("LEVEL UP TO LEVEL 2");
		levelUp1 = true;
		enemies.removeAll(enemies);
		weapons.removeAll(weapons);
		powerUps.removeAll(powerUps);
//		bombs.removeAll(bombs);
		beersAdded.removeAll(beersAdded);
		
		player = new Player2(player.getX(), player.getY(), player.getSize(), this);
		enemies.add(new Shark(50, SCREEN_HEIGHT, 100, this));
		enemies.add(new BabySurfer(450, SCREEN_HEIGHT*1.5, 100, this));
		enemies.add(new Rock(SCREEN_WIDTH/2, SCREEN_HEIGHT, 100));
		enemies.add(new Rock(SCREEN_WIDTH/2, SCREEN_HEIGHT*1.5, 100));
		powerUps.add(new Coconut(500, SCREEN_HEIGHT, 50));	
	}
	
	public void levelUp2() {
		System.out.println("LEVEL UP TO LEVEL 3");
		levelUp1 = false;
		levelUp2 = true;
		enemies.removeAll(enemies);
		weapons.removeAll(weapons);
		powerUps.removeAll(powerUps);
//		bombs.removeAll(bombs);
		beersAdded.removeAll(beersAdded);
		
		player = new Player3(player.getX(), player.getY(), player.getSize(), this);
		enemies.add(new Bird(50, SCREEN_HEIGHT, 100, this));
		enemies.add(new Airbaloon(450, SCREEN_HEIGHT*1.5, 100, this));
		enemies.add(new Airplane(SCREEN_WIDTH/4, SCREEN_HEIGHT, 200));
//		enemies.add(new Airplane(SCREEN_WIDTH/2, SCREEN_HEIGHT*1.3, 200));
		enemies.add(new Airplane(SCREEN_WIDTH/2+150, SCREEN_HEIGHT*1.6, 200));
		powerUps.add(new Redbull(500, SCREEN_HEIGHT, 50));	
	}
	
	
	
//	GETTERS AND SETTERS
	
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

	public ArrayList<GameObjects> getBombs() {
		return bombs;
	}

	public void setBombs(ArrayList<GameObjects> bombs) {
		this.bombs = bombs;
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

	public boolean isGoSlow() {
		return goSlow;
	}

	public void setGoSlow(boolean goSlow) {
		this.goSlow = goSlow;
	}

	public boolean isLevelUp1() {
		return levelUp1;
	}

	public boolean isLevelUp2() {
		return levelUp2;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
		
	
	

}

