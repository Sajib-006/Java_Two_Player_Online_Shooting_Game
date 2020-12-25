package application;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.NetworkUtil;




public class Runner2 extends Application implements Runnable{
	private Robot robot;
	public static String key =null;
    private static final double W = 1366, H = 728;

    private static final String HERO_IMAGE = "data/character.png";
    private static final String BACKGROUND_IMAGE = "background2.png";
    private static final String JUMPED  = "data/jumped.png";
    private static final String DOWN = "data/down.png";
    
    private Image heroImage, backgroundImage, jumpedImage, downImage,heroImage2,bulletImage;
    public ImageView hero,hero2, background, jump, down , jump2, down2 ,currentSprite,bullet1;
    private static boolean  jumped,ducked;
    private static boolean  jumped2,ducked2;
    static int health1 = 5;
    static int  health2 = 5;

    
    public static AnchorPane root;
    
    static NetworkUtil nc ;
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static String serverAddress = "127.0.0.1";	
    private int serverPort = 34444;
    
    
    //private static boolean shootKey;
    
    
    
    
    

    
   public void init() {

	}
    @Override
    	public void  start(Stage primaryStage) throws Exception {
        Thread thread = new Thread(this,"display");
        thread.start();
       
    	
    	heroImage = new Image(HERO_IMAGE);
    	heroImage2 = new Image("1.png");
        backgroundImage = new Image(BACKGROUND_IMAGE);
        jumpedImage = new Image(JUMPED);
        downImage = new Image(DOWN);
        bulletImage = new Image("bullet1.jpg");
        
        hero = new ImageView(heroImage);
        hero2 = new ImageView(heroImage2);
        background  = new ImageView(backgroundImage);
        jump = new ImageView(jumpedImage);
        down = new ImageView(downImage);
        jump2 = new ImageView("jumped2.png");
        down2 = new ImageView("down2.png");
        bullet1 = new ImageView(bulletImage);
        currentSprite = hero;
        
        
        
        Label label1 = new Label();
        Label label2 = new Label();
        label1.setTextFill(Color.PINK);
        label2.setTextFill(Color.PINK);
        label1.setFont(new Font("Comic Sans MS" , 32));
        label2.setFont(new Font("Comic Sans MS" , 32));
        label1.setTextFill(Color.RED);
        label2.setTextFill(Color.RED);
        label1.setTextAlignment(TextAlignment.CENTER);
        label2.setTextAlignment(TextAlignment.CENTER);
        label1.setText("Heatlh : 10");
        label2.setText("Heatlh : 10");
        label1.setTranslateY(60);
        label2.setTranslateY(60);
        label1.setTranslateX(100);
        label2.setTranslateX(1000);
        
        Label label3 = new Label();
        Label label4 = new Label();
        label3.setTextFill(Color.PINK);
        label4.setTextFill(Color.PINK);
        label3.setFont(new Font("Comic Sans MS" , 32));
        label4.setFont(new Font("Comic Sans MS" , 32));
        label3.setTextFill(Color.BLACK);
        label4.setTextFill(Color.BLACK);
        label3.setTextAlignment(TextAlignment.CENTER);
        label4.setTextAlignment(TextAlignment.CENTER);
        label3.setText("Player 1");
        label4.setText("Player 2");
        label3.setTranslateY(20);
        label4.setTranslateY(20);
        label3.setTranslateX(100);
        label4.setTranslateX(1000);
        label1.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				label1.setScaleX(1.5);
				label1.setScaleY(1.5);
				
			}
		});
        label2.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				label2.setScaleX(1.5);
				label2.setScaleY(1.5);
				
			}
		});
        label1.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				label1.setScaleX(1);
				label1.setScaleY(1);
				
			}
		});
        label2.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				label2.setScaleX(1);
				label2.setScaleY(1);
				
			}
		});
        root = new AnchorPane();
        root.getChildren().addAll(background,currentSprite,hero2,label1,label2,label3,label4);
       

        

        //moveHeroTo(200, 370);
        currentSprite.relocate(200, 310);
        hero2.relocate(1100, 310);
        
        SceneManager sceneManager = new SceneManager(primaryStage,root,"server");
        robot = new Robot(root,jump,jump2,hero,down,down2,hero2,bullet1,label1,label2,sceneManager);

        sceneManager.goToMenuScene(sceneManager,root);
        
        //Scene scene = new Scene(root, W, H);
       
        

       

        //primaryStage.setScene(scene);
        Rectangle2D sc = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(sc.getMinX());
		primaryStage.setY(sc.getMinY());
		primaryStage.setWidth(sc.getWidth());
		primaryStage.setHeight(sc.getHeight());
		System.out.println(sc.getWidth() + " " + sc.getHeight());
		
//		 serversocket = new ServerSocket(33333);
//		 socket = serversocket.accept();
//		 oos = new ObjectOutputStream(socket.getOutputStream());
//		 ois = new ObjectInputStream(socket.getInputStream());
		 //Client client = new Client(serverAddress, serverPort);
		
		 
		 
		 
		
        //primaryStage.show();
     
        
        
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
//    public Object read() {
//        Object o = null;
//        try {
//            o = ois.readObject();
//        } catch (Exception e) {
//            //System.out.println("Reading Error in network : " + e.toString());
//        }
//        return o;
//    }
//
//    public void write(Object o) {
//        try {
//            oos.writeObject(o);
//        } catch (IOException e) {
//            System.out.println("Writing  Error in network : " + e.toString());
//        }
//    }
//
//    public void closeConnection() {
//        try {
//            ois.close();
//            oos.close();
//        } catch (Exception e) {
//            System.out.println("Closing Error in network : " + e.toString());
//        }
//    }

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

    public static void main(String[] args) {
    	
    	launch(args); 
    	
    	
    	
    }
    @Override
	public void run() {
    	System.out.println("hello");
    	
    	try {
			serverSocket = new ServerSocket(33333);
			Socket clientSocket = serverSocket.accept();
	    	nc = new NetworkUtil(clientSocket);
	        new ReadThread(nc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// Server server = new Server();
    	 //NetworkUtil nc = server.
//		while (true) {
//			robot.update();
//			if (robot.isJumped()) {
//				currentSprite = jump;
//			} else if (robot.isJumped() == false && robot.isDucked() == false) {
//				currentSprite = hero;
//			}

//			ArrayList projectiles = ro bot.getProjectiles();
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


