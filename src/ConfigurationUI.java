import java.util.*;
import javax.swing.*;
import java.awt.*;

public class ConfigurationUI {

	static JFrame ui = new JFrame("Configuration UI");

	static private ArrayList<String> parameterStrings = new ArrayList<String>();

	static ArrayList<Rule> ruleList = new ArrayList<Rule>();

	RulePanel rulePanel = new RulePanel();

	ConfigurationMenu configMenu = new ConfigurationMenu();

	JPanel mainPanel = new JPanel();

	static boolean editMode = false;

	// Constructor
	ConfigurationUI(ArrayList<String> listOfAvailableParameters) {
		fetchRuleList();
		updateParameterStrings(listOfAvailableParameters);
		ui.setSize(1200, 600);
		ui.setLocationRelativeTo(ui);
	}

	// Method updates parameterStrings
	void updateParameterStrings(ArrayList<String> al) {
		for (int i = 0; i < al.size(); i++) {
			if (!parameterStrings.contains(al.get(i))) {
				parameterStrings.add(al.get(i));
			}
		}
	}

	// fetches the ruleList from the InferenceControll and saves them as ruleList
	void fetchRuleList() {
		ruleList = InferenceControl.getRuleList();
	}

	// forwards a RuleEgg to the RulePanel where it is added
	static void forwardRuleEgg(RuleEgg re) {
		RulePanel.addNewEgg(re);
	}

	static void forwardDiscreter(DiscreterEgg fe) {
		RulePanel.createDiscreterEgg(fe);
	}

	// Method to create the Configuration UI
	public void createConfigurationUI() {
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		rulePanel.eggPanel.setBackground(Color.WHITE);
		mainPanel.add(rulePanel.eggPanel);
		mainPanel.add(configMenu);
		ui.add(mainPanel);

		ui.setVisible(true);
	}

	// --------------
	// getter/ setter
	// --------------

	static ArrayList<String> getParameterStrings() {
		return parameterStrings;
	}

}
