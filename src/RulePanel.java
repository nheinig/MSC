import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RulePanel extends JPanel {

	
	public RulePanel() {
		setSize(900,800);
	}
	
	//creates a new RuleEgg and adds it to the eggPanel
	public void addNewEgg(Rule rule) {
		RuleEgg egg = new RuleEgg(rule);

		egg.setLayout(new GridBagLayout());
		GridBagConstraints cl;
		cl = new GridBagConstraints();
		cl.gridy = 0;
		
		JLabel eggLabel = new JLabel(rule.ruleName);
		egg.add(eggLabel);
		ConfigurationUI.addRuleEggToList(egg);
		this.add(egg);
		ConfigurationUI.ui.setVisible(true);
	}
}
