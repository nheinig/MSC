import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RuleCreator {
	JFrame rc = new JFrame("RuleCreator");
	
	Box mainBox = Box.createVerticalBox();
	Box nameBox = Box.createHorizontalBox();
	Box inputBox = Box.createHorizontalBox();
	Box outputBox = Box.createHorizontalBox();
	Box stateMachineBox = Box.createHorizontalBox();
	
	JLabel nameLabel = new JLabel("Rule Name: ");
	JLabel inputLabel = new JLabel("Inputs: ");
	JLabel outputLabel = new JLabel("Output: ");
	JLabel stateMachineLabel = new JLabel("State Machine: ");
	
	
	
	RuleCreator(){
		rc.setSize(400, 400);
		rc.setLayout(new FlowLayout());
		
		createRuleCreator();
	}
	
	
	void createRuleCreator() {
		
		nameBox.add(nameLabel);
		
		inputBox.add(inputLabel);
		
		outputBox.add(outputLabel);
		
		stateMachineBox.add(stateMachineLabel);
		
		mainBox.add(nameBox);
		mainBox.add(inputBox);
		mainBox.add(outputBox);
		mainBox.add(stateMachineBox);
		
		rc.add(mainBox);
	}
	
	void show() {
		rc.setVisible(true);
	}
}
