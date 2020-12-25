package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class GameWon implements SceneInterface {
	private SceneManager sceneManager;
	private Scene gameWonScene;
	private Group root2;
	AnchorPane root;
	
	
	public GameWon(SceneManager sceneManager,AnchorPane root) {
		this.sceneManager = sceneManager;
		this.root = root;
	}
	
	@Override
	public Scene init(int width, int height) {
		root2 = new Group();
//		ImageView im = new ImageView("src/gameover background.jpg");
//		root2.getChildren().add(im);
		root2.getChildren().add(Robot.gameOverBackground);
		gameWonScene = new Scene(root2, width, height, Color.AZURE);
		
		
		addGameWonText();
		addMenuButton();
		
		return gameWonScene;
	}
	
	private void addGameWonText() {
		Text gameWonText = UIGenerator.createText("Player 1 beats Player 2!!!!!!!", 400, 400);
		root2.getChildren().add(gameWonText);
	}
	
	private void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Go to Menu", 50, 500);
		
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToMenuScene(sceneManager,root);
            }
        });
		
		root2.getChildren().add(menuButton);
	}
}