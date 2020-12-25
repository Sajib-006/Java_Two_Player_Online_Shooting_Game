package application;

import java.util.Scanner;
import application.*;
import javafx.scene.input.KeyCode;
import util.NetworkUtil;

public class WriteThreadClient implements Runnable {

    private Thread thr;
    private NetworkUtil nc;
    String name;

    public WriteThreadClient(NetworkUtil nc, String name) {
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
            	//RunnerClient rc = new RunnerClient();
            	String s = RunnerClient.key;
               // Data d = new Data(name + ":" + s);
            	if(s!=null)
                nc.write(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        
        }
        nc.closeConnection();
    }
}



