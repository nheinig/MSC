import java.awt.Color;
import java.awt.Dimension;
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
	JButton saveButton = new JButton("SAVE");
	JButton closeButton = new JButton("CLOSE");
	
	Box menuBox = Box.createHorizontalBox();
	
	static boolean dragMode = true;
	static boolean addStateMode = false;
	static boolean addTransitionMode = false;
	static boolean deleteMode = false;
	
	StateMachineEditorMenu(){
		setPreferredSize(new Dimension (600,100));	
		createMenu();
	}
	
	//method that  creates the StateMachineEditorMenu
	void createMenu() {

		dragButton.setBackground(Color.GREEN);
		
		//Actionlistener that toggles dragMode and marks the dragButton on dragButton click
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
		//Actionlistener that toggles addStateMode and marks the addStateButton on addStateButton click
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
		//Actionlistener that toggles addTransitionMode and marks the addTransitionButton on addTransitionButton click
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
		//Actionlistener that toggles deleteMode and marks the deleteButton on deleteButton click
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
		//ActionListener for saveButton
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//todo
			}
		});
		//Actionlistener that closes the StateMachineEditor on closeButton click
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StateMachineEditor.hide();
			}
		});
		
		menuBox.add(dragButton);
		menuBox.add(addStateButton);
		menuBox.add(addTransitionButton);
		menuBox.add(deleteButton);
		menuBox.add(saveButton);
		menuBox.add(closeButton);
		add(menuBox);
	}
	
}
