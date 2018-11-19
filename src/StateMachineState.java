import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateMachineState extends JPanel {

	JLabel stateLabel = new JLabel();

	StateMachineState(String label) {
		setSize(25, 25);

		setBackground(Color.GREEN);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		stateLabel.setText(label);

		add(stateLabel);

		// mouseListener for stateMachineState
		//
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (contains(e.getX(), e.getY())) {
					if (StateMachineEditorMenu.addTransitionMode) {
						// action if it is the source of a transition
						if (StateMachinePanel.isSource) {
							StateMachinePanel.source = getStateMachineState();
						} else
						// action if it is the destination of a transition
						{
							StateMachinePanel.destination = getStateMachineState();
							StateMachinePanel.addNewTransition(StateMachinePanel.source, StateMachinePanel.destination);
						}
						// swap between source and destination
						StateMachinePanel.isSource = !StateMachinePanel.isSource;
					} else if (StateMachineEditorMenu.deleteMode) {
						StateMachinePanel.deleteState(getStateMachineState());
						StateMachinePanel.repaintTransitions();
					} else if (StateMachineEditorMenu.addStateMode) {
						changeOutputColor();										
					}
				}
			}
		});
	}

	void changeOutputColor() {
		if(getBackground() == Color.GREEN) {
			setBackground(Color.YELLOW);
		} else if (getBackground() == Color.YELLOW) {
			setBackground(Color.RED);
		} else if (getBackground() == Color.RED) {
			setBackground(Color.GREEN);
		}
	}
	
	// getter for the StateMachineState
	StateMachineState getStateMachineState() {
		return this;
	}
}
