import java.util.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

public class ConfigurationUI {

	static private ArrayList<String> parameterStrings = new ArrayList<String>();
	static JComboBox<String> parameterCB1 = new JComboBox<String>();
	static JComboBox<String> parameterCB2 = new JComboBox<String>();

	// create labels for inputs
	static JLabel ruleNameLabel = new JLabel("Rule name: ");
	static JLabel parameterLabel1 = new JLabel("Parameter 1: ");
	static JLabel parameterLabel2 = new JLabel("Parameter 2: ");

	// create a text field for the rule name
	JTextField ruleNameTF = new JTextField();

	// create a button to finish the creation of a new rule
	JButton finishButton = new JButton("Create Rule");

	// create Boxes for input values
	Box box = Box.createVerticalBox();
	Box nameBox = Box.createHorizontalBox();
	Box paramBox1 = Box.createHorizontalBox();
	Box paramBox2 = Box.createHorizontalBox();

	// Constructor
	ConfigurationUI(ArrayList<String> listOfAvailableParameters) {
		updateParameterStrings(listOfAvailableParameters);
	}

	// Method that sets the Values of the ComboBox
	void setParameterComboBox() {
		for (int i = 0; i < parameterStrings.size(); i++) {
			parameterCB1.addItem(parameterStrings.get(i));
			parameterCB2.addItem(parameterStrings.get(i));
		}
	}

	// Method updates parameterStrings
	void updateParameterStrings(ArrayList<String> al) {
		for (int i = 0; i < al.size(); i++) {
			if (!parameterStrings.contains(al.get(i))) {
				parameterStrings.add(al.get(i));
			}
		}
	}

