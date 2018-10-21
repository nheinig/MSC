import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class StateMachinePanel {
	
	static JPanel smPanel = new JPanel();
	int stateCounter = 0;
	
	static boolean isSource = true;
	static StateMachineState source;
	static StateMachineState destination;
	
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
	static void addNewTransition(StateMachineState s, StateMachineState d) {
		int sX = (int) s.getLocation().getX();
		int sY = (int) s.getLocation().getY();
		int dX = (int) d.getLocation().getX();
		int dY = (int) d.getLocation().getY();
		Arrow a = new Arrow(sX, sY, dX, dY);
		a.draw(smPanel.getGraphics());
	}
	
	//method that deletes a state from the smPanel
	void deleteState() {
		
	}
	
	//method that deletes a Transition from the smPanel
	void deleteTransition() {
		
	}
}
