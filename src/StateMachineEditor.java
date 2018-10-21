import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StateMachineEditor {

	static JFrame smEditor = new JFrame("StateMachineEditor");
	
	ArrayList<State> listOfStates = new ArrayList<State>();
	
	StateMachineEditorMenu menuPanel = new StateMachineEditorMenu();
	
	StateMachinePanel stateMachinePanel = new StateMachinePanel();
	
	JPanel mainPanel = new JPanel();
	
	StateMachineEditor(){
		smEditor.setSize(600,800);
		createSMEditor();
	}

	
	void createSMEditor() {
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(menuPanel);
		mainPanel.add(stateMachinePanel.smPanel);
		
		smEditor.add(mainPanel);
		
	}
	
	 static void show() {
		 smEditor.setVisible(true);
	}
}
