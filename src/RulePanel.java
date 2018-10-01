import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RulePanel extends JPanel {

	public RulePanel() {
		setSize(900, 800);
		setLayout(null);
		setBackground(Color.WHITE);
		
		EggMovement rm = new EggMovement(getComponents());
	}

	// creates a new RuleEgg and adds it to the eggPanel
	public void addNewEgg(Rule rule) {
		RuleEgg ruleEgg = new RuleEgg(rule);

		ruleEgg.setSize(200, 200);
		ruleEgg.setName(rule.ruleName);

		ConfigurationUI.addRuleEggToList(ruleEgg);
		this.add(ruleEgg);
		ConfigurationUI.ui.setVisible(true);
	}
}
