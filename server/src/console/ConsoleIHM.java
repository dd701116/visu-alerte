package console;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import reseau.Server;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.List;
import java.io.IOException;

import javax.swing.JScrollPane;

public class ConsoleIHM {

	private ConsoleControler consoleControler;
	private JFrame frmConsole;
	private JTextField textFieldConsole;
	private Server server;
	
	private JTabbedPane tabbedPane;
	private JPanel panelConsole;
	private JTextPane textPaneConsole;
	private JPanel panelSlots;
	private JPanel panelWaitingRoom;
	private JLabel lblWaitingroom;
	private List listWaitingRooms;
	private JLabel label;
	private JPanel panel;
	private JLabel lblSensors;
	private List listSensors;
	private JLabel label_1;
	private JPanel panel_1;
	private JLabel lblUsers;
	private List listIhms;
	private JLabel label_2;
	private JPanel panelLog;
	private JTextPane textPaneLog;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	public Object lock = new Object();


	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ConsoleIHM() throws IOException {
		initialize();
		
		consoleControler = new ConsoleControler(textFieldConsole,textPaneConsole,textPaneLog,listWaitingRooms,listSensors,listIhms);
		server = new Server(2121,consoleControler);
		consoleControler.setServer(server);
		textFieldConsole.addKeyListener(consoleControler);
		consoleControler.consoleShow("Hello world !");

		
		
		
	}
	
	public ConsoleControler getControler(){
		return consoleControler;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConsole = new JFrame();
		frmConsole.setTitle("Console Visu-Alerte");
		frmConsole.setResizable(false);
		frmConsole.setBounds(100, 100, 866, 514);
		frmConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsole.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frmConsole.setVisible(true);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/console.png"));
		frmConsole.setIconImage(icon.getImage());
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmConsole.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelConsole = new JPanel();
		panelConsole.setToolTipText("");
		tabbedPane.addTab("Console", null, panelConsole, null);
		panelConsole.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 855, 407);
		panelConsole.add(scrollPane);
		

		
		textPaneConsole = new JTextPane();
		scrollPane.setViewportView(textPaneConsole);
		textPaneConsole.setForeground(Color.WHITE);
		textPaneConsole.setFont(new Font("Courier New", Font.PLAIN, 15));
		textPaneConsole.setBackground(Color.BLACK);
		textPaneConsole.setEditable(false);
		
		panelSlots = new JPanel();
		tabbedPane.addTab("Slots", null, panelSlots, null);
		panelSlots.setLayout(null);
		
		panelWaitingRoom = new JPanel();
		panelWaitingRoom.setBounds(12, 13, 244, 411);
		panelSlots.add(panelWaitingRoom);
		panelWaitingRoom.setLayout(null);
		
		lblWaitingroom = new JLabel("WaitingRooms");
		lblWaitingroom.setBounds(10, 13, 140, 24);
		panelWaitingRoom.add(lblWaitingroom);
		lblWaitingroom.setFont(new Font("Arial", Font.PLAIN, 20));
		
		listWaitingRooms = new List();
		listWaitingRooms.setBounds(10, 43, 224, 358);
		panelWaitingRoom.add(listWaitingRooms);
		
		label = new JLabel("(0/10)");
		label.setFont(new Font("Arial", Font.PLAIN, 17));
		label.setBounds(176, 18, 56, 16);
		panelWaitingRoom.add(label);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(268, 13, 279, 411);
		panelSlots.add(panel);
		
		lblSensors = new JLabel("Sensors");
		lblSensors.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSensors.setBounds(10, 13, 105, 24);
		panel.add(lblSensors);
		
		listSensors = new List();
		listSensors.setBounds(10, 43, 259, 358);
		panel.add(listSensors);
		
		label_1 = new JLabel("(0/10)");
		label_1.setFont(new Font("Arial", Font.PLAIN, 17));
		label_1.setBounds(213, 19, 56, 16);
		panel.add(label_1);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(559, 13, 272, 411);
		panelSlots.add(panel_1);
		
		lblUsers = new JLabel("IHMs");
		lblUsers.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUsers.setBounds(12, 13, 83, 24);
		panel_1.add(lblUsers);
		
		listIhms = new List();
		listIhms.setBounds(10, 43, 252, 358);
		panel_1.add(listIhms);
		
		label_2 = new JLabel("(0/10)");
		label_2.setFont(new Font("Arial", Font.PLAIN, 17));
		label_2.setBounds(206, 19, 56, 16);
		panel_1.add(label_2);
		
		panelLog = new JPanel();
		tabbedPane.addTab("Log", null, panelLog, null);
		panelLog.setLayout(null);
		
		textFieldConsole = new JTextField();
		textFieldConsole.setFont(new Font("Arial", Font.PLAIN, 17));
		textFieldConsole.setBounds(0, 405, 855, 44);
		panelConsole.add(textFieldConsole);
		textFieldConsole.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 855, 449);
		panelLog.add(scrollPane_1);
		
		textPaneLog = new JTextPane();
		scrollPane_1.setViewportView(textPaneLog);
		textPaneLog.setFont(new Font("Arial", Font.PLAIN, 15));
		textPaneLog.setEditable(false);


		
	}


}
