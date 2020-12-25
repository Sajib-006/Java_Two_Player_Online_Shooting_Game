package application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Instructions implements SceneInterface {
	private SceneManager sceneManager;
	private Scene instructionsScene;
	private Group root2;
	private AnchorPane root;
	
	
	public Instructions(SceneManager sceneManager,AnchorPane root) {
		this.sceneManager = sceneManager;
		this.root = root;
	}
	
	
	@Override
	public Scene init(int width, int height) {
		root2 = new Group();
		instructionsScene = new Scene(root2, width, height, Color.AZURE);		
		
		addInstructionsText();
		addMenuButton();
		
        return instructionsScene;
	}
	
	private void addInstructionsText() {
		String text =
				"Press the four arrows to move your player.\n"+
				"The left arrow to move the player left.\n"+
				"The right arrow to move the player right.\n"+
				"The up arrow to jump. \n"+
				"The down arrow to duck or sit.\n" +
				
				
				"Press CONTROL to shoot oponent player.\n"+
				
				"There are 5 lifes for each player.\n"+
				"The player having all life finished ,he will lose.\n"+
				
				"Jump or sit to save your player from lasers.\n"+
				"Try to save yourself and kill oponent player.\n"+
				
				"If you can reduce your oponents life to zero ,you will win.\n"+
				"And if tour life is zero ande less than your oponent ,you will lose.";
				
				
				
				
				
		Text instructionsText = UIGenerator.createText(text, 100, 50, 15);
		
		root2.getChildren().add(instructionsText);
	}
	
	private void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Back to Menu", 30, 540);
        
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToMenuScene(sceneManager,root);
            }
        });
        
        root2.getChildren().add(menuButton);
	}
}