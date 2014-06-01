package playing;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Paint;

public class SnakeEngine {

	private static final int Y_AXIS = 20;
	private static final int X_AXIS = 20;
	private SnakeView sv;
	private Timer st;
	private SnakeField[][] board;
	private ArrayList<SnakeField> tail;
	private ArrayList<SnakeField> dinner;

	public SnakeEngine() {
		init();
	}

	public SnakeEngine(SnakeView _snakeView) {
		sv = _snakeView;
		init();
		sv.setDrawableObjects(tail, board, X_AXIS, Y_AXIS);
	}

	private void init() {
		st = new Timer();
		board = new SnakeField[X_AXIS][Y_AXIS];
		tail = new ArrayList<SnakeField>();
		dinner = new ArrayList<SnakeField>();
		populateBoard();
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
		st.start();
	}
}
