package connector;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import reseau.ListenerControler;
import reseau.SlotManager;

public class SensorControler implements ListenerControler{
	
	private SlotManager<Ihm> ihms;
	private ArrayList<String> sensorID1;
	private ArrayList<String> sensorID2;
	public SensorControler(SlotManager<Ihm> ihms){
		this.ihms = ihms;
		sensorID1 = new ArrayList<>();
		sensorID2 = new ArrayList<>();
	}
	
	public void hears(String s){
		//System.out.println("> Server send : "+s);
		
		/*for (Ihm ihm : ihms) {
			ihm.say(s);
		}*/
		
		for (int i = 0; i < ihms.SIZE_MAX; i++) {
			if (ihms.get(i)!=null) {
				ihms.get(i).say(s);
			}
		}
		
		String[] trame = s.split("-");
		
		if (!trame[0].equals("ALERT")) {
			String sensorID = trame[0];
			String room = trame[1];
			String date = trame[2];
			String time = trame[3];
			String temperature = trame[4];
			Double temperature_int = new Double(temperature);
			System.out.println(temperature_int);
			if (temperature_int>25 && !this.sensorID1.contains(sensorID)) {
				this.alert(sensorID+" DETECT TEMPERATURE ABOVE "+temperature_int+" deg");
				this.sensorID1.add(sensorID);

			}else if (temperature_int>30 && !this.sensorID2.contains(sensorID)) {
				this.alert(sensorID+" DETECT TEMPERATURE ABOVE "+temperature_int+" deg");
				this.sensorID2.add(sensorID);
			}
		}
	}

	@Override
	public void alert(String s) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();  
		
		String message = "ALERT-"+dtf.format(now)+"-"+s.toUpperCase();
		hears(message);
		
		SendMail mail = new SendMail();
		mail.send(message,message);
		
	}
}
