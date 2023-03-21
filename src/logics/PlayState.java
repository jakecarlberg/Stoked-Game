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
	private Color fontColor;
	
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
		powerUps.add(new Beer(500, SCREEN_HEIGHT, 50, this));
		bombs.add(new Bomb(SCREEN_WIDTH-200, SCREEN_HEIGHT+500, 50, this));
		
		beerHandler = new BeerHandler(this);
		
		score = 0;
	}

	/**
	 * Draws information text to the screen.
	 */
	@Override
	public void draw(GraphicsContext g) {

		g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.setFill(Color.RED);
		g.setFont(new Font(30)); // Big letters
		
		
		/**
		 * IMPERATIV KOD ENBART FÖR ATT ÄNDRA BAKGRUNDSBILD PÅ LEVLARNA
		 * Anledningen till detta är för att spelets funktionalitet på de olika
		 * levlarna är samma. Det som skiljer sig är objekten och bakgrundsbilernas
		 * images (ty vi har olika teman på olika levlar: Ski(1), Surf(2) & SkyDive(3)),
		 * samt antal fiender skiljer sig. Ju svårare level, desto fler fiender. I övrigt
		 * är funktionaliteten samma, varpå vi därför inte har olika states för olika
		 * levlar, utan enbart byter bakgrundsbild nedan med imperativ kod. Men för 
		 * alla olika spelobjekt på levlarna har vi olika klasser.
		 */
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
		/**
		 * SLUT PÅ IMPERATIV KOD
		 */
		
		g.fillText("Score: " + String.valueOf(score), SCREEN_WIDTH-200, 120);

		g.setFill(fontColor);
		g.fillText(informationText, 40, SCREEN_HEIGHT - 110);
		
		
		
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
			left = true;
		} else if (key.getCode() == KeyCode.RIGHT) {
			right = true;
		} else if (key.getCode() == KeyCode.SPACE) {
			
			/**
			 * IMPERATIV KOD ENBART FÖR ATT SKJUTA OLIKA VAPEN PGA OLIKA TEMAN
			 */
			if (levelUp1 == true) {
				weapons.add(new Spear(player.getX(), player.getY(), 40));
			} else if (levelUp2 == true) {
				weapons.add(new Knife(player.getX(), player.getY(), 40));
			} else {
				weapons.add(new Snowball(player.getX(), player.getY(), 20));
			}
			/**
			 * SLUT PÅ IMPERATIV KOD
			 */
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
		model.switchState(new GameOverState(model, score));
	}
	
	
	/**
	 * LEVLAR UP!
	 * Välkommen till level 2. Anledningen till denna metod är att byta ut alla
	 * objekt i objekt-listorna till de nya sub-klasserna av "mellan-klasserna",
	 * alltså alla objekt som först instansieras i level 1 på skid-temat (dessa 
	 * vi kallar för mellan-klasser då de är subklasser av GameObject, men samtidigt
	 * agerar som super-klass för objekten i level 2 och level 3.
	 */
	public void levelUp1() {
		System.out.println("LEVEL UP TO LEVEL 2");
		levelUp1 = true;
		enemies.removeAll(enemies);
		weapons.removeAll(weapons);
		powerUps.removeAll(powerUps);
		beersAdded.removeAll(beersAdded);
		
		player = new Player2(player.getX(), player.getY(), player.getSize(), this);
		enemies.add(new Shark(50, SCREEN_HEIGHT, 100, this));
		enemies.add(new BabySurfer(450, SCREEN_HEIGHT*1.5, 100, this));
		enemies.add(new Rock(SCREEN_WIDTH/2, SCREEN_HEIGHT, 100));
		enemies.add(new Rock(SCREEN_WIDTH/2, SCREEN_HEIGHT*1.5, 100));
		powerUps.add(new Coconut(500, SCREEN_HEIGHT, 50, this));	
	}
	
	/**
	 * LEVLAR UP IGEN!
	 * Välkommen till level 3. Samma sak här som ovan gälller.
	 */
	public void levelUp2() {
		System.out.println("LEVEL UP TO LEVEL 3");
		levelUp1 = false;
		levelUp2 = true;
		enemies.removeAll(enemies);
		weapons.removeAll(weapons);
		powerUps.removeAll(powerUps);
		beersAdded.removeAll(beersAdded);
		
		player = new Player3(player.getX(), player.getY(), player.getSize(), this);
		enemies.add(new Bird(50, SCREEN_HEIGHT, 100, this));
		enemies.add(new Airbaloon(450, SCREEN_HEIGHT*1.5, 100, this));
		enemies.add(new Airplane(SCREEN_WIDTH/4, SCREEN_HEIGHT, 200));
		enemies.add(new Airplane(SCREEN_WIDTH/2+150, SCREEN_HEIGHT*1.6, 200));
		powerUps.add(new Redbull(500, SCREEN_HEIGHT, 50, this));	
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

	public Player getPlayer() {
		return player;
	}
		
	
	

}

