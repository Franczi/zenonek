package pl.noskilljustfun.zenonek;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import pl.noskilljustfun.zenonek.characters.Player;

/**
 * Created by Bartosz on 20.05.2016.
 */
public class Playground extends SurfaceView implements Runnable {
    private boolean running;
    private Thread gameThread=null;
    private Player zenonek;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder playerHolder;

    public Playground(Context context) {
        super(context);
        playerHolder=getHolder();
        zenonek=new Player(context);
        paint = new Paint();
        zenonek.update();

    }

    @Override
    public void run() {

        while(running){
            update();
            draw();
            try {
                control();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private void update() {
        zenonek.update();
    }

    public void control() throws InterruptedException {
            gameThread.sleep(17);
    }

    public void draw() {


        if(playerHolder.getSurface().isValid()) {
            canvas = playerHolder.lockCanvas();

            canvas.drawColor(Color.argb(255,0,0,0));

            canvas.drawBitmap(
                    zenonek.getBitmap(),
                    zenonek.getPosX(),
                    zenonek.getPosY(),
                    paint);

            playerHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void  pause() throws InterruptedException {
        
        running=false;
        gameThread.join();
        
    }

    public void resume(){
        
        running=true;
        gameThread=new Thread(this);
        gameThread.start();
    }
}
