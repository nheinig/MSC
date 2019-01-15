import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RuleCreator {
	JFrame rc = new JFrame("RuleCreator");

	Box mainBox = Box.createVerticalBox();
	Box nameBox = Box.createHorizontalBox();
	Box inputBox = Box.createHorizontalBox();
	Box outputBox = Box.createHorizontalBox();
	Box stateMachineBox = Box.createHorizontalBox();
	Box buttonBox = Box.createHorizontalBox();
	Box rulesBox = Box.createVerticalBox();
	Box ruleLabelBox = Box.createHorizontalBox();
	Box transBox = Box.createHorizontalBox();
	Box outputLabelBox = Box.createVerticalBox();
	Box outputTextFieldBox = Box.createVerticalBox();
	Box delayBox = Box.createHorizontalBox();

	JLabel nameLabel = new JLabel("Rule Name: ");
	JLabel inputLabel = new JLabel("Inputs: ");
	JLabel outputLabel = new JLabel("Output Name:             ");
	JLabel outputLabelRed = new JLabel("Red Output:          ");
	JLabel outputLabelYellow = new JLabel("Yellow Output:          ");
	JLabel outputLabelGreen = new JLabel("Green Output:          ");
	JLabel stateMachineLabel = new JLabel("State Machine:           ");
	JLabel delayLabel = new JLabel("Alarm Delay (in ms): ");

	JTextField ruleNameTF = new JTextField();
	JTextField outputTF = new JTextField();
	JTextField redOutputTF = new JTextField();
	JTextField yellowOutputTF = new JTextField();
	JTextField greenOutputTF = new JTextField();
	JTextField delayTF = new JTextField();

	JButton createButton = new JButton("Create Rule");
	JButton cancelButton = new JButton("Cancel");
	JButton stateMachineButton = new JButton("Show StateMachine Editor");

	static JComboBox<String> inputCB1 = new JComboBox<String>();
	static JComboBox<String> inputCB2 = new JComboBox<String>();

	StateMachineEditor sme = new StateMachineEditor();

	boolean alreadyOpendSME = false;

	RuleCreator() {
		rc.setSize(400, 400);
		rc.setLayout(new FlowLayout());
		createRuleCreator();
		// ActionListener that shows StateMachineEditor on stateMachineButton click
		stateMachineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sme.show();
				if (!alreadyOpendSME) {
					StateMachinePanel.createStandardSM((String) inputCB1.getSelectedItem(),
							(String) inputCB2.getSelectedItem());
					alreadyOpendSME = true;
				}
			}
		});
	}

	void createStandardStateMachine() {

	}

	// method that fills the rc-frame
	void createRuleCreator() {

		nameBox.add(nameLabel);
		nameBox.add(ruleNameTF);

		inputBox.add(inputLabel);
		inputBox.add(inputCB1);
		inputBox.add(inputCB2);

		outputLabelBox.add(outputLabel);
		outputLabelBox.add(outputLabelRed);
		outputLabelBox.add(outputLabelYellow);
		outputLabelBox.add(outputLabelGreen);

		outputTextFieldBox.add(outputTF);
		outputTextFieldBox.add(redOutputTF);
		outputTextFieldBox.add(yellowOutputTF);
		outputTextFieldBox.add(greenOutputTF);

		delayBox.add(delayLabel);
		delayBox.add(delayTF);

		outputBox.add(outputLabelBox);
		outputBox.add(outputTextFieldBox);

		stateMachineBox.add(stateMachineLabel);
		stateMachineBox.add(stateMachineButton);

		rulesBox.add(ruleLabelBox);
		rulesBox.add(transBox);

		buttonBox.add(cancelButton);
		buttonBox.add(createButton);

		mainBox.add(nameBox);
		mainBox.add(inputBox);
		mainBox.add(outputBox);
		mainBox.add(delayBox);
		mainBox.add(stateMachineBox);
		mainBox.add(buttonBox);

		rc.add(mainBox);

		// ActionListener of the cancelButton that closes the RuleCreator and resets the
		// StateMachinePanel
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hide();
				StateMachinePanel.smPanel.removeAll();
				alreadyOpendSME = false;
			}
		});

		// ActionListener of the createButton that creates a new Rule, closes the
		// RuleCreator and resets the StateMachinePanel
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkRuleInput()) {
					try {
						crateRule();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					hide();
					StateMachinePanel.smPanel.removeAll();
					alreadyOpendSME = false;
				}
			}
		});

	}

	// method that sets rc-visibility to true
	void show() {
		rc.setVisible(true);
	}

	// method that sets rc-visibility to false and deletes all inputs made
	void hide() {
		rc.setVisible(false);
		ruleNameTF.setText("");
		outputTF.setText("");
		greenOutputTF.setText("");
		yellowOutputTF.setText("");
		redOutputTF.setText("");
		delayTF.setText("");
	}

	boolean checkRuleInput() {
		boolean delayIsInt;
		try {
			Integer.parseInt(delayTF.getText());
			delayIsInt = true;
		} catch (NumberFormatException e) {
			delayIsInt = false;
		}
		if (!ruleNameTF.getText().isEmpty() && !outputTF.getText().isEmpty() && !greenOutputTF.getText().isEmpty()
				&& !yellowOutputTF.getText().isEmpty() && !redOutputTF.getText().isEmpty()
				&& !delayTF.getText().isEmpty() && delayIsInt
				&& !inputCB1.getSelectedItem().toString().equals(inputCB2.getSelectedItem().toString())
				&& alreadyOpendSME) {

			return true;
		} else {
			System.out.println("Missing Or Wrong Rule Input!");
			return false;
		}
	}

	// method that fills the input combo boxes
	void fillInputComboBox() {
		for (int i = 0; i < ConfigurationUI.getParameterStrings().size(); i++) {
			if (((DefaultComboBoxModel) inputCB1.getModel())
					.getIndexOf(ConfigurationUI.getParameterStrings().get(i)) == -1) {
				inputCB1.addItem(ConfigurationUI.getParameterStrings().get(i));
			}
			if (((DefaultComboBoxModel) inputCB2.getModel())
					.getIndexOf(ConfigurationUI.getParameterStrings().get(i)) == -1) {
				inputCB2.addItem(ConfigurationUI.getParameterStrings().get(i));
			}
		}
	}

	// Method that creates a new Rule bases on the inputs to made in the RuleCreator
	// and the StateMachinePanel
	void crateRule() throws Exception {
		// create an empty source file
		File sourceFile = new File("src/" + ruleNameTF.getText() + ".java");
		//sourceFile.deleteOnExit();

		// generate the source code, using the source filename as the classname
		String classname = sourceFile.getName().split("\\.")[0];
		String sourceCode = "import java.sql.Timestamp;\r\n" + "import java.util.ArrayList;\r\n" + "import java.awt.Color;\r\n" + "\r\n"
				+ "public class " + classname + " extends Rule {\r\n" + "\r\n" + "	Timestamp "
				+ inputCB1.getSelectedItem().toString() + "TS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "	Timestamp " + inputCB2.getSelectedItem().toString()
				+ "TS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "	Timestamp stateTS = new Timestamp(System.currentTimeMillis());\r\n" + "\r\n" + classname
				+ "() {\r\n" + "		setRuleName(\"" + classname + "\");\r\n"
				+ "		ruleResult = new Parameter(\"" + outputTF.getText() + "\", null, null, null);\r\n"
				+ "		initializeRule();\r\n" + "		InferenceControll.addAvailableParameter(getOutputType());\r\n"
				+ "		InferenceControll.addAvailableParameterValues(listOfOutputs);\r\n"
				+ "		RuleEgg ruleEgg = new RuleEgg(this);\r\n"
				+ "		ConfigurationUI.forwardRuleEgg(ruleEgg);\r\n" + "	}\r\n" + "\r\n" +

				"	// Method to update the state based on newParameter\r\n" + "	@Override\r\n"
				+ "	void updateState(Parameter newParameter) {\r\n" + "		prevState = state;\r\n"
				+ " 	ruleResult.timestamp = newParameter.timestamp; \r\n"
				+ "		// what happens when the Parameter is of the type " + inputCB1.getSelectedItem().toString()
				+ "\r\n" + "		if (newParameter.parameterType.equals(\"" + inputCB1.getSelectedItem().toString()
				+ "\")) {\r\n" + "       " + inputCB1.getSelectedItem().toString() + "TS = newParameter.timestamp;\r\n"
				+

				"           // " + inputCB1.getSelectedItem().toString() + " is "
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 1) + "\r\n"
				+ "			if (newParameter.parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 1) + "\")) {\r\n" +

				"if (state == 0 || state == 1 || state == 2 || state == 3) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 1;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 4) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 5) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 6) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 7) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 8) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 9) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 10) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 11) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 12) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 13) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 14) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 15) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 16) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n"
				+ "						if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\")) {\r\n"
				+ "							state = 7;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 8;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 9;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\")) {\r\n"
				+ "							state = 7;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 8;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 9;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						}\r\n" + "					} else if (state == 17) {\r\n"
				+ "						state = 17;\r\n" + "					}\r\n" + "				}" +

				"			}\r\n" +

				"			// " + inputCB1.getSelectedItem().toString() + " is "
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\r\n"
				+ "			else if (newParameter.parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "if (state == 0 || state == 1 || state == 2 || state == 3) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 2;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 4) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 5) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 6) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 7) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 8) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 9) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 10) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 11) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 12) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 13) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 14) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 15) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 16) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n"
				+ "						if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\")) {\r\n"
				+ "							state = 10;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 11;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 12;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\")) {\r\n"
				+ "							state = 10;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 11;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 12;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} \r\n" + "					} else if (state == 17) {\r\n"
				+ "						state = 17;\r\n" + "					}\r\n" + "				}"
				+ "			}\r\n" +

				"           // " + inputCB1.getSelectedItem().toString() + " is "
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\r\n"
				+ "           else if (newParameter.parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "if (state == 0 || state == 1 || state == 2 || state == 3) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 3;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 4) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 5) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 6) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 15;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 7) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 8) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 9) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 5;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 10) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 11) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 12) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 15;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 13) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 14) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 15) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 15;\r\n"
				+ "					} else {\r\n" + "						state = 17;\r\n"
				+ "					}\r\n" + "				} else if (state == 16) {\r\n" + "					if ("
				+ inputCB1.getSelectedItem().toString() + "TS.getTime() - " + inputCB2.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n"
				+ "						if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\")) {\r\n"
				+ "							state = 13;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 14;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 15;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\")) {\r\n"
				+ "							state = 13;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 14;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 15;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						}\r\n" + "					} else if (state == 17) {\r\n"
				+ "						state = 17;\r\n" + "					}\r\n" + "				}"
				+ "           }\r\n" +

				"		// what happens when the Parameter is of the type " + inputCB2.getSelectedItem().toString()
				+ "\r\n" + "		else if (newParameter.parameterType.equals(\""
				+ inputCB2.getSelectedItem().toString() + "\")) {\r\n" + "			"
				+ inputCB2.getSelectedItem().toString() + "TS = newParameter.timestamp;\r\n" + "			// "
				+ inputCB2.getSelectedItem().toString() + " is "
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\r\n"
				+ "			if (newParameter.parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 1) + "\")) {\r\n"
				+ "if (state == 0 || state == 4 || state == 5 || state == 6) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 4;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 1) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 2) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 3) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 7) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 8) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 9) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 7;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 10) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 11) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 12) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 10;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 13) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 14) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 15) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 13;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 17) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n"
				+ "						if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\"n"
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 7;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 10;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 13;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\"n"
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 7;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 10;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 13;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						}\r\n" + "					} else if (state == 16) {\r\n"
				+ "						state = 16;\r\n" + "					}\r\n" + "				} "
				+ "			}\r\n" +

				"			// " + inputCB2.getSelectedItem().toString() + " is "
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\r\n"
				+ "			else if (newParameter.parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "if (state == 0 || state == 4 || state == 5 || state == 6) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 5;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 1) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 2) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 3) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 7) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 8) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 9) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 8;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 10) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 11) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 12) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 11;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 13) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 14) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 15) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 14;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 17) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n"
				+ "						if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\"n"
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 8;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 11;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 14;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\"n"
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 8;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 11;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 14;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						}\r\n" + "					} else if (state == 16) {\r\n"
				+ "						state = 16;\r\n" + "					}\r\n" + "				}"
				+ "			}\r\n" +

				"			// " + inputCB2.getSelectedItem().toString() + " is "
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\r\n"
				+ "			else if (newParameter.parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB2.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "if (state == 0 || state == 4 || state == 5 || state == 6) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 6;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 1) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 2) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 3) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 15;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 7) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 8) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 9) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 9;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 10) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 11) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 12) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 12;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 13) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 15;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 14) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 15;\r\n"
				+ "						stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 15) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n" + "						state = 15;\r\n"
				+ "					} else {\r\n" + "						state = 16;\r\n"
				+ "					}\r\n" + "				} else if (state == 17) {\r\n" + "					if ("
				+ inputCB2.getSelectedItem().toString() + "TS.getTime() - " + inputCB1.getSelectedItem().toString()
				+ "TS.getTime() < " + delayTF.getText() + ") {\r\n"
				+ "						if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\"n"
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 9;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 12;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(0).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(0).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 15;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\"n"
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 9;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 2) + "\")) {\r\n"
				+ "							state = 12;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						} else if (listOfLastInputs.get(1).parameterType.equals(\""
				+ inputCB1.getSelectedItem().toString() + "\")\r\n"
				+ "								&& listOfLastInputs.get(1).parameterValue.equals(\""
				+ InferenceControll.getParameterValue(inputCB1.getSelectedItem().toString(), 3) + "\")) {\r\n"
				+ "							state = 15;\r\n"
				+ "							stateTS = new Timestamp(System.currentTimeMillis());\r\n"
				+ "						}\r\n" + "					} else if (state == 16) {\r\n"
				+ "						state = 16;\r\n" + "					}\r\n" + "				}\r\n"
				+ "			}" + "			}\r\n" +

				"		}\r\n" + "		evaluateStateMachine();\r\n" + "	}\r\n" + "\r\n" + "	\r\n" + "	\r\n" +

				"	@Override\r\n" + "	void fillStateOutputList() {\r\n"
				+ "ArrayList<String> tempStateList0 = new ArrayList<String>();" + "\r\n" + "tempStateList0.add(\""
				+ StateMachinePanel.listOfStates.get(0).stateLabel.getText() + "\");" + "\r\n" + "tempStateList0.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");" + "\r\n"
				+ "ArrayList<String> tempStateList1 = new ArrayList<String>();" + "\r\n" + "tempStateList1.add(\""
				+ StateMachinePanel.listOfStates.get(1).stateLabel.getText() + "\");\r\n" + "\r\n"
				+ "tempStateList1.add(\"" + StateMachinePanel.listOfStates.get(1).getBackground() + "\");"
				+ "ArrayList<String> tempStateList2 = new ArrayList<String>();" + "\r\n" + "tempStateList2.add(\""
				+ StateMachinePanel.listOfStates.get(2).stateLabel.getText() + "\");" + "\r\n" + "tempStateList2.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList3 = new ArrayList<String>();" + "\r\n" + "tempStateList3.add(\""
				+ StateMachinePanel.listOfStates.get(3).stateLabel.getText() + "\");" + "\r\n" + "tempStateList3.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");" + "\r\n"
				+ "ArrayList<String> tempStateList4 = new ArrayList<String>();" + "\r\n" + "tempStateList4.add(\""
				+ StateMachinePanel.listOfStates.get(4).stateLabel.getText() + "\");" + "\r\n" + "tempStateList4.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList5 = new ArrayList<String>();" + "\r\n" + "tempStateList5.add(\""
				+ StateMachinePanel.listOfStates.get(5).stateLabel.getText() + "\");" + "\r\n" + "tempStateList5.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList6 = new ArrayList<String>();" + "\r\n" + "tempStateList6.add(\""
				+ StateMachinePanel.listOfStates.get(6).stateLabel.getText() + "\");" + "\r\n" + "tempStateList6.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList7 = new ArrayList<String>();" + "\r\n" + "tempStateList7.add(\""
				+ StateMachinePanel.listOfStates.get(7).stateLabel.getText() + "\");" + "\r\n" + "tempStateList7.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList8 = new ArrayList<String>();" + "\r\n" + "tempStateList8.add(\""
				+ StateMachinePanel.listOfStates.get(8).stateLabel.getText() + "\");" + "\r\n" + "tempStateList8.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList9 = new ArrayList<String>();" + "\r\n" + "tempStateList9.add(\""
				+ StateMachinePanel.listOfStates.get(9).stateLabel.getText() + "\");" + "\r\n" + "tempStateList9.add(\""
				+ StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList10 = new ArrayList<String>();" + "\r\n" + "tempStateList10.add(\""
				+ StateMachinePanel.listOfStates.get(10).stateLabel.getText() + "\");" + "\r\n"
				+ "tempStateList10.add(\"" + StateMachinePanel.listOfStates.get(0).getBackground() + "\");	" + "\r\n"
				+ "ArrayList<String> tempStateList11 = new ArrayList<String>();" + "\r\n" + "tempStateList11.add(\""
				+ StateMachinePanel.listOfStates.get(11).stateLabel.getText() + "\");" + "\r\n"
				+ "tempStateList11.add(\"" + StateMachinePanel.listOfStates.get(0).getBackground() + "\");" + "\r\n"
				+ "\r\n" + "		\r\n" + "		stateOutputList.add(tempStateList0);\r\n"
				+ "		stateOutputList.add(tempStateList1);\r\n" + "		stateOutputList.add(tempStateList2);\r\n"
				+ "		stateOutputList.add(tempStateList3);\r\n" + "		stateOutputList.add(tempStateList4);\r\n"
				+ "		stateOutputList.add(tempStateList5);\r\n" + "		stateOutputList.add(tempStateList6);\r\n"
				+ "		stateOutputList.add(tempStateList7);\r\n" + "		stateOutputList.add(tempStateList8);\r\n"
				+ "		stateOutputList.add(tempStateList9);\r\n" + "		stateOutputList.add(tempStateList10);\r\n"
				+ "		stateOutputList.add(tempStateList11);	}\r\n" + "	\r\n" +
				
				
				"	@Override\r\n" + "	void showStateMachine() {\r\n" + "		\r\n" + "	}\r\n" + "	\r\n" +

				
				
				"	// method to evaluate the state machine\r\n" + "	@Override\r\n"
				+ "	void evaluateStateMachine() {\r\n" 
				+ "		Timestamp tempTS = new Timestamp(System.currentTimeMillis()); \r\n"
				+ "		if (state > 6 && tempTS.getTime() - stateTS.getTime() >= " + delayTF.getText() + "){ \r\n" 
				+ "			if(Color.decode(stateOutputList.get(state).get(1)) == Color.YELLOW){ \r\n"
				+ " 			ruleResult.parameterValue = \"" + yellowOutputTF.getText() + "\"; \r\n"
				+ "			} else if (Color.decode(stateOutputList.get(state).get(1)) == Color.RED){ \r\n"
				+ "				ruleResult.parameterValue = \"" + redOutputTF.getText() + "\"; \r\n" 
				+ "			} \r\n"
				+ "		} else { \r\n ruleResult.parameterValue = \"" + greenOutputTF.getText() + "\";} \r\n"
				+ 
				"				\r\n" 

				+ "		System.out.println(\"" + outputTF.getText() + ": \" + ruleResult.parameterValue);\r\n"
				+ "		InferenceControll.handleNewAlarm(ruleResult);\r\n" + "	}\r\n" + "\r\n" +

				
				
				
				
				"	@Override\r\n" + "	void initializeRule() {\r\n" + "		this.listOfParametersNeeded.add(\""
				+ inputCB1.getSelectedItem().toString() + "\");\r\n" + "		this.listOfParametersNeeded.add(\""
				+ inputCB2.getSelectedItem().toString() + "\");\r\n"
				+ "		listOfOutputs.add(ruleResult.parameterType);\r\n" + "		listOfOutputs.add(\""
				+ greenOutputTF.getText().toString() + "\");\r\n" + "		listOfOutputs.add(\""
				+ yellowOutputTF.getText().toString() + "\");\r\n" + "		listOfOutputs.add(\""
				+ redOutputTF.getText().toString() + "\");\r\n" + "	}\r\n" + "\r\n" +

				"	@Override\r\n" + "	String getOutputType() {\r\n" + "		return ruleResult.parameterType;\r\n"
				+ "	}\r\n" + "\r\n" +

				"	@Override\r\n" + "	void updateEggLabels() {\r\n" + "		if (listOfLastInputs.size() > 1) {\r\n"
				+ "			RulePanel.forwardEggLabelUpdate(ruleName, state, prevState, listOfLastInputs.get(0),\r\n"
				+ "					listOfLastInputs.get(1), ruleResult);\r\n"
				+ "		} else if (listOfLastInputs.size() == 1) {\r\n"
				+ "			RulePanel.forwardEggLabelUpdate(ruleName, state, prevState, listOfLastInputs.get(0), null, ruleResult);\r\n"
				+ "		} else if (listOfLastInputs.size() == 0) {\r\n"
				+ "			RulePanel.forwardEggLabelUpdate(ruleName, state, prevState, null, null, ruleResult);\r\n"
				+ "		}\r\n" + "	}\r\n" + "}\r\n" + "";

		// write the source code into the source file
		FileWriter writer = new FileWriter(sourceFile);
		writer.write(sourceCode);
		writer.close();

	}

}
