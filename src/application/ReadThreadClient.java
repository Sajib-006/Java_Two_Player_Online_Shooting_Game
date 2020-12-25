package application;



import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import util.NetworkUtil;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil nc;

    public ReadThreadClient(NetworkUtil nc) {
        this.nc = nc;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
            	/*Object o;
                   o = nc.read();
                if (o != null) {
                    if (o instanceof Data) {
                        Data obj = (Data) o;
                        System.out.println(obj.getElement());
                        String str=obj.getElement();
                        System.out.println("hi str           "+str);
                        String[] strArray=str.split(" ");
                        
//                        Runner2 runner2 = new Runner2();
//                        Robot robot = runner2.getRobot();
//                        RunnerClient rc = new RunnerClient();
//                        Robot robot = rc.getRobot();*/
                        String s=(String)nc.read();
                        System.out.println("hello      "+s);
                        KeyCode key ;
                        //System.out.println("strArray[1] :"+strArray[1]);
                      
                        if(s.equals("UP"))
                        {
                        	key = KeyCode.UP;
                        	Platform.runLater(()->{
                        	Robot.jumping();
                        	});
                        	
                        }
                        
                        	
                        else if(s.equals("DOWN"))
                        {
                        	key = KeyCode.DOWN;
                        	Platform.runLater(()->{
                        		Robot.duck();
                        	});
                        }
                        
                        
                        else if(s.equals("RIGHT")){
                        	key = KeyCode.RIGHT;
                        	Platform.runLater(()->{
                        		Robot.moveRight();
                        	});
                        }
                        
                        
                        else if(s.equals("LEFT")){
                        	key = KeyCode.LEFT;
                        	Platform.runLater(()->{
                        		Robot.moveLeft();
                        	});
                        }
                        
                        
                        else if(s.equals("CONTROL")){
                        	key = KeyCode.CONTROL;
                        	Platform.runLater(()->{
                        		Robot.shoot();
                        	});
                        }
                        
                        else if(s.equals("X")){
                        	key = KeyCode.X;
                        	Platform.exit();
                        	
                        }
                        
                        
//                        else if(s.equals("W")){
//                        	key = KeyCode.W;
//                        	Platform.runLater(()->{
//                            	Robot.jumping2();
//                        	});
//                        }
//                        
//                        
//                        else if(s.equals("A")){
//                        	key = KeyCode.A;
//                        	Platform.runLater(()->{
//                        		Robot.moveLeft2();
//                        	});
//                        }
//                        
//                        
//                        else if(s.equals("S")){
//                        	key = KeyCode.S;
//                        	Platform.runLater(()->{
//                        		Robot.shoot2();
//                        	});
//                        }
//                        
//                        
//                        else if(s.equals("D")){
//                        	key = KeyCode.D;
//                        	Platform.runLater(()->{
//                        		Robot.moveRight2();
//                        	});
//                        }
                        
                        
//                        else if(s.equals("Z")){
//                        	key = KeyCode.Z;
//                        	Platform.runLater(()->{
//                        		Robot.duck2();
//                        	});
//                        }
                        
                        else if(s.equals("STOPRIGHT")){
                        	//key = KeyCode.Z;
                        	Platform.runLater(()->{
                        		Robot.stopRight();
                        	});
                        }
                        
                        else if(s.equals("STOPLEFT")){
                        	//key = KeyCode.Z;
                        	Platform.runLater(()->{
                        		Robot.stopLeft();
                        	});
                        }
                        /*if(strArray[1].equals("UP"))
                        {
                        	key = KeyCode.UP;
                        	System.out.println("xxxxxxxxxxxxxxxxxxxx");
                        	Robot.jumping();
                        	
                        }
                        
                        	
                        else if(obj.getElement().equals("DOWN"))
                        	key = KeyCode.DOWN;
                        else if(obj.getElement().equals("RIGHT"))
                        	key = KeyCode.RIGHT;
                        else if(obj.getElement().equals("LEFT"))
                        	key = KeyCode.LEFT;
                        else if(obj.getElement().equals("CONTROL"))
                        	key = KeyCode.CONTROL;
                        else if(obj.getElement().equals("W"))
                        	key = KeyCode.W;
                        else if(obj.getElement().equals("A"))
                        	key = KeyCode.A;
                        else if(obj.getElement().equals("S"))
                        	key = KeyCode.S;
                        else if(obj.getElement().equals("D"))
                        	key = KeyCode.D;
                        else if(obj.getElement().equals("Z"))
                        	key = KeyCode.Z;
                       
                    }*/
                //}
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
        nc.closeConnection();
    }
}




