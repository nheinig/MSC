import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateMachineEditorMenu extends JPanel {
	
	JButton dragButton = new JButton("DRAG");
	JButton changeOutputButton = new JButton("CHANGE OUTPUT");
	JButton closeButton = new JButton("CLOSE");
	
	Box menuBox = Box.createHorizontalBox();
	
	static boolean dragMode = true;
	static boolean changeOutputMode = false;
	
	StateMachineEditorMenu(){
		setPreferredSize(new Dimension (1200,100));	
		createMenu();
	}
	
	//method that  creates the StateMachineEditorMenu
	void createMenu() {

		dragButton.setBackground(Color.GREEN);
		
		//ActionListener that toggles dragMode and marks the dragButton on dragButton click
		dragButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dragMode = true;
				changeOutputMode = false;
				dragButton.setBackground(Color.GREEN);
				changeOutputButton.setBackground(null);
			}
		});
		//ActionListener that toggles addStateMode and marks the addStateButton on addStateButton click
		changeOutputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeOutputMode = true;
				dragMode = false;
				changeOutputButton.setBackground(Color.GREEN);
				dragButton.setBackground(null);
			}
		});
		//ActionListener that closes the StateMachineEditor on closeButton click
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StateMachineEditor.hide();
			}
		});
		
		menuBox.add(dragButton);
		menuBox.add(changeOutputButton);
		menuBox.add(closeButton);
		add(menuBox);
	}
	
}
