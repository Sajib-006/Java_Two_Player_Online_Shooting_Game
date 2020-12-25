package application;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

public class laoutX
    extends Application
{
  private Line line;

  private Scene getScene()
  {
    Group group = new Group();

    line = new Line(10, 10, 60, 10);

    group.getChildren().add(line);

    Scene scene = new Scene(group, 640, 480);

    return scene;
  }

  @Override
  public void start(Stage stage) throws Exception
  {
    stage.setScene(getScene());
    stage.show();
    System.out.println("x: " + line.getLayoutX() + ", y: " + line.getLayoutY());
  }

  public static void main(String[] args)
  {
    Application.launch(args);
  }
}