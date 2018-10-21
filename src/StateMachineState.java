import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateMachineState extends JPanel{
	
	int stateValue;
	
	JLabel stateLabel = new JLabel();
	
	StateMachineState(int sv){
		setSize(25,25);
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		
		stateValue = sv;
		stateLabel.setText(Integer.toString(sv));
		
		add(stateLabel);
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (contains(e.getX(), e.getY())) {
					if (StateMachineEditorMenu.addTransitionMode) {
						if(StateMachinePanel.isSource) {
							StateMachinePanel.source = getStateMachineState();
						} else {
							StateMachinePanel.destination = getStateMachineState();
							StateMachinePanel.addNewTransition(StateMachinePanel.source, StateMachinePanel.destination);
						}
						StateMachinePanel.isSource = !StateMachinePanel.isSource;
						}
					}
			}
		});
	}

	StateMachineState getStateMachineState() {
		return this;
	}
}
