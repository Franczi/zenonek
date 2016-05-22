package pl.noskilljustfun.zenonek;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
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
    private boolean ending;
    private Thread gameThread=null;
    private Player zenonek;
    private Meteor meteor;
    private Meteor meteor1;
    private Meteor meteor2;
    private Meteor meteor3;
    private Paint paint;
    private long timeStart;
    private long timeTaken;
    private long distanceRemaining;




    private Canvas canvas;
    private SurfaceHolder playerHolder;
    private  PlayerController playerController;
    private int x,y;



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
        timeTaken=0;
        distanceRemaining=10000;
        timeStart=System.currentTimeMillis();

        playerController=new PlayerController(scrX,scrY);

        x=scrX;
        y=scrY;

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
        boolean hitDetected=false;
        if(Rect.intersects(zenonek.getHitBox(),meteor.getHitBox()))
        {
            hitDetected=true;
            meteor.setPosY(-100);
        }
        if(Rect.intersects(zenonek.getHitBox(),meteor1.getHitBox()))
        {
            hitDetected=true;
            meteor1.setPosY(-100);
        }
        if(Rect.intersects(zenonek.getHitBox(),meteor2.getHitBox()))
        {
            hitDetected=true;
            meteor2.setPosY(-100);
        }
        if(Rect.intersects(zenonek.getHitBox(),meteor3.getHitBox()))
        {
            hitDetected=true;
            meteor3.setPosY(-100);
        }
        if(hitDetected){

            zenonek.reduceShield();
            if(zenonek.getShield()<0){

                //gra skonczona
                ending=true;
            }}
        timeTaken=System.currentTimeMillis()-timeStart;


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
            canvas.drawText("Shield:" + zenonek.getShield(), 10, 20, paint);
            canvas.drawText("Distance:"+distanceRemaining / 1000 + " KM", 800, 20, paint);
            canvas.drawText("Time:"+ timeTaken+"s",500,20,paint);


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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        playerController.buttonClicked(event, running, zenonek);

        return true;
    }


}
