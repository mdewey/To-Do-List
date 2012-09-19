package me.dewey.todo;

public class ToDoItem {
	private Long _id;
	private String _name;	
	private String _description;
	
	public ToDoItem(String n)
	{
		_name = n;
	}
	public ToDoItem() {
		//Empty constuctor
	}
	
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	
	public String get_description() {
		return _description;
	}
	public void set_description(String _description) {
		this._description = _description;
	}
}
