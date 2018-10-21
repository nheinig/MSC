import java.awt.Color;

import javax.swing.JPanel;

public class StateMachinePanel {
	
	JPanel smPanel = new JPanel();
	
	StateMachineState initState = new StateMachineState(0, true);
	
	StateMachinePanel(){
		smPanel.setSize(600,700);
		smPanel.setLayout(null);
		
		smPanel.setBackground(Color.WHITE);
		addNewState();
	}
	
	
	void addNewState() {
		smPanel.add(initState);
	}
	
	void addNewTransition() {
		
	}
	
}
