package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientIHMController implements ActionListener{

	private ClientIHM client;
	private ClientServerConnection clientServerConnection;
	
	
	public ClientIHMController(ClientIHM client) {
		this.client = client;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==client.getMntmConnect()) {
			
			if (clientServerConnection==null) {
				clientServerConnection = new ClientServerConnection(client);
				clientServerConnection.start();
				clientServerConnection.setVisible(true);
			}else{
				clientServerConnection.setVisible(true);
			}
			
		}else if (e.getSource()==client.getMntmDisconnect()) {
			try {
				if (clientServerConnection!=null) {
					if (!clientServerConnection.getListener().isExit()) {
						clientServerConnection.close();
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public ClientServerConnection getClientServerConnection() {
		return clientServerConnection;
	}

}
