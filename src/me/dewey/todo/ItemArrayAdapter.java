package me.dewey.todo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemArrayAdapter extends ArrayAdapter<ToDoItem>{
	private List<ToDoItem> myList;
	private Context context;
	
	public ItemArrayAdapter(Context context, int textViewResourceId,List<ToDoItem> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.myList = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.to_do_item_list_layout,parent,false);
		TextView itemName = (TextView)rowView.findViewById(R.id.toDoItemName);
		itemName.setText(myList.get(position).get_name());
		
		return rowView;
		
	}

}
