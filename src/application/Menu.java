package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * This class represents the Menu Scene from where the levels can be started. 
 * It is the main starting Scene for almost every action. From here, the player
 * can either go read the instructions or start the game.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class Menu implements SceneInterface {
	private SceneManager sceneManager;
	private Scene menuScene;
	private AnchorPane root;
	private Group root2;

	public Menu(SceneManager sceneManager, AnchorPane root) {
		this.sceneManager = sceneManager;
		this.root = root;
	}
	
	
	@Override
	public Scene init(int width, int height) {
		root2 = new Group();
		root2.getChildren().add(Robot.menuBackground);
		menuScene = new Scene(root2, width, height, Color.AZURE);
		
		addStartButton();
		addInstructionsButton();
		addExitButton();
		
        return menuScene;
	}
	
	private void addStartButton() {
		Button startButton = UIGenerator.createButton("Start Game", 50, 50);
		
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToBattleScene(sceneManager, 0,root);
            }
        });
        
        root2.getChildren().add(startButton);
	}
	private void addExitButton() {
		Button exitButton = UIGenerator.createButton("Exit", 50, 150);
		
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Robot.exitGame();
            	
            }
        });
        
        root2.getChildren().add(exitButton);
	}
	
	private void addInstructionsButton() {
		Button instructionsButton = UIGenerator.createButton("Instructions", 50, 100);
		
        instructionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToInstructionsScene(sceneManager);
            }
        });
        
        root2.getChildren().add(instructionsButton);
	}
}