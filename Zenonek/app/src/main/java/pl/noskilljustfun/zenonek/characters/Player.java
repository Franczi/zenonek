package pl.noskilljustfun.zenonek.characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import pl.noskilljustfun.zenonek.R;

/**
 * Created by Bartosz on 20.05.2016.
 */
public class Player {


    private final static  int MAX_SPEED=20;
    private Bitmap bitmap;
    private int posX,posY,maxX,maxY,minX,minY;
    private int speed=0;



    private boolean isPressRight=false;
    private boolean isPressLeft=false;

    private final static int MOVE_LEFT =-1;
    private final static int MOVE_RIGHT =1;
  // private boolean isMoving=false;


    public Player(Context context) {
        posX=100;
        posY=100;
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.zenonek );
    }

    public Player(Context context,int x,int y) {

        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.zenonek );

        maxX=x-bitmap.getHeight();
        minX=0;
        maxY=y-bitmap.getWidth();
        minY=0;
        posX=maxX-100;
        posY=maxY-200;
    }


    public void update(){

        if(isPressRight) {
            posX+=MOVE_RIGHT;
        }
        else if(isPressLeft){
            posX+=MOVE_LEFT;
        }

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setIsPressRight(boolean isPressRight) {
        this.isPressRight = isPressRight;
    }

    public void setIsPressLeft(boolean isPressLeft) {
        this.isPressLeft = isPressLeft;
    }
}
