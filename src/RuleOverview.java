import java.awt.Label;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RuleOverview {
	
	JFrame overview = new JFrame("RuleOverview");
	Box mainBox = Box.createVerticalBox();
	
	Box nameBox = Box.createHorizontalBox();
	
	Box inputBox = Box.createHorizontalBox();
	
	Box outputBox = Box.createHorizontalBox();
	
	Rule overviewRule;
	
	JLabel nameLabel = new JLabel("Rule Name: ");		
	JLabel inputLabel = new JLabel("Inputs: ");
	JLabel outputLabel = new JLabel("Outputs: ");
	
	
	RuleOverview(Rule rule){	
		
		overviewRule = rule;
	
		fillNameBox();
		fillInputBox();
		fillOutputBox();
		
		createRuleOverview();		
	}
	
	//method that sets visibility of the overview frame to true
	void showOverview() {
		if(!overview.isVisible()) {
			overview.setVisible(true);
		}
	}
	
	//fills the nameBox with the Labels ruleNameLabel and nameLabel
	void fillNameBox() {
		JLabel ruleNameLabel = new JLabel(overviewRule.ruleName);
		
		nameBox.add(nameLabel);
		nameBox.add(ruleNameLabel);
	}
	
	
	//fills the inputBox with the inputLabel and a Box which contains all the inputs
	void fillInputBox() {
		Box inputs = Box.createVerticalBox();
		
		for(int i = 0; i < overviewRule.listOfParametersNeeded.size(); i++) {
			JLabel input = new JLabel(overviewRule.listOfParametersNeeded.get(i));
			inputs.add(input);
		}
		
		inputBox.add(inputLabel);
		
		inputBox.add(inputs);
	}
	
	//fills the outputBox
	void fillOutputBox() {
		JLabel output = new JLabel(overviewRule.ruleResult.parameterType);
		
		outputBox.add(outputLabel);
		outputBox.add(output);		
	}
	
	
	//method that fills the overview frame
	public void createRuleOverview() {

		
		mainBox.add(nameBox);
		mainBox.add(inputBox);
		mainBox.add(outputBox);
		
		overview.add(mainBox);
		overview.setSize(400, 400);

	}
}
