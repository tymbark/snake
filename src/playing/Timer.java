package playing;

public class Timer extends Thread {
	public boolean isRunning;
	private int delay = 1000;
	private SnakeView sv;

	public Timer() {
	}

	public Timer(boolean _isrunning, int _delay, SnakeView _snakeView) {
		isRunning = _isrunning;
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
			// do some interesting stuff
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
