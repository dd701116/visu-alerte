package connector;


import java.io.IOException;
import java.net.Socket;

import reseau.ListenerControler;


public class Sensor extends Connector{
	
	public Sensor(int id, ListenerControler controler, Socket socket) throws IOException {
		super(id, controler, socket);
		this.setType("sensor");
	}

}
