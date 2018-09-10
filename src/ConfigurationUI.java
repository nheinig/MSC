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

	// Constructor
	ConfigurationUI(ArrayList<String> listOfAvailableParameters) {
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

	public void addNewEgg() {

		JPanel contentPane1 = new JPanel(new BorderLayout());
		RuleEgg egg1 = new RuleEgg();
		addRuleEggToList(egg1);
		contentPane1.add(egg1);
		mainPanel.add(contentPane1);
	}

	// Method to create the Configuration UI
	public void createConfigurationUI() {
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		addNewEgg();
		addNewEgg();
		addNewEgg();
		addNewEgg();

		ui.add(mainPanel);

		ui.setSize(400, 400);
		// ui.pack();
		ui.setVisible(true);
	}

	void addRuleEggToList(RuleEgg re) {
		if (!listOfRuleEggs.contains(re)) {
			listOfRuleEggs.add(re);
		}
	}

}
