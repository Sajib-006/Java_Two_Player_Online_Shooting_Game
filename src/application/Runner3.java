package application;



import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * Hold down an arrow key to have your hero move around the screen.
 * Hold down the shift key to have the hero run.
 */
public class Runner3 extends Application implements Runnable{
	private Robot robot;
    private static final double W = 1366, H = 728;

    private static final String HERO_IMAGE = "data/character.png";
           // "http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png";
    private static final String BACKGROUND_IMAGE = "background2.png";
    private static final String JUMPED  = "data/jumped.png";
    private static final String DOWN = "data/down.png";
    private Image heroImage, backgroundImage, jumpedImage, downImage;
    //private Node  hero;
    private ImageView hero, background, jump, down ,currentSprite;
    boolean running, goNorth, goSouth, goEast, goWest, jumped,ducked;
    
   public void init() {
//
//		setSize(800, 480);
//		setBackground(Color.BLACK);
//		setFocusable(true);
//		addKeyListener(this);
//		Frame frame = (Frame) this.getParent().getParent();
//		frame.setTitle("Q-Bot Alpha");
//		try {
//			base = getDocumentBase();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		// Image Setups
//		character = getImage(base, "data/character.png");
//		characterDown = getImage(base, "data/down.png");
//		characterJumped = getImage(base, "data/jumped.png");
//		currentSprite = character;
//		heliboy = getImage(base, "data/heliboy.png");
//		background = getImage(base, "data/background.png");
	}
    @Override
    public void start(Stage primaryStage) throws Exception {
        heroImage = new Image(HERO_IMAGE);
        backgroundImage = new Image(BACKGROUND_IMAGE);
        jumpedImage = new Image(JUMPED);
        downImage = new Image(DOWN);
        
        hero = new ImageView(heroImage);
        background  = new ImageView(backgroundImage);
        jump = new ImageView(jumpedImage);
        down = new ImageView(downImage);
        currentSprite = hero;
        //System.out.println("hi");
        
        Group root = new Group(background,currentSprite);
        robot = new Robot(root,jump,hero,down);


        

        //moveHeroTo(200, 370);
        currentSprite.relocate(200, 310);

        Scene scene = new Scene(root, W, H);
       
        

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case UP:    goNorth = true; break;
//                    case DOWN:  goSouth = true; break;
//                    case LEFT:  goWest  = true; break;
//                    case RIGHT: goEast  = true; break;
//                    case SHIFT: running = true; break;
//                    case SPACE: jumped = true; break;
//                }
                switch (event.getCode()) {
        		case UP:
        			System.out.println("Move up");
        			break;

        		case DOWN:
        			currentSprite = down;
        			if (robot.isJumped() == false && robot.isDucked()==false) {
        				robot.duck();
        				robot.setDucked(true);
        				robot.setSpeedX(0);
        			}
//        			if (robot.isDucked() == true) {
//        				//robot.duck();
//        				//robot.setDucked(false);
//        				//robot.setSpeedX(0);
//        				System.out.println(event.getCode());
//        			}
        			break;

        		case LEFT:
        			robot.moveLeft();
        			robot.setMovingLeft(true);
        			break;

        		case RIGHT:
        			robot.moveRight();
        			robot.setMovingRight(true);
        			break;

        		case SPACE:
        			robot.jumping();
        			break;

        		case CONTROL:
        			if (robot.isDucked() == false && robot.isJumped() == false) {
        				robot.shoot();
        			}
        			break;

        		}
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
//                switch (event.getCode()) {
//                    case UP:    goNorth = false; break;
//                    case DOWN:  goSouth = false; break;
//                    case LEFT:  goWest  = false; break;
//                    case RIGHT: goEast  = false; break;
//                    case SHIFT: running = false; break;
//                    case SPACE: jumped  = false; break;
//                }
            	switch (event.getCode()) {
        		case UP:
        			System.out.println("Stop moving up");
        			break;

        		case DOWN:
        			currentSprite = hero;
        			robot.setDucked(false);
        			break;

        		case LEFT:
        			robot.stopLeft();
        			break;

        		case RIGHT:
        			robot.stopRight();
        			break;

        		case SPACE:
        			break;

        		}
            }
        });

        primaryStage.setScene(scene);
        Rectangle2D sc = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(sc.getMinX());
		primaryStage.setY(sc.getMinY());
		primaryStage.setWidth(sc.getWidth());
		primaryStage.setHeight(sc.getHeight());
		System.out.println(sc.getWidth() + " " + sc.getHeight());
        primaryStage.show();
        
        
//        Path path = new Path();
//        path.getElements().addAll(new MoveTo(250,50), new VLineTo(350));
//        //path.setFill(Color.RED);
//        root.getChildren().add(path);
        
       

        
//        PathTransition pt = new PathTransition(Duration.millis(1000), path, c);
//        pt.setCycleCount(2);
//        pt.setAutoReverse(true);
        
       
       // pt2.setAutoReverse(true);
        
 //       ParallelTransition ppt = new ParallelTransition(c,ft,pt2,pt);
        
//        ft.play();
         
//        ppt.play();
        
    }

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                int dx = 0, dy = 0;
//
//                if (goNorth) dy -= 1;
//                if (goSouth) dy += 1;
//                if (goEast)  dx += 1;
//                if (goWest)  dx -= 1;
//                if (running) { dx *= 3; dy *= 3; }
//                if(jumped) dy -=20;
//                //System.out.println(dx + " " + dy);
//                moveHeroBy(dx, dy);
//            }
//        };
//        timer.start();
//       
//		Thread thread = new Thread(this);
//		thread.start();
//    
//
//    private void moveHeroBy(int dx, int dy) {
//        if (dx == 0 && dy == 0) return;
//
////        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
////        final double cy = hero.getBoundsInLocal().getHeight() / 2;
//        
//        double x = currentSprite.getLayoutX() + dx;
//        double y = currentSprite.getLayoutY() + dy;
////        System.out.println(x + " " + y);
////        System.out.println(currentSprite.getLayoutX());
////        System.out.println(currentSprite.getLayoutY());
//
//        moveHeroTo(x, y);
//    }
//
//    private void moveHeroTo(double x, double y) {
////        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
////        final double cy = hero.getBoundsInLocal().getHeight() / 2;
////        System.out.println(currentSprite.getBoundsInLocal().getWidth() );
////        System.out.println(currentSprite.getBoundsInLocal().getHeight());
//
////        if (x - cx >= 0 &&
////            x + cx <= W &&
////            y - cy >= 0 &&
////            y + cy <= H)
//        if(jumped)
//        {
//        	currentSprite = jump;
//        }
//        if(x>=70 && x<=340 && y<= 310)
//        {
//        	currentSprite.relocate(x , y);
//        }
//    }

    public static void main(String[] args) { launch(args); }
    @Override
	public void run() {
		while (true) {
			robot.update();
			if (robot.isJumped()) {
				currentSprite = jump;
			} else if (robot.isJumped() == false && robot.isDucked() == false) {
				currentSprite = hero;
			}

//			ArrayList projectiles = robot.getProjectiles();
//			for (int i = 0; i < projectiles.size(); i++) {
//				Projectile p = (Projectile) projectiles.get(i);
//				if (p.isVisible() == true) {
//					p.update();
//				} else {
//					projectiles.remove(i);
//				}
//			}

//			hb.update();
//			hb2.update();
//			bg1.update();
//			bg2.update();
			//repaint();
//			try {
//				Thread.sleep(17);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}

