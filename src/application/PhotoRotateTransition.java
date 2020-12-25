package application;
//-Dcom.sun.javafx.experimental.embedded.3d=true
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class PhotoRotateTransition
{
private ImageView imageView;

public PhotoRotateTransition(ImageView imageView, Image img){
    this.imageView = imageView;
    this.changeImage(img);
}

private void changeImage(Image img){
    this.imageView.setRotate(0);
    RotateTransition rotate1 = this.rotate1();
    rotate1.play();
    rotate1.setOnFinished(new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            imageView.setRotate(270);
            imageView.setImage(img);
            RotateTransition rotate2 = rotate2();
            rotate2.play();
        }
    });
}

private RotateTransition rotate1(){

    RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), this.imageView);
    rotateTransition.setAxis(Rotate.Y_AXIS);
    rotateTransition.setToAngle(90);
    rotateTransition.setInterpolator(Interpolator.LINEAR);
    rotateTransition.setCycleCount(1);
    return rotateTransition;
}

private RotateTransition rotate2(){
    RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(500), this.imageView);
    rotateTransition2.setAxis(Rotate.Y_AXIS);
    rotateTransition2.setToAngle(360);
    rotateTransition2.setInterpolator(Interpolator.LINEAR);
    rotateTransition2.setCycleCount(1);
    return rotateTransition2;
 }

public static void main(String[] args)
{
	ImageView hero = new ImageView("character.png");
	Image img = new Image("heliboy.png");
	PhotoRotateTransition p = new PhotoRotateTransition(hero, img);
}
}