package playing;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import java.util.logging.Handler;

import tymbark.snake.R;

public class PlayingActivity extends Activity implements OnClickListener,
        OnTouchListener {

    private static final String TAG = "PlayingActivity";
    //SnakeCage gameView;
    SnakeView gameView;
    SnakeEngine gameEngine;
    Vector2D direction;
    static boolean arc;
    boolean nyan, justStarted;
    boolean bless;
    boolean[] savedOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        Handler h;


        direction = new Vector2D();
        if (b != null) {

            Log.d("PlayingActivity.class", "intent not empty");

            savedOptions = getIntent().getExtras().getBooleanArray("options");
            arc = savedOptions[1];
        }

        Log.d(TAG, "arc=" + arc);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(R.layout.playing_view);
        //gameView = (SnakeCage) findViewById(R.id.snakeCage1);
        //gameView.setOnTouchListener(this);
        setContentView(R.layout.playing_view2);
        gameView = (SnakeView) findViewById(R.id.snakeView);
        gameView.setOnTouchListener(this);

        gameEngine = new SnakeEngine(gameView);
        
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameEngine.pauseEngine();
    }

    @Override
    protected void onResume() {
        Log.d("PlayingActivity", "onResume()");
        super.onResume();
        gameEngine.resumeEngine();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "OnClick");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean vectorStarted = false;
        Log.d(TAG, "touched");
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                if (vectorStarted)
//                    break;
//                direction.x1 = event.getX();
//                direction.y1 = event.getY();
//                vectorStarted = true;
//                break;
//            case MotionEvent.ACTION_UP:
//                direction.x2 = event.getX();
//                direction.y2 = event.getY();
//                gameView.redraw(direction.adjust());
//                break;
//        }
        return true;
    }
}