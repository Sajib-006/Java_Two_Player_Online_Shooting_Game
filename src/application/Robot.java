package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

public class Robot {
	static AnchorPane root = new AnchorPane();
	static AnchorPane rootLaser ;
	static ImageView jump = new ImageView();
	static ImageView jump2 = new ImageView();
	static ImageView hero = new ImageView();
	static ImageView hero2 = new ImageView();
	static ImageView down = new ImageView();
	static ImageView down2 = new ImageView();
	static ImageView bullet1 = new ImageView();
	
	static Label label1 = new Label();
	static Label label2 = new Label();
	static SceneManager sceneManager;
	static AnimationTimer general;
	static boolean shootKey;
	static boolean jumpOverBox = false;
	static boolean stayOverBox = false;
	static boolean jumpOverBox2 = false;
	static boolean stayOverBox2 = false;
	
	public Robot(AnchorPane r,ImageView jump,ImageView jump2,ImageView hero,ImageView down,ImageView down2,ImageView hero2,ImageView bullet1,Label label1,Label label2,SceneManager sceneManager)
	{
		root =r;
		Robot.jump = jump;
		Robot.jump2 = jump2;
		Robot.hero = hero;
		Robot.hero2 = hero2;
		Robot.down = down;
		Robot.down2 = down2;
		Robot.bullet1 = bullet1;
		Robot.label1 = label1;
		Robot.label2 = label2;
		Robot.sceneManager = sceneManager;
		Robot.general = new  AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if(startShootBase == true  ){
					shootBase();
				}
				if(startJumpBase2 == true){
					jumpBase2();
				}
				
			}
		};
		general.start();
	}
	
	static ImageView menuBackground = new ImageView("menu background.jpg");
	static ImageView gameOverBackground = new ImageView("gameover background.jpg");
	static ImageView turn = new ImageView("turn.png");
	static ImageView turn2 = new ImageView("turn2.png");
	static final int JUMPSPEED = -10;
	static final int MOVESPEED = 5;
	static final int GROUND = 310;
	static final int SHOOTSPEED = 4;
	
	private static int centerX = 0;
	private static int centerX2 = 0;
	private static int centerBoxX = 0;
	private static int centerBoxX2 = 0;
	private static int centerBaseX = 0;
	private static int centerBaseX2 = 0;
	private static int centerY = 0;
	private static int centerY2 = 0;
	private static int centerBoxY = 0;
	private static int centerBoxY2 = 0;
	private static int centerGroundX = 0;
	private static int centerGroundX2 = 0;
	private static boolean jumped = false;
	private static boolean movingLeft = false;
	private static boolean movingRight = false;
	private static boolean ducked = false;
	
	
	private static boolean jumped2 = false;
	private static boolean movingLeft2 = false;
	
	private static boolean movingRight2 = false;
	private static boolean ducked2 = false;
	private static boolean stopGoUp = false,stopGoUp2 = false;
	private static boolean shooted = false;
	private static boolean shooted2 = false;
	static boolean shootControl = false;
    static boolean shootControl2 = false;
	private static boolean startShootBase = false;
	static public boolean startJumpBase2 = false;
	private static boolean startShootBase2 = false;
	static public boolean startJumpBase = false;
	static public boolean fromBoxToBase = false;
	static public boolean fromBoxToBase2 = false;
	static public boolean onGround = false;
	static public boolean onGround2= false;

	private static int speedX = 0;
	private static int speedY = 1,speedY2 = 1;
	private static int bulletSpeed ;
	private static int bulletSpeed2 ;
	private static int bulletX = 0;
	private static int bulletX2 = 0;
	private static int k=1,r=1;
	private static Rectangle rec;
	private static Laser singleLaser;
	private static int lifeCount1 =10;
	private static int lifeCount2 =10;
	private static Thread thread;
	static String shootMusic="src/Laser.mp3",jumpMusic="src/jump.mp3",moveMusic="src/move.mp3",hurtMusic="src/hurt.mp3",gameoverMusic="src/gameover.mp3",backgroundMusic="src/background.mp3",fallMusic="src/robotdropping.mp3";
	static Media sound;
	static MediaPlayer mediaPlayerShoot,mediaPlayerRight,mediaPlayerLeft,mediaPlayerDuck,mediaPlayerHit,mediaPlayer;
	static boolean startGame = false;
    static boolean soundOn = false;
    static int count=0;
	
	
	

    private static Set<Laser> LaserSet = new HashSet<>();

    public static void playBackgroundMusic()
    {
    	sound = new Media(new File(Robot.backgroundMusic).toURI().toString());
        mediaPlayerRight = new MediaPlayer(sound);
        mediaPlayerRight.play();
        mediaPlayerRight.setCycleCount(5);
    }
    public static void exitGame()
    {
    	Platform.exit();
    }
    
    
	 static AnimationTimer an = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if (ducked == false) {
					speedX = MOVESPEED;
					
				}
				
				if(stayOverBox == false && fromBoxToBase == false  && onGround == false){
					if(centerX<150 )
					{
						System.out.println("cennterX: "+centerX);
						centerX += speedX;
						hero.setTranslateX(centerX);
					}
					if(centerX>=150)
					{
						jumpOverBox = true;
					}
				}
				if(stayOverBox == false && fromBoxToBase == true  && onGround == false){
					if(centerBaseX < 0 )
					{
						centerBaseX += speedX;
						hero.setTranslateX(centerBaseX);
					}
					if(centerBaseX>=0)
					{
						jumpOverBox = true;
					}
				}
				if(onGround == true & stayOverBox == false)
				{
					if(centerGroundX<920 )
					{
						System.out.println("cennterX: "+centerX);
						centerGroundX += speedX;
						hero.setTranslateX(centerGroundX);
					}
					else if(centerGroundX>=920)
					{
						sceneManager.goToGameWonScene(sceneManager);
					}
//					if(centerX>=400)
//					{
//						jumpOverBox = true;
//					}
				}
			   if(stayOverBox == true  && onGround == false){
//				    if(count==0) {
//				    	centerX=0;
//				    	count=1;
//				    }
					if(centerBoxX<200)
					{
						centerBoxX += speedX;
						hero.setTranslateX(centerBoxX);
						System.out.println("centeX < 100");
						
					}
					else if(centerBoxX>=200){
						sound = new Media(new File(Robot.fallMusic).toURI().toString());
				        mediaPlayerRight = new MediaPlayer(sound);
				        mediaPlayerRight.play();
						System.out.println("centerBoxX>=100");
						root.getChildren().remove(hero);
						root.getChildren().add(turn);
						turn.relocate(hero.getLayoutX()+hero.getTranslateX(),hero.getLayoutY()+hero.getTranslateY());
						
						RotateTransition rt = new RotateTransition(Duration.millis(2000),turn);
						rt.setCycleCount(1);
						rt.setByAngle(1080);
						TranslateTransition translateTransition = new TranslateTransition(); 
						translateTransition.setDuration(Duration.millis(2000)); 
				        translateTransition.setNode(turn); 
				        translateTransition.setFromY(0f);
				        translateTransition.setToY(500f);
				        ParallelTransition prt = new ParallelTransition(turn,rt,translateTransition);
						
						prt.play();
						
						
						
						
						
								prt.setOnFinished(new EventHandler<ActionEvent>(){
									
									@Override
									public void handle(ActionEvent arg0) {
										 
										
										 System.out.println("shdfvsdfsjhfsjhfsjh");
										 root.getChildren().add(hero);
										 hero.relocate(270, 510);
										 root.getChildren().remove(turn);
										 count =0;
										 stayOverBox = false;
										 onGround = true;
										 if(lifeCount1>0){
											 label1.setText("Health: "+ --lifeCount1);
										 }
											 if(lifeCount1==0)
											{
												sceneManager.goToGameWonScene2(sceneManager);
											}
									}	
								});
								
								
							}
			   			}
					
				
						
					
					
					
				}
				
				
				
				
			
		};
		 static AnimationTimer an2 = new AnimationTimer() {
				
				@Override
				public void handle(long now) {
					if (ducked == false) {
						speedX = -MOVESPEED;
					}
					if(stayOverBox == false && fromBoxToBase == false && onGround == false){
						
						if(centerX>=-150){
							centerX += speedX;
							hero.setTranslateX(centerX); 
							jumpOverBox = false;
							
						}
						if(centerX<-150){
							sound = new Media(new File(Robot.fallMusic).toURI().toString());
					        mediaPlayerRight = new MediaPlayer(sound);
					        mediaPlayerRight.play();
							System.out.println("previous jumper");
							root.getChildren().remove(hero);
							root.getChildren().add(turn);
							turn.relocate(hero.getLayoutX()+hero.getTranslateX(),hero.getLayoutY()+hero.getTranslateY());
							RotateTransition rt = new RotateTransition(Duration.millis(1000),turn);
							rt.setCycleCount(1);
							rt.setByAngle(720);
							TranslateTransition translateTransition = new TranslateTransition(); 
							translateTransition.setDuration(Duration.millis(1000)); 
					        translateTransition.setNode(turn); 
					        translateTransition.setFromY(0f);
					        translateTransition.setToY(350f);
					        ParallelTransition prt = new ParallelTransition(turn,rt,translateTransition);
							
							prt.play();
							prt.setOnFinished(new EventHandler<ActionEvent>(){
								
									@Override
									public void handle(ActionEvent arg0) {
										 
										 root.getChildren().remove(turn);
										 root.getChildren().add(hero);
										 hero.relocate(200, 310);
										 System.out.println(hero.getLayoutX()+hero.getTranslateX()+"   "+hero.getLayoutY()+hero.getTranslateY());
										 
										if(lifeCount1>0)
										 label1.setText("Health: "+ --lifeCount1);
										if(lifeCount1==0)
										{
											sceneManager.goToGameWonScene2(sceneManager);
										}
										
									}
							});
						  }
						
						}
							
							
						
					        
					        
					        
						       
					        
					      
							
						
						
					
					   
							
					if(stayOverBox == false && fromBoxToBase == true  && onGround == false){
						
						if(centerBaseX>=-300){
							centerBaseX += speedX;
							hero.setTranslateX(centerBaseX); 
							jumpOverBox = false;
							
							
						}
						if(centerBaseX<-300){
							
							root.getChildren().remove(hero);
							root.getChildren().add(turn);
							turn.relocate(hero.getLayoutX()+hero.getTranslateX(),hero.getLayoutY()+hero.getTranslateY());
							RotateTransition rt = new RotateTransition(Duration.millis(1000),turn);
							rt.setCycleCount(1);
							rt.setByAngle(720);
							TranslateTransition translateTransition = new TranslateTransition(); 
							translateTransition.setDuration(Duration.millis(1000)); 
					        translateTransition.setNode(turn); 
					        translateTransition.setFromY(0f);
					        translateTransition.setToY(350f);
					        ParallelTransition prt = new ParallelTransition(turn,rt,translateTransition);
							
							prt.play();
							prt.setOnFinished(new EventHandler<ActionEvent>(){
								
									@Override
									public void handle(ActionEvent arg0) {
										 
										 root.getChildren().remove(turn);
										 root.getChildren().add(hero);
										 hero.relocate(350, 310);
										 System.out.println(hero.getLayoutX()+hero.getTranslateX()+"   "+hero.getLayoutY()+hero.getTranslateY());
										 System.out.println("relocated (200,310)");
										 if(lifeCount1>0)
											 label1.setText("Health: "+ --lifeCount1);
											if(lifeCount1==0)
											{
												sceneManager.goToGameWonScene2(sceneManager);
											}
										 centerBaseX = 0; 
										 jumped = false;
										centerX = -270;
									}
							
							
						});
					  }
					
					}
					if(onGround == true && stayOverBox == false  )
					{
						if(centerGroundX>-200 )
						{
							System.out.println("cennterX: "+centerX);
							centerGroundX += speedX;
							hero.setTranslateX(centerGroundX);
						}
//						if(centerX>=400)
//						{
//							jumpOverBox = true;
//						}
					}
					 if(stayOverBox == true  && onGround == false){
						
						if(centerBoxX>=-5){
							centerBoxX += speedX;
							hero.setTranslateX(centerBoxX); 
							jumpOverBox = false;
							
						}
						if(centerBoxX<-5){
							
					        root.getChildren().remove(hero);
							root.getChildren().add(jump);
					        TranslateTransition translateTransition = new TranslateTransition(); 
					        
					       
					        translateTransition.setDuration(Duration.millis(800)); 
					        translateTransition.setNode(jump); 
					        translateTransition.setByY(105); 
					        translateTransition.play();
					        translateTransition.setOnFinished(new EventHandler<ActionEvent>(){
								
								@Override
								public void handle(ActionEvent arg0) {
									 
									 root.getChildren().remove(jump);
									 root.getChildren().add(hero);
									 hero.relocate(350, 310);
									 stayOverBox = false;
									 System.out.println("stayOverBox = false   " + centerX);
									 centerX =150;
									 centerBoxX = 0;
									 centerBaseX =0;
									 fromBoxToBase = true;
									
									
									
								}
						
						
					});
					        
							
						}
						
					}
					
					
					
					
					
				}
			};

			 static AnimationTimer an3 = new AnimationTimer() {
					
					@Override
					public void handle(long now) {
						if (ducked2 == false) {
							speedX = MOVESPEED;
						}
						
						if(stayOverBox2 == false && fromBoxToBase2 == false && onGround2 == false){
							
							
							if(centerX2<=100)
							{
								centerX2 += speedX;
								hero2.setTranslateX(centerX2);
								jumpOverBox2 = false;
								
							}
							
							
							if(centerX2>100){
								sound = new Media(new File(Robot.fallMusic).toURI().toString());
						        mediaPlayerRight = new MediaPlayer(sound);
						        mediaPlayerRight.play();
								System.out.println("previous jumper");
								root.getChildren().remove(hero2);
								root.getChildren().add(turn2);
								turn2.relocate(hero2.getLayoutX()+hero2.getTranslateX(),hero2.getLayoutY()+hero2.getTranslateY());
								RotateTransition rt = new RotateTransition(Duration.millis(1000),turn2);
								rt.setCycleCount(1);
								rt.setByAngle(720);
								TranslateTransition translateTransition = new TranslateTransition(); 
								translateTransition.setDuration(Duration.millis(1000)); 
						        translateTransition.setNode(turn2); 
						        translateTransition.setFromY(0f);
						        translateTransition.setToY(350f);
						        ParallelTransition prt = new ParallelTransition(turn2,rt,translateTransition);
								
								prt.play();
								prt.setOnFinished(new EventHandler<ActionEvent>(){
									
										@Override
										public void handle(ActionEvent arg0) {
											 
											 root.getChildren().remove(turn2);
											 root.getChildren().add(hero2);
											 hero2.relocate(1100, 310);
											// System.out.println(hero.getLayoutX()+hero.getTranslateX()+"   "+hero.getLayoutY()+hero.getTranslateY());
											 
											if(lifeCount2>0)
											 label2.setText("Health: "+ --lifeCount2);
											if(lifeCount2==0)
											{
												sceneManager.goToGameWonScene(sceneManager);
											}
											
										}
								});
							  }
							
							}
						
						if(stayOverBox2 == false && fromBoxToBase2 == true  && onGround2 == false){
							
							if(centerBaseX2<=300){
								centerBaseX2 += speedX;
								System.out.println("less  "+centerBaseX2);
								hero2.setTranslateX(centerBaseX2); 
								jumpOverBox2 = false;
								
								
							}
							if(centerBaseX2>300){
								
								root.getChildren().remove(hero2);
								root.getChildren().add(turn2);
								System.out.println("igh      "+centerBaseX2);
								turn2.relocate(hero2.getLayoutX()+hero2.getTranslateX(),hero2.getLayoutY()+hero2.getTranslateY());
								RotateTransition rt = new RotateTransition(Duration.millis(1000),turn2);
								rt.setCycleCount(1);
								rt.setByAngle(720);
								TranslateTransition translateTransition = new TranslateTransition(); 
								translateTransition.setDuration(Duration.millis(1000)); 
						        translateTransition.setNode(turn2); 
						        translateTransition.setFromY(0f);
						        translateTransition.setToY(350f);
						        ParallelTransition prt = new ParallelTransition(turn2,rt,translateTransition);
								
								prt.play();
								prt.setOnFinished(new EventHandler<ActionEvent>(){
									
										@Override
										public void handle(ActionEvent arg0) {
											 
											 root.getChildren().remove(turn2);
											 root.getChildren().add(hero2);
											 hero2.relocate(900, 310);
											 System.out.println(hero2.getLayoutX()+hero2.getTranslateX()+"   "+hero2.getLayoutY()+hero2.getTranslateY());
											 System.out.println("dshfhsjfjsdfjfshdhbfewfwey");
											 if(lifeCount2>0)
												 label2.setText("Health: "+ --lifeCount2);
												if(lifeCount2==0)
												{
													sceneManager.goToGameWonScene(sceneManager);
												}
											 centerBaseX2 = 0; 
											 jumped2 = false;
											//centerX2 = -270;
										}
								
								
							});
						  }
						
						}
						
						if(stayOverBox2 == true  && onGround2 == false){
							
							if(centerBoxX2<=5){
								centerBoxX2 += speedX;
								hero2.setTranslateX(centerBoxX2); 
								jumpOverBox2 = false;
								
							}
							if(centerBoxX2>5){
								
						        root.getChildren().remove(hero2);
								root.getChildren().add(jump2);
						        TranslateTransition translateTransition = new TranslateTransition(); 
						        
						       
						        translateTransition.setDuration(Duration.millis(800)); 
						        translateTransition.setNode(jump2); 
						        translateTransition.setByY(100); 
						        translateTransition.play();
						        translateTransition.setOnFinished(new EventHandler<ActionEvent>(){
									
									@Override
									public void handle(ActionEvent arg0) {
										 
										 root.getChildren().remove(jump2);
										 root.getChildren().add(hero2);
										 hero2.relocate(920, 310);
										 stayOverBox2 = false;
										 System.out.println("stayOverBox = false   " + centerX);
										 //centerX2 =150;
										 centerBoxX2 = 0;
										 centerBaseX2 =0;
										 fromBoxToBase2 = true;
										
										
										
									}
							
							
						});
						        
								
							}
							
						}
						
						if(onGround2 == true && stayOverBox2 == false  )
						{
							if(centerGroundX2<200 )
							{
								System.out.println("cennterX: "+centerX);
								centerGroundX2 += speedX;
								hero2.setTranslateX(centerGroundX2);
							}
//							if(centerX>=400)
//							{
//								jumpOverBox = true;
//							}
						}
						
						
							
						
						
						
						
						
					}
				};
				 static AnimationTimer an4 = new AnimationTimer() {
						
						@Override
						public void handle(long now) {
							if (ducked2 == false) {
								speedX = -MOVESPEED;
							}
							if(stayOverBox2 == false && fromBoxToBase2 == false  && onGround2 == false){
								
							
								if(centerX2>-170){
									centerX2 += speedX;
									hero2.setTranslateX(centerX2); 
	//								System.out.println("hi   "+hero2.getLayoutX()+hero2.getTranslateX());
									
								}
								if(centerX2<=-170)
								{
									jumpOverBox2 = true;
								}
							}
							
							if(stayOverBox2 == false && fromBoxToBase2 == true  && onGround2 == false){
								if(centerBaseX2 >= 0 )
								{
									centerBaseX2 += speedX;
									hero2.setTranslateX(centerBaseX2);
								}
								if(centerBaseX2<0)
								{
									jumpOverBox2 = true;
								}
							}
							
							
							
							if(onGround2 == true & stayOverBox2 == false)
							{
								if(centerGroundX2>-920 )
								{
									System.out.println("cennterX: "+centerX);
									centerGroundX2 += speedX;
									hero2.setTranslateX(centerGroundX2);
								}
								else if(centerGroundX2<=-920)
								{
									sceneManager.goToGameWonScene2(sceneManager);
								}
//								if(centerX>=400)
//								{
//									jumpOverBox = true;
//								}
							}
						   if(stayOverBox2 == true  && onGround2 == false){
//							    if(count==0) {
//							    	centerX=0;
//							    	count=1;
//							    }
								if(centerBoxX2>-200)
								{
									centerBoxX2 += speedX;
									hero2.setTranslateX(centerBoxX2);
									System.out.println("centeX < 100");
									
								}
								else if(centerBoxX2<=-200){
									sound = new Media(new File(Robot.fallMusic).toURI().toString());
							        mediaPlayerRight = new MediaPlayer(sound);
							        mediaPlayerRight.play();
									System.out.println("centerBoxX>=100");
									root.getChildren().remove(hero2);
									root.getChildren().add(turn2);
									turn2.relocate(hero2.getLayoutX()+hero2.getTranslateX(),hero2.getLayoutY()+hero2.getTranslateY());
									
									RotateTransition rt = new RotateTransition(Duration.millis(2000),turn2);
									rt.setCycleCount(1);
									rt.setByAngle(1080);
									TranslateTransition translateTransition = new TranslateTransition(); 
									translateTransition.setDuration(Duration.millis(2000)); 
							        translateTransition.setNode(turn2); 
							        translateTransition.setFromY(0f);
							        translateTransition.setToY(500f);
							        ParallelTransition prt = new ParallelTransition(turn2,rt,translateTransition);
									
									prt.play();
									
									
									
									
									
											prt.setOnFinished(new EventHandler<ActionEvent>(){
												
												@Override
												public void handle(ActionEvent arg0) {
													 
													
													 System.out.println("shdfvsdfsjhfsjhfsjh");
													 root.getChildren().add(hero2);
													 hero2.relocate(1000, 510);
													 root.getChildren().remove(turn2);
													 count =0;
													 stayOverBox2 = false;
													 onGround2 = true;
													 if(lifeCount2>0){
														 label2.setText("Health: "+ --lifeCount2);
													 }
														 if(lifeCount2==0)
														{
															sceneManager.goToGameWonScene(sceneManager);
														}
												}	
											});
											
											
										}
						   			}
							
							
							
						}
					};
					 static AnimationTimer jumpAnimation = new AnimationTimer() {
							
							@Override
							public void handle(long now) {
								if (jumped == false) {
									speedY = JUMPSPEED;
									jumped = true;
								}
								System.out.println(k);
								
								if(centerY>-300 && k==1){
									centerY += speedY;
									jump.setTranslateY(centerY); 
									System.out.println("up  "+jump.getBoundsInParent());
//									System.out.println(centerY);
//									System.out.println("hi   "+jump.getLayoutX()+jump.getTranslateX()+ "  "+jump.getLayoutY()+jump.getTranslateY());
								
								}
								if(centerY==-300){ speedY=-JUMPSPEED;stopGoUp =true;}
								if(centerY<0 && stopGoUp==true && jumpOverBox == false){
									centerY += speedY;
									jump.setTranslateY(centerY); 
									System.out.println("down  "+jump.getBoundsInParent());
//									System.out.println(centerY);
//									System.out.println("hi2   "+jump.getLayoutX()+jump.getTranslateX()+ "  "+jump.getLayoutY()+jump.getTranslateY());
									k=3;
								}
								if(centerY<-20 && stopGoUp==true && jumpOverBox == true){
									centerY += speedY;
									jump.setTranslateY(centerY);
									
									if(centerY >= -70)
										{
											jumpOverBox = false;
											stayOverBox = true;
											root.getChildren().remove(jump);
											root.getChildren().add(hero);
											hero.relocate(hero.getLayoutX()+hero.getTranslateX(),hero.getLayoutY()+hero.getTranslateY()-120);
											
											hero.setTranslateX(30);
											jumped = false;
											stopGoUp = false;
											System.out.println("styoverbox = true");
											jumpAnimation.stop();
											
										}
									
									k=1;
									//System.out.println("down  "+jump.getBoundsInParent());
//									System.out.println(centerY);
//									System.out.println("hi2   "+jump.getLayoutX()+jump.getTranslateX()+ "  "+jump.getLayoutY()+jump.getTranslateY());
									
								}
								
								if(centerY==0 && k==3)k=5;
								if(k==5) {
									root.getChildren().remove(jump);
									root.getChildren().add(hero);
									//hero.relocate(jump.getLayoutX()+jump.getTranslateX(), jump.getLayoutY()+jump.getTranslateY());
									//stopGoUp = false;
									
									
									jumped = false;
									k=1;
									jumpAnimation.stop();
									
								}

							}
							
							
						};
						static AnimationTimer jumpAnimation2 = new AnimationTimer() {
								
								@Override
								public void handle(long now) {
									if (jumped2 == false) {
										speedY = JUMPSPEED;
										jumped2 = true;
									}
									System.out.println(r);
									jumpBase2();
									
									
								}
								};
								
						 static AnimationTimer shootAnimation = new AnimationTimer() {
								
								@Override
								public void handle(long now) {
									if(isShooted()==false){
										setShooted(true);
										bulletSpeed = SHOOTSPEED;
									}
									shootBase();
						
							
									
								}
							};
								 static AnimationTimer shootAnimation2 = new AnimationTimer() {
										
										@Override
										public void handle(long now) {
											if(shooted2==false){
												shooted2 = true;
												bulletSpeed2 = -SHOOTSPEED;
											}
											shootBase2();
								
									
											
										}
									};
									
									
	public static void jumpBase2()
	{
		
		if (jumped2 == false) {
			speedY2 = JUMPSPEED;
			jumped2 = true;
		}
		if(centerY2>-300 && r==1){
			centerY2 += speedY2;
			jump2.setTranslateY(centerY2); 
			System.out.println("Up  hero2 position:  "+hero2.getBoundsInParent());
			System.out.println("Up  "+jump2.getBoundsInParent());
			//System.out.println(centerY2);
			//System.out.println("hi   "+jump2.getLayoutX()+jump2.getTranslateX()+ "  "+jump2.getLayoutY()+jump2.getTranslateY());
		
		}
		if(centerY2==-300){ speedY2=-JUMPSPEED;stopGoUp2 =true;}
		if(centerY2<0 && stopGoUp2==true && jumpOverBox2 == false){
			centerY2 += speedY2;
			jump2.setTranslateY(centerY2); 
			System.out.println("down  hero2 position:  "+hero2.getBoundsInParent());
			System.out.println("down  "+jump2.getBoundsInParent());
			//System.out.println(centerY2);
			//System.out.println("hi2   "+jump2.getLayoutX()+jump2.getTranslateX()+ "  "+jump2.getLayoutY()+jump2.getTranslateY());
			r=3;
		}
		if(centerY2<-20 && stopGoUp2==true && jumpOverBox2 == true){
			centerY2 += speedY2;
			jump2.setTranslateY(centerY2);
			
			if(centerY2 >= -70)
				{
					jumpOverBox2 = false;
					stayOverBox2 = true;
					startJumpBase2=false;
					root.getChildren().remove(jump2);
					root.getChildren().add(hero2);
					hero2.relocate(hero2.getLayoutX()+hero2.getTranslateX(),hero2.getLayoutY()+hero2.getTranslateY()-120);
					
					hero2.setTranslateX(-60);
					jumped2 = false;
					//stopGoUp2 = false;
					System.out.println("styoverbox = true");
				}
			
			r=1;
			
		}
		
		
		if(centerY2==0 && r==3)r=5;
		if(r==5) {
			//jumpAnimation2.stop();
			root.getChildren().add(hero2);
			root.getChildren().remove(jump2);
			//hero2.relocate(jump2.getLayoutX()+jump2.getTranslateX(), jump2.getLayoutY()+jump2.getTranslateY());
			//System.out.println("salar vai");
			jumped2 = false;
			//startShootBase = false;
			startJumpBase2=false;
			stopGoUp2=false;
			r=1;
		}
	}
	public static void shootBase()
	{
		bulletSpeed = SHOOTSPEED;
		//shooted = true;
		
		//System.out.println("entered shootAnimation");
		
		//rec.relocate(hero.getLayoutX()+hero.getTranslateX(), hero.getLayoutY()+hero.getLayoutY());
		if(bulletX<1300)
		{
			bulletX+= bulletSpeed;
			System.out.println("bullet running");
			
//			singleLaser.getLaser().setTranslateX(bulletX);
//			System.out.println("hi  " +singleLaser.getLaser().getLayoutX()+singleLaser.getLaser().getTranslateX()+"  "+singleLaser.getLaser().getLayoutY()+singleLaser.getLaser().getLayoutY() );
//			if(ifLaserHitHero()==true){
//				root.getChildren().remove(hero2);
//				singleLaser.getLaser().setVisible(false);
//			}
			rec.setTranslateX(bulletX);
			System.out.println("value of jumped2:   "+jumped2);
			if(ifLaserHitHero()==true){
				
				//root.getChildren().remove(hero2);
				System.out.println("laser is hitting.");
				if(lifeCount2>0){
					label2.setText("Health : "+ --lifeCount2);
					sound = new Media(new File(hurtMusic).toURI().toString());
		             mediaPlayer = new MediaPlayer(sound);
		             mediaPlayer.play();
					
				}
				if(lifeCount2==0)
				{
					 sound = new Media(new File(gameoverMusic).toURI().toString());
		             mediaPlayer = new MediaPlayer(sound);
		             mediaPlayer.play();
					 sceneManager.goToGameWonScene(sceneManager);
					//for(int i=0;i<10000000;i++){}
					//sceneManager.goToGameOverScene(sceneManager);
				}
				
				rec.setVisible(false);
				//shootAnimation.stop();
				bulletX=0;
				startShootBase = false;
				shootControl = false;
				setShooted(false);
				
				
			}
		}
		
		if(bulletX>=1300)
		{
			rec.setVisible(true);
			//shootAnimation.stop();
			setShooted(false);
			bulletX=0;
			System.out.println("Gone to 1300");
			startShootBase = false;
			shootControl = false;
			//thread.stop();
			//singleLaser.getLaser().setVisible(true);
			//root.getChildren().remove(rec);
			
		}
	}
	
	public static void shootBase2()
	{
		
		bulletSpeed2 = -SHOOTSPEED;
		
		if(bulletX2>-1300)
		{
			bulletX2+= bulletSpeed2;
			System.out.println("bullet running");
			
//			singleLaser.getLaser().setTranslateX(bulletX);
//			System.out.println("hi  " +singleLaser.getLaser().getLayoutX()+singleLaser.getLaser().getTranslateX()+"  "+singleLaser.getLaser().getLayoutY()+singleLaser.getLaser().getLayoutY() );
//			if(ifLaserHitHero()==true){
//				root.getChildren().remove(hero2);
//				singleLaser.getLaser().setVisible(false);
//			}
			rec.setTranslateX(bulletX2);
			System.out.println("value of jumped:   "+jumped);
			if(ifLaserHitHero2()==true){
				
				//root.getChildren().remove(hero2);
				System.out.println("laser is hitting.");
				if(lifeCount1>0){
					label1.setText("Health : "+ --lifeCount1);
					sound = new Media(new File(hurtMusic).toURI().toString());
		             mediaPlayer = new MediaPlayer(sound);
		             mediaPlayer.play();
					
				}
				if(lifeCount1==0)
				{
					 sound = new Media(new File(gameoverMusic).toURI().toString());
		             mediaPlayer = new MediaPlayer(sound);
		             mediaPlayer.play();
					sceneManager.goToGameWonScene2(sceneManager);
					//for(int i=0;i<10000000;i++){}
					//sceneManager.goToGameOverScene(sceneManager);
				}
				
				rec.setVisible(false);
				shootAnimation2.stop();
				bulletX2=0;
				startShootBase2 = false;
				shooted2 = false;
				shootControl2 = false;
				
				
			}
		}
		
		if(bulletX2<=-1300)
		{
			rec.setVisible(true);
			shootAnimation2.stop();
			shooted2 = false;
			bulletX2=0;
			System.out.println("Gone to -50");
			startShootBase2 = false;
			shootControl2 = false;
			
		}
	}





	public static void moveRight() {
		
		
		 an.start();
		 sound = new Media(new File(moveMusic).toURI().toString());
         mediaPlayerRight = new MediaPlayer(sound);
         mediaPlayerRight.play();
		
		
		
	}
	public static void moveRight2() {
		
		
		an3.start();
		sound = new Media(new File(moveMusic).toURI().toString());
        mediaPlayerRight = new MediaPlayer(sound);
        mediaPlayerRight.play();
		Bounds localBounds = hero.getBoundsInLocal();
		Bounds screenBounds = hero.localToScene(localBounds);
		System.out.println("right "+screenBounds.getWidth()+ " "+ screenBounds.getMaxX() +" "+ screenBounds.getMaxY()+ " "+screenBounds.getDepth()+" "+ screenBounds.getHeight());;
		
		
		
		
	}

	public static void moveLeft() {
		an2.start();
		sound = new Media(new File(moveMusic).toURI().toString());
        mediaPlayerLeft = new MediaPlayer(sound);
        mediaPlayerLeft.play();
		
	}
	public static void moveLeft2() {
		an4.start();
		 sound = new Media(new File(moveMusic).toURI().toString());
         mediaPlayerLeft = new MediaPlayer(sound);
         mediaPlayerLeft.play();
		
	}


	public static void stopRight() {
		setMovingRight(false);
		stop();
	}

	public static void stopLeft() {
		setMovingLeft(false);
		stop();
	}
	public static void stopRight2() {
		setMovingRight2(false);
		stop2();
	}

	public static void stopLeft2() {
		setMovingLeft2(false);
		stop2();
	}
	public void stopJumping()
	{
		if(k==5) {
			jumpAnimation.stop();
			root.getChildren().add(hero);
			root.getChildren().remove(jump);
			hero.relocate(jump.getLayoutX()+jump.getTranslateX(), jump.getLayoutY()+jump.getTranslateY());
			System.out.println("salar vai");
			jumped = false;
			k=1;
		}
	}


	private static void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
			an.stop();
			an2.stop();
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
			an.stop();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
			an2.stop();
		}

	}
	private static void stop2() {
		if (isMovingRight2() == false && isMovingLeft2() == false) {
			speedX = 0;
			an3.stop();
			an4.stop();
		}

		if (isMovingRight2() == false && isMovingLeft2() == true) {
			moveLeft2();
			an3.stop();
		}

		if (isMovingRight2() == true && isMovingLeft2() == false) {
			moveRight2();
			an4.stop();
		}

	}


	public static void jumping() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}
		
		
		root.getChildren().removeAll(hero);
		root.getChildren().addAll(jump);
		jump.relocate(hero.getLayoutX()+hero.getTranslateX(), hero.getLayoutY()+hero.getTranslateY());
		
		jumpAnimation.start();
		 sound = new Media(new File(jumpMusic).toURI().toString());
         mediaPlayer = new MediaPlayer(sound);
         mediaPlayer.play();
		
	}
	public static void jumping2() {

		root.getChildren().remove(hero2);
		root.getChildren().add(jump2);
		
		jump2.relocate(hero2.getLayoutX()+hero2.getTranslateX(), hero2.getLayoutY()+hero2.getTranslateY());
		
		//jumpAnimation2.start();
		startJumpBase2 = true;
		 sound = new Media(new File(jumpMusic).toURI().toString());
         mediaPlayer = new MediaPlayer(sound);
         mediaPlayer.play();
		
	}

	
	public static void shoot() {
		
		


		            if(shootControl == false ){
		            		shootControl = true;
						 rec = new Rectangle(260,345,20,10);
						 if(jumped == true )
						 {
							 rec.relocate(jump.getLayoutX()+jump.getTranslateX()+100, jump.getLayoutY()+jump.getTranslateY()+15);
								
						 }
						 else if(ducked == true)
						 {
							 rec.relocate(down.getLayoutX()+down.getTranslateX()+100, down.getLayoutY()+down.getTranslateY()+40);
								
						 }
						 else if(jumped == false && ducked == false)
						 {
							 
							rec.relocate(hero.getLayoutX()+hero.getTranslateX()+100, hero.getLayoutY()+hero.getTranslateY()+40);
						 }
						 
						 root.getChildren().add(rec);  
						 rec.setFill(Color.YELLOW);
						//shootAnimation.start();
						 
						 sound = new Media(new File(shootMusic).toURI().toString());
			             mediaPlayer = new MediaPlayer(sound);
			             mediaPlayer.play();
						 startShootBase = true;
		            }
					 //shootAnimation.start();
					
			
					
		
		
	


//		 singleLaser = new Laser(hero.getLayoutX()+hero.getTranslateX()+100, hero.getLayoutY()+hero.getTranslateY()+45);
//		 LaserSet.add(singleLaser);
//		 updatePlayerLasers();
		 
		 
		 
		 
		 
			
			
		//root.getChildren().add(rec);   
		//Rectangle r = new Rectangle();
		//shootAnimation.start(); 
	}
	 static void updatePlayerLasers() {
			root.getChildren().remove(rootLaser);
			rootLaser = new AnchorPane();
			transferLasersToGroup(LaserSet, rootLaser);
		}
		
		
	 static void transferLasersToGroup(Set<Laser> laserSet, AnchorPane lasers) {
			for (Laser laserObject : laserSet) {
				lasers.getChildren().add(laserObject.getLaser());
			}
			root.getChildren().add(lasers);
		}
	 static boolean ifLaserHitHero()
	 {
		 if(jumped2==false)
		 {
			 System.out.println("jumped = false");
			 if(hero2.getBoundsInParent().intersects(rec.getBoundsInParent()))
			 {
				 System.out.println("hit condition checked");
				 return true;
			 }
			 else return false;
		 }
		 else if(jumped2==true)
		 {
			 System.out.println("jumped = true");
			 if(jump2.getBoundsInParent().intersects(rec.getBoundsInParent()))
			 {
				 return true;
			 }
			 else return false;
			 
		 }
		 else return false;
	 }
		
	public static void shoot2() {
	
		if(shootControl2 == false)
		{
			shootControl2 = true;
			 rec = new Rectangle(1160,345,20,10);
			 if(jumped2 == true )
			 {
				 rec.relocate(jump2.getLayoutX()+jump2.getTranslateX(), jump2.getLayoutY()+jump2.getTranslateY()+40);
					
			 }
			 else if(ducked2 == true)
			 {
				 rec.relocate(down2.getLayoutX()+down2.getTranslateX(), down2.getLayoutY()+down2.getTranslateY()+40);
					
			 }
			 else if(jumped2 == false && ducked2 == false)
			 {
				 
				rec.relocate(hero2.getLayoutX()+hero2.getTranslateX(), hero2.getLayoutY()+hero2.getTranslateY()+40);
			 }
			 root.getChildren().add(rec);  
			 rec.setFill(Color.YELLOW);
			 shootAnimation2.start();
	         sound = new Media(new File(shootMusic).toURI().toString());
	         mediaPlayer = new MediaPlayer(sound);
	         mediaPlayer.play();
		}
	}
	
	 static boolean ifLaserHitHero2()
	 {
		 if(jumped==false)
		 {
			 System.out.println("jumped = false");
			 if(hero.getBoundsInParent().intersects(rec.getBoundsInParent()))
			 {
				 System.out.println("hit condition checked");
				 return true;
			 }
			 else return false;
		 }
		 else if(jumped==true)
		 {
			 System.out.println("jumped = true");
			 if(jump.getBoundsInParent().intersects(rec.getBoundsInParent()))
			 {
				 return true;
			 }
			 else return false;
			 
		 }
		 else return false;
	 }
	public static void duck()
	{
		if (ducked == false) {
			speedY = 0;
			ducked = true;
		}
		root.getChildren().remove(hero);
		root.getChildren().add(down);
		down.relocate(60, 70);
		Path path2 = new Path();
		
        path2.getElements().addAll(new MoveTo(hero.getLayoutX()+hero.getTranslateX(),hero.getLayoutY()+hero.getTranslateY()), new VLineTo(hero.getLayoutY()-1));
        path2.setVisible(false);
        root.getChildren().add(path2);
        PathTransition pt2 = new PathTransition(Duration.millis(500), path2, down);
        pt2.setCycleCount(1);
        pt2.play();
        pt2.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				 
				
				 root.getChildren().add(hero);
				 root.getChildren().remove(down);
				 ducked = false;
				
			}
	    	
	    });
        
	}
	
	public static void duck2()
	{
		if (ducked2 == false) {
			speedY = 0;
			ducked2 = true;
		}
		root.getChildren().remove(hero2);
		root.getChildren().add(down2);
		down2.relocate(60, 70);
		Path path2 = new Path();
		
        path2.getElements().addAll(new MoveTo(hero2.getLayoutX()+hero2.getTranslateX(),hero2.getLayoutY()+hero2.getTranslateY()), new VLineTo(hero2.getLayoutY()-1));
       path2.setVisible(false);
        root.getChildren().add(path2);
        PathTransition pt2 = new PathTransition(Duration.millis(500), path2, down2);
        pt2.setCycleCount(1);
        pt2.play();
        pt2.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				 
				
				 root.getChildren().add(hero2);
				 root.getChildren().remove(down2);
				 ducked2 = false;
				
			}
	    	
	    });
        
	}

	public static int getCenterX() {
		return centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

	public static boolean isJumped() {
		return jumped;
	}

	public static int getSpeedX() {
		return speedX;
	}

	public static int getSpeedY() {
		return speedY;
	}

	public static void setCenterX(int centerX) {
		Robot.centerX = centerX;
	}

	public static  void setCenterY(int centerY) {
		Robot.centerY = centerY;
	}

	public static void setJumped(boolean jumped) {
		Robot.jumped = jumped;
	}

	public static void setSpeedX(int speedX) {
		Robot.speedX = speedX;
	}

	public static void setSpeedY(int speedY) {
		Robot.speedY = speedY;
	}

	public static boolean isDucked() {
		return ducked;
	}

	public static void setDucked(boolean ducked) {
		Robot.ducked = ducked;
	}

	public static boolean isMovingRight() {
		return movingRight;
	}

	public static void setMovingRight(boolean movingRight) {
		Robot.movingRight = movingRight;
	}

	public static boolean isMovingLeft() {
		return movingLeft;
	}

	public static void setMovingLeft(boolean movingLeft) {
		Robot.movingLeft = movingLeft;
	}

	public static boolean isJumped2() {
		return jumped2;
	}

	public static void setJumped2(boolean jumped2) {
		Robot.jumped2 = jumped2;
	}

	public static boolean isMovingLeft2() {
		return movingLeft2;
	}

	public static void setMovingLeft2(boolean movingLeft2) {
		Robot.movingLeft2 = movingLeft2;
	}
	public static boolean isMovingRight2() {
		return movingRight2;
	}

	public static void setMovingRight2(boolean movingRight2) {
		Robot.movingRight2 = movingRight2;
	}

	public static boolean isDucked2() {
		return ducked2;
	}

	public static void setDucked2(boolean ducked2) {
		Robot.ducked2 = ducked2;
	}
	public static boolean isShooted() {
		return shooted;
	}
	public static void setShooted(boolean shooted) {
		Robot.shooted = shooted;
	}

}
