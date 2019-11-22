package item.ihm;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RulePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -647650263290332445L;

	private JScrollPane scrollPaneRule;
	private JList listRule;
	private JButton btnNewRule;
	private JButton btnEditRule;
	private JButton btnDeleteRule;
	
	public RulePanel(){
		scrollPaneRule = new JScrollPane();
		scrollPaneRule.setBounds(0, 55, 746, 468);
		this.add(scrollPaneRule);
		
		listRule = new JList();
		scrollPaneRule.setViewportView(listRule);
		
		btnNewRule = new JButton("New...");
		btnNewRule.setBounds(10, 21, 89, 23);
		this.add(btnNewRule);
		
		btnEditRule = new JButton("Edit");
		btnEditRule.setBounds(109, 21, 89, 23);
		this.add(btnEditRule);
		
		btnDeleteRule = new JButton("Delete");
		btnDeleteRule.setBounds(208, 21, 89, 23);
		this.add(btnDeleteRule);
	}
}
