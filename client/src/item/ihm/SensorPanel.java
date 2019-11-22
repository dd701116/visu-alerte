package item.ihm;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import item.Sensor;

public class SensorPanel extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -191549826940896271L;
	

	private JList<Sensor> list_1;
	private DefaultListModel<Sensor> sensors;
	
	public SensorPanel(DefaultListModel<Sensor>  sensors){
		
		this.sensors = sensors;
		
		list_1 = new JList<Sensor>(sensors);
		list_1.setCellRenderer(new SensorRenderer());
		this.setViewportView(list_1);
	}
}
