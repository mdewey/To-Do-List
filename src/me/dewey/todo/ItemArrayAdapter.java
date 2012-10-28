package me.dewey.todo;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
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
		
		//make row
		View rowView = inflater.inflate(R.layout.to_do_item_list_layout,parent,false);
		
		//set title
		TextView itemName = (TextView)rowView.findViewById(R.id.toDoItemName);
		itemName.setText(myList.get(position).get_name());
		
		//set descriptions
		TextView itemDescription = (TextView)rowView.findViewById(R.id.toDoItemDesciption);
		
		if (myList.get(position).get_description().length() > 0 )
		{
			String desc;
			if (myList.get(position).get_description().length() > 30 )
			{
				desc = myList.get(position).get_description().substring(0,30) + "...";
			}
			else
			{
				desc = myList.get(position).get_description();
			}
			itemDescription.setText(desc);
			itemDescription.setVisibility(View.VISIBLE);
		}
		else
		{
			itemDescription.setVisibility(View.GONE);
			itemName.setPadding(0, 30, 0, 30);
		}
		
		//set label
		TextView itemLabel = (TextView)rowView.findViewById(R.id.toDoItemLabel);
		if (myList.get(position).get_label() != null)
		{
			if (myList.get(position).get_label().length() > 0) 
			{
				itemLabel.setText(myList.get(position).get_label());
				
			}
			else
			{
				hideLabel(true, itemLabel);
			}
			
		}
		else
		{
			hideLabel(true, itemLabel);
		}
				
		return rowView;
		
	}
	
	private void hideLabel(boolean hide, TextView tv)
	{
		if (hide)
		{
			tv.setVisibility(View.GONE);
		}
		else
		{
			tv.setVisibility(View.VISIBLE);
		}
	}

}
