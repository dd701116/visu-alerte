package item;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Hashtable;

public class Sensor extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3724007047861851429L;
	
	private String name;
	
	private ArrayList<String> time;
	private ArrayList<String> data;

	public Sensor(String n) {
		name = n;
		time = new ArrayList<>();
		data = new ArrayList<>();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<String> getTime() {
		return time;
	}


	public void setTime(ArrayList<String> time) {
		this.time = time;
	}


	public ArrayList<String> getData() {
		return data;
	}


	public void setData(ArrayList<String> data) {
		this.data = data;
	}
	


}
