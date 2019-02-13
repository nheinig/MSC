import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DiscreterEditor {
	JFrame fe = new JFrame("DiscreterEditor");

	Box mainBox = Box.createVerticalBox();
	Box personsBox = Box.createHorizontalBox();
	Box spo2Box = Box.createHorizontalBox();
	Box tubeBox = Box.createHorizontalBox();

	JLabel personsLBLabel = new JLabel("PersonsUB: ");
	JLabel personsUBLabel = new JLabel("PersonsLB: ");
	JLabel spo2LBLabel = new JLabel("SpO2LB: ");
	JLabel spo2UBLabel = new JLabel("SpO2UB: ");
	JLabel tubeLBLabel = new JLabel("TubeLB: ");
	JLabel tubeUBLabel = new JLabel("TubeUB: ");

	JTextField personsLBTF = new JTextField(String.valueOf(Discreter.getPersonsLB()));
	JTextField personsUBTF = new JTextField(String.valueOf(Discreter.getPersonsUB()));
	JTextField spo2LBTF = new JTextField(String.valueOf(Discreter.getSpO2LB()));
	JTextField spo2UBTF = new JTextField(String.valueOf(Discreter.getSpO2UB()));
	JTextField tubeLBTF = new JTextField(String.valueOf(Discreter.getTubeLB()));
	JTextField tubeUBTF = new JTextField(String.valueOf(Discreter.getTubeUB()));

	JButton applyButton = new JButton("Apply");

	public DiscreterEditor() {

		fe.setSize(400, 400);
		fe.setLayout(new FlowLayout());

		createFE();

	}

	void createFE() {
		personsBox.add(personsLBLabel);
		personsBox.add(personsLBTF);
		personsBox.add(personsUBLabel);
		personsBox.add(personsUBTF);

		spo2Box.add(spo2LBLabel);
		spo2Box.add(spo2LBTF);
		spo2Box.add(spo2UBLabel);
		spo2Box.add(spo2UBTF);

		tubeBox.add(tubeLBLabel);
		tubeBox.add(tubeLBTF);
		tubeBox.add(tubeUBLabel);
		tubeBox.add(tubeUBTF);

		mainBox.add(personsBox);
		mainBox.add(spo2Box);
		mainBox.add(tubeBox);
		mainBox.add(applyButton);

		fe.add(mainBox);

		// ActionListener of the createButton that creates a new Rule, closes the
		// RuleCreator and resets the StateMachinePanel
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkInput()) {
					updateBounds();
					hide();
				}
			}
		});

	}

	// method that checks if all inputs were made and are correct
	boolean checkInput() {
		boolean isDouble;
		try {
			Double.parseDouble(personsLBTF.getText());
			Double.parseDouble(personsUBTF.getText());
			Double.parseDouble(spo2LBTF.getText());
			Double.parseDouble(spo2UBTF.getText());
			Double.parseDouble(tubeLBTF.getText());
			Double.parseDouble(tubeUBTF.getText());
			isDouble = true;
		} catch (NumberFormatException e) {
			isDouble = false;
		}
		if (isDouble) {
			return true;
		} else {
			System.out.println("Wrong Input!");
			return false;
		}
	}

	// method that updates the bounds of the Discreter
	void updateBounds() {
		Discreter.setPersonsLB(Double.parseDouble(personsLBTF.getText()));
		Discreter.setPersonsUB(Double.parseDouble(personsUBTF.getText()));
		Discreter.setSpO2LB(Double.parseDouble(spo2LBTF.getText()));
		Discreter.setSpO2UB(Double.parseDouble(spo2UBTF.getText()));
		Discreter.setTubeLB(Double.parseDouble(tubeLBTF.getText()));
		Discreter.setTubeUB(Double.parseDouble(tubeUBTF.getText()));
	}

	void show() {
		fe.setVisible(true);
	}

	void hide() {
		fe.setVisible(false);
	}

}
