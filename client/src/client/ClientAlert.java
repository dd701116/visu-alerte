package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class ClientAlert implements WindowListener{

	private JFrame frmClientAlert;
	private JTextPane txtpnLog;
	

	private JLabel lblMessage;
	private boolean alert;
	private boolean bornNow;


	

	/**
	 * Create the application.
	 */
	public ClientAlert(String message) {
		initialize(message);
		alert = true;
		bornNow = true;
	}

	public boolean isBornNow() {
		return bornNow;
	}
	
	public void setBornNow(boolean bornNow) {
		this.bornNow = bornNow;
	}

	public boolean isAlert() {
		return alert;
	}

	public JTextPane getTxtpnLog() {
		return txtpnLog;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String message) {
		frmClientAlert = new JFrame();
		frmClientAlert.setTitle("Client - alert box");
		frmClientAlert.setResizable(false);
		frmClientAlert.getContentPane().setBackground(new Color(139, 0, 0));
		frmClientAlert.getContentPane().setLayout(null);
		frmClientAlert.setVisible(true);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 615, 98);
		frmClientAlert.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("ALERT BOX");
		lblTitle.setForeground(new Color(128, 0, 0));
		lblTitle.setFont(new Font("Arial", Font.ITALIC, 35));
		lblTitle.setBounds(206, 25, 201, 47);
		panel.add(lblTitle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(12, 111, 591, 377);
		frmClientAlert.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMessage = new JLabel(message);
		lblMessage.setFont(new Font("Arial", Font.PLAIN, 24));
		lblMessage.setForeground(new Color(255, 255, 255));
		lblMessage.setBounds(12, 32, 567, 29);
		panel_1.add(lblMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(12, 85, 567, 279);
		panel_1.add(scrollPane);
		
		txtpnLog = new JTextPane();
		scrollPane.setViewportView(txtpnLog);
		txtpnLog.setFont(new Font("Arial", Font.PLAIN, 13));
		txtpnLog.setForeground(new Color(255, 255, 255));
		txtpnLog.setBackground(new Color(0, 0, 0));
		txtpnLog.setEditable(false);
		frmClientAlert.setBounds(100, 100, 621, 536);
		frmClientAlert.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		alert = false;
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
