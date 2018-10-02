import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RuleCreator {
	JFrame rc = new JFrame("RuleCreator");
	
	Box mainBox = Box.createVerticalBox();
	Box nameBox = Box.createHorizontalBox();
	Box inputBox = Box.createHorizontalBox();
	Box outputBox = Box.createHorizontalBox();
	Box stateMachineBox = Box.createHorizontalBox();
	Box buttonBox = Box.createHorizontalBox();
	Box rulesBox = Box.createVerticalBox();
	Box ruleLabelBox = Box.createHorizontalBox();
	Box transBox = Box.createHorizontalBox();
	
	
	JLabel nameLabel = new JLabel("Rule Name: ");
	JLabel inputLabel = new JLabel("Inputs: ");
	JLabel outputLabel = new JLabel("Output: ");
	JLabel stateMachineLabel = new JLabel("State Machine:   ");
	JLabel srcStateLabel = new JLabel("Source    ");
	JLabel destStateLabel = new JLabel("Destination    ");
	JLabel condLabel = new JLabel("Condition    ");
	
	
	JTextField ruleNameTF = new JTextField();
	JTextField outputTF = new JTextField();
	JTextField srcTF = new JTextField();
	JTextField destTF = new JTextField();
	JTextField condTF = new JTextField();

	JButton addLineButton = new JButton("+");
	JButton createButton = new JButton("Create Rule");
	JButton cancelButton = new JButton("Cancel");
	
	
	static JComboBox<String> inputCB1 = new JComboBox<String>();
	static JComboBox<String> inputCB2 = new JComboBox<String>();
	
	
	RuleCreator(){
		rc.setSize(400, 400);
		rc.setLayout(new FlowLayout());
		createRuleCreator();
	}
	
	//method that fills the rc-frame
	void createRuleCreator() {
		
		nameBox.add(nameLabel);
		nameBox.add(ruleNameTF);
		
		inputBox.add(inputLabel);
		inputBox.add(inputCB1);
		inputBox.add(inputCB2);
		
		outputBox.add(outputLabel);
		outputBox.add(outputTF);
		
		ruleLabelBox.add(srcStateLabel);
		ruleLabelBox.add(destStateLabel);
		ruleLabelBox.add(condLabel);
		ruleLabelBox.add(addLineButton);
		
		stateMachineBox.add(stateMachineLabel);		
		stateMachineBox.add(rulesBox);
		
		transBox.add(srcTF);
		transBox.add(destTF);
		transBox.add(condTF);
		 
		rulesBox.add(ruleLabelBox);
		rulesBox.add(transBox);
		
		buttonBox.add(cancelButton);
		buttonBox.add(createButton);
		
		mainBox.add(nameBox);
		mainBox.add(inputBox);
		mainBox.add(outputBox);
		mainBox.add(stateMachineBox);
		mainBox.add(buttonBox);
		
		rc.add(mainBox);
	}
	
	//method that sets rc-visibility to true
	void show() {
		rc.setVisible(true);
	}
	
	//method that fills the input combo boxes
	void fillInputComboBox() {
		for(int i = 0; i < ConfigurationUI.getParameterStrings().size(); i++) {
			System.out.println("HERE!!!");	
			if(((DefaultComboBoxModel)inputCB1.getModel()).getIndexOf(ConfigurationUI.getParameterStrings().get(i)) == -1) {
				inputCB1.addItem(ConfigurationUI.getParameterStrings().get(i));
			}
			if(((DefaultComboBoxModel)inputCB2.getModel()).getIndexOf(ConfigurationUI.getParameterStrings().get(i)) == -1) {
				inputCB2.addItem(ConfigurationUI.getParameterStrings().get(i));
			}
		}
	}
}
