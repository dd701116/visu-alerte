package client;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JTextPane;

import item.Room;
import item.ihm.ItemRoom;
import item.ihm.ItemRoomRenderer;
import item.ihm.RoomIHM;
import reseau.ClientListenerController;
import reseau.Listener;

public class ClientIHM implements WindowListener{

	private JFrame frmClient;
	
	private JMenuBar menuBar;
	
	private JMenu mnFile;
	private JMenuItem mntmOpenFile;
	private JMenuItem mntmCloseFile;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	
	private JMenu mnServer;
	private JMenuItem mntmConnect;
	private JMenuItem mntmDisconnect;
	private JMenuItem mntmSearch;
	
	private JMenu mnFilter;
	private JMenuItem mntmRooms;
	private JMenuItem mntmTemperatures;
	
	private JLabel lblVersion;
	private JPanel panelBody;
	private JScrollPane scrollPaneRoom;
	private JList<ItemRoom> roomList;
	private DefaultListModel<ItemRoom> roomsModel;
	
	private ClientIHMController controller;
	private RoomIHM tabbedPane;
	
	private int id;
	
	
	/**
	 * Create the application.
	 */
	
	public ClientIHM() {
		initialize();
		
		this.controller = new ClientIHMController(this);
		mntmConnect.addActionListener(controller);
		mntmDisconnect.addActionListener(controller);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.setTitle("Visu-Alerte");
		frmClient.setResizable(false);
		frmClient.setBounds(100, 100, 1059, 657);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.getContentPane().setLayout(null);
		frmClient.setVisible(true);
		frmClient.addWindowListener(this);
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/ihm.png"));
		frmClient.setIconImage(icon.getImage());
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1053, 21);
		frmClient.getContentPane().add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpenFile = new JMenuItem("Open File...");
		mnFile.add(mntmOpenFile);
		
		mntmCloseFile = new JMenuItem("Close File");
		mnFile.add(mntmCloseFile);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		mntmSaveAs = new JMenuItem("Save As...");
		mnFile.add(mntmSaveAs);
		
		mnServer = new JMenu("Server");
		menuBar.add(mnServer);
		
		mntmConnect = new JMenuItem("Connect...");
		mnServer.add(mntmConnect);
		
		mntmDisconnect = new JMenuItem("Disconnect");
		mnServer.add(mntmDisconnect);
		
		mntmSearch = new JMenuItem("Search...");
		mnServer.add(mntmSearch);
		
		mnFilter = new JMenu("Filter");
		menuBar.add(mnFilter);
		
		mntmRooms = new JMenuItem("Rooms");
		mnFilter.add(mntmRooms);
		
		mntmTemperatures = new JMenuItem("Temperatures");
		mnFilter.add(mntmTemperatures);
		
		lblVersion = new JLabel("( Visu-Alerte )> version 0.1");
		lblVersion.setBounds(890, 595, 150, 25);
		frmClient.getContentPane().add(lblVersion);
		
		panelBody = new JPanel();
		panelBody.setBounds(0, 32, 1053, 573);
		frmClient.getContentPane().add(panelBody);
		panelBody.setLayout(null);
		
		scrollPaneRoom = new JScrollPane();
		scrollPaneRoom.setBounds(10, 11, 272, 551);
		panelBody.add(scrollPaneRoom);
		
		roomsModel = new DefaultListModel<>();
		roomList = new JList(roomsModel);
		roomList.setCellRenderer(new ItemRoomRenderer(this));
		scrollPaneRoom.setViewportView(roomList);
		tabbedPane = new RoomIHM(JTabbedPane.TOP);
		tabbedPane.setBounds(292, 11, 751, 551);
		panelBody.add(tabbedPane);
		
		
	}

	public JMenuItem getMntmConnect() {
		return mntmConnect;
	}

	public ClientIHMController getController() {
		return controller;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JList<ItemRoom> getRoomList() {
		return roomList;
	}

	public JScrollPane getScrollPaneRoom() {
		return scrollPaneRoom;
	}

	public void setRoomList(JList<ItemRoom> roomList) {
		this.roomList = roomList;
	}

	public DefaultListModel<ItemRoom> getRoomsModel() {
		return roomsModel;
	}

	public RoomIHM getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(RoomIHM tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JMenuItem getMntmDisconnect() {
		return mntmDisconnect;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		try {
			
			if (controller.getClientServerConnection()!=null) {
				
				controller.getClientServerConnection().close();
				
				while(!controller.getClientServerConnection().getListener().isExit()){
					Thread.sleep(1000);
				}
			}	
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
