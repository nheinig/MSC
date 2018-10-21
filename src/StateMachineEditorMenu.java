import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateMachineEditorMenu extends JPanel {
	
	JButton dragButton = new JButton("DRAG-MODE");
	JButton addStateButton = new JButton("ADD-STATE-MODE");
	JButton addTransitionButton = new JButton("ADD-TRANSITION-MODE");
	JButton deleteButton = new JButton("DELETE-MODE");
	
	Box menuBox = Box.createHorizontalBox();
	
	boolean dragMode = true;
	boolean addStateMode = false;
	boolean addTransitionMode = false;
	boolean deleteMode = false;
	
	StateMachineEditorMenu(){
		setSize(600,100);
		menuBox.add(dragButton);
		menuBox.add(addStateButton);
		menuBox.add(addTransitionButton);
		menuBox.add(deleteButton);
		add(menuBox);
		createMenu();
	}
	
	
	void createMenu() {

		dragButton.setBackground(Color.GREEN);
		
		dragButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dragMode = true;
				addStateMode = false;
				addTransitionMode = false;
				deleteMode = false;
				dragButton.setBackground(Color.GREEN);
				addStateButton.setBackground(null);
				addTransitionButton.setBackground(null);
				deleteButton.setBackground(null);
			}
		});
		addStateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStateMode = true;
				dragMode = false;
				addTransitionMode = false;
				deleteMode = false;
				addStateButton.setBackground(Color.GREEN);
				dragButton.setBackground(null);
				addTransitionButton.setBackground(null);
				deleteButton.setBackground(null);
			}
		});
		addTransitionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTransitionMode = true;
				dragMode = false;
				addStateMode = false;
				deleteMode = false;
				addTransitionButton.setBackground(Color.GREEN);
				dragButton.setBackground(null);
				addStateButton.setBackground(null);
				deleteButton.setBackground(null);
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMode = true;
				dragMode = false;
				addStateMode = false;
				addTransitionMode = false;
				deleteButton.setBackground(Color.GREEN);
				dragButton.setBackground(null);
				addStateButton.setBackground(null);
				addTransitionButton.setBackground(null);
			}
		});
		
	}
	
}
