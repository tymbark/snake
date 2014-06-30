package playing;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.logging.LogRecord;

public class SnakeEngine{

    private static final int Y_AXIS = 30;
    private static final int X_AXIS = 20;
    private static final String TAG = "SnakeEngine";
    private static final int REDRAW_IMAGE = 1;
    private SnakeView gameView;
    private Timer gameTimer;
    private SnakeField[][] board;
    private ArrayList<SnakeField> tail;
    private ArrayList<SnakeField> dinner;
    private boolean isRunning;
    private Thread delay;
    private Handler handler;

    public SnakeEngine() {
        init();
    }

    public SnakeEngine(SnakeView _snakeView) {
        gameView = _snakeView;
        init();
    }

    private void init() {
        gameTimer = new Timer(1000, handler);
        delay = new Thread();
        board = new SnakeField[Y_AXIS][X_AXIS];
        tail = new ArrayList<SnakeField>();
        dinner = new ArrayList<SnakeField>();
        populateBoard();
        gameView.setDrawableObjects(tail, board, X_AXIS, Y_AXIS);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == REDRAW_IMAGE){
                    Log.d(TAG,"redrawing this shit");
                    gameView.invalidate();
                }
                super.handleMessage(msg);
            }
        };
    }



    private void populateBoard() {
        for (int i = 0; i < Y_AXIS; i++) {
            for (int j = 0; j < X_AXIS; j++) {
                board[i][j] = new SnakeField(i, j, null, null);
            }
        }
        for (int i = 3; i >= 0; i--) {
            board[10 + i][10].dir = 1;
            board[10 + i][10].snake = true;
            board[10 + i][10].empty = false;
            tail.add(board[10 + i][10]);
        }
        board[10][10].head = true;
        board[13][10].butt = true;
    }

    public void startEngine() {
        gameTimer.startTimer();
        Log.d(TAG,"startEngine");
        //isRunning = true;
        //run();
    }

    public void pauseEngine() {
        gameTimer.stopTimer();
        Log.d(TAG,"pauseEngine");
        //isRunning = false;
    }

    public void resumeEngine() {
        gameTimer.startTimer();
        Log.d(TAG, "resumeEngine");
        //isRunning = true;
        //run();
    }

}
