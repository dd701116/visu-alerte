package item.ihm;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import item.Sensor;

public class SensorRenderer extends JLabel implements ListCellRenderer<Sensor> {

	public SensorRenderer() {
		setOpaque(true);
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends Sensor> list, Sensor sensor, int index,
	        boolean isSelected, boolean cellHasFocus) {

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/sensor.png"));
        
        setIcon(imageIcon);
        setText(sensor.getName());
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());	            
                		
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
         
        return this;
	}

}
