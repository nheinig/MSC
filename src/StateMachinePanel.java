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
	static ArrayList<StateMachineState> listOfStates = new ArrayList<StateMachineState>();

	StateMachinePanel() {
		smPanel.setPreferredSize(new Dimension(1200, 550));
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
		addNewState("init", 20, smPanel.getHeight() / 2);

		String p1v1 = "";
		String p1v2 = "";
		String p1v3 = "";
		String p1Missing = parameterType1 + "Missing";
		String p2v1 = "";
		String p2v2 = "";
		String p2v3 = "";
		String p2Missing = parameterType2 + "Missing";

		// search for the first parameterType in the
		// InferenceControll.listOfParameterValues to get the possible values for
		// ParameterType1
		for (int i = 0; i < InferenceControl.listOfParameterValues.size(); i++) {
			if (InferenceControl.listOfParameterValues.get(i).contains(parameterType1)) {
				p1v1 = InferenceControl.listOfParameterValues.get(i).get(1);
				p1v2 = InferenceControl.listOfParameterValues.get(i).get(2);
				p1v3 = InferenceControl.listOfParameterValues.get(i).get(3);
				break;
			}
		}
		// search for the first parameterType in the
		// InferenceControll.listOfParameterValues to get the possible values for
		// ParameterType2
		for (int i = 0; i < InferenceControl.listOfParameterValues.size(); i++) {
			if (InferenceControl.listOfParameterValues.get(i).contains(parameterType2)) {
				p2v1 = InferenceControl.listOfParameterValues.get(i).get(1);
				p2v2 = InferenceControl.listOfParameterValues.get(i).get(2);
				p2v3 = InferenceControl.listOfParameterValues.get(i).get(3);
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

		addNewState(s1, smPanel.getWidth() / 4 + 20 , 20);
		addNewState(s2, smPanel.getWidth() / 4 + 20, smPanel.getHeight() / 2);
		addNewState(s3, smPanel.getWidth() / 4 + 20 , smPanel.getHeight() - 50);
		addNewState(s4, smPanel.getWidth() / 2 + 20, 20);
		addNewState(s5, smPanel.getWidth() / 2 + 20, smPanel.getHeight() / 2);
		addNewState(s6, smPanel.getWidth() / 2 + 20, smPanel.getHeight() - 50);
		addNewState(s7, (smPanel.getWidth() / 4) * 3 + 20, 20);
		addNewState(s8, smPanel.getWidth() / 4 * 3 + 20, smPanel.getHeight() / 2);
		addNewState(s9, smPanel.getWidth() / 4 * 3 + 20, smPanel.getHeight() - 50);
		addNewState(p1Missing, 20, 20);
		addNewState(p2Missing, 20, smPanel.getHeight() - 50);
	}
	
}
