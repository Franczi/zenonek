package pl.noskilljustfun.zenonek;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

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
}
