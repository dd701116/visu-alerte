package connector;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import reseau.Listener;
import reseau.ListenerControler;

public abstract class Connector {
	
	private int id;
	private Listener listener;
	private PrintWriter output;
	private String type;
	
	public Connector(int id){
		this.setId(id);
	}
	
	public Connector(int id, ListenerControler controler, Socket socket) throws IOException{
		this.setId(id);
		this.listener = new Listener(controler,socket);
		this.output = new PrintWriter(socket.getOutputStream());
	}
	
	public void say(String s){
		this.output.println(s);
		this.output.flush();
	}
	
	public void start(){
		this.listener.open();
	}
	
	public void stop() throws IOException{
		this.listener.close();
	}
	
	public boolean isOn(){
		return !this.listener.isExit();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	
}
