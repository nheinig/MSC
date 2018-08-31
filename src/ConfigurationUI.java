import java.util.ArrayList;
import javax.swing.*;

public class ConfigurationUI {

	static private ArrayList<String> parameterStrings = new ArrayList<String>();
	static JComboBox<String> parameterCB1 = new JComboBox<String>();
	static JComboBox<String> parameterCB2 = new JComboBox<String>();
	
	//create labels for inputs
	static JLabel ruleNameLabel = new JLabel("Rule name: ");
	static JLabel parameterLabel1 = new JLabel("Parameter 1: ");		
	static JLabel parameterLabel2 = new JLabel("Parameter 2: ");
	
	//create a text field for the rule name
	JTextField ruleNameTF = new JTextField("Enter rule name here.");
	
	//create a button to finish the creation of a new rule
	JButton finishButton = new JButton("Create Rule");
	
	//create Boxes for input values
	Box box = Box.createVerticalBox();
	Box nameBox = Box.createHorizontalBox();
	Box paramBox1 = Box.createHorizontalBox();
	Box paramBox2 = Box.createHorizontalBox();		
	
	

	//Constructor
	ConfigurationUI(ArrayList<String> listOfAvailableParameters){
		updateParameterStrings(listOfAvailableParameters);
	}
	
	//Method that sets the Values of the ComboBox
	void setParameterComboBox() {
		for(int i = 0; i < parameterStrings.size(); i++) {
			parameterCB1.addItem(parameterStrings.get(i));
			parameterCB2.addItem(parameterStrings.get(i));
		}
	}
	
	//Method updates parameterStrings
	void updateParameterStrings(ArrayList<String> al) {
		for(int i = 0; i < al.size(); i++) {
			if(!parameterStrings.contains(al.get(i))) {
				parameterStrings.add(al.get(i));
			}
		}
	}
	
	//Method to create the Configuration UI
	public void createConfigurationUI() {		
		JFrame ui = new JFrame("Inference Engine");
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setParameterComboBox();
		
		//adding components to their boxes
		nameBox.add(ruleNameLabel);
		nameBox.add(ruleNameTF);
		
		paramBox1.add(parameterLabel1);
		paramBox1.add(parameterCB1);
		
		paramBox2.add(parameterLabel2);
		paramBox2.add(parameterCB2);
		
		box.add(nameBox);
		box.add(paramBox1);
		box.add(paramBox2);
		
		box.add(finishButton);

		ui.add(box);
			
		ui.setSize(400, 400);
		ui.pack();
		ui.setVisible(true);
	}	
	
}
