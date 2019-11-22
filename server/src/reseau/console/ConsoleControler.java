package reseau.console;


import reseau.ListenerControler;
import reseau.Server;

public class ConsoleControler implements ListenerControler{
	
	
	public ConsoleControler() {
	}
	
	public void setServer(Server s){
		
	}

	public void consoleShow(String s){
		
	}
	
	public void consoleShowln(String s){
		
	}
	
	public void consoleLog(String s){
		
	}
	
	public void addWaitingRoom(String id){
		
	}
	
	public void addSensor(String id){
		
	}
	
	public void addIhm(String id){
		
	}
	
	public void removeWaitingRoom(String id){
		
	}
	
	public void removeSensor(String id){
		
	}
	
	public void removeIhm(String id){
		
	}
	
	
	public void resetInputConsole(){
		
	}
	public String getCommande(){
		
		return "";
	}
	
	public String executor(String cmd) {
		
		String res=cmd+" is not a command !";
		
		switch (cmd) {
		/*case "server stop":
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
			break;*/

		default:
			break;
		}
		return res;
	}

	@Override
	public void hears(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alert(String s) {
		// TODO Auto-generated method stub
		
	}
	
}
