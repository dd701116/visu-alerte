package client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import reseau.ClientListenerController;
import reseau.Listener;

public class ClientServerConnection extends Thread implements ActionListener{

	private JFrame frame;
	private JTextField textIP;
	private JTextField textPORT;
	private JPanel panelStatut;
	private JButton btnNewButton;
	private JTextPane textPane;
	private JScrollBar scrollbar ;
	private boolean statut;
	
	private ClientIHM client;
	private int clientID;
	
	private Listener listener;
	private ClientListenerController controller;
	private Socket socket;
	private PrintWriter output;
	
	
	/**
	 * Create the application.
	 */
	public ClientServerConnection(ClientIHM client) {
		this.client = client;
		initialize();
		

		btnNewButton.addActionListener(this);
	}
	
	public void run(){
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 434, 53);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVER CONNECTION");
		lblNewLabel.setBounds(115, 11, 221, 32);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		
		panelStatut = new JPanel();
		panelStatut.setBackground(Color.RED);
		panelStatut.setBounds(10, 11, 50, 32);
		panel.add(panelStatut);
		
		textIP = new JTextField();
		textIP.setText("127.0.0.1");
		textIP.setBounds(10, 64, 315, 29);
		frame.getContentPane().add(textIP);
		textIP.setColumns(10);
		
		textPORT = new JTextField();
		textPORT.setText("2121");
		textPORT.setBounds(335, 64, 89, 29);
		frame.getContentPane().add(textPORT);
		textPORT.setColumns(10);
		
		btnNewButton = new JButton("Connection");
		btnNewButton.setBounds(10, 104, 414, 23);
		frame.getContentPane().add(btnNewButton);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 138, 414, 162);
		frame.getContentPane().add(scrollPane);
		
		scrollbar = scrollPane.getVerticalScrollBar();
		scrollbar.setValue( scrollbar.getMaximum() );
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(0, 0, 414, 162);
		scrollPane.setViewportView(textPane);
		statut = false;
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNewButton)){

			String ip = textIP.getText().trim();
			int port = new Integer(textPORT.getText());
			if (!ip.equals("") && port!=0) {
				try {
					if(statut){
						close();
					}else{
						open(ip,port);
						
					}
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			
			
		}
		
	}
	
	public void open(String ip, int port) throws UnknownHostException, IOException{
		controller = new ClientListenerController(client, textPane,scrollbar);
		socket = new Socket(ip, port);
		this.listener = new Listener(controller, socket);
		this.output = new PrintWriter(socket.getOutputStream());
		this.listener.open();
		statut = true;
		panelStatut.setBackground(Color.GRAY);
		btnNewButton.setText("Disconnect");
		this.say("ihm");
	}
	
	public void close() throws IOException{

		this.listener.close();
		statut = false;
		panelStatut.setBackground(Color.RED);
		btnNewButton.setText("Connection");
		client.getRoomsModel().removeAllElements();
		client.getTabbedPane().removeAll();
	}
	public void say(String s){
		this.output.println(s);
		this.output.flush();
	}

	public int getClientID() {
		return clientID;
	}

	public JPanel getPanelStatut() {
		return panelStatut;
	}

	public Listener getListener() {
		return listener;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public boolean isStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}
}
