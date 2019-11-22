package item;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JTextPane;

import item.ihm.ItemRoom;

public class Room{
	
	private String name;
	private DefaultListModel<Sensor> sensors;
	private boolean updated;
	private ArrayList<String> data;
	private JTextPane dataView;
	

	public Room(String n) {
		name = n;
		sensors = new DefaultListModel<>();
		updated = true;
		data = new ArrayList<>();
		dataView = new JTextPane();
	}
	
	public String getName() {
		return name;
	}
	
	public DefaultListModel<Sensor>  getSensors() {
		return sensors;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setDataView(JTextPane dataView) {
		this.dataView = dataView;
		
	}
	
	public JTextPane getDataView() {
		return dataView;		
	}

}
