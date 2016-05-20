package pl.noskilljustfun.zenonek;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by Bartosz on 20.05.2016.
 */
public class Playground extends SurfaceView implements Runnable {
    private boolean running;
    private Thread gameThread;
    public Playground(Context context) {
        super(context);
    }

    @Override
    public void run() {

        while(running){
            update();
            draw();
            control();
        }


    }

    private void update() {
    }

    public void control() {
    }

    public void draw() {
    }

    public void  pause() throws InterruptedException {
        
        running=false;
        gameThread.join();
        
    }

    public void resume(){
        
        running=true;
        gameThread=new Thread();
        gameThread.start();
    }
}
