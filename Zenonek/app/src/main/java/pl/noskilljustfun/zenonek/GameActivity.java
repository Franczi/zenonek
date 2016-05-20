package pl.noskilljustfun.zenonek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    private Playground playground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playground=new Playground(getApplicationContext());
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
