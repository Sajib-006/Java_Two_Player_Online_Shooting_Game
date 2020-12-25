package application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Battle  implements SceneInterface {
	public static final int TOTAL_LEVELS = 4;
	String key =null;
	private Scene battleScene;
	private SceneManager sceneManager;
	private int level;
	private String name;
	
	
	private AnchorPane root;
	
	
	
	
	public Battle(SceneManager sceneManager, int level, AnchorPane root, String name) {
		this.sceneManager = sceneManager;
		this.level = level;
		this.root = root;
		this.name = name;
	}
	
	
	@Override
	public Scene init(int width, int height) {
		
		battleScene = new Scene(root, width, height);
		//addPlayerLasers();
		
		//battleScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		 battleScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	              
	              key = event.getCode().toString();
	              System.out.println(key);
	              if(name.equals("server")){
	            	  System.out.println("write entered");
	            	  Runner2.nc.write(key);
	            	  
	              }else if(name.equals("client")){
	            	  RunnerClient.nc.write(key);
	              }
	              
	              System.out.println("key  "+key);
	                switch (event.getCode()) {
	                //key = String(event.getCode());
	        		case UP:
	        			if(Robot.isJumped()== false){
	        				//Robot.startJumpBase2=true;
	            			Robot.jumping();
	        			}
	        				
	            			break;

	        		case DOWN:
	        			//currentSprite = down;
	        			if (Robot.isJumped() == false && Robot.isDucked()==false) {
	        				Robot.duck();
	        				Robot.setDucked(true);
	        				Robot.setSpeedX(0);
	        			}
//	        			if (robot.isDucked() == true) {
//	        				//robot.duck();
//	        				//robot.setDucked(false);
//	        				//robot.setSpeedX(0);
//	        				System.out.println(event.getCode());
//	        			}
	        			break;

	        		case LEFT:
	        			Robot.moveLeft();
	        			Robot.setMovingLeft(true);
	        			break;

	        		case RIGHT:
	        			Robot.moveRight();
	        			Robot.setMovingRight(true);
	        			break;

	        		//case SPACE:
	        			

	        		case CONTROL:
//	        			if (Robot.isDucked() == false && Robot.isJumped() == false) {
//	        				Robot.shootKey = true;
//	        				Robot.shoot();
//	        			}
	        			if(Robot.isShooted() == false)
	        					Robot.shoot();
	    
	        			break;
	        			
	        		case X:
	        			Robot.exitGame();
	        			
	        		case W:
	        			if(Robot.isJumped2()== false )
	        			Robot.jumping2();
	        			break;
	        		case D:
	        			Robot.moveRight2();
	        			Robot.setMovingRight2(true);
	        			break;
	        		case S:
	        			if (Robot.isDucked2() == false && Robot.isJumped2() == false) {
        				Robot.shoot2();
	        			}
	        			Robot.shoot2();
	        			break;
	        		case A:
	        			Robot.moveLeft2();
	        			Robot.setMovingLeft2(true);
	        			break;
	        		case Z:
	        			if (Robot.isJumped2() == false && Robot.isDucked2()==false) {
	        				Robot.duck2();
	        				Robot.setDucked2(true);
	        				Robot.setSpeedX(0);
	        			}
	        			
	        			break;
	        		
					default:
						break;

	        		}
	            }
	        });
		 
		 

	        battleScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
