package item.ihm;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import client.ClientIHM;

public class ItemRoomRenderer extends JLabel implements ListCellRenderer<ItemRoom> {

	private ClientIHM clientIHM;
	
	public ItemRoomRenderer(ClientIHM clientIHM) {
		setOpaque(true);
		this.clientIHM = clientIHM;
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends ItemRoom> list, ItemRoom room, int index,
	        boolean isSelected, boolean cellHasFocus) {
		
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/room.png"));
	         
	        setIcon(imageIcon);
	        setText(room.getName());
	        
	        if (isSelected) {
	            setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());	            
	            
			   if (room.getRoom().isUpdated()) {
				   //	On detruit pour reconstruire
				   clientIHM.getTabbedPane().removeAll();
			
				   //	On reconstruit
				   clientIHM.getTabbedPane().draw(room.getRoom());
			        
				   room.getRoom().setUpdated(false);
			   }
	    		
	    		
	    		
	        } else {
	            setBackground(list.getBackground());
	            setForeground(list.getForeground());
	            room.getRoom().setUpdated(true);
	        }
	         
	        return this;
	}

}
