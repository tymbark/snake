package com.example.snake;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HiScoreTable {

	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "nickname";
	public static final String KEY_SCORE = "d";

	private static final String DATABASE_NAME = "HiScoreDB";
	private static final String DATABASE_TABLE = "scoreTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper mHelper;
	private final Context mContext;
	private SQLiteDatabase mDataBase;
	int sizeOfHiScores = 15;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_SCORE + " INTEGER NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			db.execSQL("DELETE FROM KEY_ROWID WHERE NAME = '" + DATABASE_TABLE
					+ "'");
			onCreate(db);
		}

	}

	public HiScoreTable(Context c) {
		mContext = c;
	}

	public HiScoreTable open() throws SQLException {
		mHelper = new DbHelper(mContext);
		mDataBase = mHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mHelper.close();
	}

	public long createEntry(String name, int score) {

		long size = DatabaseUtils.queryNumEntries(mDataBase, DATABASE_TABLE);
		if (size >= sizeOfHiScores) {
			mDataBase.delete(DATABASE_TABLE,
					KEY_SCORE + "=" + getLastHiScore(), null);
		}

		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_SCORE, score);
		return mDataBase.insert(DATABASE_TABLE, null, cv);

	}

	/*
	 * public String getData() { //probably unused String[] columns = new
	 * String[]{ KEY_ROWID, KEY_NAME, KEY_SCORE}; Cursor c =
	 * mDataBase.query(DATABASE_TABLE, columns, null, null, null, null, null);
	 * String result = "";
	 * 
	 * int iRow = c.getColumnIndex(KEY_ROWID); int iName =
	 * c.getColumnIndex(KEY_NAME); int iScore = c.getColumnIndex(KEY_SCORE);
	 * 
	 * for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){ result = result +
	 * c.getString(iRow)+" "+c.getString(iName)+" "+c.getString(iScore)+"\n"; }
	 * 
	 * return result; }
	 */

	public void deleteAll() {
		mDataBase.delete(DATABASE_TABLE, null, null);

	}



	public ArrayList<HighScoreRow> getData() {
		ArrayList<HighScoreRow> array = new ArrayList<HighScoreRow>();
		String[] columns = new String[] { KEY_NAME, KEY_ROWID, KEY_SCORE };
		Cursor c = mDataBase.query(DATABASE_TABLE, columns, null, null, null,
				null, KEY_SCORE + " DESC");

		int name = c.getColumnIndex(KEY_NAME);
		int score = c.getColumnIndex(KEY_SCORE);
		int idx = c.getColumnIndex(KEY_ROWID);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			array.add(new HighScoreRow(c.getInt(idx), c.getString(name), c
					.getInt(score)));
		}
		return array;
	}

	public String getDataName() {
		String[] columns = new String[] { KEY_NAME };
		Cursor c = mDataBase.query(DATABASE_TABLE, columns, null, null, null,
				null, KEY_SCORE + " DESC");
		String result = "";

		int iName = c.getColumnIndex(KEY_NAME);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iName) + " " + "\n";
		}

		return result;
	}

	public String getDataScore() {
		String[] columns = new String[] { KEY_SCORE };
		Cursor c = mDataBase.query(DATABASE_TABLE, columns, null, null, null,
				null, KEY_SCORE + " DESC");
		String result = "";

		int iScore = c.getColumnIndex(KEY_SCORE);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getInt(iScore) + "\n";
		}

		return result;
	}

	public int getLastHiScore() {
		long size = DatabaseUtils.queryNumEntries(mDataBase, DATABASE_TABLE);
		if (size < sizeOfHiScores)
			return 0;
		String[] columns = new String[] { KEY_SCORE };
		Cursor c = mDataBase.query(DATABASE_TABLE, columns, null, null, null,
				null, KEY_SCORE + " DESC");
		String result = "";
		int iScore = c.getColumnIndex(KEY_SCORE);
		c.moveToLast();
		result = c.getString(iScore);
		int foo = Integer.parseInt(result);
		return foo;
	}
/*
	public String getDataLP() {
		String[] columns = new String[] { KEY_SCORE };
		Cursor c = mDataBase.query(DATABASE_TABLE, columns, null, null, null,
				null, KEY_SCORE + " DESC");
		String result = "";
		int i = 1;
		// int iScore = c.getColumnIndex(KEY_SCORE);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + i + "\n";
			i++;
		}

		return result;
	}*/
}
