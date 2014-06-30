package playing;

import android.os.Handler;
import android.util.Log;

public class Timer extends Thread {
    private static final String TAG = "Timer";
    private final Handler handler;
    private boolean isRunning;
    private int delay = 1000;

    public Timer(int _delay, Handler handler) {
        setDelay(delay);
        this.handler = handler;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void run() {
        Log.d(TAG, "run()");
        while (isRunning) {
            Log.d(TAG, "notifying handler to UI ...");
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
