package juandavid.example.com.memothis.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import juandavid.example.com.memothis.database.DatabaseContract.FeedEntry;

/**
 * Created by juandavid on 26/04/17.
 */

class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "FeedReader.db";

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
					FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_QUESTION + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_ANSWER + TEXT_TYPE  + " )";
	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

	DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
}
