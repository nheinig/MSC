import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateMachineState extends JPanel {

	int stateValue;

	JLabel stateLabel = new JLabel();

	StateMachineState(int sv) {
		setSize(25, 25);

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		stateValue = sv;
		stateLabel.setText(Integer.toString(sv));

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
					}
				}
			}
		});
	}

	// getter for the StateMachineState
	StateMachineState getStateMachineState() {
		return this;
	}
}
