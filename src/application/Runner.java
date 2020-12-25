package application;

import java.awt.Frame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Hold down an arrow key to have your hero move around the screen.
 * Hold down the shift key to have the hero run.
 */
public class Runner extends Application implements Runnable{

    private static final double W = 1366, H = 728;

    private static final String HERO_IMAGE = "data/character.png";
           // "http://icons.iconarchive.com/icons/raindropmemory/legendora/64/Hero-icon.png";
    private static final String BACKGROUND_IMAGE = "background2.png";
    private static final String JUMPED  = "data/jumped.png";
    private static final String DOWN = "data/down.png";
    private Image heroImage, backgroundImage, jumpedImage, downImage;
    //private Node  hero;
    private ImageView hero, background, jump, down ,currentSprite;
    boolean running, goNorth, goSouth, goEast, goWest, jumped;
    
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
        System.out.println("hi");
        
        Group root = new Group(background,currentSprite);
        Robot robot = new Robot();


        

        //moveHeroTo(200, 370);
        currentSprite.relocate(200, 310);

        Scene scene = new Scene(root, W, H);
       
        

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = true; break;
                    case DOWN:  goSouth = true; break;
                    case LEFT:  goWest  = true; break;
                    case RIGHT: goEast  = true; break;
                    case SHIFT: running = true; break;
                    case SPACE: jumped = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = false; break;
                    case DOWN:  goSouth = false; break;
                    case LEFT:  goWest  = false; break;
                    case RIGHT: goEast  = false; break;
                    case SHIFT: running = false; break;
                    case SPACE: jumped  = false; break;
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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast)  dx += 1;
                if (goWest)  dx -= 1;
                if (running) { dx *= 3; dy *= 3; }
                if(jumped) dy -=20;
                //System.out.println(dx + " " + dy);
                moveHeroBy(dx, dy);
            }
        };
        timer.start();
       
		Thread thread = new Thread(this);
		thread.start();
    }

    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

//        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
//        final double cy = hero.getBoundsInLocal().getHeight() / 2;
        
        double x = currentSprite.getLayoutX() + dx;
        double y = currentSprite.getLayoutY() + dy;
//        System.out.println(x + " " + y);
//        System.out.println(currentSprite.getLayoutX());
//        System.out.println(currentSprite.getLayoutY());

        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
//        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
//        final double cy = hero.getBoundsInLocal().getHeight() / 2;
//        System.out.println(currentSprite.getBoundsInLocal().getWidth() );
//        System.out.println(currentSprite.getBoundsInLocal().getHeight());

//        if (x - cx >= 0 &&
//            x + cx <= W &&
//            y - cy >= 0 &&
//            y + cy <= H)
        if(jumped)
        {
        	currentSprite = jump;
        }
        if(x>=70 && x<=340 && y<= 310)
        {
        	currentSprite.relocate(x , y);
        }
    }

    //public static void main(String[] args) { launch(args); }
	@Override
	public void run() {
		while(true)
		{
			 //root = new Group(background,currentSprite);
			System.out.println("hii");
		}
		
	}
}
