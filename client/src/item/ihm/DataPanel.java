package item.ihm;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DataPanel extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6065881095972336463L;


	private JTextPane data; 
	
	public DataPanel(){
		data = new JTextPane();
		data.setEditable(false);
		this.setViewportView(data);
	}
	
	public DataPanel(JTextPane data){
		this.data = data;
		data.setEditable(false);
		this.setViewportView(data);
	}
	
	public JTextPane getData() {
		return data;
	}

	public void setData(JTextPane data) {
		this.data = data;
	}
}
