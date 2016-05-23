package pl.noskilljustfun.zenonek;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private boolean levelUP;
    private int level;
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


    private Context context;
    private Canvas canvas;
    private SurfaceHolder playerHolder;
    private  PlayerController playerController;
    private int x,y;
    Bitmap background;



    public Playground(Context context,int scrX, int scrY) {
        super(context);
        this.context=context;

        playerController=new PlayerController(scrX,scrY);
        x=scrX;
        y=scrY;
        startGame();
        playerHolder=getHolder();
        paint = new Paint();
        background=BitmapFactory.decodeResource(context.getResources(), R.drawable.space);
        level=1;


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

        zenonek.update();


        if(!ending&&!levelUP) {

            meteor.update();
            meteor1.update();
            meteor2.update();
            meteor3.update();
            distanceRemaining -= zenonek.getSpeed();
            timeTaken=System.currentTimeMillis()-timeStart;
        }
        if(distanceRemaining<0)
        {
            levelUP=true;
        }


    }

    public void control() throws InterruptedException {
            gameThread.sleep(17);
    }

    public void draw() {



        if(playerHolder.getSurface().isValid()) {
            canvas = playerHolder.lockCanvas();

            canvas.drawColor(Color.argb(255, 0, 0, 0));

            canvas.drawBitmap(background,0,0,paint);

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
            paint.setColor(Color.argb(255, 255, 255, 255));
            for (Rect rec: rects
                    ) {
                RectF recf= new RectF(rec.left,rec.top,rec.right,rec.bottom);
                canvas.drawRoundRect(recf, 15f, 15f, paint);
            }

            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTextSize(45);
            canvas.drawText("Shield:" + zenonek.getShield(), 10, 40, paint);
            canvas.drawText("Distance:" + distanceRemaining / 100 + " KM", 10, 100, paint);
            canvas.drawText("Level: " + level, 500, 100, paint);
            canvas.drawText("Time:" + timeTaken / 1000 + "s", 500, 40, paint);


            if(ending)
            {
                paint.setTextSize(80);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("GAME OVER", 350, 200, paint);
                paint.setTextSize(25);
                canvas.drawText("Time:" + timeTaken/1000 + "s", 350, 250, paint);
                canvas.drawText("Distance Remaining to next level:" + distanceRemaining / 100 + " KM", 350, 300, paint);
                paint.setTextSize(80);
                canvas.drawText("TAP to REPLAY!", 350, 400, paint);

            }

            if(levelUP)
            {
                paint.setTextSize(80);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("LEVEL UP!", 350, 200, paint);
                paint.setTextSize(40);
                canvas.drawText("Let's speed up!", 350, 290, paint);
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
    private void startGame(){
        //inicjalizacja obiektow
        //inicjalizacja metody game end
        ending=false;
        zenonek=new Player(context,x,y);
        meteor=new Meteor(context,x,y);
        meteor1=new Meteor(context,x,y);
        meteor2=new Meteor(context,x,y);
        meteor3=new Meteor(context,x,y);


        //reset czasu i dystansu, predksci i levelu
        distanceRemaining=10000; //10km
        timeTaken=0;
        level=1;

        //pobranie czasu startowego
        timeStart=System.currentTimeMillis();
    }

    private void continueGame(){
        levelUP=false;
        level++;
        distanceRemaining=10000*level;
        int currentSpeed=zenonek.getSpeed()+5;
        zenonek.setSpeed(currentSpeed);
        int currentMeteorSpeed=meteor.getSpeed()+5;
        meteor.setSpeed(currentMeteorSpeed);

        currentMeteorSpeed=meteor1.getSpeed()+5;
        meteor1.setSpeed(currentMeteorSpeed);

        currentMeteorSpeed=meteor2.getSpeed()+5;
        meteor2.setSpeed(currentMeteorSpeed);

        currentMeteorSpeed=meteor3.getSpeed()+5;
        meteor3.setSpeed(currentMeteorSpeed);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        playerController.buttonClicked(event, running, zenonek);
        //jezeli jestesmy na screenie koncowym zacznij nowa gre
        if(ending){
            startGame();
        }
        else if(levelUP){
            continueGame();
        }

        return true;
    }


}
