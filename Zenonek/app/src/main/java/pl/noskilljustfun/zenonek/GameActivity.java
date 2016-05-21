package pl.noskilljustfun.zenonek;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends AppCompatActivity {

    private Playground playground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point point= new Point();

     display.getSize(point);

        playground=new Playground(this,point.x,point.y);
        setContentView(playground);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            playground.pause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        playground.resume();
    }
}
