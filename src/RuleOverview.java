import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;

public class RuleOverview {

	boolean isShown = false;

	Box mainBox = Box.createVerticalBox();
	Box nameBox = Box.createHorizontalBox();
	Box inputBox = Box.createHorizontalBox();
	Box outputBox = Box.createHorizontalBox();

	Rule overviewRule;

	JLabel nameLabel = new JLabel("Rule Name: ");
	JLabel inputLabel = new JLabel("Inputs: ");
	JLabel outputLabel = new JLabel("Outputs: ");

	RuleOverview(Rule rule) {

		overviewRule = rule;

		fillNameBox();
		fillInputBox();
		fillOutputBox();

		createRuleOverview();
	}

	// method that sets visibility of the overview frame to true
	void showOverview() {
		if (!isShown) {
			isShown = true;
			ConfigurationMenu.overviewBox.add(mainBox);
		} else {
			isShown = false;
			ConfigurationMenu.overviewBox.remove(mainBox);
		}
	}

	// fills the nameBox with the Labels ruleNameLabel and nameLabel
	void fillNameBox() {
		JLabel ruleNameLabel = new JLabel(overviewRule.getRuleName());

		nameBox.add(nameLabel);
		nameBox.add(ruleNameLabel);
	}

	// fills the inputBox with the inputLabel and a Box which contains all the
	// inputs
	void fillInputBox() {
		Box inputs = Box.createVerticalBox();

		for (int i = 0; i < overviewRule.listOfParametersNeeded.size(); i++) {
			JLabel input = new JLabel(overviewRule.listOfParametersNeeded.get(i));
			inputs.add(input);
		}

		inputBox.add(inputLabel);

		inputBox.add(inputs);
	}

	// fills the outputBox
	void fillOutputBox() {
		Box outputs = Box.createVerticalBox();

		for (int i = 0; i < overviewRule.listOfOutputs.size(); i++) {
			JLabel output = new JLabel(overviewRule.listOfOutputs.get(0));
			outputs.add(output);
		}

		outputBox.add(outputLabel);
		outputBox.add(outputs);
	}

	// method that fills the overview frame
	public void createRuleOverview() {
		mainBox.setBorder(BorderFactory.createLineBorder(Color.black));
		mainBox.add(nameBox);
		mainBox.add(inputBox);
		mainBox.add(outputBox);
	}
}
