package hiscore;

public class HighScoreRow {
	private int mId;
	private String mName;
	private int mResult;

	HighScoreRow(int id, String name, int result) {
		mId = id;
		mName = name;
		setResult(result);
	}

	public void setResult(int mResult) {
		this.mResult = mResult;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public int getResult() {
		return mResult;
	}

	public int getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}

}