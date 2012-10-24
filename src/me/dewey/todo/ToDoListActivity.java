package me.dewey.todo;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ToDoListActivity extends ListActivity {
	
	//TODO: Add database support for data living past one life
	private ItemDatasource datasource;
	private List<ToDoItem> listItems;
    private ItemArrayAdapter adapter;
    private ListView myList;
    private Vibrator vibe;
    final Context context = this;
	    
	
	/** Android Events **/
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Open DB connection 
        datasource = new ItemDatasource(this);
        datasource.open();
        
        //gets list of items and inits the listitem array
        listItems = GetListOfItems();
        
        //inits vibrator for long button click
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE) ;
        
        //sets adapter for listview
        adapter = new ItemArrayAdapter(this, android.R.layout.simple_list_item_1,listItems);
        setListAdapter(adapter);
        
        //set onclick listners 
        myList = (ListView)findViewById(android.R.id.list);
        myList.setOnItemClickListener(new OnItemClickListener() {
	
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				ShowItemDetailDialog(view, listItems.get(position), position);
					
			}
		});
        myList.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View view,final int pos, long id) {
				vibe.vibrate(50);
				AlertDialog.Builder builder = new AlertDialog.Builder(ToDoListActivity.this);
				builder.setMessage("Are you sure you want to delete " +listItems.get(pos).get_name());
				builder.setCancelable(false);
				builder.setPositiveButton("Yes", new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						RemoveItemFromList(pos);

					}
				});
				builder.setNegativeButton("No", new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
							//Nothing happends here
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
				return true;
			}
		});
        
    }
    /** helper Functions **/
     
    

    private List<ToDoItem> GetListOfItems()
    {
    	//TODO: Query Database and get the list from datasource
    	return datasource.getAllItems();
		
    }
    
    
    public void ShowItemDetailDialog(View v, ToDoItem item, final int pos)
    {
    	 LayoutInflater li = LayoutInflater.from(context);
         View display = li.inflate(R.layout.see_item_details,null);
         
         AlertDialog.Builder adb = new AlertDialog.Builder(context);
         
         adb.setView(display);
         final TextView itemTitleTextBox = (TextView)display.findViewById(R.id.viewItemTitle);
         final TextView itemDescriptionTextBox = (TextView)display.findViewById(R.id.viewItemDescription);
         
         itemTitleTextBox.setText(item.get_name());
         itemDescriptionTextBox.setText(item.get_description());
         
         adb.setCancelable(true)
            .setPositiveButton("Did!!!", new DialogInterface.OnClickListener() {
   			
   			public void onClick(DialogInterface dialog, int which) {
   			
   				RemoveItemFromList(pos);
   				dialog.cancel();
   				
   			}
   		})
   		.setNegativeButton("Not Done!", new DialogInterface.OnClickListener() {
   			
   			public void onClick(DialogInterface dialog, int which) {
   				// TODO Auto-generated method stub
   				dialog.cancel();
   			}
   		});
         
         AlertDialog alertDialog = adb.create();
         
         alertDialog.show();
    	
    }
    
    public void ShowAddItemDialog(View v)
    {
      LayoutInflater li = LayoutInflater.from(context);
      View display = li.inflate(R.layout.get_item_dialog_layout, null);
      
      AlertDialog.Builder adb = new AlertDialog.Builder(context);
      
      adb.setView(display);
      final EditText itemTitleEditBox = (EditText)display.findViewById(R.id.itemTitle);
      final EditText itemDescriptionEditBox = (EditText)display.findViewById(R.id.itemDescription);
      final EditText itemLabelEditBox = (EditText)display.findViewById(R.id.itemLabel);
      
      adb.setCancelable(true)
         .setPositiveButton("Add!", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
				String itemTitle= itemTitleEditBox.getText().toString();
				String itemDescription = itemDescriptionEditBox.getText().toString();
				String itemLabel = itemLabelEditBox.getText().toString();
				String itemLabelColor= "";

				if (itemTitle.length() > 0)
				{
					ToDoItem newItem = datasource.createItem(itemTitle, itemDescription, itemLabel,itemLabelColor);
		    		//add value to list
		    		adapter.add(newItem);
		        	adapter.notifyDataSetChanged();

				}
				else
				{
					Toast.makeText(context, "Nothing to add!", Toast.LENGTH_LONG).show();
				}
				
			}
		})
		.setNegativeButton("Cancel!", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
      
      AlertDialog alertDialog = adb.create();
      
      alertDialog.show();
 
    }
    
    public void RemoveItemFromList(int pos)
    {
    	//TODO: THIS IS SMOKE AND MIRRORS UNTIL I GET custom adapter going
    	//KNOWBUG: if i have x and x on the same list, they will both be deleted
    	ToDoItem t = listItems.get(pos);
    	datasource.deleteItem(t);
    	
    	adapter.remove(adapter.getItem(pos));
    	adapter.notifyDataSetChanged();
    }
  //prevents page from reloading (keeps keyboard hidden so it doesn't cause WebView activity to restart
    @Override
    public void onConfigurationChanged(Configuration newConfig){
    	super.onConfigurationChanged(newConfig);
    }//end onConfigurationChanged
    

}