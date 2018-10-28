import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class StateMachinePanel {
	
	static JPanel smPanel = new JPanel();
	int stateCounter = 0;
	
	static boolean isSource = true;
	static StateMachineState source;
	static StateMachineState destination;
	static ArrayList<Arrow> arrowList = new ArrayList<>();	
	static ArrayList<ArrayList<StateMachineState>> transitionList = new ArrayList<ArrayList<StateMachineState>>();
	
	StateMachinePanel(){
		smPanel.setPreferredSize(new Dimension (600,600));
		smPanel.setLayout(null);
		
		smPanel.setBackground(Color.WHITE);

		//mouseListener:if addStateMode is true adds a new state to the panel on mouse click
		smPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(StateMachineEditorMenu.addStateMode) {
					addNewState(stateCounter, e);
					StateMachineEditor.show();
				} 
				
			}
		});
	}
	
	//method that adds a new State to the smPanel
	void addNewState(int stateLabel, MouseEvent e) {
		StateMachineState state = new StateMachineState(stateLabel);
		smPanel.add(state);
		state.setLocation(e.getX(), e.getY());
		StateMachineMovement sm = new StateMachineMovement(state);
		stateCounter ++;
	}
	
	
	//method that adds a ArrayList with source and destination of a new Transition to the transitionList and calls drawTransition
	static void addNewTransition(StateMachineState s, StateMachineState d) {		
		ArrayList<StateMachineState> tempList = new ArrayList<StateMachineState>();
		tempList.add(s);
		tempList.add(d);
		transitionList.add(tempList);
		drawTransition(s, d);
	}
	
	//method that draws a transition (arrow) from a source s to a destination d
	static void drawTransition(StateMachineState s, StateMachineState d) {
		int sX = (int) s.getLocation().getX();
		int sY = (int) s.getLocation().getY();
		int dX = (int) d.getLocation().getX();
		int dY = (int) d.getLocation().getY();
		Arrow a = new Arrow(sX, sY, dX, dY);
		a.draw(smPanel.getGraphics());
	}
	
	//method that repaints the Transitions by executing drawTransition for all source destination pairs in the transitionList
	static void repaintTransitions() {
		System.out.println(transitionList.size());
		for (int i = 0; i < transitionList.size(); i++) {
			StateMachineState s = transitionList.get(i).get(0);
			StateMachineState d = transitionList.get(i).get(1);
			drawTransition(s,d);
		}
	}
	
	//method that deletes a state from the smPanel
	static void deleteState(StateMachineState state) {
		smPanel.remove(state);
		for(int i = 0; i < transitionList.size(); i++) {
			if(transitionList.get(i).contains(state)) {
				//transitionList.remove(i);
				//i--;
			}		
		}
		smPanel.repaint();
		repaintTransitions();
	}
	
	//method that deletes a Transition from the smPanel
	void deleteTransition() {
		
	}
}
