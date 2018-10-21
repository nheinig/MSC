import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class StateMachinePanel {
	
	JPanel smPanel = new JPanel();
	int stateCounter = 0;
	
	StateMachinePanel(){
		smPanel.setSize(600,700);
		smPanel.setLayout(null);
		
		smPanel.setBackground(Color.WHITE);

		
		smPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(StateMachineEditorMenu.addStateMode) {
					addNewState(stateCounter);
					StateMachineEditor.show();
				}		
			}
		});
	}
	
	//method that adds a new State to the smPanel
	void addNewState(int stateLabel) {
		StateMachineState testState = new StateMachineState(stateLabel);
		smPanel.add(testState);
		StateMachineMovement sm = new StateMachineMovement(testState);
		stateCounter ++;
	}
	
	
	//method that adds a new Transition to the smPanel
	void addNewTransition(int source, int destination, String guard, String action) {
		
	}
	
	//method that deletes a state from the smPanel
	void deleteState() {
		
	}
	
	//method that deletes a Transition from the smPanel
	void deleteTransition() {
		
	}
}
