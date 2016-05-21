package pl.noskilljustfun.zenonek.characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import pl.noskilljustfun.zenonek.R;

/**
 * Created by zeno on 2016-05-21.
 */
public class Meteor {
    private Bitmap bitmap;
    private int posX,posY;
    private int speed=1;

    //wychodzenie poza ekran

    private int maxY;
    private int minY;

    private int maxX;


    private int minX;






    public Meteor(Context context, int screenY, int screenX) {
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor );
        maxY=screenY+400;
        maxX=screenX;
        minX=1;
        minY=1;
        Random generator=new Random();
        speed=generator.nextInt(6)+10;

        posY=minY;
        posX=generator.nextInt(minX)+bitmap.getWidth();


    }


    public Bitmap getBitmap() {
        return bitmap;
    }


    public void update(int playerSpeed){
        posY+=playerSpeed;
        posY+=speed;

        if(posY>maxY+bitmap.getHeight()){
            Random generator=new Random();
            speed=generator.nextInt(10)+10;
            posY=minY;
            posX=generator.nextInt(maxX)-bitmap.getWidth();

        }}
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


}
