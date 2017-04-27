package juandavid.example.com.memothis.database;

import android.provider.BaseColumns;

/**
 * Created by juandavid on 26/04/17.
 */

final class DatabaseContract {
	private DatabaseContract(){}

	static class FeedEntry implements BaseColumns{
		static final String TABLE_NAME = "ItemList";
		static final String COLUMN_QUESTION = "question";
		static final String COLUMN_ANSWER = "answer";

		@SuppressWarnings("unused")
		public static final String[] ALL_COLUMNS = {COLUMN_QUESTION, COLUMN_ANSWER};
	}
}
