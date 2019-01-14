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
		setSize(250, 30);

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
					if (StateMachineEditorMenu.changeOutputMode) {
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
