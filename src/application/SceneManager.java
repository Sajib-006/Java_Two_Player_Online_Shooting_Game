package application;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SceneManager {
	public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
	private Stage stage;
	private Timeline animation;
	private AnchorPane root;
	private String name;
		
	
	
	
	public SceneManager(Stage primaryStage,AnchorPane root,String name) {
		this.stage = primaryStage;
		this.animation = new Timeline();
		this.root = root;
		this.name = name;
		stage.show();
	}

	
	public void goToMenuScene(SceneManager sceneManager,AnchorPane root) {
		animation.stop();
		Menu menu = new Menu(sceneManager,root);
		Scene menuScene = menu.init(1366, 728);
		stage.setScene(menuScene);
	}
	
	
	public void goToInstructionsScene(SceneManager sceneManager) {
		animation.stop();
		Instructions instructions = new Instructions(sceneManager,root);
		Scene instructionsScene = instructions.init(1366,728);
		stage.setScene(instructionsScene);
	}
	
	
	public void goToGameOverScene(SceneManager sceneManager) {
		animation.stop();
		GameOver gameOver = new GameOver(sceneManager,root);
		Scene gameOverScene = gameOver.init(1366,728);
		//gameOverScene.getCh
		stage.setScene(gameOverScene);
	}
	
	
	public void goToGameWonScene(SceneManager sceneManager) {
		animation.stop();
		GameWon gameWon = new GameWon(sceneManager,root);
		Scene gameWonScene = gameWon.init(1366,728);
		stage.setScene(gameWonScene);
	}
	
	public void goToGameWonScene2(SceneManager sceneManager) {
		animation.stop();
		GameWon2 gameWon = new GameWon2(sceneManager,root);
		Scene gameWonScene = gameWon.init(1366,728);
		stage.setScene(gameWonScene);
	}
	
//	public void goToNextLevelScene(SceneManager sceneManager, int level) {
//		animation.stop();
//		NextLevel nextLevel = new NextLevel(sceneManager, level);
//		Scene nextLevelScene = nextLevel.init(Main.SIZE, Main.SIZE);
//		stage.setScene(nextLevelScene);
//	}
	
	
	public void goToBattleScene(SceneManager sceneManager, int level, AnchorPane root) {
		Robot.startGame = true;
		Robot.playBackgroundMusic();
		 
		if(name.equals("server")){
			Battle battle = new Battle(sceneManager, level,root,name);
			Scene battleScene = battle.init(1366,728);
			stage.setScene(battleScene);
		}
		else if(name.equals("client")){
			BattleClient battle = new BattleClient(sceneManager, level,root);
			Scene battleScene = battle.init(1366,728);
			stage.setScene(battleScene);
		}
		//Scene battleScene = new Scene(root,1366,728);
		
		
		//KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> battle.step(SECOND_DELAY));
		//setGameLoop(frame);
	}
	

//	public void goToBossBattleScene(SceneManager sceneManager) {
//		BossBattle bossBattle = new BossBattle(sceneManager);
//		Scene bossBattleScene = bossBattle.init(Main.SIZE, Main.SIZE);
//		stage.setScene(bossBattleScene);
//		
//		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> bossBattle.step(SECOND_DELAY));
//		setGameLoop(frame);
//	}
//	
	private void setGameLoop(KeyFrame frame) {
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
}