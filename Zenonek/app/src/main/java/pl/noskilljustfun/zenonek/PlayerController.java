package pl.noskilljustfun.zenonek;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import pl.noskilljustfun.zenonek.characters.Player;

/**
 * Created by Bartosz on 21.05.2016.
 */
public class PlayerController {

    private Rect left;
    private Rect right;



    public PlayerController(int scrWidth, int scrHeight) {

            left = new Rect(20
                        ,scrHeight-150
                        ,220
                        ,scrHeight-50);

        right = new Rect(scrWidth-220
                        ,scrHeight-150
                        ,scrWidth-20
                        ,scrHeight-50);
    }

    public ArrayList<Rect> getAllRectangles(){

        ArrayList<Rect> rects = new ArrayList<Rect>();
        rects.add(left);
        rects.add(right);
        return rects;
    }

    public void buttonClicked(MotionEvent motionEvent, boolean running, Player zenonek)    {

        int clickCounter = motionEvent.getPointerCount();
        int temp=0;
        for(int i=0;i<clickCounter;i++){
            int x= (int) motionEvent.getX(i);
            int y=(int) motionEvent.getY(i);

            if(running){
                switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){

                    case MotionEvent.ACTION_DOWN:
                        if(left.contains(x,y)){
                            zenonek.setIsPressLeft(true);
                        }
                        else if(right.contains(x,y)){
                            zenonek.setIsPressRight(true);
                        }
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        if(left.contains(x,y)){
                            zenonek.setIsPressLeft(true);
                        }
                        else if(right.contains(x,y)){
                            zenonek.setIsPressRight(true);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (right.contains(x, y)) {
                            zenonek.setIsPressRight(false);
                        } else if (left.contains(x, y)) {
                            zenonek.setIsPressLeft(false);
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        if(left.contains(x,y)){
                            zenonek.setIsPressLeft(true);
                        }
                        else if(right.contains(x,y)){
                            zenonek.setIsPressRight(true);
                        }
                        break;

                }
            }


        }
    }

}
