package tcpobject;

import java.util.Scanner;
import application.*;
import javafx.scene.input.KeyCode;
import util.NetworkUtil;

public class WriteThread implements Runnable {

    private Thread thr;
    private NetworkUtil nc;
    String name;

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
            	Runner2 runner2 = new Runner2();
            	String s = runner2.key;
                Data d = new Data(name + ":" + s);
                nc.write(d);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            nc.closeConnection();
        }
    }
}



