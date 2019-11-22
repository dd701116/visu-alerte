package reseau;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;

import client.ClientAlert;
import client.ClientIHM;
import item.Room;
import item.Sensor;
import item.ihm.ItemRoom;

public class ClientListenerController implements ListenerControler{

	private ClientIHM client;
	private JTextPane console;
	private JTextPane consoleAlert;
	private JScrollBar scrollbar;
	
	private Hashtable<String, Sensor> sensors;
	private Hashtable<String, Room> rooms;
	private Stack<String> buffConsole;
	
	private ArrayList<ClientAlert> clientAlert;
	
	private Boolean alert;
	
	
	public ClientListenerController(ClientIHM client, JTextPane console, JScrollBar scrollbar) {
		this.client = client;
		this.console = console;
		this.consoleAlert = new JTextPane();
		this.scrollbar = scrollbar;
		this.alert = false;
		this.clientAlert = new ArrayList<>();
		this.buffConsole = new Stack<>();
		
		sensors = new Hashtable<>();
		rooms = new Hashtable<>();
		
		client.getRoomsModel().removeAllElements();
		
	}
	
	@Override
	public void hears(String s) {
		
		String ok_message = "ok-connected";
		
		String[] trame = s.split("-");
		
		if (!trame[0].equals("ALERT")) {
			String sensorID = trame[0];
			String room = trame[1];
			String date = trame[2];
			String time = trame[3];
			String temperature = trame[4];
			
			

			ItemRoom r = new ItemRoom(room);
			// On regarde si la room existe
			if (rooms.containsKey(room)) {
			
				if (sensors.containsKey(sensorID)) {
					

					sensors.get(sensorID).getTime().add(time);
					sensors.get(sensorID).getData().add(temperature);
					rooms.get(room).getDataView().setText(rooms.get(room).getDataView().getText()+"\n"+s);
				}else{
					Sensor sensor = new Sensor(sensorID);
					rooms.get(room).getSensors().addElement(sensor);
					sensors.put(sensorID, sensor);
				}
			//	Si elle n'existe pas, on la cree
			}else{
				rooms.put(room, r.getRoom());
				client.getRoomsModel().addElement(r);
			}
		}else{
			this.alert = true;
			
			String typeMsg = trame[0];
			String date = trame[1];
			String time = trame[2];
			String message = trame[3];
			
			ClientAlert alertBox = new ClientAlert(message);
			clientAlert.add(alertBox);
			
			
			
			
			
			
		}
		
		
		if (console.getText().length()>500) {
			console.setText("");
			buffConsole.add(console.getText());
		}
		
		
		if (alert) {
			//consoleAlert.setText(consoleAlert.getText()+"\n ["+client.getId()+"]> "+s);
			
			alert = false;
			for(ClientAlert clientA : clientAlert){
				if (clientA.isBornNow()) {
					if (console.getText().length()==0) {
						s=buffConsole.lastElement()+"\n > "+s;
					}else if(console.getText().length()<300 && buffConsole.size()>1){
						s=buffConsole.lastElement()+console.getText()+"\n > "+s;
					}else{
						s=console.getText()+"\n > "+s;
					}
					clientA.setBornNow(false);
				}
				if(clientA.isAlert()){
					alert = true;
					clientA.getTxtpnLog().setText(clientA.getTxtpnLog().getText()+"\n > "+s);
				}
			}
		}
		
		console.setText(console.getText()+"\n ["+client.getId()+"]> "+s);
		scrollbar.setValue( scrollbar.getMaximum());
		
		
		
	}

	@Override
	public void alert(String s) {
		// TODO Auto-generated method stub
		
	}
	
	public void setID(int i){
		client.setId(i);
	}
	public ClientIHM getClient() {
		return client;
	}

}
