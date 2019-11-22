package item.ihm;

import java.awt.Component;

import item.Room;

public class ItemRoom extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6066149487701171509L;
	private Room room;
	
	public ItemRoom(Room r) {
		room = r;
	}
	
	public ItemRoom(String n) {
		room = new Room(n);
	}
	
	public String getName(){
		return room.getName();
	}

	public Room getRoom() {
		return room;
	}

}

