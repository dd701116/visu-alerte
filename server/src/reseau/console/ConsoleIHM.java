package reseau.console;

import reseau.console.ConsoleControler;

import java.io.IOException;
import java.net.Socket;
import connector.Connector;

public class ConsoleIHM extends Connector{

	private ConsoleControler consoleControler;
	
	public ConsoleIHM(int id, ConsoleControler controler, Socket socket) throws IOException {
		
		super(id,controler,socket);
		this.setType("console");
		
	}
	
	public ConsoleControler getControler(){
		return consoleControler;
	}
}
