package playing;

import android.util.Log;

public class Timer extends Thread {
    private static final String TAG = "Timer";
    private boolean isRunning;
    private int delay = 1000;
    private SnakeView sv;
    Runnable r;

    public Timer() {
    }

    public Timer(int _delay, SnakeView _snakeView) {
        setDelay(delay);
        sv = _snakeView;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void run() {
        while (isRunning) {
            sv.post(new Runnable() {
                @Override
                public void run() {
                    sv.invalidate();
                }
            });
            Log.d(TAG, "run()");
            try {
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopTimer() {
        isRunning = false;
    }

    public void startTimer() {
        isRunning = true;
        start();
    }
}
