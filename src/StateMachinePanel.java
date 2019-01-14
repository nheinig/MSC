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
		smPanel.setPreferredSize(new Dimension(1200, 600));
		smPanel.setLayout(null);

		smPanel.setBackground(Color.WHITE);

	}


	// method that adds a new State to the smPanel 
	static void addNewState(String stateLabel, int x, int y) {
		StateMachineState state = new StateMachineState(stateLabel);
		smPanel.add(state);
		state.setLocation(x, y);
		StateMachineMovement sm = new StateMachineMovement(state);
		listOfStates.add(state);
	}
	
	// creates a Standard StateMachine
	static void createStandardSM(String parameterType1, String parameterType2) {
		createStandardSMStates(parameterType1, parameterType2);	
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

		addNewState(s1, smPanel.getWidth() / 2 - 337, smPanel.getHeight() / 2);
		addNewState(s2, smPanel.getWidth() / 2 - 187, smPanel.getHeight() / 2 - 100);
		addNewState(s3, smPanel.getWidth() / 2 - 37, smPanel.getHeight() / 2 - 200);
		addNewState(s4, smPanel.getWidth() / 2 + 113, smPanel.getHeight() / 2 - 125);
		addNewState(s5, smPanel.getWidth() / 2 + 263, smPanel.getHeight() / 2 - 25);
		addNewState(s6, smPanel.getWidth() / 2 + 262, smPanel.getHeight() / 2 + 75);
		addNewState(s7, smPanel.getWidth() / 2 + 102, smPanel.getHeight() / 2 + 175);
		addNewState(s8, smPanel.getWidth() / 2 - 48, smPanel.getHeight() / 2 + 175);
		addNewState(s9, smPanel.getWidth() / 2 - 188, smPanel.getHeight() / 2 + 75);
	}
	
}
