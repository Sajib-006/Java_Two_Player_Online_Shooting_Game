package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameOver implements SceneInterface {
	private SceneManager sceneManager;
	private Scene gameOverScene;
	private Group root2;
	private AnchorPane root;
	
	
	public GameOver(SceneManager sceneManager, AnchorPane root) {
		this.sceneManager = sceneManager;
		this.root = root;
	}
	
	@Override
	public Scene init(int width, int height) {
		root2 = new Group();
		root2.getChildren().add(Robot.gameOverBackground);
		gameOverScene = new Scene(root2, width, height, Color.AZURE);
		
		addGameOverText();
		addMenuButton();
		
		return gameOverScene;
	}
	
	private void addGameOverText() {
		Text gameOverText = UIGenerator.createText("GAME OVER", 50, 50);
		root2.getChildren().add(gameOverText);
	}
	
	private void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Go to Menu", 50, 100);
		
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToMenuScene(sceneManager,root);
            }
        });
		
		root2.getChildren().add(menuButton);
	}
}