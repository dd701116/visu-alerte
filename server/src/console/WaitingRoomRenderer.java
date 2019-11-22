package console;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import reseau.WaitingRoom;

public class WaitingRoomRenderer extends JLabel implements ListCellRenderer<Object>{


	private static final long serialVersionUID = 4205560345043834813L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> arg0, Object arg1, int arg2,
			boolean arg3, boolean arg4) {

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/waitingroom.png"));
         
        setIcon(imageIcon);
        setText(Integer.toString(((WaitingRoom)arg1).getIdx()));
         
        return this;
	}

}
