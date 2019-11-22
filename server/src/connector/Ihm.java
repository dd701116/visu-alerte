package connector;

import java.io.IOException;
import java.net.Socket;

import reseau.ListenerControler;


public class Ihm extends Connector{


	public Ihm(int id, ListenerControler controler, Socket socket) throws IOException {
		super(id,controler,socket);
		this.setType("ihm");
	}

}
