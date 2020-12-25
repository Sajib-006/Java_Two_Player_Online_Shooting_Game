package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ShootBullet implements Runnable{
	Thread thr;
    int bulletX=0;
	ShootBullet()
	{
		thr=new Thread(this);
		thr.start();
	}
	
	public void run()
	{
		Robot.bulletSpeed = Robot.SHOOTSPEED;
		//shooted = true;
		
		//System.out.println("entered shootAnimation");
		
		//rec.relocate(hero.getLayoutX()+hero.getTranslateX(), hero.getLayoutY()+hero.getLayoutY());
		if(bulletX<1300)
		{
			bulletX+= Robot.bulletSpeed;
			System.out.println("bullet running");
			
//			singleLaser.getLaser().setTranslateX(bulletX);
//			System.out.println("hi  " +singleLaser.getLaser().getLayoutX()+singleLaser.getLaser().getTranslateX()+"  "+singleLaser.getLaser().getLayoutY()+singleLaser.getLaser().getLayoutY() );
//			if(ifLaserHitHero()==true){
//				root.getChildren().remove(hero2);
//				singleLaser.getLaser().setVisible(false);
//			}
			Robot.rec.setTranslateX(bulletX);
			System.out.println("value of jumped2:   "+Robot.jumped2);
			if(Robot.ifLaserHitHero()==true){
				
				//root.getChildren().remove(hero2);
				System.out.println("laser is hitting.");
				if(Robot.lifeCount2>0){
					Robot.label2.setText("Health : "+ --Robot.lifeCount2);
					Robot.sound = new Media(new File(Robot.hurtMusic).toURI().toString());
					Robot. mediaPlayer = new MediaPlayer(Robot.sound);
		             Robot.mediaPlayer.play();
					
				}
				if(Robot.lifeCount2==0)
				{
					Robot.sound = new Media(new File(Robot.gameoverMusic).toURI().toString());
					 Robot.mediaPlayer = new MediaPlayer(Robot.sound);
					 Robot.mediaPlayer.play();
		             Robot.sceneManager.goToGameWonScene(Robot.sceneManager);
					//for(int i=0;i<10000000;i++){}
					Robot.sceneManager.goToGameOverScene(Robot.sceneManager);
				}
				
				Robot.rec.setVisible(false);
				//shootAnimation.stop();
				//bulletX=0;
				Robot.startShootBase = false;
				Robot.setShooted(false);
			}
		}
		
		if(bulletX>=1300)
		{
			Robot.rec.setVisible(true);
			//shootAnimation.stop();
			Robot.setShooted(false);
			//bulletX=0;
			System.out.println("Gone to 1300");
			Robot.startShootBase = false;
			//thread.stop();
			//singleLaser.getLaser().setVisible(true);
			//root.getChildren().remove(rec);
		}
	}
}
