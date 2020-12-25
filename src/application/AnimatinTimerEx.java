package application;

import javafx.animation.AnimationTimer;

public class AnimatinTimerEx extends AnimationTimer{

	@Override
	public void handle(long now) {
		// TODO Auto-generated method stub
		
	}
	private volatile boolean running;

    @Override
    public void start() {
         super.start();
         running = true;
    }

    @Override
    public void stop() {
        super.stop();
        running = false;
        System.out.println("stopped");
    }

    public boolean isRunning() {
        return running;
    }

}

