package me.dewey.todo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemDatasource {
	
	//Database Fields
	private SQLiteDatabase database;
	private DBHelper dbhelper;
	private String[] allColumns = { DBHelper.KEY_ID, DBHelper.COLUMN_ITEM_NAME, DBHelper.COLUMN_DESCRIPTION_NAME, DBHelper.COLUMN_LABEL_NAME, DBHelper.COLUMN_LABEL_COLOR_NAME };
	
	public ItemDatasource(Context c)
	{
		dbhelper = new DBHelper(c);
	}
	
	public void open() throws SQLException
	{
		database = dbhelper.getWritableDatabase();
	}
	public void close()
	{
		dbhelper.close();
	}
	
	public ToDoItem createItem(String newName, String newDescription, String newLabel, String newLabelColor)
	{
		ContentValues values = new ContentValues();
		values.put(DBHelper.COLUMN_ITEM_NAME, newName);
		values.put(DBHelper.COLUMN_DESCRIPTION_NAME, newDescription);
		values.put(DBHelper.COLUMN_LABEL_NAME, newLabel);
		values.put(DBHelper.COLUMN_LABEL_COLOR_NAME, newLabelColor);
		long insertId = database.insert(DBHelper.GROUP_TABLE_NAME, null, values);
		Cursor cursor = database.query(DBHelper.GROUP_TABLE_NAME, allColumns, DBHelper.KEY_ID + "=" + insertId, null, null, null,null);
		cursor.moveToFirst();
		ToDoItem newItem = cursorToItem(cursor);
		cursor.close();
		return newItem;
	}
	
	public void deleteItem(ToDoItem item)
	{
		//long id = item.get_id();
		String name = item.get_name();
		database.delete(DBHelper.GROUP_TABLE_NAME, DBHelper.COLUMN_ITEM_NAME +"='"+ name+"'", null);	
	}
	
	public List<ToDoItem> getAllItems()
	{
		List<ToDoItem> items = new ArrayList<ToDoItem>();
		Cursor cursor = database.query(DBHelper.GROUP_TABLE_NAME, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast())
		{
			ToDoItem tdi = cursorToItem(cursor);
			items.add(tdi);
			cursor.moveToNext();
		}
		cursor.close();
		return items;

	}

	private ToDoItem cursorToItem(Cursor cursor) {
		ToDoItem item = new ToDoItem();
		item.set_id(cursor.getLong(0));
		item.set_name(cursor.getString(1));
		item.set_description(cursor.getString(2));
		item.set_label(cursor.getString(3));
		return item;
	}
}

