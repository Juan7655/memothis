package juandavid.example.com.memothis.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import juandavid.example.com.memothis.database.DatabaseContract.FeedEntry;

/**
 * Created by juandavid on 16/04/17.
 */

public final class ItemList {
	private static ItemList instance = new ItemList();

	private ItemList() {
	}

	public static ItemList getInstance() {
		return instance;
	}

	public String getName(Context context, int id) {
		SQLiteDatabase db = (new DatabaseHelper(context)).getReadableDatabase();

		Cursor c = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_QUESTION},
				FeedEntry._ID + " = " + id, null, null, null, FeedEntry._ID + " ASC", "1");
		c.moveToFirst();
		String val = c.getString(0);
		c.close();

		return val;
	}

	public String getDefinition(Context context, int id) {
		SQLiteDatabase db = (new DatabaseHelper(context)).getReadableDatabase();

		Cursor c = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_ANSWER},
				FeedEntry._ID + " = " + id, null, null, null, FeedEntry._ID + " ASC", "1");
		c.moveToFirst();
		String val = c.getString(0);
		c.close();

		return val;
	}

	public boolean isDefinition(Context context, int id, String expected) {
		return getDefinition(context, id).equals(expected);
	}

	public String[] getNameArray(Context context) {
		SQLiteDatabase db = (new DatabaseHelper(context)).getReadableDatabase();

		Cursor c = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_QUESTION},
				null, null, null, null, FeedEntry._ID + " ASC");

		String[] array = new String[c.getCount()];
		c.moveToFirst();
		int i = 0;
		do array[i++] = c.getString(0);
		while (c.moveToNext());
		c.close();
		return array;
	}

	List<String> getNameList(Context context) {
		ArrayList<String> array = new ArrayList<>();

		SQLiteDatabase db = (new DatabaseHelper(context)).getReadableDatabase();

		Cursor c = db.query(FeedEntry.TABLE_NAME, new String[]{FeedEntry.COLUMN_QUESTION},
				null, null, null, null, FeedEntry._ID + " ASC");
		c.moveToFirst();

		do array.add(c.getString(0));
		while (c.moveToNext());
		c.close();
		return array;
	}

	void setVocabularyList(Context context, List<String> names, List<String> definitions) {
		if (names.size() != definitions.size())
			throw new IndexOutOfBoundsException("Definitions array size must correspond to Names array");

		DatabaseHelper helper = new DatabaseHelper(context);
		SQLiteDatabase db = (helper).getWritableDatabase();
		helper.onUpgrade(db, 1, 1);

		for (String name : names) {
			if (name != null) {
				ContentValues values = new ContentValues();
				values.put(FeedEntry.COLUMN_QUESTION, name);
				values.put(FeedEntry.COLUMN_ANSWER, definitions.get(names.indexOf(name)));

				db.insert(DatabaseContract.FeedEntry.TABLE_NAME, null, values);
			}
		}
	}
}
