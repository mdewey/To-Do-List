package me.dewey.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    
	private static final int DATABASE_VERSION = 3;

	//Groups table with one column
	public  static final String GROUP_TABLE_NAME = "Items";
	public  static final String KEY_ID = "_id";
	public  static final String COLUMN_ITEM_NAME = "ItemName";
	public  static final String COLUMN_DESCRIPTION_NAME = "ItemDescription";
    private static final String CREATE_GROUP_TABLE =
                "CREATE TABLE " + GROUP_TABLE_NAME + "(" + KEY_ID
    			+ " integer primary key autoincrement, " 
                + COLUMN_ITEM_NAME	+ " text not null,"
                + COLUMN_DESCRIPTION_NAME	+ " text not null);";
    
      
    
	public DBHelper(Context context) {
		super(context, "ToDoList", null, DATABASE_VERSION);
	}
	
	  @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(CREATE_GROUP_TABLE);
	    }
	  
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Figure out what do to here
		
	}

}
