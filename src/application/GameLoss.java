package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class GameLoss implements SceneInterface {
	private SceneManager sceneManager;
	private Scene gameWonScene;
	private Group root2;
	AnchorPane root;
	
	
	public GameLoss(SceneManager sceneManager,AnchorPane root) {
		this.sceneManager = sceneManager;
		this.root = root;
	}
	
	@Override
	public Scene init(int width, int height) {
		root2 = new Group();
		gameWonScene = new Scene(root2, width, height, Color.AZURE);
		
		addGameWonText();
		addMenuButton();
		
		return gameWonScene;
	}
	
	private void addGameWonText() {
		Text gameWonText = UIGenerator.createText("You lose the game! ", 50, 50);
		root2.getChildren().add(gameWonText);
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