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
	
	
	//method that adds a new Transition to the smPanel
	static void addNewTransition(StateMachineState s, StateMachineState d) {
		int sX = (int) s.getLocation().getX();
		int sY = (int) s.getLocation().getY();
		int dX = (int) d.getLocation().getX();
		int dY = (int) d.getLocation().getY();
		Arrow a = new Arrow(sX, sY, dX, dY);
		ArrayList<StateMachineState> tempList = new ArrayList<StateMachineState>();
		tempList.add(s);
		tempList.add(d);
		transitionList.add(tempList);
		a.draw(smPanel.getGraphics());
	}
	
	
	static void repaintTransitions() {
		smPanel.getGraphics().clearRect(0, 0, smPanel.getWidth(), smPanel.getHeight());
		for (int i = 0; i < transitionList.size(); i++) {
			Arrow a = new Arrow((int)transitionList.get(i).get(0).getLocation().getX(), (int)transitionList.get(i).get(0).getLocation().getY(), (int)transitionList.get(i).get(1).getLocation().getX(), (int)transitionList.get(i).get(1).getLocation().getY());
			a.draw(smPanel.getGraphics());
		}
	}
	
	//method that deletes a state from the smPanel
	static void deleteState(StateMachineState state) {
		smPanel.remove(state);
		smPanel.repaint();
	}
	
	//method that deletes a Transition from the smPanel
	void deleteTransition() {
		
	}
}
