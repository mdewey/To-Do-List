package me.dewey.todo;

public class ToDoItem {
	private Long _id;
	private String _name;	
	private String _description;
	private String _label;
	private String _labelColor;
	
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
	public String get_label() {
		return _label;
	}
	public void set_label(String _label) {
		this._label = _label;
	}
	public String get_labelColor() {
		return _labelColor;
	}
	public void set_labelColor(String _labelColor) {
		this._labelColor = _labelColor;
	}
}
