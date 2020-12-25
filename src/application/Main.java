package application;
	
import java.awt.event.KeyEvent;
import java.event.KeyListener;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application implements KeyListener {
	Image img1 = new Image("background1.jpg");
	ImageView image1 = new ImageView(img1);
	
	
	//image1.setTranslateX(20);
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group(image1);
			Scene scene = new Scene(root);
			image1.setFitHeight(728.0);
			image1.setFitWidth(1366.0);
			image1.setPreserveRatio(true);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Rectangle2D sc = Screen.getPrimary().getVisualBounds();
			primaryStage.setX(sc.getMinX());
			primaryStage.setY(sc.getMinY());
			primaryStage.setWidth(sc.getWidth());
			primaryStage.setHeight(sc.getHeight());
			System.out.println(sc.getWidth() + " " + sc.getHeight());
			
			Canvas canvas = new Canvas(1366,728);
			
			root.getChildren().add(canvas);
			//root.getChildren().add(image1);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			drawShapes(gc);
			gc.setFill(Color.RED);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(2);
			Font font = Font.font("Times New Roman", FontWeight.BOLD, 48);
			gc.setFont(font);
			gc.fillText("hellow World", 50, 300);
			gc.strokeText("hellow World", 50, 300);
			Image earth = new Image("earth2.png");
			gc.drawImage(earth, 200, 300);
			
			
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void init()
	{
		
	}
		
		private void drawShapes(GraphicsContext gc) {
			gc.setFill(Color.GREEN);
			gc.setStroke(Color.BLUE);
			gc.setLineWidth(5);
			gc.strokeLine(40, 10, 10, 40);
			gc.fillOval(10, 60, 30, 30);
			gc.strokeOval(60, 60, 30, 30);
			gc.fillRoundRect(110, 60, 30, 30, 10, 10);
			gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
			gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
			gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
			gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
			gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
			gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
			gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
			gc.fillPolygon(new double[]{10, 40, 10, 40},
			new double[]{210, 210, 240, 240}, 4);
			gc.strokePolygon(new double[]{60, 90, 60, 90},
			new double[]{210, 210, 240, 240}, 4);
			gc.strokePolyline(new double[]{110, 140, 110, 140},
			new double[]{210, 210, 240, 240}, 4);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		System.out.println("Move up");
		break;
		case KeyEvent.VK_DOWN:
		System.out.println("Move down");
		break;
		case KeyEvent.VK_LEFT:
		robot.moveLeft();
		break;
		case KeyEvent.VK_RIGHT:
		robot.moveRight();
		break;
		case KeyEvent.VK_SPACE:
		robot.jump();
		break;
		}
		}
		
		
		@Override
		public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		System.out.println("Stop moving up");
		break;
		case KeyEvent.VK_DOWN:
		System.out.println("Stop moving down");
		break;
		case KeyEvent.VK_LEFT:
		robot.stop();
		break;
		case KeyEvent.VK_RIGHT:
		robot.stop();
		break;
		case KeyEvent.VK_SPACE:
		System.out.println("Stop jumping");
		break;
		}
		}
		
		
		@Override
		public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
