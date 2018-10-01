import java.util.*;
import javax.swing.*;
import javax.swing.Box;

import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.FileWriter;

public class ConfigurationUI {

	JFrame ui = new JFrame("Inference Engine");

	JPanel eggPanel = new JPanel();
	Box menuBox = Box.createVerticalBox();
		
	JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, eggPanel, menuBox);

	static Box overviewBox = Box.createVerticalBox();
	Box menuButtonBox = Box.createVerticalBox();	

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
	
	//creates the Menu (fills the menuPanel)
	public void createMenu() {
		
		JButton editButton = new JButton("Toggle Editmode");
		menuButtonBox.add(editButton);
		
		menuBox.add(overviewBox);
		menuBox.add(menuButtonBox);
		
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
		addRuleEggToList(egg);
		eggPanel.add(egg);
	}

	// Method to create the Configuration UI
	public void createConfigurationUI() {
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ui.setSize(1200, 800);
		eggPanel.setLayout(new BoxLayout(eggPanel, BoxLayout.X_AXIS));
		
		eggPanel.setSize(900, 800);
		//adds all rules that exist to the eggPanel
		for(int i= 0; i < ruleList.size(); i++) {
			addNewEgg(ruleList.get(i));
		}

		createMenu();
		
		ui.add(mainPane);		

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
