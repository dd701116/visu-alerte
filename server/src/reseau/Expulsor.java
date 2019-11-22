package reseau;

import connector.Ihm;
import connector.Sensor;
import console.ConsoleControler;

public class Expulsor extends Thread{
	
	private boolean activate = false;
	private SlotManager<WaitingRoom> waiting_room;
	private SlotManager<Sensor> sensors;
	private SlotManager<Ihm> ihms;
	private ConsoleControler consoleControler;
	
	public Expulsor(SlotManager<WaitingRoom> wr, SlotManager<Sensor> sensors, SlotManager<Ihm> ihms, ConsoleControler consoleControler){
		activate = true;
		this.waiting_room = wr;
		this.sensors = sensors;
		this.ihms = ihms;
		this.consoleControler = consoleControler;
	}
	
	public void run(){
		
		while(activate){
			for (int i=0; i<waiting_room.SIZE_MAX; i++ ) {
				if (waiting_room.get(i)!=null) {
					if (waiting_room.get(i).isEmpty()) {
						
						consoleControler.removeWaitingRoom("WR-"+waiting_room.get(i).getIdx());
						
						waiting_room.get(i).close();
						waiting_room.remove(i);
						System.out.println("[x] WR-"+i+" are closed !");
						

						consoleControler.consoleLog("[x] WR-"+i+" is closed !");
						
					}					
				}
				
			}
			
			for (int i = 0; i < sensors.SIZE_MAX; i++) {
				if (sensors.get(i)!=null) {
					if (!sensors.get(i).isOn()) {
						

						consoleControler.removeSensor("CS-"+sensors.get(i).getId());
						
						sensors.remove(i);
						System.out.println("[x] CS-"+i+" is closed !");
						
						consoleControler.consoleLog("[x] CS-"+i+" is closed !");
					}
				}
			}
			
			for (int i = 0; i < ihms.SIZE_MAX; i++) {
				if (ihms.get(i)!=null) {
					if (!ihms.get(i).isOn()) {
						

						consoleControler.removeIhm("CI-"+ihms.get(i).getId());
												
						ihms.remove(i);
						System.out.println("[x] CI-"+i+" is closed !");
						
						consoleControler.consoleLog("[x] CI-"+i+" is closed !");
					}
				}
			}
			
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void open(){
		this.start();
	}
	
	public void close(){
		this.activate = false;
	}
}
