package console;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import connector.Connector;

public class ConnectorRenderer extends JLabel implements ListCellRenderer<Connector>{

	private static final long serialVersionUID = -1116737819394141959L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Connector> arg0, Connector arg1, int arg2,
			boolean arg3, boolean arg4) {


		String type = arg1.getType();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/" + type + ".png"));
         
        setIcon(imageIcon);
        setText(Integer.toString(arg1.getId()));
         
        return this;
	}

}
