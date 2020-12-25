package application;



import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Laser {
	public static final double MOVE_SHIFT = 5;
	
	private Rectangle laser;
	
	
	

	public Laser(double x, double y) {
		
		this.laser = UIGenerator.createRectangle(Color.YELLOW, x, y, 20, 10);
	}
	
	
	public Rectangle getLaser() {
		return laser;
	}
	
}