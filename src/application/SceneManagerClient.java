package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class handles all the scene changes. In the whole game, there is only one Stage that is used.
 * That is why this class takes in the primaryStage as a parameter in the constructor. All the scene
 * changes are done on this stage. Because this stage is a saved state across the game, the same
 * SceneManager object has to be used throughout the game. The implication is that every public method
 * this class provides takes in a SceneManager object as a parameter. Each of these public methods
 * goes to a separate Scene. So from the Scene classes, to instigate a scene switch, any one of the
 * provided public methods can be called with the current SceneManager object passed in as a 
 * parameter.
 * 
 */
public class SceneManagerClient {
	public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
	private Stage stage;
	private Timeline animation;
	private AnchorPane root;
		
	
	
	
	public SceneManagerClient(Stage primaryStage,AnchorPane root) {
		this.stage = primaryStage;
		this.animation = new Timeline();
		this.root = root;
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
		GameOver gameOver = new GameOver(sceneManager);
		Scene gameOverScene = gameOver.init(1366,728);
		stage.setScene(gameOverScene);
	}
	
	
	public void goToGameWonScene(SceneManager sceneManager) {
		animation.stop();
		GameWon gameWon = new GameWon(sceneManager);
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
		BattleClient battle = new BattleClient(sceneManager, level,root);
		Scene battleScene = battle.init(1366,728);
		//Scene battleScene = new Scene(root,1366,728);
		stage.setScene(battleScene);
		
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