import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.FileWriter;

public class ConfigurationUI {

	JFrame ui = new JFrame("Inference Engine");

	JPanel mainPanel = new JPanel();

	static private ArrayList<String> parameterStrings = new ArrayList<String>();

	ArrayList<RuleEgg> listOfRuleEggs = new ArrayList<RuleEgg>();
	
	ArrayList<Rule> ruleList = new ArrayList<Rule>();

	// Constructor
	ConfigurationUI(ArrayList<String> listOfAvailableParameters) {
		fetchRuleList();
		updateParameterStrings(listOfAvailableParameters);
	}

	// Method updates parameterStrings
	void updateParameterStrings(ArrayList<String> al) {
		for (int i = 0; i < al.size(); i++) {
			if (!parameterStrings.contains(al.get(i))) {
				parameterStrings.add(al.get(i));
			}
		}
	}

	//fetches the ruleList from the InferenceControll and saves them as ruleList
	void fetchRuleList() {
		ruleList = InferenceControll.getRuleList();
	}
	
	
	//adds a new RuleEgg to the ConfigurationUI
	public void addNewEgg(Rule rule) {

		JPanel contentPane = new JPanel(new BorderLayout());
		RuleEgg egg = new RuleEgg(rule);
		
		egg.setLayout(new GridBagLayout());
		GridBagConstraints cl;
		cl = new GridBagConstraints();
		cl.gridy = 0;
		
		JLabel label = new JLabel(rule.ruleName);
		egg.add(label);

		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		
		
		addRuleEggToList(egg);
		contentPane.add(egg);
		mainPanel.add(contentPane);
	}

	// Method to create the Configuration UI
	public void createConfigurationUI() {
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		for(int i= 0; i < ruleList.size(); i++) {
			addNewEgg(ruleList.get(i));
		}


		ui.add(mainPanel);

		ui.setSize(600, 400);
		// ui.pack();
		ui.setVisible(true);
	}
	
	
	//Adds a RuleEggs to a List
	void addRuleEggToList(RuleEgg re) {
		if (!listOfRuleEggs.contains(re)) {
			listOfRuleEggs.add(re);
		}
	}

}
