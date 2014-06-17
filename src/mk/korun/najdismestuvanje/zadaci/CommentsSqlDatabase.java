package mk.korun.najdismestuvanje.zadaci;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.internal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CommentsSqlDatabase {
	private SQLiteDatabase database;
	private CommentsSqlHelper dbHelper;
	private String[] allColumns = { CommentsSqlHelper.COLUMN_ID, CommentsSqlHelper.COLUMN_TITLE,
			CommentsSqlHelper.COLUMN_TEXT };

	public CommentsSqlDatabase(Context context) {
		dbHelper = new CommentsSqlHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createComment(String title, String text) {
		ContentValues values = new ContentValues();
		values.put(CommentsSqlHelper.COLUMN_TITLE, title);
		values.put(CommentsSqlHelper.COLUMN_TEXT, text);
		long insertId = database.insert(CommentsSqlHelper.TABLE_COMMENTS, null, values);
		return insertId;
		/*
		 * Cursor cursor = database.query(dbHelper.TABLE_COMMENTS, allColumns,
		 * dbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		 * cursor.moveToFirst(); Comment newComment = cursorToComment(cursor);
		 * cursor.close(); return newComment;
		 */
	}

	public List<String> getAllComments() {
		List<String> comments = new ArrayList<String>();

		Cursor cursor = database.query(CommentsSqlHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return comments;
	}

	private String cursorToComment(Cursor cursor) {
		return cursor.getString(1) + "-" + cursor.getString(2);
	}

	
	
	/*
	 * Podklasa za rabota so baza
	 */
	public class CommentsSqlHelper extends SQLiteOpenHelper {
		public static final String TABLE_COMMENTS = "comments";
		
		public static final String COLUMN_ID = "id";
		public static final String COLUMN_TITLE = "title";
		public static final String COLUMN_TEXT = "text";

		private static final String DATABASE_NAME = "najdismestuvanje.db";
		private static final int DATABASE_VERSION = 1;

		private final String DATABASE_CREATE = "create table " + TABLE_COMMENTS	+ "(" 
				+ COLUMN_ID + " integer primary key autoincrement, "
				+ COLUMN_TITLE + " text not null, " 
				+ COLUMN_TEXT + " text not null);";

		public CommentsSqlHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(CommentsSqlHelper.class.getName(),
					"Upgrading database from version " + oldVersion + " to "
							+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
			onCreate(db);
		}

	}
}
