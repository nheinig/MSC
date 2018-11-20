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
	static ArrayList<StateMachineTransition> listOfTransitions = new ArrayList<StateMachineTransition>();
	static ArrayList<StateMachineState> listOfStates = new ArrayList<StateMachineState>();

	StateMachinePanel() {
		smPanel.setPreferredSize(new Dimension(600, 600));
		smPanel.setLayout(null);

		smPanel.setBackground(Color.WHITE);

		// mouseListener:if addStateMode is true adds a new state to the panel on mouse
		// click
		smPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (StateMachineEditorMenu.addStateMode) {
					addNewState(Integer.toString(stateCounter), e);
					StateMachineEditor.show();
				}

			}
		});
	}

	// method that adds a new State to the smPanel
	void addNewState(String stateLabel, MouseEvent e) {
		StateMachineState state = new StateMachineState(stateLabel);
		smPanel.add(state);
		state.setLocation(e.getX(), e.getY());
		StateMachineMovement sm = new StateMachineMovement(state);
		listOfStates.add(state);
		stateCounter++;
	}

	static void addNewState(String stateLabel, int x, int y) {
		StateMachineState state = new StateMachineState(stateLabel);
		smPanel.add(state);
		state.setLocation(x, y);
		StateMachineMovement sm = new StateMachineMovement(state);
		listOfStates.add(state);
	}

	// method that adds a ArrayList with source and destination of a new Transition
	// to the transitionList and calls drawTransition
	static void addNewTransition(StateMachineState s, StateMachineState d) {
		StateMachineTransition transition = new StateMachineTransition(s, d);
		drawTransition(s, d);
	}

	// method that draws a transition (arrow) from a source s to a destination d
	static void drawTransition(StateMachineState s, StateMachineState d) {
		int sX = (int) s.getLocation().getX();
		int sY = (int) s.getLocation().getY();
		int dX = (int) d.getLocation().getX();
		int dY = (int) d.getLocation().getY();
		Arrow a = new Arrow(sX, sY, dX, dY);
		a.draw(smPanel.getGraphics());
	}

	// method that repaints the Transitions by executing drawTransition for all
	// source destination pairs in the transitionList
	static void repaintTransitions() {
		// smPanel.repaint();
		for (int i = 0; i < listOfTransitions.size(); i++) {
			StateMachineState s = listOfTransitions.get(i).source;
			StateMachineState d = listOfTransitions.get(i).destination;
			drawTransition(s, d);
		}
	}

	// method that deletes a state from the smPanel
	static void deleteState(StateMachineState state) {
		smPanel.remove(state);
		for (int i = 0; i < listOfTransitions.size(); i++) {
			if (listOfTransitions.get(i).contains(state)) {
				listOfTransitions.remove(i);
				i--;
			}
		}
		smPanel.repaint();
		repaintTransitions();
	}

	// method that deletes a Transition from the smPanel
	void deleteTransition() {

	}

	// creates a Standard StateMachine
	static void createStandardSM(String parameterType1, String parameterType2) {
		createStandardSMStates(parameterType1, parameterType2);
		createStandardSMTransitions();				
		StateMachineEditor.show();
	}

	//method that creates the States for the StandardSM based on the parameterTypes
	static void createStandardSMStates(String parameterType1, String parameterType2) {
		addNewState("init", smPanel.getWidth() / 2, smPanel.getHeight() / 2);

		String p1v1 = "";
		String p1v2 = "";
		String p1v3 = "";
		String p2v1 = "";
		String p2v2 = "";
		String p2v3 = "";

		// search for the first parameterType in the
		// InferenceControll.listOfParameterValues to get the possible values for
		// ParameterType1
		for (int i = 0; i < InferenceControll.listOfParameterValues.size(); i++) {
			if (InferenceControll.listOfParameterValues.get(i).contains(parameterType1)) {
				p1v1 = InferenceControll.listOfParameterValues.get(i).get(1);
				p1v2 = InferenceControll.listOfParameterValues.get(i).get(2);
				p1v3 = InferenceControll.listOfParameterValues.get(i).get(3);
				break;
			}
		}
		// search for the first parameterType in the
		// InferenceControll.listOfParameterValues to get the possible values for
		// ParameterType2
		for (int i = 0; i < InferenceControll.listOfParameterValues.size(); i++) {
			if (InferenceControll.listOfParameterValues.get(i).contains(parameterType2)) {
				p2v1 = InferenceControll.listOfParameterValues.get(i).get(1);
				p2v2 = InferenceControll.listOfParameterValues.get(i).get(2);
				p2v3 = InferenceControll.listOfParameterValues.get(i).get(3);
				break;
			}
		}

		String s1 = parameterType1 + " == " + p1v1 + " && " + parameterType2 + " == " + p2v1;
		String s2 = parameterType1 + " == " + p1v1 + " && " + parameterType2 + " == " + p2v2;
		String s3 = parameterType1 + " == " + p1v1 + " && " + parameterType2 + " == " + p2v3;
		String s4 = parameterType1 + " == " + p1v2 + " && " + parameterType2 + " == " + p2v1;
		String s5 = parameterType1 + " == " + p1v2 + " && " + parameterType2 + " == " + p2v2;
		String s6 = parameterType1 + " == " + p1v2 + " && " + parameterType2 + " == " + p2v3;
		String s7 = parameterType1 + " == " + p1v3 + " && " + parameterType2 + " == " + p2v1;
		String s8 = parameterType1 + " == " + p1v3 + " && " + parameterType2 + " == " + p2v2;
		String s9 = parameterType1 + " == " + p1v3 + " && " + parameterType2 + " == " + p2v3;

		addNewState(s1, smPanel.getWidth() / 2 - 150, smPanel.getHeight() / 2);
		addNewState(s2, smPanel.getWidth() / 2 - 84, smPanel.getHeight() / 2 - 67);
		addNewState(s3, smPanel.getWidth() / 2 - 18, smPanel.getHeight() / 2 - 133);
		addNewState(s4, smPanel.getWidth() / 2 + 40, smPanel.getHeight() / 2 - 100);
		addNewState(s5, smPanel.getWidth() / 2 + 106, smPanel.getHeight() / 2 - 33);
		addNewState(s6, smPanel.getWidth() / 2 + 142, smPanel.getHeight() / 2 + 34);
		addNewState(s7, smPanel.getWidth() / 2 + 75, smPanel.getHeight() / 2 + 102);
		addNewState(s8, smPanel.getWidth() / 2 + 7, smPanel.getHeight() / 2 + 132);
		addNewState(s9, smPanel.getWidth() / 2 - 60, smPanel.getHeight() / 2 + 66);
	}
	
	static void createStandardSMTransitions() {
		for(int i = 0; i < listOfStates.size(); i++) {
			addNewTransition(listOfStates.get(0), listOfStates.get(i));
		}
	}
}