	void createNewRule() throws Exception {
		// create an empty source file
		File sourceFile = new File("src/" + ruleNameTF.getText() + ".java");
		sourceFile.deleteOnExit();

		// generate the source code, using the source filename as the classname
		String classname = sourceFile.getName().split("\\.")[0];
		String sourceCode = "import java.sql.Timestamp;\r\n" + "\r\n" + "public class " + classname
				+ " extends Rule {\r\n" + "	\r\n" + "	Parameter alarm = new Parameter(\" "
				+ parameterCB1.getSelectedItem().toString() + parameterCB2.getSelectedItem().toString()
				+ "alarm \", null, \"none\");\r\n" + "	\r\n" + "	Timestamp "
				+ parameterCB1.getSelectedItem().toString() + "TS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "	Timestamp " + parameterCB2.getSelectedItem().toString()
				+ "TS = new Timestamp(System.currentTimeMillis());\r\n" + "	\r\n" + "	" + classname + "() {\r\n"
				+ "		super.ruleName = \"" + classname + "\";\r\n" + "	}\r\n" + "	\r\n"
				+ "	//Method to update the state based on newParameter\r\n" + "	@Override\r\n"
				+ "	void updateState(Parameter newParameter) {\r\n"
				+ "		// what happens when the Parameter is of the type " + parameterCB1.getSelectedItem().toString()
				+ "\r\n" + "		if(newParameter.parameterType.equals(\"" + parameterCB1.getSelectedItem().toString()
				+ "\")) {\r\n" + "			" + parameterCB1.getSelectedItem().toString()
				+ "TS = newParameter.timestamp;\r\n"
				+ "			if(newParameter.parameterValue.equals(\"none\")) {\r\n"
				+ "				if(state == 0 || state == 3) {\r\n" + "					state = 1;\r\n"
				+ "				} else if(state == 2|| state == 6) {\r\n" + "					state = 5;\r\n"
				+ "				} else if(state == 4|| state == 7) {\r\n" + "					state = 8;\r\n"
				+ "				} else if((state == 5 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000)) || state == 10) {\r\n"
				+ "					state = 9;\r\n" + "				}\r\n" + "				if("
				+ parameterCB1.getSelectedItem().toString() + "TS.getTime() > "
				+ parameterCB2.getSelectedItem().toString()
				+ "TS.getTime() + 20000 && !(state == 9 || state == 10)) {\r\n" + "					state = 9;\r\n"
				+ "					alarm.timestamp = newParameter.timestamp;\r\n" + "				}\r\n"
				+ "			} \r\n" + "			else if(newParameter.parameterValue.equals(\"many\")) {\r\n"
				+ "				if(state == 0 || state == 1){\r\n" + "					state = 3;\r\n"
				+ "				} else if(state == 2 || state == 5){\r\n" + "					state = 8;\r\n"
				+ "				} else if(state == 4 || state == 8){\r\n" + "					state = 7;\r\n"
				+ "				} else if((state == 6 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000)) || state == 9) {\r\n"
				+ "					state = 10;\r\n" + "				} \r\n" + "				if("
				+ parameterCB1.getSelectedItem().toString() + "TS.getTime() > "
				+ parameterCB2.getSelectedItem().toString()
				+ "TS.getTime() + 20000 && !(state == 9 || state == 10)) {\r\n" + "					state = 10;\r\n"
				+ "					alarm.timestamp = newParameter.timestamp;\r\n" + "				}\r\n"
				+ "			}\r\n" + "		} \r\n" + "		//what happens when the Parameter is of the type "
				+ parameterCB2.getSelectedItem().toString() + "\r\n"
				+ "		else if(newParameter.parameterType.equals(\"" + parameterCB2.getSelectedItem().toString()
				+ "\")) {\r\n" + "			 " + parameterCB2.getSelectedItem().toString()
				+ "TS = newParameter.timestamp;			\r\n"
				+ "			if(newParameter.parameterValue.equals(\"disconnected\")) {\r\n"
				+ "				if(state == 0|| state == 4) {\r\n" + "					state = 2;\r\n"
				+ "				} else if(state == 1|| state == 8){\r\n" + "					state = 5;\r\n"
				+ "					alarm.timestamp = newParameter.timestamp;\r\n"
				+ "				} else if(state == 3|| state == 7){\r\n" + "					state = 6;\r\n"
				+ "					alarm.timestamp = newParameter.timestamp;\r\n"
				+ "				} else if((state == 5 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000))) {\r\n"
				+ "					state = 9;\r\n"
				+ "				} else if((state == 6 && (newParameter.timestamp.getTime() - alarm.timestamp.getTime() >= 3000))) {\r\n"
				+ "					state = 10;\r\n" + "				}\r\n" + "				if("
				+ parameterCB2.getSelectedItem().toString() + "TS.getTime() > "
				+ parameterCB1.getSelectedItem().toString()
				+ "TS.getTime() + 20000 && !(state == 9 || state == 10)) {\r\n" + "					state = 10;\r\n"
				+ "				}\r\n" + "			} \r\n"
				+ "			else if(newParameter.parameterValue.equals(\"connected\")) {\r\n"
				+ "				if(state == 0 || state == 2) {\r\n" + "					state = 4;\r\n"
				+ "				} else if(state == 1 || state == 5 || state == 9){\r\n"
				+ "					state = 8;\r\n"
				+ "				} else if(state == 3 || state == 6 || state == 10){\r\n"
				+ "					state = 7;\r\n" + "				}\r\n" + "				if("
				+ parameterCB2.getSelectedItem().toString() + "TS.getTime() > "
				+ parameterCB1.getSelectedItem().toString()
				+ "TS.getTime() + 20000 && !(state == 9 || state == 10)) {\r\n" + "					state = 10;\r\n"
				+ "					alarm.timestamp = newParameter.timestamp;\r\n" + "				}\r\n"
				+ "			}\r\n"
				+ "			if (state == 10 && (newParameter.timestamp.getTime() > alarm.timestamp.getTime() + 20000)) {\r\n"
				+ "				state = 9;\r\n" + "			}		\r\n" + "		}\r\n"
				+ "		System.out.println(\"" + classname + "-State: \" + state);\r\n"
				+ "		evaluateStateMachine();\r\n" + "	}\r\n" + "	\r\n"
				+ "	//method to evaluate the state machine\r\n" + "	@Override\r\n"
				+ "	void evaluateStateMachine() {\r\n" + "		if(state == 9) {\r\n"
				+ "			alarm.parameterValue = \"hnr\";\r\n" + "		} else if(state == 10) {\r\n"
				+ "			alarm.parameterValue = \"local\";\r\n" + "		} else {\r\n"
				+ "			alarm.parameterValue = \"none\";\r\n" + "		}\r\n"
				+ "		System.out.println(\"PVT-Alarm: \" + alarm.parameterValue);\r\n"
				+ "		InferenceControll.handleNewAlarm(alarm);\r\n" + "	}		\r\n" + "	\r\n"
				+ "	@Override\r\n" + "	void initializeRule() {\r\n"
				+ "		this.listOfParametersNeeded.add(ruleName);\r\n" + "		this.listOfParametersNeeded.add(\""
				+ parameterCB1.getSelectedItem().toString() + "\");\r\n" + "		this.listOfParametersNeeded.add(\""
				+ parameterCB2.getSelectedItem().toString() + "\");\r\n"
				+ "		this.registerRuleAtInferenceControll();\r\n"
				+ "		InferenceControll.addAvailableParameter(getOutputType());\r\n" + "	}\r\n" + "	\r\n"
				+ "	@Override\r\n" + "	String getOutputType() {\r\n" + "		return alarm.parameterType;\r\n"
				+ "	}\r\n" + "}\r\n" + "";

		// write the source code into the source file
		FileWriter writer = new FileWriter(sourceFile);
		writer.write(sourceCode);
		writer.close();
	}

	// Method to create the Configuration UI
	public void createConfigurationUI() {
		JFrame ui = new JFrame("Inference Engine");
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setParameterComboBox();

		// adding components to their boxes
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
		finishButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!ruleNameTF.getText().equals("")) {
					if (!parameterCB1.getSelectedItem().toString().equals(parameterCB2.getSelectedItem().toString())) {

						try {
							createNewRule();
							System.out.println("Rule was " + ruleNameTF.getText() + " created!");
						} catch (Exception e1) {
							System.out.println("Rule could not be created!");
							e1.printStackTrace();
						}
					} else {
						System.out.println("RULE PARAMETERS MUST BE DIFFERENT!");
					}
				} else {
					System.out.println("EMPTY RULE NAME NOT ALLOWED!");
				}
			}
		});

		ui.add(box);

		ui.setSize(400, 400);
		ui.pack();
		ui.setVisible(true);
	}

}