//	                switch (event.getCode()) {
//	                    case UP:    goNorth = false; break;
//	                    case DOWN:  goSouth = false; break;
//	                    case LEFT:  goWest  = false; break;
//	                    case RIGHT: goEast  = false; break;
//	                    case SHIFT: running = false; break;
//	                    case SPACE: jumped  = false; break;
//	                }
	            	switch (event.getCode()) {
	        		case UP:
	        			//System.out.println("Stop moving up");
	        			//robot.stopJumping();
	        			break;

	        		case DOWN:
	        			
	        			Robot.setDucked(false);
	        			break;

	        		case LEFT:
	        			Robot.stopLeft();
	        			 if(name.equals("server")){
	   	            	  Runner2.nc.write("STOPLEFT");
	   	              	 }else if(name.equals("client")){
	   	            	  RunnerClient.nc.write("STOPLEFT");
	   	              	 }
	        			
	        			break;

	        		case RIGHT:
	        			Robot.stopRight();
	        			 if(name.equals("server")){
		   	            	  Runner2.nc.write("STOPRIGHT");
		   	              	 }else if(name.equals("client")){
		   	            	  RunnerClient.nc.write("STOPRIGHT");
		   	              	 }
	        			
	        			break;
	        	
	        			
	        		case W:
	        			//System.out.println("Stop moving up");
	        			break;
	
	        		case Z:
	        			//currentSprite = hero2;
	        			Robot.setDucked2(false);
	        			break;
	
	        		case A:
	        			System.out.println("Robot.stopLeft2()");
	        			Robot.stopLeft2();
	        			break;
	
	        		case D:
	        			System.out.println("Robot.stopRight2()");
	        			Robot.stopRight2();
	        			break;

	        		

	        		}
	            }
	        });

		
		return battleScene;
	}
	
	/**
	 * Entry point for game loop
	 * @param elapsedTime seconds elapsed since last loop
	 */
//	public void step(double elapsedTime) {
//		checkTimeUp(elapsedTime);
//		
//		if (stepCounter % getEnemyCreationRate() == 0) {
//			createEnemy();
//		}
//		
//		moveEnemies();
//		movePlayerLasers();
//		checkPlayerLaserHitEnemy();
//		checkPlayerEnemyIntersect();
//		checkEnemyReachBottom();	
//		
//		stepCounter++;
//	}
//	
//	private void playerWinsLevel() {
//		sceneManager.goToNextLevelScene(sceneManager, level + 1);
//	}
	
//	private void addLevelText() {
//		addBottomLeftText("Level " + level);
//	}
//	
//	private void addEnemies() {
//		enemies = new Group();
//		root.getChildren().add(enemies);
//	}
//	
//	private int getEnemyCreationRate() {
//		int[] enemyCreationRate = {150, 125, 100, 75};
//		return enemyCreationRate[level];
//	}
//	
//	private double getEnemyTravelRate() {
//		double[] enemyTravelRate = {0.9, 1.0, 1.1, 1.2};
//		return enemyTravelRate[level];
//	}
	
//	private void checkTimeUp(double elapsedTime) {
//		timer += elapsedTime;
//		if (timer > TIMER_LIMIT) {
//			playerWinsLevel();
//		}
//	}
	
//	private void createEnemy() {
//		Enemy enemyObject = new Enemy();
//		Rectangle enemy = enemyObject.getEnemy();
//		enemies.getChildren().add(enemy);
//	}
	
//	private void moveEnemies() {
//		for (Node enemyNode : enemies.getChildren()) {
//			Rectangle enemy = (Rectangle) enemyNode;
//			enemy.setY(enemy.getY() + getEnemyTravelRate());
//		}
//	}
	
//	private void checkPlayerLaserHitEnemy() {
//		checkPlayerLaserHitRectangleInGroup(enemies, true);
//	}
	
//	private void checkPlayerEnemyIntersect() {
//		for (Node enemyNode : enemies.getChildren()) {
//			Rectangle enemy = (Rectangle) enemyNode;
//			playerLosesIfIntersectEnemy(enemy);
//		}
//	}
//	
//	private void checkEnemyReachBottom() {
//		for (Node enemyNode : enemies.getChildren()) {
//			Rectangle enemy = (Rectangle) enemyNode;
//			if (enemy.getY() + Enemy.HEIGHT > Main.SIZE) {
//				playerLoses();
//			}
//		}
//	}
//	
	
	
//	private void handleKeyPressed(KeyCode code) {
//		switch (code) {
//			case Q:
//				quitToMenu();
//				break;
//			case SPACE:
//				// cheat code to skip to next level
//				playerWinsLevel();
//				break;
//			case W: 
//				shootPlayerLaser("UP");
//				break;
//			default:
//				playerObject.handlePlayerKey(code);
//		}
//	}
}