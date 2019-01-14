import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	Box outputLabelBox = Box.createVerticalBox();
	Box outputTextFieldBox = Box.createVerticalBox();
	Box delayBox = Box.createHorizontalBox();
	
	
	JLabel nameLabel = new JLabel("Rule Name: ");
	JLabel inputLabel = new JLabel("Inputs: ");
	JLabel outputLabel = new JLabel("Output Name:             ");
	JLabel outputLabelRed = new JLabel("Red Output:          ");
	JLabel outputLabelYellow = new JLabel("Yellow Output:          ");
	JLabel outputLabelGreen = new JLabel("Green Output:          ");
	JLabel stateMachineLabel = new JLabel("State Machine:           ");
	JLabel delayLabel = new JLabel("Alarm Delay (in ms): ");
	
	
	JTextField ruleNameTF = new JTextField();
	JTextField outputTF = new JTextField();
	JTextField redOutputTF = new JTextField();
	JTextField yellowOutputTF = new JTextField();
	JTextField greenOutputTF = new JTextField();
	JTextField delayTF = new JTextField();
	
	JButton createButton = new JButton("Create Rule");
	JButton cancelButton = new JButton("Cancel");
	JButton stateMachineButton = new JButton("Show StateMachine Editor");	
	
	static JComboBox<String> inputCB1 = new JComboBox<String>();
	static JComboBox<String> inputCB2 = new JComboBox<String>();
	
	StateMachineEditor sme = new StateMachineEditor();
	
	boolean alreadyOpendSME = false;
	
	RuleCreator(){
		rc.setSize(400, 400);
		rc.setLayout(new FlowLayout());
		createRuleCreator();
		//ActionListener that shows StateMachineEditor on stateMachineButton click
		stateMachineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sme.show();
				if(!alreadyOpendSME) {
					StateMachinePanel.createStandardSM((String)inputCB1.getSelectedItem(), (String)inputCB2.getSelectedItem());
					alreadyOpendSME = true;
				}
			}
		});
	}
	
	void createStandardStateMachine() {
		
	}
	
	
	//method that fills the rc-frame
	void createRuleCreator() {
		
		nameBox.add(nameLabel);
		nameBox.add(ruleNameTF);
		
		inputBox.add(inputLabel);
		inputBox.add(inputCB1);
		inputBox.add(inputCB2);
		
		
		outputLabelBox.add(outputLabel);
		outputLabelBox.add(outputLabelRed);
		outputLabelBox.add(outputLabelYellow);
		outputLabelBox.add(outputLabelGreen);
		
		outputTextFieldBox.add(outputTF);
		outputTextFieldBox.add(redOutputTF);
		outputTextFieldBox.add(yellowOutputTF);
		outputTextFieldBox.add(greenOutputTF);
		
		delayBox.add(delayLabel);
		delayBox.add(delayTF);
		
		outputBox.add(outputLabelBox);
		outputBox.add(outputTextFieldBox);
		
		stateMachineBox.add(stateMachineLabel);		
		stateMachineBox.add(stateMachineButton);
		
		 
		rulesBox.add(ruleLabelBox);
		rulesBox.add(transBox);
		
		buttonBox.add(cancelButton);
		buttonBox.add(createButton);
		
		mainBox.add(nameBox);
		mainBox.add(inputBox);
		mainBox.add(outputBox);
		mainBox.add(delayBox);
		mainBox.add(stateMachineBox);
		mainBox.add(buttonBox);
		
		rc.add(mainBox);
		
		//ActionListener of the cancelButton that closes the RuleCreator and resets the StateMachinePanel
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hide();
				StateMachinePanel.smPanel.removeAll();
				alreadyOpendSME = false;
			}
		});
		
		//ActionListener of the createButton that creates a new Rule, closes the RuleCreator and resets the StateMachinePanel
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hide();
				StateMachinePanel.smPanel.removeAll();
				alreadyOpendSME = false;
			}
		});
		
		
	}
	
	//method that sets rc-visibility to true
	void show() {
		rc.setVisible(true);
	}
	
	//method that sets rc-visibility to false and deletes all inputs made
		void hide() {
			rc.setVisible(false);
			ruleNameTF.setText("");
			outputTF.setText("");
			greenOutputTF.setText("");
			yellowOutputTF.setText("");
			redOutputTF.setText("");
			delayTF.setText("");
		}
	
	//method that fills the input combo boxes
	void fillInputComboBox() {
		for(int i = 0; i < ConfigurationUI.getParameterStrings().size(); i++) {
			if(((DefaultComboBoxModel)inputCB1.getModel()).getIndexOf(ConfigurationUI.getParameterStrings().get(i)) == -1) {
				inputCB1.addItem(ConfigurationUI.getParameterStrings().get(i));
			}
			if(((DefaultComboBoxModel)inputCB2.getModel()).getIndexOf(ConfigurationUI.getParameterStrings().get(i)) == -1) {
				inputCB2.addItem(ConfigurationUI.getParameterStrings().get(i));
			}
		}
	}
	
	//Method that creates a new Rule bases on the inputs to made in the RuleCreator and the StateMachinePanel
	void crateRule() {
	
	}	
	
}

