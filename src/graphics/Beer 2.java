package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

public class Beer extends GameObjects{

		private Image beer;
		private Rectangle2D rect;
		
		public Beer(double x, double y, double size) {
			super(x, y, size);
			
			try {
				beer = new Image(new FileInputStream("beer.png"));
			} catch (FileNotFoundException e) {
				System.out.println("Unable to find image-files!");
			}
		}

		@Override
		public void draw(GraphicsContext g) {
			if (getY() < -100) {
				// The position is exiting the screen, so we reset it
				this.setX(new RandomX().makeRandomPersons());
				this.setY(800);
			}
//			  ColorAdjust colorAdjust = new ColorAdjust();
//			  colorAdjust.setBrightness(0.5);
//			  g.setEffect(colorAdjust);
			g.drawImage(beer, getX(), getY(), getSize(), getSize());

		}

		@Override
		public void update() {
			this.setY(getY() - 10);
			rect = new Rectangle2D(getX(), getY(), getSize()-10, getSize()-10);
		}
		
		
		
		
		
		
		
		
		
		

		@Override
		public void updateSlow() {
			this.setY(getY() - 5);
			rect = new Rectangle2D(getX(), getY(), getSize()-10, getSize()-10);
			
		}

		@Override
		public Rectangle2D getRect() {
			return rect;
		}
		

	}
