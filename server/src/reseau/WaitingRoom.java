package reseau;

import java.io.IOException;
import java.net.Socket;
import java.util.EmptyStackException;

import connector.Ihm;
import connector.IhmControler;
import connector.Sensor;
import connector.SensorControler;
import console.ConsoleControler;

public class WaitingRoom extends Thread{

	private int id;
	
	private SlotManager<Sensor> sensors;
	private SlotManager<Ihm> ihms;
	
	private Listener listener;
	private boolean waiting = true;
	
	private final int MAX_TRY = 5;
	
	private ConsoleControler consoleControler;
	
	public WaitingRoom(int id, ConsoleControler consoleControler, SlotManager<Sensor> sensors, SlotManager<Ihm> ihms, Socket socket){
		this.id = id;
		this.sensors = sensors;
		this.ihms = ihms;
		this.consoleControler = consoleControler;
		
		listener = new Listener(new WaitingRoomControler(), socket);
	}
	
	public void run(){
		listener.open();
		int test = 0;
		while(waiting){
			try {
				String type;
				if((type = listener.hearSomething()) != null){
					System.out.println("> WR received : "+type);
					
					try {
						
						if(type.equals("sensor")){
							
							if (sensors.size()<Server.SERVER_SENSOR_CAPACITY) {
								
								Sensor s = new Sensor(listener.getSocket().getPort(), new SensorControler(ihms) ,listener.getSocket());

								System.out.println("[+] {"+listener.getSocket().getInetAddress()+"} new sensor is added !");
								

								consoleControler.consoleLog("[+] {"+listener.getSocket().getInetAddress()+"} new sensor is added");
								
								s.start();
								
								s.say("ok-connected");
								

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								s.say(Integer.toString(listener.getSocket().getPort()));
								sensors.add(s);
								

								consoleControler.addSensor("CS-"+s.getId());
								
							}else{
								Sensor s = new Sensor(listener.getSocket().getPort(), new SensorControler(ihms) ,listener.getSocket());
								s.say("To much Sensor ("+sensors.size()+"/"+Server.SERVER_SENSOR_CAPACITY+") !");
								

								consoleControler.consoleLog("To much Sensor ("+sensors.size()+"/"+Server.SERVER_SENSOR_CAPACITY+") !");
								
								listener.close();
							}
							
						}else if(type.equals("ihm")){
							
							// ATTENTTION LE CONTROLER
							
							if (ihms.size()<Server.SERVER_IHM_CAPACITY) {
								
								Ihm i = new Ihm(listener.getSocket().getPort(), new IhmControler() ,listener.getSocket());
								
								
								System.out.println("[+] {"+listener.getSocket().getInetAddress()+"} new ihm is added !");
								

								consoleControler.consoleLog("[+] {"+listener.getSocket().getInetAddress()+"} new ihm is added");
								
								i.start();
								
								i.say("ok-connected");
								
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								i.say(Integer.toString(listener.getSocket().getPort()));
								
								ihms.add(i);
								

								consoleControler.addIhm("CI-"+i.getId());
							}else{
								Ihm i = new Ihm(listener.getSocket().getPort(), new IhmControler() ,listener.getSocket());
								i.say("To much Ihm ("+ihms.size()+"/"+Server.SERVER_IHM_CAPACITY+") !");
								
								
								consoleControler.consoleLog("To much Ihm ("+ihms.size()+"/"+Server.SERVER_IHM_CAPACITY+") !");
								
								listener.close();
							}
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					waiting=false;
				}
				// else : wait
			} catch (EmptyStackException e) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				test++;
				if (test>=MAX_TRY) {
					waiting=false;
				}
			}
		}
	}
	
	public boolean isEmpty(){
		return !waiting;
	}
	
	public Listener getListener(){
		return listener;
	}
	
	public void open(){
		this.start();
	}
	public void close(){
		this.waiting = false;
		this.listener.exit();
	}

	public int getIdx() {
		return id;
	}

	public void setIdx(int id) {
		this.id = id;
	}
}
