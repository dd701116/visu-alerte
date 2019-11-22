package item.ihm;

import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import item.Room;

public class ProfilePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8765497875791364745L;

	private Room room;
	private JLabel labelName;
	private JTextField name;
	
	public ProfilePanel(Room r) {
		this.room = r;
		
		labelName = new JLabel("Name : ");
		name = new JTextField(room.getName());
		
		labelName.setBounds(10, 21, 50, 23);
		name.setBounds(70, 21, 89, 23);
		name.setEditable(false);
		
		this.add(labelName);
		this.add(name);
	}
}
