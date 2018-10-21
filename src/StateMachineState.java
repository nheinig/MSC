import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateMachineState extends JPanel{
	boolean isInitialState;
	
	int stateValue;
	
	JLabel stateLabel = new JLabel();
	
	StateMachineState(int sv, boolean isInit){
		setSize(25,25);
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		isInitialState = isInit;
		stateValue = sv;
		stateLabel.setText(Integer.toString(sv));
		
		add(stateLabel);
	}
	
}
