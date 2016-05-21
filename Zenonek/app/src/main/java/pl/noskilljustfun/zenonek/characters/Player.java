package pl.noskilljustfun.zenonek.characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import pl.noskilljustfun.zenonek.R;

/**
 * Created by Bartosz on 20.05.2016.
 */
public class Player {

    private Bitmap bitmap;
    private int posX,posY,maxX,maxY,minX,minY;
    private int speed=0;

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
        posY=maxY-100;
    }


    public void update(){
        //TODO dodanie poruszania sie w poziomie
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

}
