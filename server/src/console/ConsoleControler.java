package console;

import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import connector.Connector;
import connector.Sensor;
import reseau.Server;
import reseau.SlotManager;
import reseau.WaitingRoom;

public class ConsoleControler implements KeyListener{
	
	private Server server;
	private JTextField textFieldConsole;
	private JTextPane textPaneConsole;
	private JTextPane textPaneLog;
	private List listWaitingRooms;
	private List listSensors;
	private List listIhms;
	
	
	public ConsoleControler(JTextField textFieldConsole, JTextPane textPaneConsole, JTextPane textPaneLog,
			List listWaitingRooms2, List listSensors2, List listIhms2) throws IOException {
		this.textFieldConsole = textFieldConsole;
		this.textPaneConsole = textPaneConsole;
		this.textPaneLog = textPaneLog;
		this.listWaitingRooms = listWaitingRooms2;
		this.listSensors = listSensors2;
		this.listIhms = listIhms2;
		
	}
	
	public void setServer(Server s){
		server =s;
	}

	public void consoleShow(String s){
		textPaneConsole.setText(textPaneConsole.getText()+s);
	}
	
	public void consoleShowln(String s){
		textPaneConsole.setText(textPaneConsole.getText()+"\n"+s);
	}
	
	public void consoleLog(String s){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
		
		textPaneLog.setText(textPaneLog.getText()+"\n["+dtf.format(now)+"] "+s);
	}
	
	public void addWaitingRoom(String id){
		listWaitingRooms.add(id);
	}
	
	public void addSensor(String id){
		listSensors.add(id);
	}
	
	public void addIhm(String id){
		listIhms.add(id);
	}
	
	public void removeWaitingRoom(String id){
		listWaitingRooms.remove(id);
	}
	
	public void removeSensor(String id){
		listSensors.remove(id);
	}
	
	public void removeIhm(String id){
		listIhms.remove(id);
	}
	
	
	public void resetInputConsole(){
		textFieldConsole.setText("");
	}
	public String getCommande(){
		return textFieldConsole.getText();
	}
	
	public String executor(String cmd) {
		
		String res=cmd+" is not a command !";
		
		switch (cmd) {
		case "server stop":
			if (server.isAlive()) {
				try {
					server.close();
				} catch (Exception e){
					consoleShowln(e.getMessage());
					consoleLog(e.getMessage());
				}
				res = "The server is closed !";
			}else{
				res = "No server on !";
			}
			break;
		case "server start":
			if (!server.isAlive()) {
				server.start();
				res = "The server is open !";
			}else{
				res = "The server is already on !";
			}
			break;

		default:
			break;
		}
		return res;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
        {
			String cmd = getCommande();
			
			if(!cmd.trim().equals("")){
				resetInputConsole();
				consoleShowln("> "+cmd);
				consoleLog(cmd);
				consoleShowln(executor(cmd));
				
				
			}else{
				resetInputConsole();
				consoleShowln("> "+cmd);
			}
			
			
        }
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
