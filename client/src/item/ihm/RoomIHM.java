package item.ihm;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import item.Room;

public class RoomIHM extends JTabbedPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2510475851011171321L;
	
	private ProfilePanel panelProfile;
	private SensorPanel panelSensor;
	private DataPanel panelData;
	private StatPanel panelStat;
	private RulePanel panelRules;
	
	
	
	public RoomIHM(int tabPlacement){
		super(tabPlacement);
		
		
		
		
	}
	
	
	public void draw(Room room){
		
		panelProfile = new ProfilePanel(room);
		this.addTab("Profile", null, panelProfile, null);
		panelProfile.setLayout(null);
		
		panelSensor = new SensorPanel(room.getSensors());
		this.addTab("Sensor", null, panelSensor, null);
		
		
		
		panelData = new DataPanel(room.getDataView());
		this.addTab("Data", null, panelData, null);
		
		
		panelStat = new StatPanel();
		this.addTab("Stat", null, panelStat, null);
		panelStat.setLayout(null);
		panelStat.draw(room);
		
		panelRules = new RulePanel();
		this.addTab("Rules", null, panelRules, null);
		panelRules.setLayout(null);
	}


	public ProfilePanel getPanelProfile() {
		return panelProfile;
	}


	public SensorPanel getPanelSensor() {
		return panelSensor;
	}


	public DataPanel getPanelData() {
		return panelData;
	}


	public StatPanel getPanelStat() {
		return panelStat;
	}


	public RulePanel getPanelRules() {
		return panelRules;
	}

}
