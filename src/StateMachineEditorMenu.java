import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StateMachineEditorMenu extends JPanel {
	
	JButton dragButton = new JButton("DRAG");
	JButton addStateButton = new JButton("ADD STATE");
	JButton addTransitionButton = new JButton("ADD TRANSITION");
	JButton deleteButton = new JButton("DELETE");
	
	Box menuBox = Box.createHorizontalBox();
	
	boolean dragMode = true;
	boolean addStateMode = false;
	boolean addTransitionMode = false;
	boolean deleteMode = false;
	
	StateMachineEditorMenu(){
		menuBox.add(dragButton);
		menuBox.add(addStateButton);
		menuBox.add(addTransitionButton);
		menuBox.add(deleteButton);
		add(menuBox);
		
	}
	
	
	void createMenu() {

		dragButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dragMode = true;
				addStateMode = false;
				addTransitionMode = false;
				deleteMode = false;
			}
		});
		addStateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStateMode = true;
				dragMode = false;
				addTransitionMode = false;
				deleteMode = false;
			}
		});
		addTransitionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTransitionMode = true;
				dragMode = false;
				addStateMode = false;
				deleteMode = false;
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMode = true;
				dragMode = false;
				addStateMode = false;
				addTransitionMode = false;
			}
		});
		
	}
	
}
