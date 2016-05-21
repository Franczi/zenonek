package pl.noskilljustfun.zenonek;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import pl.noskilljustfun.zenonek.characters.Meteor;
import pl.noskilljustfun.zenonek.characters.Player;

/**
 * Created by Bartosz on 20.05.2016.
 */
public class Playground extends SurfaceView implements Runnable {
    private boolean running;
    private Thread gameThread=null;
    private Player zenonek;
    private Meteor meteor;
    private Meteor meteor1;
    private Meteor meteor2;
    private Meteor meteor3;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder playerHolder;
    private  PlayerController playerController;

    public Playground(Context context) {
        super(context);
        playerHolder=getHolder();
        zenonek=new Player(context);

        paint = new Paint();
        zenonek.update();
    }

    public Playground(Context context,int scrX, int scrY) {
        super(context);
        playerHolder=getHolder();
        zenonek=new Player(context,scrX,scrY);
        meteor=new Meteor(context,scrX,scrY);
        meteor1=new Meteor(context,scrX,scrY);
        meteor2=new Meteor(context,scrX,scrY);
        meteor3=new Meteor(context,scrX,scrY);
        paint = new Paint();
        zenonek.update();
        meteor.update(10);
        meteor1.update(10);
        meteor2.update(10);
        meteor3.update(10);
        playerController=new PlayerController(scrX,scrY);
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
        meteor.update(10);
        meteor1.update(10);

        meteor2.update(10);

        meteor3.update(10);

    }

    public void control() throws InterruptedException {
            gameThread.sleep(17);
    }

    public void draw() {


        if(playerHolder.getSurface().isValid()) {
            canvas = playerHolder.lockCanvas();




            canvas.drawColor(Color.argb(255, 0, 0, 0));

            canvas.drawBitmap(
                    zenonek.getBitmap(),
                    zenonek.getPosX(),
                    zenonek.getPosY(),
                    paint);
            canvas.drawBitmap(
                    meteor.getBitmap(),
                    meteor.getPosX(),
                    meteor.getPosY(),
                    paint);
            canvas.drawBitmap(
                    meteor1.getBitmap(),
                    meteor1.getPosX(),
                    meteor1.getPosY(),
                    paint);
            canvas.drawBitmap(
                    meteor2.getBitmap(),
                    meteor2.getPosX(),
                    meteor2.getPosY(),
                    paint);
            canvas.drawBitmap(
                    meteor3.getBitmap(),
                    meteor3.getPosX(),
                    meteor3.getPosY(),
                    paint);

            ArrayList<Rect> rects=playerController.getAllRectangles();
            paint.setColor(Color.argb(200, 255, 255, 255));
            for (Rect rec: rects
                    ) {
                RectF recf= new RectF(rec.left,rec.top,rec.right,rec.bottom);
                canvas.drawRoundRect(recf, 15f, 15f, paint);
            }

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
