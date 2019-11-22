package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import connector.Ihm;
import connector.Sensor;
import console.ConsoleIHM;
import console.ConsoleControler;

public class Server extends Thread{

	private ServerSocket socket;
	private Expulsor expulsor;
	
	private SlotManager<WaitingRoom> waiting_room;
	private SlotManager<Sensor> sensors;
	private SlotManager<Ihm> ihms;
	
	private ConsoleControler consoleControler;

	public static final int SERVER_SENSOR_CAPACITY = 10;
	public static final int SERVER_IHM_CAPACITY = 10;
	public static final int SERVER_WAITING_CAPACITY = 10;
	public static final int SERVER_CONSOLE_CAPACITY = 5;
	
	private int port;
	private boolean exit = true;
	
	
	public Server(int port, ConsoleControler console) throws IOException{
		this.port = port;
		socket = new ServerSocket(port);
		
		sensors = new SlotManager<Sensor>(SERVER_SENSOR_CAPACITY);
		waiting_room = new SlotManager<WaitingRoom>(SERVER_WAITING_CAPACITY);
		ihms=new SlotManager<Ihm>(SERVER_IHM_CAPACITY);
		consoleControler=console;
		
		expulsor = new Expulsor(waiting_room,sensors,ihms,consoleControler);
	}
	
	public void run(){
		
		exit = false;
		
		try {
			
			expulsor.open();
			
			
			System.out.println("> Server waiting connection on [:"+this.port+"] !");

			

			consoleControler.consoleLog("Server waiting connection on [:"+this.port+"] ");

			Socket client;
			while ((client = socket.accept())!=null && !exit){		
				
				System.out.println("[~] "+client.getInetAddress()+" connecting...");
				

				consoleControler.consoleLog("[~] "+client.getInetAddress()+" connecting...");
				
				if (waiting_room.size()<SERVER_WAITING_CAPACITY) {
					
					WaitingRoom wr = new WaitingRoom(waiting_room.getFirstEmpltySlot(),consoleControler,sensors, ihms, client);
					waiting_room.add(wr);

					consoleControler.addWaitingRoom("WR-"+wr.getIdx());
					
					wr.start();					

					System.out.println("[..] "+client.getInetAddress()+" waiting  on WR-"+(wr.getIdx())+" !");
					

					consoleControler.consoleLog("[..] "+client.getInetAddress()+" waiting  on WR-"+(wr.getIdx())+"");
				}
				

				System.out.println("> Server waiting connection on [:"+this.port+"] !");
				

				consoleControler.consoleLog("Server waiting connection on [:"+this.port+"] ");
				
			}
			
			expulsor.close();
			
			System.out.println("> Server is closed !");
			
			consoleControler.consoleLog("Server is closed !");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	public void close() throws Exception {
		exit=true;
		socket.close();
		
		for (int i = 0; i < waiting_room.SIZE_MAX; i++) {
			if (waiting_room.get(i)!=null) {
				waiting_room.get(i).close();
			}
		}
		for (int i = 0; i < sensors.SIZE_MAX; i++) {
			if (sensors.get(i)!=null) {
				sensors.get(i).stop();
			}
		}
		for (int i = 0; i < ihms.SIZE_MAX; i++) {
			if (ihms.get(i)!=null) {
				ihms.get(i).stop();
			}
		}
		
	}

}
