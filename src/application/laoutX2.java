package application;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class laoutX2 extends Application {

    private Line line, line2;        

    public static void main(String[] args) {
        Application.launch(args);
    }


    private Scene getScene() {
        Group group = new Group();

        //layout position is x:0 and y:0
        //painting starts at x:10 and y:10
        line = new Line(10, 10, 60, 10);
        line2 = new Line(10, 50, 60, 50);
        
        //x position for layout
        line.setLayoutX(100);
        //y position for layout
        line.setLayoutY(100);
        line2.setLayoutX(line.getLayoutX());
        line2.setLayoutY(line.getLayoutY());

        group.getChildren().addAll(line,line2);

        Scene scene = new Scene(group, 640, 480);

        return scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(getScene());
        stage.show();
        System.out.println("x: " + line.getLayoutX() + ", y: " + line.getLayoutY());
        System.out.println("start x: " + line.getStartX() + ", start y: " + line.getStartY());
        System.out.println("x: " + line2.getLayoutX() + ", y: " + line2.getLayoutY());
        System.out.println("start x: " + line2.getStartX() + ", start y: " + line2.getStartY());
    }
}