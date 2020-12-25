package application;

import java.util.Scanner;
import application.*;
import javafx.scene.input.KeyCode;
import util.NetworkUtil;

public class WriteThread implements Runnable {

    private Thread thr;
    private NetworkUtil nc;
    String name,s=null,x="shit";

    public WriteThread(NetworkUtil nc, String name) {
        this.nc = nc;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            //Scanner input = new Scanner(System.in);
            while (true) {
                //String s = input.nextLine();
            	//Runner2 runner2 = new Runner2();
            	s = Runner2.key;
            	 System.out.println(s);
                //runner2.key="h";
                //Data d = new Data(name + " " + s);
                //System.out.println(d.getElement());
                if(s!=null){
                	if(x.equals(s)){
                	
                	}else
                	{
                		x=s;
                    	System.out.println("madarchod:        "+x);
                        nc.write(s);
                	}
                }
            }
        } catch (Exception e) {
            System.out.println(e);
    }
        nc.closeConnection();
}
}



